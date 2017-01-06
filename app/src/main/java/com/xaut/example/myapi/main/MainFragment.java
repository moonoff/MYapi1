package com.xaut.example.myapi.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.xaut.example.myapi.MainActivity;
import com.xaut.example.myapi.R;
import com.xaut.example.myapi.adapter.DataAdapter;
import com.xaut.example.myapi.api.AppClient;
import com.xaut.example.myapi.api.MyApiStore;
import com.xaut.example.myapi.api.RetrofitCallback;
import com.xaut.example.myapi.utils.ToastUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by pc on 2016/12/14.
 */

public class MainFragment extends Fragment implements MainFragmentContact.View {
    private String TAG = "MainFragment";
    public MyApiStore apiStores = AppClient.retrofit().create(MyApiStore.class);
    //    @Named("local")
    @Inject
    MainFragmentContact.Presenter mainPresenter;
    @Inject
    ToastUtil toastUtil;
    //    @BindView(R.id.pullLoadMoreRecyclerView)
    PullLoadMoreRecyclerView pullLoadMoreRecyclerView;

    private MainFragmentComponent mainFragmentComponent;
    private DataAdapter dataAdapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() instanceof MainActivity) {
            mainFragmentComponent = ((MainActivity) getActivity()).getMainComponent().mainFragmentComponent();
            mainFragmentComponent.inject(this);
        }
        mainPresenter.setView(this);
        mainPresenter.toastButtonClick();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        pullLoadMoreRecyclerView = (PullLoadMoreRecyclerView) view.findViewById(R.id.pullLoadMoreRecyclerView);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    int page = 1;

    public void initView() {
        pullLoadMoreRecyclerView.setStaggeredGridLayout(2);
        pullLoadMoreRecyclerView.setRefreshing(true);
//        pullLoadMoreRecyclerView.setLinearLayout();
        dataAdapter = new DataAdapter(getActivity());
        pullLoadMoreRecyclerView.setAdapter(dataAdapter);
        pullLoadMoreRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                page = 1;
                mainPresenter.loadData(page);
            }

            @Override
            public void onLoadMore() {
                page++;
                mainPresenter.loadData(page);
            }
        });
        loadMyData(page);
    }


    @Override
    public void loadMyData(int page) {
        Call<ResponseBody> call;
        Log.i(TAG, "loadMyData: " + (page == 1));
        switch (MainActivity.myenum) {
            case nav_first:
                if (page == 1) {
                    call = apiStores.loadTheHome();
                } else {
                    call = apiStores.loadTheHome(page);
                    Log.i(TAG, "loadMyData: " + call.toString());
                }
                break;
            case nav_clothing:
                if (page == 1) {
                    call = apiStores.loadCloth(1);
                } else {
                    call = apiStores.loadCloth(1,page);
                    Log.i(TAG, "loadMyData: " + call.toString());
                }
                break;
            case nav_infant:
                if (page == 1) {
                    call = apiStores.loadCloth(2);
                } else {
                    call = apiStores.loadCloth(2,page);
                    Log.i(TAG, "loadMyData: " + call.toString());
                }
                break;
            case nav_beauty:
                if (page == 1) {
                    call = apiStores.loadCloth(3);
                } else {
                    call = apiStores.loadCloth(3,page);
                    Log.i(TAG, "loadMyData: " + call.toString());
                }
                break;
            case nav_stand_home:
                if (page == 1) {
                    call = apiStores.loadCloth(4);
                } else {
                    call = apiStores.loadCloth(4,page);
                    Log.i(TAG, "loadMyData: " + call.toString());
                }
                break;
            case nav_box_toe:
                if (page == 1) {
                    call = apiStores.loadCloth(5);
                } else {
                    call = apiStores.loadCloth(5,page);
                    Log.i(TAG, "loadMyData: " + call.toString());
                }
                break;
            case nav_eat:
                if (page == 1) {
                    call = apiStores.loadCloth(6);
                } else {
                    call = apiStores.loadCloth(6,page);
                    Log.i(TAG, "loadMyData: " + call.toString());
                }
                break;
            case nav_write:
                if (page == 1) {
                    call = apiStores.loadCloth(7);
                } else {
                    call = apiStores.loadCloth(7,page);
                    Log.i(TAG, "loadMyData: " + call.toString());
                }
                break;
            case nav_home_elect:
                if (page == 1) {
                    call = apiStores.loadCloth(8);
                } else {
                    call = apiStores.loadCloth(8,page);
                    Log.i(TAG, "loadMyData: " + call.toString());
                }
                break;
            case nav_other:
                if (page == 1) {
                    call = apiStores.loadCloth(9);
                } else {
                    call = apiStores.loadCloth(9,page);
                    Log.i(TAG, "loadMyData: " + call.toString());
                }
                break;
            default:
                if (page == 1) {
                    call = apiStores.loadCloth(2);
                } else {
                    call = apiStores.loadCloth(2,page);
                }
        }

        if (page == 1) {
            dataAdapter.clear();
        }
        call.enqueue(new RetrofitCallback<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody responseBody) {
                try {
                    Document document = Jsoup.parse(new String(responseBody.bytes(), "UTF-8"));
                    Document document2 = Jsoup.parse(document.getElementsByClass("row").toString());
                    Elements item = document2.getElementsByClass(" col-xs-12 noPadding divContent");
                    List<Element> dataElementList = new ArrayList<>();

                    for (Element ele : item) {
                        Log.i(TAG, "onSuccess: ===" + ele.toString());
                        dataElementList.add(ele);
                    }
                    dataAdapter.addAll(dataElementList);
                    if (dataElementList.size() < 40) {
                        //因为我的博客一页40条数据，当小于8时，说明没有下一页了
                        pullLoadMoreRecyclerView.setHasMore(false);
                    } else {
                        pullLoadMoreRecyclerView.setHasMore(true);
                    }
                    pullLoadMoreRecyclerView.scrollToTop();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int code, String msg) {
                toastUtil.showToast(msg);
            }

            @Override
            public void onThrowable(Throwable t) {
                toastUtil.showToast(t.getMessage());
            }

            @Override
            public void onFinish() {
                pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
            }
        });
    }

    @Override
    public void setUserName(String name) {
//        ((TextView) getView().findViewById(R.id.et_user)).setText(name);
    }

    @Override
    public void showToast(String msg) {
        toastUtil.showToast(msg);
    }
}
