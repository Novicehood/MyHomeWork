package activity;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wanandroid.R;

import java.util.ArrayList;

import adapter.ArticleAdapter;
import adapter.ParentAdapter;
import config.ApiConfig;
import data.Child;
import httputil.HttpCallbackListener;
import httputil.HttpUtil;

public class systemFragment extends Fragment {
    ArrayList<Child> childArrayList=new ArrayList<>();

    public static systemFragment newInstance() {
        return new systemFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.system_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        HttpUtil.sendSystemRequest(ApiConfig.TREE, childArrayList, new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        RecyclerView recyclerView=getActivity().findViewById(R.id.tree_root);
                        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
                        recyclerView.setLayoutManager(layoutManager);
                        ParentAdapter adapter=new ParentAdapter(childArrayList);
                        recyclerView.setAdapter(adapter);
                    }
                });
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

}