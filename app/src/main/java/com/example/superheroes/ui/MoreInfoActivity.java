package com.example.superheroes.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.room.Database;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.superheroes.Database.AccessDatabase;
import com.example.superheroes.Database.HeroDatabase;
import com.example.superheroes.Heroes.Heroes;
import com.example.superheroes.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;
import java.util.function.Consumer;

public class MoreInfoActivity extends AppCompatActivity {
    Activity context;
    Boolean isFav;
    View rootview;
    Button bt_share;
    TextView tv_name, tv_power1, tv_power2, tv_power3, tv_power4, tv_power5;
    TextView tv_app1, tv_app2, tv_app3, tv_app4, tv_app5, tv_app6;
    TextView tv_bio1, tv_bio2, tv_bio3, tv_bio4, tv_bio5, tv_bio6, tv_bio7;
    TextView tv_work1, tv_work2, tv_connect1, tv_connect2;
    ImageView im_hero_medium, im_fav;
    Heroes hero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.content.Context context = getApplicationContext();
        setContentView(R.layout.activity_more_info);

        assignView();
        setTextAndImage();
        findFav(hero);
        bt_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View rootView = getWindow().getDecorView().findViewById(android.R.id.content);

                store(getScreenShot(rootView), UUID.randomUUID().toString());

            }
        });
        im_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccessDatabase accessDatabase = new AccessDatabase(context);
                if (isFav) {
                    accessDatabase.deleteNewUser(hero);
                    Toast.makeText(context, "Removed From Favorites", Toast.LENGTH_SHORT).show();
                    DrawableCompat.setTint(im_fav.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.purple_dark));
                    isFav = false;
                } else {
                    accessDatabase.saveNewUser(hero);
                    Toast.makeText(context, "Added to Favorites", Toast.LENGTH_SHORT).show();
                    DrawableCompat.setTint(im_fav.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.design_default_color_error));
                    isFav = true;
                }
            }
        });

    }

    private void findFav(Heroes hero) {
        AccessDatabase accessDatabase = new AccessDatabase(this.getApplicationContext());
        if (accessDatabase.isFav(hero)) {
            isFav = true;
            DrawableCompat.setTint(im_fav.getDrawable(), ContextCompat.getColor(this, R.color.design_default_color_error));
        } else isFav = false;
    }

    private void assignView() {
        bt_share = findViewById(R.id.bt_share);
        rootview = findViewById(R.id.cl_rootview);
        tv_name = findViewById(R.id.tv_heroName);
        tv_app1 = findViewById(R.id.tv_app1);
        tv_app2 = findViewById(R.id.tv_app2);
        tv_app3 = findViewById(R.id.tv_app3);
        tv_app4 = findViewById(R.id.tv_app4);
        tv_app5 = findViewById(R.id.tv_app5);
        tv_app6 = findViewById(R.id.tv_app6);
        tv_power1 = findViewById(R.id.tv_power);
        tv_power2 = findViewById(R.id.tv_power2);
        tv_power3 = findViewById(R.id.tv_power3);
        tv_power4 = findViewById(R.id.tv_power4);
        tv_power5 = findViewById(R.id.tv_power5);
        tv_bio1 = findViewById(R.id.tv_bio);
        tv_bio2 = findViewById(R.id.tv_bio2);
        tv_bio3 = findViewById(R.id.tv_bio3);
        tv_bio4 = findViewById(R.id.tv_bio4);
        tv_bio5 = findViewById(R.id.tv_bio5);
        tv_bio6 = findViewById(R.id.tv_bio6);
        tv_bio7 = findViewById(R.id.tv_bio7);
        tv_work1 = findViewById(R.id.tv_work1);
        tv_work2 = findViewById(R.id.tv_work2);
        tv_connect1 = findViewById(R.id.tv_connect1);
        tv_connect2 = findViewById(R.id.tv_connect2);
        im_hero_medium = findViewById(R.id.im_hero_medium);
        im_fav = findViewById(R.id.im_fav);


    }

    @SuppressLint("SetTextI18n")
    private void setTextAndImage() {
        Intent intent = getIntent();
        hero = intent.getParcelableExtra("HeroData");
        if (hero != null)
            Log.v("bruhuseeing", "notnull");

        tv_name.setText(hero.getName());
        tv_power1.setText(new StringBuilder().append("Combat: ").append(hero.getPowerstats().getCombat()).toString());
        tv_power2.setText("Durability: " + hero.getPowerstats().getDurability());
        tv_power3.setText("Intelligence: " + hero.getPowerstats().getIntelligence());
        tv_power4.setText("Power: " + hero.getPowerstats().getPower());
        tv_power5.setText("Speed: " + hero.getPowerstats().getSpeed());
        tv_app1.setText("Gender: " + hero.getAppearance().getGender());
        tv_app2.setText("Race: " + hero.getAppearance().getRace());
        tv_app3.setText("Eye Color: " + hero.getAppearance().getEyeColor());
        tv_app4.setText("Hair Color: " + hero.getAppearance().getHairColor());
        tv_app5.setText("Height:: " + hero.getAppearance().getHeight().get(0) + "/" + hero.getAppearance().getHeight().get(1));
        tv_app6.setText("Weight: " + hero.getAppearance().getWeight().get(0) + "/" + hero.getAppearance().getWeight().get(0));
        tv_bio1.setText("Alignment: " + hero.getBiography().getAlignment());
        tv_bio2.setText("Full Name: " + hero.getBiography().getFullName());
        tv_bio3.setText("POB: " + hero.getBiography().getPlaceOfBirth());
        tv_bio4.setText("Alter Egos: " + hero.getBiography().getAlterEgos());
        tv_bio5.setText("First Appearance: " + hero.getBiography().getFirstAppearance());
        tv_bio6.setText("Publisher: " + hero.getBiography().getPublisher());
        tv_bio7.setText("Aliases: " + hero.getBiography().getAliases().toString());
        tv_work1.setText("Occupation: " + hero.getWork().getOccupation());
        tv_work2.setText("Base: " + hero.getWork().getBase());

        tv_connect1.setText("Relatives: " + hero.getConnections().getRelatives());
        tv_connect2.setText("Group Affiliations :" + hero.getConnections().getGroupAffiliation());


        Picasso.get().load(hero.getImages().getSm()).into(im_hero_medium);

    }


    public static Bitmap getScreenShot(View view) {
        View screenView = view.getRootView();
        screenView.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(screenView.getDrawingCache());
        screenView.setDrawingCacheEnabled(false);
        return bitmap;
    }

    public  void store(Bitmap bm, String fileName) {
        try {

            File cachePath = new File(getCacheDir(), "images");
            cachePath.mkdirs(); // don't forget to make the directory
            FileOutputStream stream = new FileOutputStream(cachePath + "/image.png"); // overwrites this image every time
            bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
            stream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        shareImage();
    }

    private void shareImage(){
        File imagePath = new File(getCacheDir(), "images");
        File newFile = new File(imagePath, "image.png");
        Uri contentUri = FileProvider.getUriForFile(context, "com.example.myapp.fileprovider", newFile);

        if (contentUri != null) {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); // temp permission for receiving app to read this file
            shareIntent.setDataAndType(contentUri, getContentResolver().getType(contentUri));
            shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
            startActivity(Intent.createChooser(shareIntent, "Choose an app"));
        }

    }

}