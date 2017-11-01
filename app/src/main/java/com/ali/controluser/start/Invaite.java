package com.ali.controluser.start;

import android.app.Activity;
import android.os.Bundle;

import com.ali.controluser.R;

public class Invaite extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invaite);

        String colors[] = {"Red","Blue","White","Yellow","Black", "Green","Purple","Orange","Grey"};

//

///////////felana araye sabetha khob ast...
        /*MaterialSpinner spinner2 = (MaterialSpinner) findViewById(R.id.spinner);
        spinner2.setItems(colors);
        spinner2.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });*/
    }
}
