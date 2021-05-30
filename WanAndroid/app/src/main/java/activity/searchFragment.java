package activity;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.wanandroid.R;

import java.util.ArrayList;

import adapter.ArticleAdapter;
import config.ApiConfig;
import data.Article;
import httputil.HttpCallbackListener;
import httputil.HttpUtil;

public class searchFragment extends Fragment {
    ArrayList<Article> articles=new ArrayList<>();

    public static searchFragment newInstance() {
        return new searchFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.search_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EditText editText=getActivity().findViewById(R.id.search_textview);
        Button search=getActivity().findViewById(R.id.search_btn);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String author=editText.getText().toString();
                Intent intent=new Intent(getContext(),SearchResult.class);
                intent.putExtra("author",author);
                startActivity(intent);
            }
        });
    }

}