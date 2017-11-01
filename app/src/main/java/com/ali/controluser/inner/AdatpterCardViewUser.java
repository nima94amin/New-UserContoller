package com.ali.controluser.inner;

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
 * Created by Ali_Najafi on 4/14/2017.
 */public class AdatpterCardViewUser  extends RecyclerView.Adapter<ViewHolder>   {

    Context context;
    LayoutInflater layoutInflater;

    ImageView avatar;
    TextView usrname,mobile,register;
    LinearLayout cardAdapter;


    public AdatpterCardViewUser(Context context)
    {
        this.context=context;
        layoutInflater=layoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view =layoutInflater.inflate(R.layout.card_view_users,parent,false);
        ViewHolder viewHolder =new ViewHolder(view);

        //avatar=(ImageView) view.findViewById(R.id.avatar_users);
        usrname=(TextView)  view.findViewById(R.id.tv_username_users);
        mobile =(TextView)  view.findViewById(R.id.tv_mobile_users);
        register =(TextView)view.findViewById(R.id.tv_register_users);

        cardAdapter =(LinearLayout) view.findViewById(R.id.card_adapter);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(Users.first_users.get(position).getRegister()==1){
            holder.tvRegisterUsers.setText("ثبت نام کرده");

        }else{
            holder.tvRegisterUsers.setText("ثبت نام نکرده");

        }

        holder.tvUsernameUsers.setText(Users.first_users.get(position).getUsername().toString());
        holder.tvMobileUsers.setText(Users.first_users.get(position).getMoblie().toString());


    }

    @Override
    public int getItemCount() {
        return Users.first_users.size();
    }

}


///

