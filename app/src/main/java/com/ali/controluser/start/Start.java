package com.ali.controluser.start;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ali.controluser.R;
import com.ali.controluser.main.MainActivity;

public class Start extends Activity implements View.OnClickListener {

    //*************    ArrayList   *************//


    //*************    database   *************//


    //*************   ImageButton   *************//

    ImageButton imUsers,imQustion,imInvate,imRequest,imXite, imSeting;


    //*************    Dialog   *************//
    private ProgressDialog nDialog;
    ProgressBar p;

    //*************    textView   *************//
    TextView txtuser,txtinvaite,txtseeting,txtexite,txtquestion,txtreqest;

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
        setContentView(R.layout.activity_start);

        typeface = Typeface.createFromAsset(MainActivity.context.getAssets(),"font/BNazanin.ttf");



        imUsers    = (ImageButton) findViewById(R.id.imb_users);
       // imQustion  = (ImageButton) findViewById(R.id.imb_qustion);
        imInvate   = (ImageButton) findViewById(R.id.imb_invate);
       // imRequest  = (ImageButton) findViewById(R.id.imb_request);
        imSeting   = (ImageButton) findViewById(R.id.imb_seting);
        imXite     = (ImageButton) findViewById(R.id.imb_exite);

        txtexite    =(TextView)findViewById(R.id.txt_exite);
        txtuser    =(TextView)findViewById(R.id.txt_user);
        txtinvaite    =(TextView)findViewById(R.id.txt_invaite);
      //  txtreqest    =(TextView)findViewById(R.id.txt_reqest);
     //   txtquestion    =(TextView)findViewById(R.id.txt_question);
        txtseeting    =(TextView)findViewById(R.id.txt_seeting);

        //*************    fontTextViews   *************//
        txtexite.setTypeface(typeface);
       // txtquestion.setTypeface(typeface);
        txtinvaite.setTypeface(typeface);
        txtseeting.setTypeface(typeface);
        txtuser.setTypeface(typeface);
        //txtreqest.setTypeface(typeface);

        //*************    click for imageView   *************//
        imUsers.setOnClickListener(this);
       // imQustion.setOnClickListener(this);
       // imRequest.setOnClickListener(this);
        imInvate.setOnClickListener(this);
        imXite.setOnClickListener(this);
        imSeting.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if(v==imUsers){
            Toast.makeText(getApplicationContext(), "users Clicked", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(MainActivity.context, Users.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            MainActivity.context.startActivity(intent);

        }else if(v ==imInvate){
            Toast.makeText(getApplicationContext(), "invated Clicked", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(MainActivity.context, Invaite.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            MainActivity.context.startActivity(intent);
        }else if(v==imXite){
            Toast.makeText(getApplicationContext(), "exite Clicked", Toast.LENGTH_SHORT).show();
            finish();

        }else{
            Toast.makeText(getApplicationContext(), "setings... Clicked", Toast.LENGTH_SHORT).show();
            //////////////botom code comed ples when you want give hamid....
            Intent intent=new Intent(MainActivity.context, Seeting.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            MainActivity.context.startActivity(intent);

        }

    }
}
