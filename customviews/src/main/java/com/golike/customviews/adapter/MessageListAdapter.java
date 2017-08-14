package com.golike.customviews.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.golike.customviews.model.UIMessage;

import java.util.List;

/**
 * Created by admin on 2017/8/14.
 */

public class MessageListAdapter extends BaseAdapter<UIMessage>  {

    public MessageListAdapter(Context context) {
        this.mContext=context;
    }

    @Override
    protected View newView(Context context, int pos, ViewGroup parent) {
        return null;
    }

    @Override
    protected void bindView(View convertView, int pos, UIMessage uiMessage) {

    }
}
