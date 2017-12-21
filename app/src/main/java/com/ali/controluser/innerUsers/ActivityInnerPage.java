package com.ali.controluser.innerUsers;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.AsyncTask;
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
import com.ali.controluser.database.JSONParser;
import com.ali.controluser.database.dbConnector;
import com.ali.controluser.inner.class_login;
import com.ali.controluser.main.MainActivity;
import com.ali.controluser.recyclerListener.RecyclerTouchListener;
import com.ali.controluser.start.Users;
import com.ali.controluser.struct.Algol_sever;
import com.ali.controluser.struct.Javab_sever;
import com.ali.controluser.struct.Score_sever;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

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
    private final String urlselectfriendsinfo = "http://aliexamination.ir/selectFriendsInfo.php";
    private final String urlselectScoreType = "http://aliexamination.ir/selectTypeScore.php";
    private final String urlselectAlgorithm = "http://aliexamination.ir/selectAlgorthim.php";
    private final String urlselectAnsewerUser ="http://aliexamination.ir/selectAnswerUser.php";
    private final String urlInsertScore        ="http://aliexamination.ir/insertScore.php";
    private final String urlselectScore        = "http://aliexamination.ir/selectScore.php";
    private final String urlUpdateAnswertScore ="http://aliexamination.ir/updateAnswerScore.php";
    private final String urlselectInformationUesr = "http://aliexamination.ir/selectImformaion.php";


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

            Boolean result=moshkhast(usrname1);
            Log.i("reusltali",result.toString()+usrname1);


        }else if(v==imbScore){
            //Toast.makeText(getApplicationContext(), "as soon as", Toast.LENGTH_SHORT).show();

            lyMoshkhast.setVisibility(View.GONE);
            lyFrend.setVisibility(View.GONE);
            lyScore.setVisibility(View.VISIBLE);
            score();
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
    private Boolean moshkhast(String username){

        new class_selectInformationUser(username).execute();
        return false;
    }

    //*************    select information any user form sever   *************//
    public class class_selectInformationUser extends AsyncTask {   ////select from javab dade ha...

        JSONParser jsonParser=new JSONParser();
        JSONArray jsonArray =null;
        String username;
        String city,mobile,name,family,fathername,code,email,ostan,address;



        public class_selectInformationUser(String username){
            this.username=username;
            Log.i("give usernamesescore",username);

        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            Log.i("comcomcom",city);
            tvUsername.setText(username);
            tvMoblie.setText(mobile);
            tvName.setText(name);
            tvFamiliy.setText(family);
            tvFathername.setText(fathername);
            tvCode.setText(code);
            tvEmail.setText(email);
            tvOstan.setText(ostan);
            tvCity.setText(city);
            tvAddress.setText(address);
        }

        @Override
        protected Object doInBackground(Object[] params) {

            Log.i("select_usersifnfo","jskjsd");

            HashMap<String,String> param=new HashMap<String , String>();
            param.put("username",username);

            Log.i("select_scoerinfor","aliii="+username);
            JSONObject jsonObject =jsonParser.makeHttpRequest(urlselectInformationUesr,"POST",param);   //receive information form sever and put into jsonObject...

            try {

                int t=jsonObject.getInt("t");

                Log.i("alialialiselectfrscore","jskjsd="+t);
                if(t==1){

                    jsonArray= jsonObject.getJSONArray("information");    /// input json response["travel"] in php code ;  and give me length...

                    for(int i=0 ; i<jsonArray.length();i++){

                        JSONObject temp=jsonArray.getJSONObject(i);

                        name = temp.getString("name");
                        family = temp.getString("family");
                        fathername = temp.getString("fathername");
                        code = temp.getString("code");
                        email = temp.getString("email");
                        city = temp.getString("city");
                        ostan = temp.getString("ostan");
                        address = temp.getString("address");
                        mobile = temp.getString("mobile");


                    }

                }else{
                    Toast.makeText(MainActivity.context,"no imfomainion", Toast.LENGTH_SHORT).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.i("show2scoretype","come here"+typeScore.size());



            return null;

        }


    }

    //*************    excute  score for any user  *************//

    private  void  score(){
        String username=usrname1;
        typeScore.clear();
        scores.clear();
        algorithm.clear();
        answwerUser.clear();
        new class_selectTypeScore(username).execute();


    }
    //*************    excute  score for any user form sever  *************//

    public class class_selectTypeScore extends AsyncTask {   ////select from javab dade ha...

        JSONParser jsonParser=new JSONParser();
        JSONArray jsonArray =null;
        String username;



        public class_selectTypeScore(String username){
            this.username=username;

            Log.i("give usernamesescore",username);

        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            ////////////if and else for check is a type thate not excute score for this for any users...
            if(typeScore.size()>0){
                for(int i=0;i<typeScore.size();i++){   ///baraye chanta ham test kon

                    String type=typeScore.get(i);
                    algorithm.clear();        /////clear for next type  an should clear answers list in post selectAlgorithm...

                    new class_selectAlgorithm(type).execute();

                }

            }else{
                ///  heres should fill scores list for show the managers...
                new class_selectScores(username).execute();
                Log.i("comelae","elsse:::"+username);

            }
        }

        @Override
        protected Object doInBackground(Object[] params) {

            Log.i("select_socore_users","jskjsd");

            HashMap<String,String> param=new HashMap<String , String>();
            param.put("username",username);

            Log.i("select_scoer","aliii="+username);
            JSONObject jsonObject =jsonParser.makeHttpRequest(urlselectScoreType,"POST",param);   //receive information form sever and put into jsonObject...

            try {

                int t=jsonObject.getInt("t");

                Log.i("alialialiselectfrscore","jskjsd="+t);
                if(t==1){

                    jsonArray= jsonObject.getJSONArray("typescore");    /// input json response["travel"] in php code ;  and give me length...

                    for(int i=0 ; i<jsonArray.length();i++){

                        JSONObject temp=jsonArray.getJSONObject(i);

                        String type = temp.getString("type");


                        ////////////////////inja in etelaat ra vared list first user mikonim...


                        //////this is a templite.....
                        typeScore.add(type);

                        //firend_user.get(i).setUsername();
                        Log.i("xsoal_server2scortype", "t: " + typeScore);


                    }

                }else{
                    // Toast.makeText(MainActivity.context,"no imfomainion", Toast.LENGTH_SHORT).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.i("show2scoretype","come here"+typeScore.size());



            return null;

        }


    }
    //*************    select algorthim form sever for any question for excute scores any user  || use in class_selectTypeScore *************//

    public class class_selectAlgorithm extends AsyncTask {

        JSONParser jsonParser=new JSONParser();
        JSONArray jsonArray =null;
        String type;



        public class_selectAlgorithm(String type){
            this.type=type;
            Log.i("give usernamese",type);

        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            /////////////// full a  list of anwsers...
            answwerUser.clear();
            new class_selectAnsewrdUser(type).execute();



        }

        @Override
        protected Object doInBackground(Object[] params) {

            Log.i("select algorthm","jskjsd");

            HashMap<String,String> param=new HashMap<String , String>();
            param.put("type",type);     /////ch

            Log.i("selct algorthm","aliii="+type);
            JSONObject jsonObject =jsonParser.makeHttpRequest(urlselectAlgorithm,"POST",param);   //receive information form sever and put into jsonObject...

            try {

                int t=jsonObject.getInt("t");

                Log.i("alialialiselectfrtype","jskjsd="+t);
                if(t==1){
                    algorithm.clear();
                    jsonArray= jsonObject.getJSONArray("algorithm");    /// input json response["travel"] in php code ;  and give me length...

                    for(int i=0 ; i<jsonArray.length();i++){

                        JSONObject temp=jsonArray.getJSONObject(i);

                        String id = temp.getString("id");
                        Double w1 =Double.parseDouble(temp.getString("w1"));
                        Double w2 = Double.parseDouble(temp.getString("w2"));
                        Double w3 =Double.parseDouble(temp.getString("w3"));
                        Double w4 = Double.parseDouble(temp.getString("w4"));
                        Log.i("w11",w1+":"+w2+":"+w3+":"+w4+"");


                        ////////////////////inja in etelaat ra vared list first user mikonim...


                        //////this is a templite.....
                        Algol_sever algol_sever = new Algol_sever(type,id,w1,w2,w3,w4);
                        algol_sever.setType(type);
                        algol_sever.setId(id);
                        algol_sever.setW1(w1);
                        algol_sever.setW2(w2);
                        algol_sever.setW3(w3);
                        algol_sever.setW4(w4);



                        algorithm.add(algol_sever);
                        //firend_user.get(i).setUsername();
                        Log.i("xsoal_algorthim", "t: " + algorithm);


                    }

                }else{
                    // Toast.makeText(MainActivity.context,"no imfomainion", Toast.LENGTH_SHORT).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.i("show2friendalgo","come here"+algorithm.size());



            return null;

        }


    }

    //*************    select answer for any user for execute score for user  || use in class_selectAlgorithm   *************//

    public class class_selectAnsewrdUser extends AsyncTask {

        JSONParser jsonParser=new JSONParser();
        JSONArray jsonArray =null;
        String type;



        public class_selectAnsewrdUser(String type){
            this.type=type;

            Log.i("give usernamesescore",type);

        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            algorithmFunction(type); //////for excute score uers....

        }

        @Override
        protected Object doInBackground(Object[] params) {

            Log.i("select_socore_ans","jskjsd");

            HashMap<String,String> param=new HashMap<String , String>();
            param.put("type",type);
            param.put("username",usrname1);

            Log.i("select_anser","aliii="+type+":"+usrname1);
            JSONObject jsonObject =jsonParser.makeHttpRequest(urlselectAnsewerUser,"POST",param);   //receive information form sever and put into jsonObject...

            try {

                int t=jsonObject.getInt("t");

                Log.i("alialialiAnswer","jskjsd="+t);
                if(t==1){
                    answwerUser.clear();
                    jsonArray= jsonObject.getJSONArray("answers");    /// input json response["travel"] in php code ;  and give me length...

                    for(int i=0 ; i<jsonArray.length();i++){

                        JSONObject temp=jsonArray.getJSONObject(i);

                        String id = temp.getString("id");
                        int answer = Integer.parseInt(temp.getString("answer"));


                        Javab_sever javab_sever =new Javab_sever("",type,id,answer);
                        javab_sever.setId(id);
                        javab_sever.setAnswer(answer);

                        //////this is a templite.....
                        answwerUser.add(javab_sever);

                        //firend_user.get(i).setUsername();
                        Log.i("xsoal_server2javab", "t: " + answwerUser);


                    }

                }else{
                    // Toast.makeText(MainActivity.context,"no imfomainion", Toast.LENGTH_SHORT).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.i("show2scoretypeanser","come here"+answwerUser.size());



            return null;

        }


    }
    //*************    for execute score form algorithm and answer   || use in class_selectAnsewrdUser  *************//

    private void algorithmFunction(String type){
        Double total =0.0;
        for(int i=0;i<algorithm.size();i++){
            int answer =answwerUser.get(i).getAnswer();
            Double scor = 0.0 , result;
            if(answer==1){
                scor=algorithm.get(i).getW1();

            }else if(answer==2){
                scor=algorithm.get(i).getW2();

            }else if(answer==3){
                scor=algorithm.get(i).getW3();

            }else if(answer==4){
                scor=algorithm.get(i).getW4();


            }else {  ////ans==0

            }

            result = answer* scor;
            total += result;
        }
        /////////////////   insert into score table in server...
        Log.i("algorithm ali",total.toString());
        String username=usrname1;
        new class_insertScore(username,type,total+"").execute();


    }

    //*************    insert scores for user that executed in function algorithm in sever \\ use in algorithmFunction  *************//
    public class class_insertScore extends AsyncTask {

        JSONParser jsonParser=new JSONParser();
        String type, username;
        String score;



        public class_insertScore(String username,String type,String score){
            this.type=type;
            this.username=username;
            this.score=score;
            Log.i("give usernamesescore",type +":"+usrname1+":"+score);

        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            new class_selectScores(username).execute();

            new class_updateAnswerScore(username,type).execute();
            scores.clear();


        }

        @Override
        protected Object doInBackground(Object[] params) {

            Log.i("select_socore_ans","jskjsd");

            HashMap<String,String> paramscor=new HashMap<String , String>();

            paramscor.put("username",username);
            paramscor.put("type",type);
            paramscor.put("score",score);

            Log.i("select_anser","aliii="+type+":"+usrname1);
            JSONObject jsonObject =jsonParser.makeHttpRequest(urlInsertScore,"POST",paramscor);   //receive information form sever and put into jsonObject...

            try {

                int t=jsonObject.getInt("t");

                Log.i("alialialiAnswer2","jskjsd="+t);
                if(t==1){


                    Log.i("xsoal_server2javab", "t: " + answwerUser);


                }else{
                    // Toast.makeText(MainActivity.context,"no imfomainion", Toast.LENGTH_SHORT).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }



            return null;

        }


    }
    //*************    update scores for user that executed in alghorithm for use later   *************//

    public class class_updateAnswerScore extends AsyncTask {

        JSONParser jsonParser=new JSONParser();
        String type, username;



        public class_updateAnswerScore(String username,String type){
            this.type=type;
            this.username=username;

            Log.i("give usernameseupdate",type +":"+username+":");

        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

        }

        @Override
        protected Object doInBackground(Object[] params) {

            Log.i("select_socore_ansupdate","jskjsd");

            HashMap<String,String> paramscor=new HashMap<String , String>();

            paramscor.put("username",username);
            paramscor.put("type",type);


            Log.i("select_anser","aliii="+type+":"+usrname1);
            JSONObject jsonObject =jsonParser.makeHttpRequest(urlUpdateAnswertScore,"POST",paramscor);   //receive information form sever and put into jsonObject...

            try {

                int t=jsonObject.getInt("t");

                Log.i("alialialiAnswer2","jskjsd="+t);
                if(t==1){


                    Log.i("xsoal_serveavabupdate", "t: okok" );


                }else{
                    // Toast.makeText(MainActivity.context,"no imfomainion", Toast.LENGTH_SHORT).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }



            return null;

        }


    }

    //*************    select score for any user for sever  *************//

    public class class_selectScores extends AsyncTask {   /////baraye jostejooie moshakhasate dostan...   ////test...

        JSONParser jsonParser=new JSONParser();
        JSONArray jsonArray =null;
        String username;


        public class_selectScores(String username){
            this.username=username;

        }


        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);          ////inalazem nist baya dar text view ha inaha ra update konim.


            ////////////////
            recyclerViewScore = (RecyclerView) findViewById(R.id.rc_score);
            AdapterCardViewFrinds adapterCardView2 = new AdapterCardViewFrinds(MainActivity.context);

            AdapterCardViewScore adapterCardViewScore = new AdapterCardViewScore(MainActivity.context);  ///inja migi ke che cardi ra nemayesh dahad...
            recyclerViewScore.setAdapter(adapterCardViewScore);
            recyclerViewScore.setLayoutManager(new LinearLayoutManager((MainActivity.context)));
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.context);
            recyclerViewScore.setLayoutManager(mLayoutManager);
            recyclerViewScore.setItemAnimator(new DefaultItemAnimator());
            recyclerViewScore.addOnItemTouchListener(new RecyclerTouchListener(MainActivity.context, recyclerViewScore, new RecyclerTouchListener.ClickListener() {
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

        @Override
        protected Object doInBackground(Object[] params) {

            Log.i("alialialiinfoali2",username+":");

            HashMap<String,String> param=new HashMap<String , String>();
            param.put("username",username);


            JSONObject jsonObject =jsonParser.makeHttpRequest(urlselectScore,"POST",param);   //receive information form sever and put into jsonObject...

            try {
                scores.clear();
                int t=jsonObject.getInt("t");

                Log.i("aliiinfotselecetScores",username+"="+t);
                if(t==1){

                    jsonArray= jsonObject.getJSONArray("scores");    /// input json response["travel"] in php code ;  and give me length...

                    for(int i=0 ; i<jsonArray.length();i++){

                        JSONObject temp=jsonArray.getJSONObject(i);

                        String type=temp.getString("type");
                        String score =temp.getString("score");

                        Score_sever score_sever = new Score_sever(username,type,Double.parseDouble(score));
                        score_sever.setType(type);
                        score_sever.setScore(Double.parseDouble(score));

                        scores.add(score_sever);

                        Log.i("alialialiinfo2score",type+"="+score);

                    }

                }else{
                    //Toast.makeText(MainActivity.context,"no imfomainion", Toast.LENGTH_SHORT).show();

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.i("show2sizeScore","come here"+scores.size());


            return null;

        }


    }



}
