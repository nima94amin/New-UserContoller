package com.ali.controluser.innerUsers;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ali.controluser.R;
import com.ali.controluser.database.dbConnector;
import com.ali.controluser.inner.class_login;
import com.ali.controluser.main.MainActivity;
import com.ali.controluser.recyclerListener.RecyclerTouchListener;
import com.ali.controluser.start.Users;
import com.ali.controluser.struct.Algol_sever;
import com.ali.controluser.struct.Javab_sever;
import com.ali.controluser.struct.Score_sever;

import java.util.ArrayList;

public class ActivityInnerPage extends Activity   implements View.OnClickListener {

    //*************    ArrayList   *************//

    public static ArrayList<Algol_sever> algorithm  = new ArrayList<Algol_sever>();   //algorithm... injalazem nist
    public static ArrayList<String> typeScore = new ArrayList<String>();
    public static ArrayList<Javab_sever> answwerUser = new ArrayList<Javab_sever>();
    public static ArrayList<Score_sever> scores   = new ArrayList<Score_sever>();

    //*************    database   *************//

    dbConnector dbusers;

    //*************   ImageButton   *************//

    ImageButton imbMoshkhast,imbFrend,imbScore;


    //*************    Dialog   *************//
    private ProgressDialog nDialog;
    ProgressBar p;

    //*************    textView   *************//
    TextView tvUsername,tvMoblie,tvName,tvFamiliy,tvFathername,tvCode,tvEmail,tvOstan, tvCity,tvAddress;


    //*************    editText   *************//


    //*************    variables   *************//
    int id;
    String usrname1;
    static String number = "";

    //*************    recyclerView   *************//
    private RecyclerView recyclerView ,recyclerViewScore;



    //*************    LinearLayout   *************//
    LinearLayout lyMoshkhast,lyFrend,lyScore;


    //*************    font   *************//
    public static Typeface typeface;



    //*************    url   *************//


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inner_page);

        dbusers = new dbConnector(this,"test",null,1);


        lyMoshkhast = (LinearLayout) findViewById(R.id.ly_moshakhast);
        lyFrend     = (LinearLayout) findViewById(R.id.ly_frends);
        lyScore     = (LinearLayout) findViewById(R.id.ly_score);

        imbMoshkhast = (ImageButton) findViewById(R.id.imb_moshkhast_u);
        imbFrend     = (ImageButton) findViewById(R.id.imb_frend_u);
        imbScore     = (ImageButton) findViewById(R.id.imb_score_u);

        tvFathername = (TextView)findViewById(R.id.tv_father_mskst);
        tvUsername   = (TextView)findViewById(R.id.tv_username_mskst);
        tvMoblie     = (TextView)findViewById(R.id.tv_moblie__mskst);
        tvName       = (TextView)findViewById(R.id.tv_name_mskst);
        tvFamiliy    = (TextView)findViewById(R.id.tv_family_mskst);
        tvCode       = (TextView)findViewById(R.id.tv_code__mskst);
        tvOstan      = (TextView)findViewById(R.id.tv_ostan_mskst);
        tvCity       = (TextView)findViewById(R.id.tv_city_mskst);
        tvAddress    = (TextView)findViewById(R.id.tv_address_mskst);
        tvEmail      = (TextView)findViewById(R.id.tv_email_mskst);

        imbMoshkhast.setOnClickListener(this);
        imbFrend.setOnClickListener(this);
        imbScore.setOnClickListener(this);
        Bundle extra = getIntent().getExtras();

        if (extra != null) {
            id = Integer.parseInt(extra.getString("id"));
            usrname1= Users.first_users.get(id).getUsername().toString();
            Log.i("ekkssss","are"+id+usrname1);

        }else{
            Log.i("ekkssss","are");
        }

    }

    @Override
    public void onClick(View v) {
        if(v ==imbMoshkhast){
            lyMoshkhast.setVisibility(View.VISIBLE);
            lyFrend.setVisibility(View.GONE);
            lyScore.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "1 Clicked", Toast.LENGTH_SHORT).show();

           // Boolean result=moshkhast(usrname1);
           // Log.i("reusltali",result.toString()+usrname1);


        }else if(v==imbScore){
            //Toast.makeText(getApplicationContext(), "as soon as", Toast.LENGTH_SHORT).show();

            lyMoshkhast.setVisibility(View.GONE);
            lyFrend.setVisibility(View.GONE);
            lyScore.setVisibility(View.VISIBLE);
           // score();
            ///show this score in rc_score...


            /////////////////

        }else{
            lyMoshkhast.setVisibility(View.GONE);
            lyFrend.setVisibility(View.VISIBLE);
            lyScore.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "3 Clicked", Toast.LENGTH_SHORT).show();

            recyclerView = (RecyclerView) findViewById(R.id.rc_firend);

            AdapterCardViewFrinds adapterCardViewFrinds = new AdapterCardViewFrinds(MainActivity.context);
            recyclerView.setAdapter(adapterCardViewFrinds);
            recyclerView.setLayoutManager(new LinearLayoutManager((MainActivity.context)));
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.context);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.addOnItemTouchListener(new RecyclerTouchListener(MainActivity.context, recyclerView, new RecyclerTouchListener.ClickListener() {
                @Override
                public void onClick(View view, int position) {   ////////////click barye frends ast..
                    // Log.i("boool_if",Users.firend_user.get(position).getUsername().toString()+":"+1);


                    int register = Users.firend_user.get(position).getRegister();
                    Log.i("give username",Users.firend_user.get(position).getUsername().toString()+"::"+register);


                    if (register == 0) {
                        number =Users.firend_user.get(position).getMoblie().toString();
                        new class_login(number,"0").execute();

                        Log.i("statussms",  "alialaiasi");
                        Toast.makeText(MainActivity.context, "این شماره به لیست ارسال sms اضافه شد.", Toast.LENGTH_LONG).show();

                        /////////////////////////////inja ro mikamam vasl konam be sever va test konam...

                        //  new insetNumber().execute();
                    } else {
                        ///// khasti in ja benvis ke moshkastat ra nomayesh dahad..
                        Log.i("give username",Users.firend_user.get(position).getUsername().toString());

                    }

                }
                @Override
                public void onLongClick(View view, int position) {

                }
            }));
        }

    }
    //*************    select information any user   *************//




}
