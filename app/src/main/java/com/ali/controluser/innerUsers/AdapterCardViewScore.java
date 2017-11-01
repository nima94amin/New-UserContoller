package com.ali.controluser.innerUsers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ali.controluser.R;

/**
 */

public class AdapterCardViewScore extends RecyclerView.Adapter<ViewHolderFirend>  {

    Context context;
    LayoutInflater layoutInflater;

    ImageView avatar;
    TextView usrname,mobile,register;
    LinearLayout cardAdapter;


    //////i'm create this constroctor
    public AdapterCardViewScore(Context context)
    {
        this.context=context;
        layoutInflater=layoutInflater.from(context);
    }

    @Override
    public ViewHolderFirend onCreateViewHolder(ViewGroup parent, int viewType) {

        View view =layoutInflater.inflate(R.layout.card_view_users,parent,false);
        ViewHolderFirend viewHolder =new ViewHolderFirend(view);

        usrname=(TextView)  view.findViewById(R.id.tv_username_users);
        mobile =(TextView)  view.findViewById(R.id.tv_mobile_users);
        register =(TextView)view.findViewById(R.id.tv_register_users);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolderFirend holder, int position) {

        holder.tvRegisterUsers.setText("");

        holder.tvUsernameUsers.setText(ActivityInnerPage.scores.get(position).getScore().toString());   ///// put this to cycle progers for score

        holder.tvMobileUsers.setText(ActivityInnerPage.scores.get(position).getType().toString());
    }




    @Override
    public int getItemCount() {
        return ActivityInnerPage.scores.size();
    }

    private void setMargins (View view, int left, int top, int right, int bottom) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(left, top, right, bottom);
            view.requestLayout();
        }
    }
}
