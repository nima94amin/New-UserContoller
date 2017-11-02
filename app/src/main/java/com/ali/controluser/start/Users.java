package com.ali.controluser.start;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ali.controluser.R;
import com.ali.controluser.database.JSONParser;
import com.ali.controluser.database.dbConnector;
import com.ali.controluser.inner.AdatpterCardViewUser;
import com.ali.controluser.struct.First_user_server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

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
    private final String url = "http://aliexamination.ir/firestUser.php";



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
    public class class_selectAllUsers extends AsyncTask {

        JSONParser jsonParser=new JSONParser();
        JSONArray jsonArray =null;

        public class_selectAllUsers(){


        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            //*************    show user in user_recycler   *************//

           // show();
        }

        @Override
        protected Object doInBackground(Object[] params) {

            Log.i("alialiali","jskjsd");

            HashMap<String,String> param=new HashMap<String , String>();

            JSONObject jsonObject =jsonParser.makeHttpRequest(url,"GET",param);   //receive information form sever and put into jsonObject...

            try {

                int t=jsonObject.getInt("t");

                Log.i("alialialit","jskjsd="+t);
                if(t==1){

                    jsonArray= jsonObject.getJSONArray("firstuser");    /// input json response["travel"] in php code ;  and give me length...

                    for(int i=0 ; i<jsonArray.length();i++){

                        JSONObject temp=jsonArray.getJSONObject(i);

                        String username = temp.getString("username");
                        String mobile        = temp.getString("mobile");
                        int register        =Integer.parseInt(temp.getString("register"));

                        ////////////////////inja in etelaat ra vared list first user mikonim...
                        Log.i("boool_se",register+":"+1);



                        //////this is a templite.....
                        First_user_server first_user_server = new First_user_server(username,mobile, register);
                        first_user_server.setUsername(username);
                        first_user_server.setMoblie(mobile);
                        first_user_server.setRegister(register);


                        Users.first_users.add(first_user_server);
                        Log.i("xsoal_server22222_se", "t: " + Users.first_users+"kjkhk="+username);


                    }

                }else{
                    Toast.makeText(getApplicationContext(),"no imfomainion", Toast.LENGTH_SHORT).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.i("show2","come here"+Users.first_users.size());

            return null;

        }


    }



}
