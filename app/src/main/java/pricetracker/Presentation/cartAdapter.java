package pricetracker.Presentation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import pricetracker.Objects.Item;
import pricetracker.Presentation.*;
import pricetracker.R;

// itemAdapter inherits RecyclerView adapter class
public class cartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Item[] data1;
    Context context;

    //    updating the constructor (takes our list of info)
    public cartAdapter(Context ct, Item[] starter) {
        this.context = ct;
        data1 = starter;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view, parent, false);
        return new MyViewHolder(view);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView myText1;
        TextView myText2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.textView);
            myText2 = itemView.findViewById(R.id.textPrice);

        }

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder itemViewHolder = (MyViewHolder) holder;
        itemViewHolder.myText1.setText(data1[position].getProdName());
        itemViewHolder.myText2.setText("$" + data1[position].getProdPrice());

    }

    @Override
    public int getItemCount() {
        return data1.length;
    }
}
