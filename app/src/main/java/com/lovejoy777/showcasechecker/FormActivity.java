package com.lovejoy777.showcasechecker;

import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
/**
 * Created by wh0_cares on 7/30/15.
 */
public class FormActivity extends AppCompatActivity {

    EditText title, description, author, downloadlink,
            backupdownloadlink, icon, promo, screenshot_1,
            screenshot_2, screenshot_3, googleplus, version,
            donatedownload, backupdonatedownload, donateversion, wallpaper, pluginversion;

    CheckBox lollipopsupport, msupport, basicrrolollipop, basicrrom,
            layerstype2lollipop, layerstype3, layerstype3m, touchwiz,
            lg, sense, xperia, asuszenui,
            hdpi, mdpi, xhdpi, xxhdpi,
            xxxhdpi, free, donate, paid,
            doesitneedupdating, willyoubeupdating, bootanimation, font, iconpack;

    Button generate;

    String s_title, s_title2, s_description, s_author,
            s_downloadlink, s_backupdownloadlink, s_icon, s_promo,
            s_screenshot_1, s_screenshot_2, s_screenshot_3, s_googleplus,
            s_version, s_donatedownload, s_backupdonatedownload, s_donateversion, s_wallpaper, s_pluginversion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        // Handle Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_action_back);
        setSupportActionBar(toolbar);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        title = (EditText) findViewById(R.id.title);
        description = (EditText) findViewById(R.id.description);
        author = (EditText) findViewById(R.id.author);
        downloadlink = (EditText) findViewById(R.id.downloadlink);
        backupdownloadlink = (EditText) findViewById(R.id.backupdownloadlink);
        icon = (EditText) findViewById(R.id.icon);
        promo = (EditText) findViewById(R.id.promo);
        screenshot_1 = (EditText) findViewById(R.id.screenshot_1);
        screenshot_2 = (EditText) findViewById(R.id.screenshot_2);
        screenshot_3 = (EditText) findViewById(R.id.screenshot_3);
        googleplus = (EditText) findViewById(R.id.googleplus);
        version = (EditText) findViewById(R.id.version);
        donatedownload = (EditText) findViewById(R.id.donatedownload);
        backupdonatedownload = (EditText) findViewById(R.id.backupdonatedownload);
        donateversion = (EditText) findViewById(R.id.donateversion);
        wallpaper = (EditText) findViewById(R.id.wallpaper);
        pluginversion = (EditText) findViewById(R.id.pluginversion);

        lollipopsupport = (CheckBox) findViewById(R.id.lollipopsupport);
        msupport = (CheckBox) findViewById(R.id.msupport);
        basicrrolollipop = (CheckBox) findViewById(R.id.basicrrolollipop);
        basicrrom = (CheckBox) findViewById(R.id.basicrrom);
        layerstype2lollipop = (CheckBox) findViewById(R.id.layerstype2lollipop);
        layerstype3 = (CheckBox) findViewById(R.id.layerstype3);
        layerstype3m = (CheckBox) findViewById(R.id.layerstype3m);
        touchwiz = (CheckBox) findViewById(R.id.touchwiz);
        lg = (CheckBox) findViewById(R.id.lg);
        sense = (CheckBox) findViewById(R.id.sense);
        xperia = (CheckBox) findViewById(R.id.xperia);
        asuszenui = (CheckBox) findViewById(R.id.asuszenui);
        hdpi = (CheckBox) findViewById(R.id.hdpi);
        mdpi = (CheckBox) findViewById(R.id.mdpi);
        xhdpi = (CheckBox) findViewById(R.id.xhdpi);
        xxhdpi = (CheckBox) findViewById(R.id.xxhdpi);
        xxxhdpi = (CheckBox) findViewById(R.id.xxxhdpi);
        free = (CheckBox) findViewById(R.id.free);
        donate = (CheckBox) findViewById(R.id.donate);
        paid = (CheckBox) findViewById(R.id.paid);
        doesitneedupdating = (CheckBox) findViewById(R.id.doesitneedupdating);
        willyoubeupdating = (CheckBox) findViewById(R.id.willyoubeupdating);
        bootanimation = (CheckBox) findViewById(R.id.bootanimation);
        font = (CheckBox) findViewById(R.id.font);
        iconpack = (CheckBox) findViewById(R.id.iconpack);

        generate = (Button) findViewById(R.id.generate);
    }

    public void send(View v) {
        if (author.getText().toString().trim().length() > 0) {
            String nospace = author.getText().toString();
            nospace = nospace.replaceAll(" ", "_");
            s_title2 = nospace;
            //s_author = author.getText().toString();
        } else {
            s_title2 = "false";
        }
        File file = new File(Environment.getExternalStorageDirectory(), s_title2 + ".json");
        try {
            FileOutputStream out = new FileOutputStream(file);
            writeJson(out);
            View coordinatorLayoutView = findViewById(R.id.snackbar);
            Snackbar.make(coordinatorLayoutView, "Created /sdcard/" + s_title2 + ".json", Snackbar.LENGTH_LONG)
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
        if (title.getText().toString().trim().length() > 0) {
            s_title = title.getText().toString();
        } else {
            s_title = "false";
        }
        if (description.getText().toString().trim().length() > 0) {
            s_description = description.getText().toString();
        } else {
            s_description = "false";
        }
        if (author.getText().toString().trim().length() > 0) {
            s_author = author.getText().toString();
        } else {
            s_author = "false";
        }
        if (downloadlink.getText().toString().trim().length() > 0) {
            s_downloadlink = downloadlink.getText().toString();
        } else {
            s_downloadlink = "false";
        }
        if (backupdownloadlink.getText().toString().trim().length() > 0) {
            s_backupdownloadlink = backupdownloadlink.getText().toString();
        } else {
            s_backupdownloadlink = "false";
        }
        if (icon.getText().toString().trim().length() > 0) {
            s_icon = icon.getText().toString();
        } else {
            s_icon = "false";
        }
        if (promo.getText().toString().trim().length() > 0) {
            s_promo = promo.getText().toString();
        } else {
            s_promo = "false";
        }
        if (screenshot_1.getText().toString().trim().length() > 0) {
            s_screenshot_1 = screenshot_1.getText().toString();
        } else {
            s_screenshot_1 = "false";
        }
        if (screenshot_2.getText().toString().trim().length() > 0) {
            s_screenshot_2 = screenshot_2.getText().toString();
        } else {
            s_screenshot_2 = "false";
        }
        if (screenshot_3.getText().toString().trim().length() > 0) {
            s_screenshot_3 = screenshot_3.getText().toString();
        } else {
            s_screenshot_3 = "false";
        }
        if (googleplus.getText().toString().trim().length() > 0) {
            s_googleplus = googleplus.getText().toString();
        } else {
            s_googleplus = "false";
        }
        if (version.getText().toString().trim().length() > 0) {
            s_version = version.getText().toString();
        } else {
            s_version = "false";
        }
        if (donatedownload.getText().toString().trim().length() > 0) {
            s_donatedownload = donatedownload.getText().toString();
        } else {
            s_donatedownload = "false";
        }
        if (backupdonatedownload.getText().toString().trim().length() > 0) {
            s_backupdonatedownload = backupdonatedownload.getText().toString();
        } else {
            s_backupdonatedownload = "false";
        }
        if (donateversion.getText().toString().trim().length() > 0) {
            s_donateversion = donateversion.getText().toString();
        } else {
            s_donateversion = "false";
        }
        if (wallpaper.getText().toString().trim().length() > 0) {
            s_wallpaper = wallpaper.getText().toString();
        } else {
            s_wallpaper = "false";
        }
        if (pluginversion.getText().toString().trim().length() > 0) {
            s_pluginversion = pluginversion.getText().toString();
        } else {
            s_pluginversion = "false";
        }
        String s_lollipopsupport = String.valueOf(lollipopsupport.isChecked());
        String s_msupport = String.valueOf(msupport.isChecked());
        String s_basicrrolollipop = String.valueOf(basicrrolollipop.isChecked());
        String s_basicrrom = String.valueOf(basicrrom.isChecked());
        String s_layerstype2lollipop = String.valueOf(layerstype2lollipop.isChecked());
        String s_layerstype3 = String.valueOf(layerstype3.isChecked());
        String s_layerstype3m = String.valueOf(layerstype3m.isChecked());
        String s_touchwiz = String.valueOf(touchwiz.isChecked());
        String s_lg = String.valueOf(lg.isChecked());
        String s_sense = String.valueOf(sense.isChecked());
        String s_xperia = String.valueOf(xperia.isChecked());
        String s_asuszenui = String.valueOf(asuszenui.isChecked());
        String s_hdpi = String.valueOf(hdpi.isChecked());
        String s_mdpi = String.valueOf(mdpi.isChecked());
        String s_xhdpi = String.valueOf(xhdpi.isChecked());
        String s_xxhdpi = String.valueOf(xxhdpi.isChecked());
        String s_xxxhdpi = String.valueOf(xxxhdpi.isChecked());
        String s_free = String.valueOf(free.isChecked());
        String s_donate = String.valueOf(donate.isChecked());
        String s_paid = String.valueOf(paid.isChecked());
        String s_doesitneedupdating = String.valueOf(doesitneedupdating.isChecked());
        String s_willyoubeupdating = String.valueOf(willyoubeupdating.isChecked());
        String s_bootanimation = String.valueOf(bootanimation.isChecked());
        String s_font = String.valueOf(font.isChecked());
        String s_iconpack = String.valueOf(iconpack.isChecked());

        writer.name("title").value(s_title);
        writer.name("description").value(s_description);
        writer.name("author").value(s_author);
        writer.name("link").value(s_downloadlink);
        writer.name("backup_link").value(s_backupdownloadlink);
        writer.name("icon").value(s_icon);
        writer.name("promo").value(s_promo);
        writer.name("screenshot_1").value(s_screenshot_1);
        writer.name("screenshot_2").value(s_screenshot_2);
        writer.name("screenshot_3").value(s_screenshot_3);
        writer.name("googleplus").value(s_googleplus);
        writer.name("version").value(s_version);
        writer.name("donate_link").value(s_donatedownload);
        writer.name("backup_donate_link").value(s_backupdonatedownload);
        writer.name("donate_version").value(s_donateversion);
        writer.name("bootani").value(s_bootanimation);
        writer.name("font").value(s_font);
        writer.name("wallpaper").value(s_wallpaper);
        writer.name("plugin_version").value(s_pluginversion);
        writer.name("for_L").value(s_lollipopsupport);
        writer.name("for_M").value(s_msupport);
        writer.name("basic").value(s_basicrrolollipop);
        writer.name("basic_m").value(s_basicrrom);
        writer.name("type2").value(s_layerstype2lollipop);
        writer.name("type3").value(s_layerstype3);
        writer.name("type3_m").value(s_layerstype3m);
        writer.name("touchwiz").value(s_touchwiz);
        writer.name("lg").value(s_lg);
        writer.name("sense").value(s_sense);
        writer.name("xperia").value(s_xperia);
        writer.name("zenui").value(s_asuszenui);
        writer.name("hdpi").value(s_hdpi);
        writer.name("mdpi").value(s_mdpi);
        writer.name("xhdpi").value(s_xhdpi);
        writer.name("xxhdpi").value(s_xxhdpi);
        writer.name("xxxhdpi").value(s_xxxhdpi);
        writer.name("free").value(s_free);
        writer.name("donate").value(s_donate);
        writer.name("paid").value(s_paid);
        writer.name("needs_update").value(s_doesitneedupdating);
        writer.name("will_update").value(s_willyoubeupdating);
        writer.name("iconpack").value(s_iconpack);
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
}
