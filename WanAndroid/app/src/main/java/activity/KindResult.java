package activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.wanandroid.R;

import java.util.ArrayList;

import adapter.ArticleAdapter;
import config.ApiConfig;
import data.Article;
import httputil.HttpCallbackListener;
import httputil.HttpUtil;

public class KindResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ArrayList<Article> articles=new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kind_result);
        Intent intent=getIntent();
        int id=intent.getIntExtra("id",60);
        HttpUtil.sendKindRequest(ApiConfig.KINDS, articles, id, new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        RecyclerView recyclerView=findViewById(R.id.kind_recycler);
                        LinearLayoutManager layoutManager=new LinearLayoutManager(getParent());
                        recyclerView.setLayoutManager(layoutManager);
                        ArticleAdapter adapter=new ArticleAdapter(articles);
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