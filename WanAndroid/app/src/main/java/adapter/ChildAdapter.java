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

import activity.KindResult;
import data.Child;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ViewHolder> {
    private ArrayList <String> mchildren;
    private ArrayList<Integer> mIds;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.child_layout,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.child_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                int id=mIds.get(position);
                Intent intent=new Intent(parent.getContext(), KindResult.class);
                intent.putExtra("id",id);
                parent.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String name=mchildren.get(position);
        holder.child_view.setText(name);
    }

    @Override
    public int getItemCount() {
        return mchildren.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView child_view;

        public ViewHolder(View view){
            super(view);
            child_view=view.findViewById(R.id.child_textview);
        }
    }
    public ChildAdapter(ArrayList<String> children,ArrayList<Integer> ids){
        this.mIds=ids;
        this.mchildren=children;
    }
}
