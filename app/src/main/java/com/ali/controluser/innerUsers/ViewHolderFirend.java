package com.ali.controluser.innerUsers;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ali.controluser.R;

/**
 */

public class ViewHolderFirend extends RecyclerView.ViewHolder  {

    public TextView tvUsernameUsers,tvMobileUsers,tvRegisterUsers;
    public ImageView avtarUsers;


    public ViewHolderFirend(View itemView) {
        super(itemView);

        tvUsernameUsers = (TextView) itemView.findViewById(R.id.tv_username_users);
        tvMobileUsers   = (TextView) itemView.findViewById(R.id.tv_mobile_users);
        tvRegisterUsers = (TextView) itemView.findViewById(R.id.tv_register_users);

        avtarUsers      = (ImageView) itemView.findViewById(R.id.avatar_users);

    }

}