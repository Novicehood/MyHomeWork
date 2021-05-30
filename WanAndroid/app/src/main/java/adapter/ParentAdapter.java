package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.wanandroid.R;

import java.util.ArrayList;

import data.Child;
import layout.AutoLayoutManager;

public class ParentAdapter extends RecyclerView.Adapter<ParentAdapter.ViewHolder> {
    private ArrayList<Child> mchildArrayList;
    private Context mContext;

    public ParentAdapter(ArrayList<Child> childArrayList){
        mchildArrayList=childArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext=parent.getContext();
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.root,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Child child=mchildArrayList.get(position);
        holder.root_name.setText(child.getName());
        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        holder.root_recycle.setLayoutManager(layoutManager);
        ChildAdapter adapter=new ChildAdapter(child.children,child.ids);
        holder.root_recycle.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return mchildArrayList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView root_name;
        RecyclerView root_recycle;
        public ViewHolder(View view){
            super(view);
            root_name=view.findViewById(R.id.root_name);
            root_recycle=view.findViewById(R.id.root_recycler);
        }
    }

}
