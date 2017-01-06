package com.xaut.example.myapi.webs;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.xaut.example.myapi.R;
import com.xaut.example.myapi.utils.AppConstants;
import com.xaut.example.myapi.webs.database.Items;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ItemActivity extends AppCompatActivity {
    @BindView(R.id.activity_item_img)
    ImageView activity_item_img;
    @BindView(R.id.activity_item_text1_name)
    TextView activity_item_text1_name;
    @BindView(R.id.activity_item_text2_changes)
    TextView activity_item_text2_changes;
    @BindView(R.id.activity_item_text3_sales)
    TextView activity_item_text3_sales;
    @BindView(R.id.activity_item_but1)
    Button activity_item_but1;
    @BindView(R.id.activity_item_but2)
    Button activity_item_but2;
    public String TAG="ItemActivity";
    String s5;
    String s6;
    String s4;
    String s3;
    String s2;
    String s1;
    private Realm realm;
    List<Items> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        ButterKnife.bind(this);

//        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
//
//        // Clear the realm from last time
//        Realm.deleteRealm(realmConfiguration);
//
//        // Create a new empty instance of Realm
        realm = Realm.getDefaultInstance();
        items= new ArrayList<>();

        String string=this.getIntent().getStringExtra(AppConstants.LIST_DATA);
        Document document= Jsoup.parse(string);
        s1=document.getElementsByTag("img").attr("src");
        s2=document.getElementsByTag("img").attr("title");
        s3=document.getElementsByClass("divPrice").text();
        s4 =document.getElementsByClass("pCoupon").text();
        Elements elements=document.getElementsByClass("divGo");
        s5=elements.get(0).getElementsByTag("a").get(0).attr("href");
        s6=elements.get(0).getElementsByTag("a").get(1).attr("href");

        Glide.with(ItemActivity.this).load(s1).into(activity_item_img);
        activity_item_text1_name.setText(s2);
        activity_item_text2_changes.setText(s3);
        activity_item_text3_sales.setText(s4);
        activity_item_but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(s5);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        activity_item_but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ItemActivity.this,WebViewActivity.class).putExtra(AppConstants.WEB_URL,s6));
            }
        });
    }

    private void loadCities() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Items myItems = realm.createObject(Items.class);
                myItems.setImg(s1);
                myItems.setName(s2);
                myItems.setPrice(s3);
                myItems.setYouhui(s4);
                myItems.setBut1(s5);
                myItems.setBut2(s6);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.items,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();
        if (id==R.id.action_fla){
            loadCities();
            item.setIcon(R.drawable.ic_favorite_black_24dp);
            Toast.makeText(this, "已收藏", Toast.LENGTH_SHORT).show();
        }else if(id==R.id.action_send){

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
