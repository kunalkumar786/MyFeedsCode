package com.example.myfeeds.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.myfeeds.model.FeedModel;
import com.example.myfeeds.network.NetworkClient;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedViewModel extends AndroidViewModel {
MutableLiveData<ArrayList<FeedModel>> feed_data;

    public FeedViewModel(@NonNull Application application) {
        super(application);
    }
public MutableLiveData<ArrayList<FeedModel>> getFeedData(){
        if(feed_data==null){
            feed_data=new MutableLiveData<>();
            callFeedApi();
        }
return feed_data;
    }
public void callFeedApi(){
    NetworkClient.APIClient apiClient = NetworkClient.getNetworkClientInstance().getApiClient();
    apiClient.callAPIExecutor("").enqueue(new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            try {
                if (response.code() == 200) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        JSONArray jsonArray=jsonObject.getJSONArray("rows");
                        if(jsonArray!=null&&jsonArray.length()>0){
                            ArrayList<FeedModel> feed_result=new ArrayList<>();
                            for(int i=0;i<jsonArray.length();i++){
                                FeedModel feedModel=new FeedModel();
                                feedModel.setHeader_title(jsonObject.getString("title"));
                                JSONObject result=jsonArray.getJSONObject(i);
                                feedModel.setTitle(result.getString("title"));
                                feedModel.setDescription(result.getString("description"));
                                feedModel.setImageHref(result.getString("imageHref"));
                               feed_result.add(feedModel);
                            }
                        feed_data.setValue(feed_result);
                        }


                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }


            }catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {

        }
    });
}




}
