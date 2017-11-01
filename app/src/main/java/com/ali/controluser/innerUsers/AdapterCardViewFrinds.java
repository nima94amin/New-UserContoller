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
import com.ali.controluser.start.Users;

/**
 */

public class AdapterCardViewFrinds extends RecyclerView.Adapter<ViewHolderFirend>  {

    Context context;
    LayoutInflater layoutInflater;

    ImageView avatar;
    TextView usrname,mobile,register;
    LinearLayout cardAdapter;


    //////i'm create this constroctor
    public AdapterCardViewFrinds(Context context)
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
        cardAdapter =(LinearLayout) view.findViewById(R.id.card_adapter);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolderFirend holder, int position) {
        int copy=Users.firend_user.get(position).getRegister();
        if(copy==1){
            holder.tvRegisterUsers.setText("ثبت نام کرده");

        }else{
            holder.tvRegisterUsers.setText("ثبت نام نکرده");

        }


        holder.tvUsernameUsers.setText(Users.firend_user.get(position).getUsername().toString());


        //holder.tvUsernameUsers.setText(Users.firend_user.get(position).getUsername().toString());
        //holder.tvUsernameUsers.setVisibility(View.VISIBLE);
        holder.tvMobileUsers.setText(Users.firend_user.get(position).getMoblie().toString());
        //setMargins(holder.tvMobileUsers,10,10,10,10);
    }



    @Override
    public int getItemCount() {
        return Users.firend_user.size();
    }

    private void setMargins (View view, int left, int top, int right, int bottom) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(left, top, right, bottom);
            view.requestLayout();
        }
    }
}
