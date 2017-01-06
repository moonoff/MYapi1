package com.xaut.example.myapi.webs;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xaut.example.myapi.R;
import com.xaut.example.myapi.adapter.DataAdapter;
import com.xaut.example.myapi.webs.database.Items;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class Main2Activity extends AppCompatActivity {

    private String TAG="Main2Activity";
    List<Items> result;
    @BindView(R.id.main2_recy)
    RecyclerView main2_recy;
    @BindView(R.id.main2_text)
    TextView main2_text;
    Main2DataAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        result=new ArrayList<>();

        Log.i(TAG, "onCreate: "+result.size());
        if (result.size()==0){
            main2_text.setVisibility(View.VISIBLE);
            main2_recy.setVisibility(View.INVISIBLE);
        }
        main2_text.setVisibility(View.GONE);
        main2_recy.setVisibility(View.VISIBLE);
        main2_recy.setLayoutManager(new LinearLayoutManager(this));
        adapter=new Main2DataAdapter();

        main2_recy.setAdapter(adapter);
//        qqqq();
    }
    Realm realm;
    public RealmResults<Items> qqqq(){

//        adapter.addData();

        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    public class Main2DataAdapter extends RecyclerView.Adapter<Main2DataAdapter.ViewHolder>{

        List <Items> result2;


        public Main2DataAdapter(){
            result2=new ArrayList<>();
            addData();
        }
        public void  addData(){
          realm=Realm.getDefaultInstance();;
            RealmQuery<Items> query = realm.where(Items.class);
// Execute the query:
            RealmResults<Items> result1 = query.findAll();

// Or alternatively do the same all at once (the "Fluent interface"):
           result2 = realm.where(Items.class)
                    .findAll();
            Log.i(TAG, "addData: "+result2.size());
//            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view=LayoutInflater.from(Main2Activity.this).inflate(R.layout.main2_item, parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.main2_item_text.setText(result2.get(position).getName());

        }

        @Override
        public int getItemCount() {
            Log.i(TAG, "getItemCount: "+result2.size());
            return result2.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
//            @BindView(R.id.main2_item_img)
            ImageView main2_item_img;
            TextView main2_item_text;

            public ViewHolder(View itemView) {
                super(itemView);
//                ButterKnife.bind(this,itemView);
                main2_item_img=(ImageView) itemView.findViewById(R.id.main2_item_img);
                main2_item_text=(TextView)itemView.findViewById(R.id.main2_item_text);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                main2_item_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        main2_item_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite_black_24dp));
                    }
                });
            }
        }
    }
}
