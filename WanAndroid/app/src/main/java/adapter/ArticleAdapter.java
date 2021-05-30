package adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wanandroid.R;

import java.util.ArrayList;

import activity.DisplayArticle;
import data.Article;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
    private ArrayList<Article> mArticles;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.article_item,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                Article article=mArticles.get(position);
                String link=article.getLink();
                Intent intent=new Intent(v.getContext(), DisplayArticle.class);
                intent.putExtra("link",link);
                parent.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleAdapter.ViewHolder holder, int position) {
        Article article=mArticles.get(position);
        holder.sharedUser.setText(article.getShareUser());
        holder.title.setText(article.getTitle());
        holder.chapterName.setText(article.getSuper_ChapterName());
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView sharedUser;
        TextView title;
        TextView chapterName;
        public ViewHolder(View view){
            super(view);
            sharedUser=view.findViewById(R.id.article_shareduser);
            title=view.findViewById(R.id.article_title);
            chapterName=view.findViewById(R.id.article_chaptername);
        }
    }
    public ArticleAdapter(ArrayList<Article> articles){
        this.mArticles=articles;
    }

}
