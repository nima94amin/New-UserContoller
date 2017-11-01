package com.ali.controluser.start;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ProgressBar;

import com.ali.controluser.R;
import com.ali.controluser.database.dbConnector;
import com.ali.controluser.inner.AdatpterCardViewUser;
import com.ali.controluser.struct.First_user_server;

import java.util.ArrayList;

public class Users extends Activity {

    //*************    ArrayList   *************//

    public static ArrayList<First_user_server> first_users = new ArrayList<First_user_server>();
    public static ArrayList<First_user_server> firend_user = new ArrayList<First_user_server>();
    //*************    database   *************//

    static dbConnector dbusers;
    dbConnector db;
    //*************   ImageButton   *************//



    //*************    Dialog   *************//
    private ProgressDialog nDialog;
    ProgressBar p;

    //*************    textView   *************//

    //*************    RecyclerView   *************//
    static RecyclerView recyclerView;
    static AdatpterCardViewUser adatpterCardViewUser;


    //*************    variables   *************//
    static String number = "";

    //*************    sharedPreferences   *************//

    //*************    font   *************//
    public static Typeface typeface;



    //*************    url   *************//



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        dbusers = new dbConnector(this, "test", null, 1);
        first_users.clear();
        Log.i("alialialias","jskjsd");

        //*************    url   *************//
        select();

    }

    //*************    url   *************//

    private void select() {    ///az ssever list karbarn ra miavard ham onaye ro ke sabte nam kardam va ham onee ra ke nakardan..

        /////////////////////
       // new class_selectAllUsers().execute();

    }

    //*************    select all user form server and show to manager    *************//


}
