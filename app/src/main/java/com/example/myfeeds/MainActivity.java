package com.example.myfeeds;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.myfeeds.adapter.MyFeedAdapter;
import com.example.myfeeds.model.FeedModel;
import com.example.myfeeds.view_model.FeedViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
@BindView(R.id.pullToRefresh)
 SwipeRefreshLayout pullToRefresh;
@BindView(R.id.feed_list)
    ListView feed_list;
    FeedViewModel feedViewModel;
    String header_title;
MyFeedAdapter myFeedAdapter;
 int initial_data=4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        feedViewModel= ViewModelProviders.of(this).get(FeedViewModel.class);
        feedViewModel.getFeedData().observe(this, new Observer<ArrayList<FeedModel>>() {
            @Override
            public void onChanged(ArrayList<FeedModel> feedModels) {
                loadData(feedModels);

            }
        });
pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
    @Override
    public void onRefresh() {
        refreshData();
        pullToRefresh.setRefreshing(false);
    }
});

    }

    private void refreshData() {
        feedViewModel.getFeedData().observe(this, new Observer<ArrayList<FeedModel>>() {
            @Override
            public void onChanged(ArrayList<FeedModel> feedModels) {
                myFeedAdapter=new MyFeedAdapter(MainActivity.this,feedModels);
                feed_list.setAdapter(myFeedAdapter);
            }
        });


    }
public void loadData(ArrayList<FeedModel> feedModels){
    ArrayList<FeedModel> feeddata=new ArrayList<>();
    boolean index_less=false;
    for(int i=0;i<=feedModels.size();i++){

        if(feedModels.size()>initial_data&&i<=initial_data) {
            header_title=feedModels.get(i).getHeader_title();
            feeddata.add(feedModels.get(i));
        }if(feedModels.size()<initial_data){
            index_less=true;
        }
    }

    if(header_title!=null) {
        getSupportActionBar().setTitle(header_title);
    }else{
        getSupportActionBar().setTitle(getString(R.string.app_name));
    }
    if(index_less){
        myFeedAdapter=new MyFeedAdapter(MainActivity.this,feedModels);
        feed_list.setAdapter(myFeedAdapter);
    }else{
        myFeedAdapter=new MyFeedAdapter(MainActivity.this,feeddata);
        feed_list.setAdapter(myFeedAdapter);

    }
}
}