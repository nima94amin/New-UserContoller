package com.ali.controluser.inner;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ali.controluser.R;

/**
 * Created by Ali_Najafi on 4/14/2017.
 */

public class ViewHolder extends RecyclerView.ViewHolder  {

    TextView tvUsernameUsers,tvMobileUsers,tvRegisterUsers;
    ImageView avtarUsers;
    public ViewHolder(View itemView) {
        super(itemView);

        tvUsernameUsers = (TextView) itemView.findViewById(R.id.tv_username_users);
        tvMobileUsers   = (TextView) itemView.findViewById(R.id.tv_mobile_users);
        tvRegisterUsers = (TextView) itemView.findViewById(R.id.tv_register_users);

        avtarUsers      = (ImageView) itemView.findViewById(R.id.avatar_users);

    }

}
