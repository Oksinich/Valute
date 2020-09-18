package com.example.valute;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class ValuteAdapter extends RecyclerView.Adapter<ValuteAdapter.ValuteHolder> {
    private List<OneValute> oneValutes = new ArrayList<>();
    private OnValuteListener onValuteListener;

    public ValuteAdapter(List<OneValute> oneValutes,OnValuteListener onValuteListener) {
        this.oneValutes = oneValutes;
        this.onValuteListener = onValuteListener;
    }

    @NonNull
    @Override
    public ValuteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.one_valute,parent,false);
        return new ValuteHolder(view,onValuteListener);
    }
    public void setData(List<OneValute> list, OnValuteListener onValuteListener){
        this.oneValutes = list;
        this.onValuteListener = onValuteListener;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ValuteHolder holder, int position) {
        holder.name.setText(oneValutes.get(position).getName());
        holder.value.setText(oneValutes.get(position).getValue()+"");
    }

    @Override
    public int getItemCount() {
        return oneValutes.size();
    }

    public class ValuteHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name;
        private TextView value;
        OnValuteListener onValuteListener;

        public ValuteHolder(@NonNull View itemView, OnValuteListener onValuteListener) {
            super(itemView);
            name = itemView.findViewById(R.id.NameID);
            value = itemView.findViewById(R.id.ValueID);
            this.onValuteListener = onValuteListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onValuteListener.onValuteClick(getAdapterPosition());
        }
    }
        public interface OnValuteListener{
            void onValuteClick(int position);
        }

}
