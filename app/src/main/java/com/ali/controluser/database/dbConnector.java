package com.ali.controluser.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Ali_Najafi on 15/02/2017.
 */
public class dbConnector  extends SQLiteOpenHelper {

    public dbConnector(Context context, String name, SQLiteDatabase.CursorFactory factory,
                       int version) {
        super(context, name, factory, version);
        Log.i("dbbbbb","Connect...");


        create_Tables() ;
    }



    @Override
    public void onCreate(SQLiteDatabase arg0) {


    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub

    }



    private void create_Tables() {
       /* Log.i("ali_tag" , "creating tables");

        String query =
                "CREATE TABLE IF NOT EXISTS soalat (" +
                        "id       TEXT                                   , " +
                        "type    TEXT                                   , " +
                        "qustion    TEXT                                   , " +
                        "g1    TEXT                                 , " +
                        "g2    TEXT                                   , " +
                        "g3    TEXT                               , " +
                        "g4    TEXT                                   , " +
                        "answer    TEXT                                    " +
                        "); ";
        String query1 =
                "CREATE TABLE IF NOT EXISTS type (" +
                        "type       TEXT                                   , " +
                        "count    INTEGER                                  , " +
                        "slove    BOOLEAN                                   " +
                        "); ";

        this.exec(query);
        this.exec(query1);

*/

       // ******************************************************

        String query3 =
                "CREATE TABLE IF NOT EXISTS s_algorithm (" +
                        "type       TEXT                                   , " +
                        "id    TEXT                                   , " +
                        "w1    DOUBLE                                   , " +
                        "w2         DOUBLE                                , " +
                        "w3         DOUBLE                                   , " +
                        "w4        DOUBLE                               " +

                        "); ";
        String query4 =
                "CREATE TABLE IF NOT EXISTS s_answerd_user (" +
                        "username       TEXT                                   , " +
                        "type    TEXT                                 " +
                        "); ";
        String query5 =
                "CREATE TABLE IF NOT EXISTS s_firends (" +
                        "username       TEXT                                   , " +
                        "frind_mob    TEXT                                 " +
                        "); ";

        String query6 =
                "CREATE TABLE IF NOT EXISTS s_frist_user (" +
                        "username       TEXT                                   , " +
                        "moblie    TEXT                                  , " +
                        "register    INT                                   " +
                        "); ";
        String query7 =
                "CREATE TABLE IF NOT EXISTS s_javab (" +
                        "username       TEXT                                   , " +
                        "id           TEXT                                   , " +
                        "type          TEXT                                   , " +
                        "answer         INT                                 " +

                        "); ";
        String query8 =
                "CREATE TABLE IF NOT EXISTS s_score (" +
                        "username       TEXT                                   , " +
                        "type    TEXT                                  , " +
                        "score    DOUBLE                                   " +
                        "); ";

        String query9 =
                "CREATE TABLE IF NOT EXISTS s_soalat (" +
                        "id       TEXT                                   , " +
                        "type    TEXT                                   , " +
                        "qustion    TEXT                                   , " +
                        "g1    TEXT                                 , " +
                        "g2    TEXT                                   , " +
                        "g3    TEXT                               , " +
                        "g4    TEXT                                    " +
                        "); ";

        String query10 =
                "CREATE TABLE IF NOT EXISTS s_type (" +
                        "type       TEXT                                   , " +
                        "counter    INT                                   " +
                        "); ";

        String query11 =
                "CREATE TABLE IF NOT EXISTS s_users (" +
                        "username       TEXT                                   , " +
                        "name    TEXT                                   , " +
                        "family    TEXT                                   , " +
                        "fathername    TEXT                                 , " +
                        "code   TEXT                                   , " +
                        "ostan    TEXT                               , " +
                        "city    TEXT                                   , " +
                        "address    TEXT                                   , " +
                        "moblie    TEXT                                    " +
                        "); ";
        String query12 =
                "CREATE TABLE IF NOT EXISTS s_sms (" +
                        "number       TEXT    UNIQUE                               , " +
                        "counter   INT                                  " +
                        "); ";

        this.exec(query3);
        this.exec(query4);
        this.exec(query5);
        this.exec(query6);
        this.exec(query7);
        this.exec(query8);
        this.exec(query9);
        this.exec(query10);
        this.exec(query11);
        this.exec(query12);

    }



    public Boolean exec(String query) {

        try {
            this.getWritableDatabase().execSQL(query);
            return true;
        } catch (Exception e) {
            return false;
        }

    }


    public Boolean insert(String table , ContentValues values) {


        try {
            this.getWritableDatabase().insert(table, null , values);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
    public Boolean update(String table , ContentValues values,String where,String []arg) {


        try {
            this.getWritableDatabase().update(table,values,where,arg);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public Cursor select (String query) {

        Cursor c = null;
        c =  this.getWritableDatabase().rawQuery(query, null);

        return c;
    }




}
