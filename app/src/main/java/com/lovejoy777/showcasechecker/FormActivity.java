package com.lovejoy777.showcasechecker;

import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.ParseException;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.JsonWriter;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by wh0_cares on 7/30/15.
 */
public class FormActivity extends AppCompatActivity {
    Button generate;
    String[] strings = new String[]{"title", "description", "author", "link", "backup_link", "icon",
            "promo", "screenshot_1", "screenshot_2", "screenshot_3", "googleplus", "version", "donate_link",
            "donate_version", "wallpaper", "plugin_version", "toolbar_background_color", "for_L", "for_M",
            "basic", "basic_m", "type2", "type3", "type3_m", "touchwiz", "lg", "sense", "xperia", "zenui",
            "hdpi", "mdpi", "xhdpi", "xxhdpi", "xxxhdpi", "free", "donate", "paid", "needs_update", "will_update",
            "bootani", "font", "iconpack"};
    ArrayList<String> values = new ArrayList<String>(Arrays.asList(strings));
    ArrayList<String> values2 = new ArrayList<String>();
    ArrayList<String> values3 = new ArrayList(values.subList(0, 17));
    ArrayList<String> values4 = new ArrayList(values.subList(17, 42));
    public final int[] EditText = {R.id.title, R.id.description, R.id.author, R.id.link,
            R.id.backup_link, R.id.icon, R.id.promo, R.id.screenshot_1, R.id.screenshot_2, R.id.screenshot_3,
            R.id.googleplus, R.id.version, R.id.donate_link, R.id.donate_version, R.id.wallpaper, R.id.plugin_version,
            R.id.toolbar_background_color};
    public final int[] CheckBox = {R.id.for_L, R.id.for_M, R.id.basic, R.id.basic_m,
            R.id.type2, R.id.type3, R.id.type3_m, R.id.touchwiz, R.id.lg, R.id.sense,
            R.id.xperia, R.id.zenui, R.id.hdpi, R.id.mdpi, R.id.xhdpi, R.id.xxhdpi, R.id.xxxhdpi, R.id.free,
            R.id.donate, R.id.paid, R.id.needs_update, R.id.will_update, R.id.bootani, R.id.font, R.id.iconpack};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_action_back);
        setSupportActionBar(toolbar);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        generate = (Button) findViewById(R.id.generate);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int requestCode = extras.getInt("code");
            if (requestCode == 1) {
                editJson();
                getSupportActionBar().setTitle(R.string.editjson);
                generate.setText(getString(R.string.edit));
            }
        }
    }

    public void send(View v) {
        values2.clear();
        for (int id : EditText) {
            EditText t = (EditText) findViewById(id);
            values2.add((t.getText().toString().trim().length() > 0) ? t.getText().toString() : "false");
        }
        for (int id2 : CheckBox) {
            CheckBox c = (CheckBox) findViewById(id2);
            values2.add(String.valueOf(c.isChecked()));
        }
        String title = values2.get(0);
        title = title.replaceAll(" ", "_");
        String title2 = title;
        values2.set(0, title2);
        File file = new File(Environment.getExternalStorageDirectory(), title2 + ".json");
        try {
            FileOutputStream out = new FileOutputStream(file);
            writeJson(out);
            View coordinatorLayoutView = findViewById(R.id.snackbar);
            Snackbar.make(coordinatorLayoutView, "Created /sdcard/" + title2 + ".json", Snackbar.LENGTH_LONG)
                    .show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeJson(OutputStream out) throws IOException {
        JsonWriter writer = new JsonWriter(new OutputStreamWriter(out, "UTF-8"));
        writer.setIndent("    ");
        jsonFinal(writer);
    }

    public void jsonFinal(JsonWriter writer) throws IOException {
        writer.beginObject();
        for (int i = 0; i < values.size(); i++) {
            String json1 = values.get(i);
            String json2 = values2.get(i);
            writer.name(json1).value(json2);
        }
        writer.endObject();
        writer.close();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_info:
                alertDialog(getString(R.string.instructions));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void alertDialog(String message) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(FormActivity.this);
        Drawable icon = ContextCompat.getDrawable(FormActivity.this, R.drawable.ic_info_white_24dp).mutate();
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

    public void editJson() {
        try {
            String file = getIntent().getExtras().getString("file");
            Toast.makeText(getApplicationContext(), file, Toast.LENGTH_LONG).show();
            File testjson = new File(file);
            FileInputStream stream = new FileInputStream(testjson);
            String jString = null;
            try {
                FileChannel fc = stream.getChannel();
                MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
                jString = Charset.defaultCharset().decode(bb).toString();
            } finally {
                stream.close();
            }
            JSONObject object = new JSONObject(jString);
            Iterator<String> iter = object.keys();
            while (iter.hasNext()) {
                String key = iter.next();
                int keyID = getResources().getIdentifier(key, "id", getPackageName());
                if (values3.contains(key)) {
                    Object value = object.get(key);
                    String jsonvalue = value.toString();
                    ((EditText) findViewById(keyID)).setText(jsonvalue, TextView.BufferType.EDITABLE);
                } else if (values4.contains(key)) {
                    Object value = object.get(key);
                    String jsonvalue = value.toString();
                    CheckBox cb = (CheckBox) findViewById(keyID);
                    boolean c;
                    c = jsonvalue.equals("true") ? true : false;
                    cb.setChecked(c);
                }
            }
        } catch (ParseException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}