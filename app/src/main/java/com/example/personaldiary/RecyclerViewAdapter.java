package com.example.personaldiary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personaldiary.db.User;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    LayoutInflater inflater;
    private Context context;
    private List<User> userList;

    public RecyclerViewAdapter(Context context){
        this.inflater=LayoutInflater.from(context);
        this.context=context;
    }

    public void setUserList(List<User> userList){
        this.userList=userList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        holder.date.setText(this.userList.get(position).DATE);
        holder.data.setText(this.userList.get(position).DATA);
    }

    @Override
    public int getItemCount() {
        return this.userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView date,data;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.textViewForDate);
            data=itemView.findViewById(R.id.textViewForNotes);
        }
    }
}
