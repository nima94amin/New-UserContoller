package com.ali.controluser.main;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ali.controluser.R;
import com.ali.controluser.start.Start;
import com.ali.controluser.struct.Algol_sever;
import com.ali.controluser.struct.Answer_user_server;
import com.ali.controluser.struct.First_user_server;
import com.ali.controluser.struct.Frind_sever;
import com.ali.controluser.struct.Javab_sever;
import com.ali.controluser.struct.Score_sever;
import com.ali.controluser.struct.Soal_sever;
import com.ali.controluser.struct.Type_server;
import com.ali.controluser.struct.Users_sever;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //*************    ArrayList   *************//

    //*************    database   *************//


    //*************    button   *************//
    Button btnEnter;


    //*************    Dialog   *************//
    private ProgressDialog nDialog;

    //*************    editText   *************//
    EditText txtUsername,txtMobile;

    //*************    TextInputLayout   *************//

    android.support.design.widget.TextInputLayout usernameText;
    android.support.design.widget.TextInputLayout mobileText;


    //*************    variables   *************//
    String username,mobile;


    //*************   context    *************//
    public static Context context;

    //*************    sharedPreferences   *************//

    //*************    font   *************//
    public static Typeface typeface;



    //*************    url   *************//


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();

        /////////////////// color of statusBar ...

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.rgb(40,53,147));
        }

        typeface = Typeface.createFromAsset(MainActivity.this.getAssets(),"font/BNazanin.ttf");

        //*************    insetfont   *************//

        usernameText =(android.support.design.widget.TextInputLayout)findViewById(R.id.input_layout_usename);
        usernameText.setTypeface(typeface);
        mobileText =(android.support.design.widget.TextInputLayout)findViewById(R.id.input_layout_mobile);
        mobileText.setTypeface(typeface);
        btnEnter    = (Button)findViewById(R.id.btn_enter);
        btnEnter.setTypeface(typeface);


        txtUsername = (EditText) findViewById(R.id.txtusername);
        txtMobile    = (EditText) findViewById(R.id.txtmoble);

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ///////check from sever username and password first work    .......  1

                username = txtUsername.getText().toString();
                mobile = txtMobile.getText().toString();

                ///chke usernaame...

                Intent intent=new Intent(MainActivity.context, Start.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                MainActivity.context.startActivity(intent);


            }
        });


    }
}
