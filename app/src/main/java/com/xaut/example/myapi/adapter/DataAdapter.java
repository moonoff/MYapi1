package com.xaut.example.myapi.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xaut.example.myapi.R;
import com.xaut.example.myapi.utils.AppConstants;
import com.xaut.example.myapi.webs.ItemActivity;
import com.xaut.example.myapi.webs.WebViewActivity;

import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by pc on 2016/12/15.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private List<Element> dataElementList = new ArrayList<>();
    private String TAG="DataAdapter";
    Context context;
    public DataAdapter(Context context){
        this.context=context;
    }
    public void addAll(List<Element> dataElementList){
        this.dataElementList=dataElementList;
        Log.i("TAG", "addAll: "+dataElementList.size());
        notifyDataSetChanged();
    }

    public void clear() {
        this.dataElementList.clear();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_data_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Element titleElement = dataElementList.get(position);
//        Log.i(TAG, "onSuccess: img==="+titleElement.getElementsByTag("img").attr("src"));        //图片
//        Log.i(TAG, "onSuccess: title==="+titleElement.getElementsByTag("img").attr("title"));    //标题
//        Log.i(TAG, "onSuccess: divPrice==="+titleElement.getElementsByClass("divPrice").text());//价格
//        Log.i(TAG, "onSuccess: divPrice==="+titleElement.getElementsByClass("pCoupon").text());//雀雀
        Glide.with(holder.itemView.getContext()).load(dataElementList.get(position).getElementsByTag("img").attr("src")).into(holder.main_fra_list_img);
        holder.main_fra_list_text1_name.setText(titleElement.getElementsByTag("img").attr("title"));
        holder.main_fra_list_text2_changes.setText(titleElement.getElementsByClass("divPrice").text());
        holder.main_fra_list_text3_sales.setText(titleElement.getElementsByClass("pCoupon").text());

    }

    @Override
    public int getItemCount() {
        Log.i(TAG, "getItemCount: "+dataElementList.size());
        return dataElementList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

//        @BindView(R.id.main_fra_list_img)
        ImageView main_fra_list_img;
//        @BindView(R.id.main_fra_list_text1_name)
        TextView main_fra_list_text1_name;
//        @BindView(R.id.main_fra_list_text2_changes)
        TextView main_fra_list_text2_changes;
//        @BindView(R.id.main_fra_list_text3_sales)
        TextView main_fra_list_text3_sales;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            main_fra_list_img=(ImageView) itemView.findViewById(R.id.main_fra_list_img);
            main_fra_list_text1_name=(TextView)itemView.findViewById(R.id.main_fra_list_text1_name);
            main_fra_list_text2_changes=(TextView)itemView.findViewById(R.id.main_fra_list_text2_changes);
            main_fra_list_text3_sales=(TextView)itemView.findViewById(R.id.main_fra_list_text3_sales);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Element titleElement = dataElementList.get(getLayoutPosition());
                    Intent intent=new Intent(context, ItemActivity.class);
                    intent.putExtra(AppConstants.LIST_DATA,titleElement.toString());
                    context.startActivity(intent);
                }
            });
        }
    }
}
