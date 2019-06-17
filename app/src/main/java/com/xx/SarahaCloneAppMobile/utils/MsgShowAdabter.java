package com.xx.SarahaCloneAppMobile.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.xx.SarahaCloneAppMobile.R;

import java.util.ArrayList;

public class MsgShowAdabter  extends RecyclerView.Adapter<MsgShowAdabter.holder>{

    Context mcontext;
    ArrayList<MsgModel> msgModelArrayList;

    public MsgShowAdabter(Context mcontext, ArrayList<MsgModel> msgModelArrayList) {
        this.mcontext = mcontext;
        this.msgModelArrayList = msgModelArrayList;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(mcontext).inflate(R.layout.item_msg, viewGroup, false);

        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final holder holder, final int i) {
        MsgModel model = msgModelArrayList.get(i);
        holder.time.setText(model.timeMsg);
        holder.msg.setText(model.contentMsg);
        holder.buttonViewOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //creating a popup menu
                PopupMenu popup = new PopupMenu(mcontext, holder.buttonViewOption);
                //inflating menu from xml resource
                popup.inflate(R.menu.optinal_menu);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu1:
                               deleteMsg(i);
                                break;

                        }
                        return false;
                    }
                });
                //displaying the popup
                popup.show();

            }
        });
    }

    private void deleteMsg(int i) {
        /////////////////////////////////////////////////
    }


    @Override
    public int getItemCount() {
        return msgModelArrayList.size();
    }

    class holder extends RecyclerView.ViewHolder {
        TextView msg;
        TextView time;
        ImageButton buttonViewOption;
        public holder(@NonNull View itemView) {
            super(itemView);
            msg = itemView.findViewById(R.id.txtContentmsg);
            time= itemView.findViewById(R.id.txtTime);
            buttonViewOption = itemView.findViewById(R.id.textViewOptions);
        }
    }
}
