package com.golike.customviews;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.golike.customviews.AutoRefreshListView.Mode;
import com.golike.customviews.adapter.MessageListAdapter;
import com.golike.customviews.model.Conversation.ConversationType;
import com.golike.customviews.model.Event.ReadReceiptEvent;
import com.golike.customviews.model.Event.ReadReceiptRequestEvent;
import com.golike.customviews.model.Event.ErrorCode;
import com.golike.customviews.model.Message.MessageDirection;
import com.golike.customviews.model.Message.SentStatus;
import com.golike.customviews.model.Message;
import com.golike.customviews.model.ReadReceiptMessage;
import com.golike.customviews.model.UIMessage;
import com.golike.customviews.AutoRefreshListView.State;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by admin on 2017/8/8.
 */

public class ChatView extends FrameLayout implements AbsListView.OnScrollListener {

    private Context mContext;
    private View mChatUIView;
    private AutoRefreshListView mChatList;
    private View mMsgListView;
    private MessageListAdapter mListAdapter;
    private EditExtension mEditExtension;
    public String mTargetId;
    public ConversationType mConversationType;
    public boolean mHasMoreLocalMessages = true;

    public ChatView(Context context) {
        super(context);
        this.mContext = context;
        initViews();
        initEvent();
    }


    public ChatView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initViews();
        initEvent();
    }

    public ChatView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initViews();
        initEvent();
    }

    private void initEvent() {
        this.getHistoryMessage(this.mConversationType, this.mTargetId, 30, 3,null);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    public void initViews() {
        this.mChatUIView = LayoutInflater.from(this.getContext()).inflate(R.layout.ee_fr_conversation, (ViewGroup) null);
        this.mEditExtension = this.findViewById(mChatUIView, R.id.ee_extension);
        this.mMsgListView = this.findViewById(mChatUIView, R.id.ee_layout_msg_list);
        this.mChatList = this.findViewById(mMsgListView, R.id.ee_list);
        this.mChatList.requestDisallowInterceptTouchEvent(true);
        this.mChatList.setMode(Mode.START);
        this.mChatList.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        this.mListAdapter = this.onResolveAdapter(mContext);
        this.mChatList.setAdapter(this.mListAdapter);
        mChatList.addOnScrollListener(this);
        mChatUIView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        this.addView(mChatUIView);
    }


    public void getHistoryMessage(ConversationType conversationType, String targetId, final int reqCount, final int scrollMode,List<Message> messages) {
        this.mChatList.onRefreshStart(Mode.START);
        if (messages != null && messages.size() > 0) {
            Iterator index = messages.iterator();
            while (index.hasNext()) {
                Message message = (Message) index.next();
                boolean contains = false;
                for (int uiMessage = 0; uiMessage < this.mListAdapter.getCount(); ++uiMessage) {
                    contains = (this.mListAdapter.getItem(uiMessage)).getMessageId() == message.getMessageId();
                    if (contains) {
                        break;
                    }
                }
                if (!contains) {
                    UIMessage var7 = UIMessage.obtain(message);
                    this.mListAdapter.add(var7, 0);
                }
            }
            this.mListAdapter.notifyDataSetChanged();
            this.mChatList.onRefreshComplete(10, 10, false);
        }
    }


    public EditExtension getEditExtension() {
        return mEditExtension;
    }

    public AutoRefreshListView getChatList() {
        return mChatList;
    }

    //订阅方法，当接收到事件的时候，会调用该方法
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Message msg) {
        UIMessage uiMsg = UIMessage.obtain(msg);
        uiMsg.setSentStatus(Message.SentStatus.SENT);
        this.mListAdapter.add(uiMsg);
        this.mListAdapter.notifyDataSetChanged();
        this.mChatList.smoothScrollToPosition(this.mChatList.getCount());
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public  void onEventMainThread(ReadReceiptEvent event) {
        Log.i("ConversationFragment", "ReadReceiptEvent");
        if( this.mTargetId.equals(event.getMessage().getTargetId()) && this.mConversationType.equals(event.getMessage().getConversationType()) && event.getMessage().getMessageDirection().equals(MessageDirection.RECEIVE)) {
            ReadReceiptMessage content = (ReadReceiptMessage)event.getMessage().getContent();
            long ntfTime = content.getLastMessageSendTime();

            for(int i = this.mListAdapter.getCount() - 1; i >= 0; --i) {
                UIMessage uiMessage = this.mListAdapter.getItem(i);
                if(uiMessage.getMessageDirection().equals(MessageDirection.SEND) && uiMessage.getSentStatus() == SentStatus.SENT && ntfTime >= uiMessage.getSentTime()) {
                    uiMessage.setSentStatus(SentStatus.READ);
                    int first = this.mChatList.getFirstVisiblePosition();
                    int last = this.mChatList.getLastVisiblePosition();
                    int position = this.getPositionInListView(i);
                    if(position >= first && position <= last) {
                        this.mListAdapter.getView(i, this.getListViewChildAt(i), this.mChatList);
                    }
                }
            }
        }

    }

    private View getListViewChildAt(int adapterIndex) {
        int header = this.mChatList.getHeaderViewsCount();
        int first = this.mChatList.getFirstVisiblePosition();
        return this.mChatList.getChildAt(adapterIndex + header - first);
    }


    private int getPositionInListView(int adapterIndex) {
        int header = this.mChatList.getHeaderViewsCount();
        return adapterIndex + header;
    }

    protected <T extends View> T findViewById(View view, int id) {
        return (T) view.findViewById(id);
    }

    public MessageListAdapter onResolveAdapter(Context context) {
        return new MessageListAdapter(context);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_TOUCH_SCROLL) {
            if (this.mEditExtension != null) {
                this.mEditExtension.collapseExtension();
            }
        } else if (scrollState == SCROLL_STATE_IDLE) {
            int last = this.mChatList.getLastVisiblePosition();
            if (this.mChatList.getCount() - last > 2) {
                this.mChatList.setTranscriptMode(1);
            } else {
                this.mChatList.setTranscriptMode(2);
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }


    private final Runnable measureAndLayout = new Runnable() {
        @Override
        public void run() {
            measure(
                    MeasureSpec.makeMeasureSpec(getWidth(), MeasureSpec.EXACTLY),
                    MeasureSpec.makeMeasureSpec(getHeight(), MeasureSpec.EXACTLY));
            layout(getLeft(), getTop(), getRight(), getBottom());
        }
    };

    @Override
    public void requestLayout() {
        super.requestLayout();

        // The spinner relies on a measure + layout pass happening after it calls requestLayout().
        // Without this, the widget never actually changes the selection and doesn't call the
        // appropriate listeners. Since we override onLayout in our ViewGroups, a layout pass never
        // happens after a call to requestLayout, so we simulate one here.
        post(measureAndLayout);
    }

}
