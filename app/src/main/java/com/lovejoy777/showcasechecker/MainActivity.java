package com.lovejoy777.showcasechecker;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    CardView card1, card2;
    EditText ETJson;
    Button button;
    Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Handle Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //card1 = (CardView) findViewById(R.id.CardView_card1);
        card1 = (CardView) findViewById(R.id.CardView1);
        card2 = (CardView) findViewById(R.id.CardView_card2);
        ETJson = (EditText) findViewById(R.id.ettextview1);
        button = (Button) findViewById(R.id.button);
        button1 = (Button) findViewById(R.id.button1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent jsonFormIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://wh0cares.github.io/submit.html"));
                startActivity(jsonFormIntent);


            }
        });

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent formactivity = new Intent(MainActivity.this, FormActivity.class);
                Bundle bndlanimation =
                        ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.anni1, R.anim.anni2).toBundle();
                startActivity(formactivity, bndlanimation);

            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String etjson = ETJson.getText().toString();

                File dir1 = new File(etjson);
                if (dir1.exists()) {

                    savePrefs("etjson", etjson);

                    Intent freeactivity = new Intent(MainActivity.this, Screen1Free.class);

                    Bundle bndlanimation =
                            ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.anni1, R.anim.anni2).toBundle();
                    startActivity(freeactivity, bndlanimation);
                } else {

                    Toast.makeText(getApplicationContext(), "enter a valid path", Toast.LENGTH_LONG).show();


                }
            }
        });






    } // ends onCreate



    public void savePrefs(String key, String value) {

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(key, value);
        edit.commit();
    }




    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.back2, R.anim.back1);
    }




}
