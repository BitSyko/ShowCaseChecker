package com.lovejoy777.showcasechecker;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.nononsenseapps.filepicker.FilePickerActivity;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    CardView card1, card2;
    Button button1, button2;
    private static final int CODE_SD = 0;
    private static final int CODE_SD2 = 1;
    String file1, file2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Handle Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        card1 = (CardView) findViewById(R.id.CardView1);
        card2 = (CardView) findViewById(R.id.CardView2);
        button1 = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
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
                Intent i = new Intent(MainActivity.this, FilePickerActivity.class);
                i.putExtra(FilePickerActivity.EXTRA_ALLOW_MULTIPLE, false);
                i.putExtra(FilePickerActivity.EXTRA_ALLOW_CREATE_DIR, false);
                i.putExtra(FilePickerActivity.EXTRA_MODE, FilePickerActivity.MODE_FILE);
                i.putExtra(FilePickerActivity.EXTRA_START_PATH, Environment.getExternalStorageDirectory().getPath());
                startActivityForResult(i, CODE_SD);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, FilePickerActivity.class);
                i.putExtra(FilePickerActivity.EXTRA_ALLOW_MULTIPLE, false);
                i.putExtra(FilePickerActivity.EXTRA_ALLOW_CREATE_DIR, false);
                i.putExtra(FilePickerActivity.EXTRA_MODE, FilePickerActivity.MODE_FILE);
                i.putExtra(FilePickerActivity.EXTRA_START_PATH, Environment.getExternalStorageDirectory().getPath());
                startActivityForResult(i, CODE_SD2);
            }
        });

    } // ends onCreate

    private void alertDialog(String message) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        Drawable icon = ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_info_white_24dp).mutate();
        icon.setColorFilter(getResources().getColor(R.color.textColorPrimary), PorterDuff.Mode.MULTIPLY);
        dialog.setTitle("Instructions")
                .setIcon(icon)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                    }
                }).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_info:
                alertDialog(getString(R.string.instructions));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.back2, R.anim.back1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CODE_SD && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            file1 = uri.toString();
            selectedFile();
        } else if (requestCode == CODE_SD2 && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            file2 = uri.toString();
            editFile();
        }
    }

    public void selectedFile() {
        String file = file1.replace("file:///storage/emulated/0/", "/sdcard/");
        File dir1 = new File(file);
        if (dir1.exists()) {
            Intent freeactivity = new Intent(MainActivity.this, Screen1Free.class);
            freeactivity.putExtra("file", file);
            Bundle bndlanimation =
                    ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.anni1, R.anim.anni2).toBundle();
            startActivity(freeactivity, bndlanimation);
        }
    }

    public void editFile() {
        String file = file2.replace("file:///storage/emulated/0/", "/sdcard/");
        File dir1 = new File(file);
        if (dir1.exists()) {
            Intent formactivity = new Intent(MainActivity.this, FormActivity.class);
            formactivity.putExtra("code", CODE_SD2);
            formactivity.putExtra("file", file);
            Bundle bndlanimation =
                    ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.anni1, R.anim.anni2).toBundle();
            startActivity(formactivity, bndlanimation);
        }
    }
}