package com.example.androidrecyclerview;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ItemHolder> {

    private List<String> itemsName;
    private OnItemClickListener onItemClickListener;
    private LayoutInflater layoutInflater;

    public RecyclerViewAdapter(Context context){
        layoutInflater = LayoutInflater.from(context);
        itemsName = new ArrayList<String>();
    }

    @Override
    public RecyclerViewAdapter.ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View itemView = layoutInflater.inflate(R.layout.layout_item, parent, false);
        //return new ItemHolder(itemView, this);
        CardView itemCardView = (CardView)layoutInflater.inflate(R.layout.layout_cardview, parent, false);
        return new ItemHolder(itemCardView, this);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ItemHolder holder, int position) {
        holder.setItemName(itemsName.get(position));
    }

    @Override
    public int getItemCount() {
        return itemsName.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        onItemClickListener = listener;
    }

    public OnItemClickListener getOnItemClickListener(){
        return onItemClickListener;
    }

    public interface OnItemClickListener{
        public void onItemClick(ItemHolder item, int position);
    }

    public void add(int location, String iName){
        itemsName.add(location, iName);
        notifyItemInserted(location);
    }

    public void remove(int location){
        if(location >= itemsName.size())
            return;

        itemsName.remove(location);
        notifyItemRemoved(location);
    }

    public static class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private RecyclerViewAdapter parent;
        TextView textItemName;

        private CardView cardView;

        /*
        public ItemHolder(View itemView, RecyclerViewAdapter parent) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.parent = parent;
            textItemName = (TextView) itemView.findViewById(R.id.item_name);
        }
        */

        public ItemHolder(CardView cView, RecyclerViewAdapter parent) {
            super(cView);
            cardView = cView;
            cardView.setOnClickListener(this);
            this.parent = parent;
            textItemName = (TextView) cardView.findViewById(R.id.card_item_name);
        }

        public void setItemName(CharSequence name){
            textItemName.setText(name);
        }

        public CharSequence getItemName(){
            return textItemName.getText();
        }

        @Override
        public void onClick(View v) {
            final OnItemClickListener listener = parent.getOnItemClickListener();
            if(listener != null){
                listener.onItemClick(this, getPosition());
            }
        }
    }
}
