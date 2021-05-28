package com.example.caoviethoang_ktra2_bai2;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LichThiAdapter extends RecyclerView.Adapter<LichThiAdapter.LichThiViewHolder> {
    List<LichThi> lichThiList;


    public LichThiAdapter() {
        lichThiList=new ArrayList<>();
    }
    public void getAll(List<LichThi> lichThiList){
        this.lichThiList=lichThiList;
    }

    @NonNull
    @Override
    public LichThiAdapter.LichThiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v=inflater.inflate(R.layout.list_item,parent,false);
        return new LichThiViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull LichThiAdapter.LichThiViewHolder holder, int position) {
        LichThi lichThi=lichThiList.get(position);
        holder.txtid.setText(String.valueOf(lichThi.getId()));
        holder.txtname.setText(lichThi.getName());
        holder.txtdate.setText(lichThi.getDate());
        holder.txttime.setText(lichThi.getTime());
        if(lichThi.isThi()){
            holder.cb.setChecked(true);
        }else{
            holder.cb.setChecked(false);
        }
        holder.cb.setEnabled(false);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(holder.itemView.getContext(),LichThiActivity.class);
                intent.putExtra("id",String.valueOf(lichThi.getId()));
                intent.putExtra("name",lichThi.getName());
                intent.putExtra("date",lichThi.getDate());
                intent.putExtra("time",lichThi.getTime());
                intent.putExtra("thi",String.valueOf(lichThi.isThi()));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(lichThiList != null) {
            return lichThiList.size();
        }
        return 0;
    }

    public class LichThiViewHolder extends RecyclerView.ViewHolder {
        TextView txtid,txtname,txtdate,txttime;
        CheckBox cb;
        public LichThiViewHolder(@NonNull View itemView) {
            super(itemView);
            txtid=itemView.findViewById(R.id.txtid);
            txtname=itemView.findViewById(R.id.txtname);
            txtdate=itemView.findViewById(R.id.txtdate);
            txttime=itemView.findViewById(R.id.txttime);
            cb=itemView.findViewById(R.id.cb);
        }
    }
}
