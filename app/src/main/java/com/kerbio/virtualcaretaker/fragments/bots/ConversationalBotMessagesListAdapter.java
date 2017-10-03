package com.kerbio.virtualcaretaker.fragments.bots;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.amazonaws.mobile.bots.Conversation;
import com.amazonaws.mobile.bots.TextMessage;
import com.kerbio.virtualcaretaker.R;

/**
 * Created by niroshan on 6/1/17.
 */
public class ConversationalBotMessagesListAdapter extends BaseAdapter {
    private Context context;
    private int count;
    private static LayoutInflater layoutInflater;

    public ConversationalBotMessagesListAdapter(Context context) {
        this.context = context;
        count = Conversation.getCount();
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;

        TextMessage item = Conversation.getMessage(position);

        if (convertView == null) {
            if ("tx".equals(item.getFrom())) {
                convertView = layoutInflater
                        .inflate(R.layout.conversational_bot_user_message_layout, null);
                holder = new Holder();
                holder.message = (TextView) convertView
                        .findViewById(R.id.editTextUserInput);
            } else {
                convertView = layoutInflater
                        .inflate(R.layout.conversational_bot_response_message_layout, null);
                holder = new Holder();
                holder.message = (TextView) convertView
                        .findViewById(R.id.editTextBotResponse);
            }
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.message.setText(item.getMessage());
        return convertView;
    }

    // Helper class to recycle View's
    static class Holder {
        TextView message;
    }

    // Add new items
    public void refreshList(TextMessage message) {
        Conversation.add(message);
        notifyDataSetChanged();
    }

}
