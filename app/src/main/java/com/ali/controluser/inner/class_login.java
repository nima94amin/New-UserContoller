package com.ali.controluser.inner;

import android.os.AsyncTask;
import android.util.Log;

import com.ali.controluser.database.JSONParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Ali_Najafi on 3/21/2017.
 */

public class class_login extends AsyncTask {

    JSONParser jsonParser=new JSONParser();
    JSONArray jsonArray =null;
    String number, counter;
    private  final String url="http://aliexamination.ir/addSms.php";
    public class_login(String number,String counter){
          this.number=number;
        this.counter=counter;

    }



    @Override
    protected Object doInBackground(Object[] params) {

        Log.i("alialiali","jskjsd");

        HashMap<String,String> param=new HashMap<String , String>();

        param.put("number",number);
        param.put("counter",counter);
        JSONObject jsonObject =jsonParser.makeHttpRequest(url,"POST",param);   //receive information form sever and put into jsonObject...

        try {

            int t=jsonObject.getInt("t");

            Log.i("alialialiadd","jskjsd="+t);
            if(t==1){

                //Toast.makeText(MainActivity.context, "این شماره به لیست ارسال sms اضافه شد.", Toast.LENGTH_LONG).show();

            }else{
                //Toast.makeText(MainActivity.context,"no imfomainion", Toast.LENGTH_SHORT).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;

    }


}


