package com.golike.myapplication.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.golike.customviews.EditExtension;
import com.golike.customviews.EditExtensionManager;
import com.golike.customviews.IExtensionClickListener;
import com.golike.customviews.model.Conversation;
import com.golike.customviews.model.Conversation.ConversationType;
import com.golike.customviews.model.Message;
import com.golike.customviews.model.TextMessage;
import com.golike.customviews.plugin.IPluginModule;
import com.golike.customviews.utilities.PermissionCheckUtil;
import com.golike.myapplication.R;
import com.golike.myapplication.manager.AudioRecordManager;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements IExtensionClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private EditExtension mEditExtension;
    private boolean mEnableMention;
    private float mLastTouchY;
    private boolean mUpDirection;
    private float mOffsetLimit;
    private boolean finishing = false;
    private FrameLayout mChatView;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.chat_page, container, false);
        // Inflate the layout for this fragment
        //this.mChatView = (FrameLayout)view.findViewById(R.id.chat_view);
        this.mEditExtension = (EditExtension)view.findViewById(R.id.rc_extension);
        this.mEditExtension.setConversation(Conversation.ConversationType.PRIVATE,"xxxx");
        this.mEditExtension.setExtensionClickListener(this);
        this.mEditExtension.setFragment(this);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onSendToggleClick(View view, String text) {
        if(!TextUtils.isEmpty(text) && !TextUtils.isEmpty(text.trim())) {
            //TextMessage textMessage = TextMessage.obtain(text);
            Message message = Message.obtain("xxx", ConversationType.PRIVATE, text);
            EventBus.getDefault().post(message);
        } else {
            Log.e("ConversationFragment", "text content must not be null");
        }

    }


    @Override
    public void onImageResult(List<Uri> var1, boolean var2) {

    }

    @Override
    public void onLocationResult(double var1, double var3, String var5, Uri var6) {

    }

    @Override
    public void onSwitchToggleClick(View var1, ViewGroup var2) {

    }

    @Override
    public void onVoiceInputToggleTouch(View v, MotionEvent event) {
        String[] permissions = new String[]{"android.permission.RECORD_AUDIO"};
        if(!PermissionCheckUtil.checkPermissions(this.getActivity(), permissions)) {
            if(event.getAction() == 0) {
                PermissionCheckUtil.requestPermissions(this, permissions, 100);
            }

        } else {
            if(event.getAction() == 0) {
                //AudioPlayManager.getInstance().stopPlay();
                AudioRecordManager.getInstance().startRecord(v.getRootView(),  ConversationType.PRIVATE, "xxx");
                this.mLastTouchY = event.getY();
                this.mUpDirection = false;
                ((Button)v).setText(R.string.rc_audio_input_hover);
            } else if(event.getAction() == 2) {
                if(this.mLastTouchY - event.getY() > this.mOffsetLimit && !this.mUpDirection) {
                    AudioRecordManager.getInstance().willCancelRecord();
                    this.mUpDirection = true;
                    ((Button)v).setText(R.string.rc_audio_input);
                } else if(event.getY() - this.mLastTouchY > -this.mOffsetLimit && this.mUpDirection) {
                    AudioRecordManager.getInstance().continueRecord();
                    this.mUpDirection = false;
                    ((Button)v).setText(R.string.rc_audio_input_hover);
                }
            } else if(event.getAction() == 1 || event.getAction() == 3) {
                AudioRecordManager.getInstance().stopRecord();
                ((Button)v).setText(R.string.rc_audio_input);
            }

//            if(this.mConversationType.equals(ConversationType.PRIVATE)) {
//                RongIMClient.getInstance().sendTypingStatus(this.mConversationType, this.mTargetId, "RC:VcMsg");
//            }

        }
    }

    @Override
    public void onEmoticonToggleClick(View var1, ViewGroup var2) {

    }

    @Override
    public void onPluginToggleClick(View var1, ViewGroup var2) {

    }

    @Override
    public void onMenuClick(int var1, int var2) {

    }

    @Override
    public void onEditTextClick(EditText var1) {

    }

    @Override
    public boolean onKey(View var1, int var2, KeyEvent var3) {
        return false;
    }

    @Override
    public void onExtensionCollapsed() {

    }

    @Override
    public void onExtensionExpanded(int var1) {

    }

    @Override
    public void onPluginClicked(IPluginModule var1, int var2) {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
