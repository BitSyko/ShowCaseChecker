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

/**
 * Created by wh0_cares on 7/30/15.
 */
public class FormActivity extends AppCompatActivity {
    EditText title, description, author, downloadlink,
            backupdownloadlink, icon, promo, screenshot_1,
            screenshot_2, screenshot_3, googleplus, version,
            donatedownload, donateversion, wallpaper, pluginversion, color;

    CheckBox lollipopsupport, msupport, basicrrolollipop, basicrrom,
            layerstype2lollipop, layerstype3, layerstype3m, touchwiz,
            lg, sense, xperia, asuszenui,
            hdpi, mdpi, xhdpi, xxhdpi,
            xxxhdpi, free, donate, paid,
            doesitneedupdating, willyoubeupdating, bootanimation, font, iconpack;

    Button generate;

    String [] strings = new String [] {"title", "description", "author", "link", "backup_link", "icon",
            "promo", "screenshot_1", "screenshot_2", "screenshot_3", "googleplus", "version", "donate_link",
            "donate_version", "wallpaper", "plugin_version", "toolbar_background_color", "for_L", "for_M",
            "basic", "basic_m", "type2", "type3", "type3_m", "touchwiz", "lg", "sense", "xperia", "zenui",
            "hdpi", "mdpi", "xdpi", "xxhdpi", "xxxhdpi", "free", "donate", "paid", "needs_update", "will_update",
            "bootani", "font", "iconpack"};

    ArrayList<String> values = new ArrayList<String>(Arrays.asList(strings));
    ArrayList<String> values2 = new ArrayList<String>();

    public final int[] EditText = { R.id.title, R.id.description, R.id.author, R.id.downloadlink,
            R.id.backupdownloadlink, R.id.icon, R.id.promo, R.id.screenshot_1, R.id.screenshot_2, R.id.screenshot_3,
            R.id.googleplus, R.id.version, R.id.donatedownload, R.id.donateversion, R.id.wallpaper, R.id.pluginversion,
            R.id.color};
    public final int[] CheckBox = { R.id.lollipopsupport, R.id.msupport, R.id.basicrrolollipop, R.id.basicrrom,
            R.id.layerstype2lollipop, R.id.layerstype3, R.id.layerstype3m, R.id.touchwiz, R.id.lg, R.id.sense,
            R.id.xperia, R.id.asuszenui, R.id.hdpi, R.id.mdpi, R.id.xhdpi, R.id.xxhdpi, R.id.xxxhdpi, R.id.free,
            R.id.donate, R.id.paid, R.id.doesitneedupdating, R.id.willyoubeupdating, R.id.bootanimation, R.id.font, R.id.iconpack};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        // Handle Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_action_back);
        setSupportActionBar(toolbar);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        generate = (Button) findViewById(R.id.generate);

        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            int requestCode = extras.getInt("code");
            if (requestCode == 1){
                editJson();
                getSupportActionBar().setTitle(R.string.editjson);
                generate.setText(getString(R.string.edit));
            }
        }
    }

    public void send(View v) {
        values2.clear();
        for(int id : EditText){
            EditText t = (EditText) findViewById(id);
            values2.add((t.getText().toString().trim().length() > 0) ? t.getText().toString() : "false");
        }
        for(int id2 : CheckBox){
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
        //System.out.println("ArrayListValues " + title2);
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

    private void alertDialog( String message ) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(FormActivity.this);
        Drawable icon = ContextCompat.getDrawable(FormActivity.this, R.drawable.ic_info_white_24dp).mutate();
        icon.setColorFilter(getResources().getColor(R.color.textColorPrimary), PorterDuff.Mode.MULTIPLY );
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
            }
            finally {
                stream.close();
            }

            JSONObject object = new JSONObject(jString);
            title.setText(object.getString("title"));
            description.setText(object.getString("description"));
            author.setText(object.getString("author"));
            downloadlink.setText(object.getString("link"));
            backupdownloadlink.setText(object.getString("backup_link"));
            icon.setText(object.getString("icon"));
            promo.setText(object.getString("promo"));
            screenshot_1.setText(object.getString("screenshot_1"));
            screenshot_2.setText(object.getString("screenshot_2"));
            screenshot_3.setText(object.getString("screenshot_3"));
            googleplus.setText(object.getString("googleplus"));
            version.setText(object.getString("version"));
            donatedownload.setText(object.getString("donate_link"));
            donateversion.setText(object.getString("donate_version"));
            wallpaper.setText(object.getString("wallpaper"));
            pluginversion.setText(object.getString("plugin_version"));
            color.setText(object.getString("toolbar_background_color"));
            if (object.getString("for_L").equals("true"))
            {
                lollipopsupport.setChecked(true);
            }
            if (object.getString("for_M").equals("true"))
            {
                msupport.setChecked(true);
            }
            if (object.getString("basic").equals("true"))
            {
                basicrrolollipop.setChecked(true);
            }
            if (object.getString("basic_m").equals("true"))
            {
                basicrrom.setChecked(true);
            }
            if (object.getString("type2").equals("true"))
            {
                layerstype2lollipop.setChecked(true);
            }
            if (object.getString("type3").equals("true"))
            {
                layerstype3.setChecked(true);
            }
            if (object.getString("type3_m").equals("true"))
            {
                layerstype3m.setChecked(true);
            }
            if (object.getString("touchwiz").equals("true"))
            {
                touchwiz.setChecked(true);
            }
            if (object.getString("lg").equals("true"))
            {
                lg.setChecked(true);
            }
            if (object.getString("sense").equals("true"))
            {
                sense.setChecked(true);
            }
            if (object.getString("xperia").equals("true"))
            {
                xperia.setChecked(true);
            }
            if (object.getString("zenui").equals("true"))
            {
                asuszenui.setChecked(true);
            }
            if (object.getString("hdpi").equals("true"))
            {
                hdpi.setChecked(true);
            }
            if (object.getString("mdpi").equals("true"))
            {
                mdpi.setChecked(true);
            }
            if (object.getString("xhdpi").equals("true"))
            {
                xhdpi.setChecked(true);
            }
            if (object.getString("xxhdpi").equals("true"))
            {
                xxhdpi.setChecked(true);
            }
            if (object.getString("xxxhdpi").equals("true"))
            {
                xxxhdpi.setChecked(true);
            }
            if (object.getString("free").equals("true"))
            {
                free.setChecked(true);
            }
            if (object.getString("donate").equals("true"))
            {
                donate.setChecked(true);
            }
            if (object.getString("paid").equals("true"))
            {
                paid.setChecked(true);
            }
            if (object.getString("needs_update").equals("true"))
            {
                doesitneedupdating.setChecked(true);
            }
            if (object.getString("will_update").equals("true"))
            {
                willyoubeupdating.setChecked(true);
            }
            if (object.getString("bootani").equals("true"))
            {
                bootanimation.setChecked(true);
            }
            if (object.getString("font").equals("true"))
            {
                font.setChecked(true);
            }
            if (object.getString("iconpack").equals("true")) {
                iconpack.setChecked(true);
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
