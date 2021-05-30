package activity;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.wanandroid.R;
import com.google.android.material.internal.FlowLayout;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Random;

import adapter.ArticleAdapter;
import config.ApiConfig;
import data.Article;
import httputil.HttpCallbackListener;
import httputil.HttpUtil;
import json.ParseJson;
import layout.AutoLayoutManager;

public class homeFragment extends Fragment {
    ArrayList<Article> articles=new ArrayList<>();

    public static homeFragment newInstance() {
        return new homeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Random random=new Random();
        int page=random.nextInt(20);
        HttpUtil.sendHomePageRequest(ApiConfig.HOMEPAGE, page,articles,new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        RecyclerView recyclerView=getActivity().findViewById(R.id.recycler_view);
                        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                        recyclerView.setLayoutManager(layoutManager);
                        ArticleAdapter adapter=new ArticleAdapter(articles);
                        recyclerView.setAdapter(adapter);
                    }
                });
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }

        });
    }

}