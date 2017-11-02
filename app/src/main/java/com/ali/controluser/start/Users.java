package com.ali.controluser.start;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ali.controluser.R;
import com.ali.controluser.database.JSONParser;
import com.ali.controluser.database.dbConnector;
import com.ali.controluser.inner.AdatpterCardViewUser;
import com.ali.controluser.inner.class_login;
import com.ali.controluser.innerUsers.ActivityInnerPage;
import com.ali.controluser.main.MainActivity;
import com.ali.controluser.recyclerListener.RecyclerTouchListener;
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
    private final String urlselectfriends = "http://aliexamination.ir/selectFriends.php";
    private final String urlselectfriendsinfo = "http://aliexamination.ir/selectFriendsInfo.php"; //for selct informain in database ?????





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
    //*************    show users to recycler_user   *************//
    public void show() {

        Log.i("show", "come here" + first_users.size());
////////////////////////hehe ali clean this commentonly....
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_user);
        adatpterCardViewUser = new AdatpterCardViewUser(MainActivity.context);
        recyclerView.setAdapter(adatpterCardViewUser);
        recyclerView.setLayoutManager(new LinearLayoutManager((MainActivity.context)));
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.context);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(MainActivity.context, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                int register = first_users.get(position).getRegister();
                Log.i("give username",first_users.get(position).getUsername().toString()+"::"+register);


                if (register == 0) {
                    number = first_users.get(position).getMoblie().toString();
                    new class_login(number,"0").execute();

                    Log.i("statussms",  "alialaiasi");
                    Toast.makeText(MainActivity.context, "این شماره به لیست ارسال sms اضافه شد.", Toast.LENGTH_LONG).show();

                    /////////////////////////////inja ro mikamam vasl konam be sever va test konam...

                    //  new insetNumber().execute();
                } else {
                    Log.i("give username",first_users.get(position).getUsername().toString());

                    selectFrends(position);   /////////////////////this is select  firend s any users/.....


                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


    }
    //*************    select any user frinde    *************//

    public  void selectFrends(int position) {
        firend_user.clear();


        Log.i("give username",first_users.get(position).getUsername().toString());

        new class_selectFriends(first_users.get(position).getUsername().toString(),position).execute();
    }


    //*************    select any user frinde from server   *************//
    public class class_selectFriends extends AsyncTask {

        JSONParser jsonParser=new JSONParser();
        JSONArray jsonArray =null;
        String username;
        int position;


        public class_selectFriends(String username,int position){
            this.username=username;
            this.position=position;
            Log.i("give usernamese",username);

        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            // show();                 ////////////bebin lazme


            Toast.makeText(MainActivity.context, position + " is selected!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.context, ActivityInnerPage.class);
            // intent.putExtra("name",ti);
            intent.putExtra("id", position + "");
            intent.putExtra("username", first_users.get(position).getUsername().toString());   /////??????????????
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            MainActivity.context.startActivity(intent);
            Log.i("size freind",firend_user.size()+"");

            for(int i=0;i<firend_user.size();i++){
                String number= firend_user.get(i).getMoblie();
                Log.i("fori",firend_user.size()+":"+i+"::"+number);

                new class_selectFriendsInfo(number,i).execute();

            }
        }

        @Override
        protected Object doInBackground(Object[] params) {

            Log.i("alialialiselrct freind","jskjsd");

            HashMap<String,String> param=new HashMap<String , String>();
            param.put("username",username);

            Log.i("alialialiselectfr","aliii="+username);
            JSONObject jsonObject =jsonParser.makeHttpRequest(urlselectfriends,"POST",param);   //receive information form sever and put into jsonObject...

            try {

                int t=jsonObject.getInt("t");

                Log.i("alialialiselectfr","jskjsd="+t);
                if(t==1){

                    jsonArray= jsonObject.getJSONArray("friends");    /// input json response["travel"] in php code ;  and give me length...

                    for(int i=0 ; i<jsonArray.length();i++){

                        JSONObject temp=jsonArray.getJSONObject(i);

                        String friend_mob = temp.getString("friend_mob");


                        ////////////////////inja in etelaat ra vared list first user mikonim...


                        //////this is a templite.....
                        First_user_server first_user_server = new First_user_server(username, friend_mob, 1);
                        first_user_server.setUsername(username);
                        first_user_server.setMoblie(friend_mob);


                        firend_user.add(first_user_server);
                        //firend_user.get(i).setUsername();
                        Log.i("xsoal_server22222+++", "t: " + firend_user);


                    }

                }else{
                    // Toast.makeText(MainActivity.context,"no imfomainion", Toast.LENGTH_SHORT).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.i("show2friend","come here"+Users.first_users.size());



            return null;

        }


    }

    //*************    select firends information from servr  || use in  class_selectFriends *************//

    public class class_selectFriendsInfo extends AsyncTask {   /////baraye jostejooie moshakhasate dostan...   ////test...

        JSONParser jsonParser=new JSONParser();
        JSONArray jsonArray =null;
        String friend_mob;
        String usernameFriend;
        String registerFriend;
        int pos;

        public class_selectFriendsInfo(String friend_mob, int pos){
            this.friend_mob=friend_mob;
            this.pos=pos;

        }


        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            firend_user.get(pos).setUsername(usernameFriend);
            firend_user.get(pos).setRegister(Integer.parseInt(registerFriend));
            Log.i("postcom","1"+pos+":"+firend_user);
        }

        @Override
        protected Object doInBackground(Object[] params) {

            Log.i("alialialiinfoali",friend_mob+":"+pos);

            HashMap<String,String> param=new HashMap<String , String>();
            param.put("mobile",friend_mob);


            JSONObject jsonObject =jsonParser.makeHttpRequest(urlselectfriendsinfo,"POST",param);   //receive information form sever and put into jsonObject...

            try {

                int t=jsonObject.getInt("t");

                Log.i("alialialiinfot",friend_mob+"="+t);
                if(t==1){

                    jsonArray= jsonObject.getJSONArray("friendsinfo");    /// input json response["travel"] in php code ;  and give me length...

                    for(int i=0 ; i<jsonArray.length();i++){

                        JSONObject temp=jsonArray.getJSONObject(i);

                        usernameFriend = temp.getString("username");
                        registerFriend = temp.getString("register");


                    }

                }else{
                    //Toast.makeText(MainActivity.context,"no imfomainion", Toast.LENGTH_SHORT).show();
                    usernameFriend = "no username";
                    registerFriend = "0";
                }

                Log.i("alialialiinfo2",friend_mob+"="+usernameFriend+registerFriend+":"+pos);


            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.i("show2","come here"+Users.first_users.size());



            return null;

        }


    }






}
