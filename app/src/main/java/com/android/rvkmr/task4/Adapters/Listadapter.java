package com.android.rvkmr.task4.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.rvkmr.task4.Models.ExecutionTimePOJO;
import com.android.rvkmr.task4.R;

import java.util.List;

/**
 * Created by rvkmr on 10-03-2018.
 */

public class Listadapter extends RecyclerView.Adapter<Listadapter.ViewHolder> {
    List<ExecutionTimePOJO> list;
    Context context;
    int layout;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ExecutionTimePOJO pojo=list.get(position);
        holder.letter.setText(pojo.getLetter());
        holder.realm.setText(String.valueOf(pojo.getRealm()));
        holder.sqlite.setText(String.valueOf(pojo.getSqlite()));

    }

    public Listadapter( Context context,List<ExecutionTimePOJO> list,int layout) {
        this.context=context;
        this.list=list;
        this.layout=layout;
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView letter;
        TextView realm;
        TextView sqlite;

        public ViewHolder(View itemView) {
            super(itemView);
            letter=itemView.findViewById(R.id.tv_letter);
            realm=itemView.findViewById(R.id.tv_realm_exe_time);
            sqlite=itemView.findViewById(R.id.tv_sqlite_exe_time);

        }
    }
}
