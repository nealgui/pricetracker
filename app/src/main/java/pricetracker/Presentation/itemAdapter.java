package pricetracker.Presentation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pricetracker.Logic.adapterInterface;
import pricetracker.Objects.*;
import pricetracker.R;

// itemAdapter inherits RecyclerView adapter class
public class itemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements adapterInterface {

    private final ArrayList<Item> starter;
    Context context;

    //    updating the constructor (takes our list of info)
    public itemAdapter(Context ct, ArrayList<Item> starter) {
        this.starter = starter;
        this.context = ct;
    }

    @Override
    public RecyclerView setAdapter(cartAdapter adapter, RecyclerView recyclerView, Context context) {
        return null;
    }

    @Override
    public RecyclerView setAdapter(historyAdapter adapter, RecyclerView recyclerView, Context context) {
        return null;
    }

    @Override
    public RecyclerView setAdapter(itemAdapter adapter, RecyclerView recyclerView, Context context) {
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        return recyclerView;
    }

    //    get reference to the textView id in recycler_view.xml, will link view to this
    private static class ItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final TextView textPrice;

        ConstraintLayout mainLayout;

        public ItemViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            textPrice = itemView.findViewById(R.id.textPrice);

            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }

    //    LayoutInflater uses the recycler_view.xml file to build the View objects ("instantiates")
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view, parent, false);
        return new ItemViewHolder(v);
    }

    //    updates the data i-th position of the RecyclerView's item
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.textView.setText(starter.get(position).getProdName()); // showing the item's name
        itemViewHolder.textPrice.setText("$" + starter.get(position).getProdPrice()); // showing the item's name

        itemViewHolder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PurchaseItem.class);
                Item item = starter.get(position);
                Intent updatedIntent = setIntents(intent, item);
                MainActivity.newLogic.setCurrProd(item);
                context.startActivity(updatedIntent);
            }
        });
    }

    public Intent setIntents(Intent intent, Item item) {
        intent.putExtra("tit", item.getProdName());
        intent.putExtra("des", item.getDesc());
        float x = item.getProdPrice();
        intent.putExtra("pri", String.valueOf(x));
        intent.putExtra("sto", String.valueOf(item.getProdStock()));
        intent.putExtra("stor", item.getStoreName());
        intent.putExtra("img", item.getImage());
        intent.putExtra("ph1", String.valueOf(item.getPh1()));
        intent.putExtra("ph2", String.valueOf(item.getPh2()));
        intent.putExtra("ph3", String.valueOf(item.getPh3()));
        intent.putExtra("ph4", String.valueOf(item.getPh4()));
        intent.putExtra("ph5", String.valueOf(item.getPh5()));
        intent.putExtra("ph6", String.valueOf(item.getPh6()));
        intent.putExtra("rev", item.getReview());

        return intent;
    }

    //    number of items in the dataset
    @Override
    public int getItemCount() {
        return starter.size();
    }

    public List<String> getItems() {
        List<String> itemNames = new ArrayList<>();
        for (int i = 0; i < starter.size(); i++) {
            itemNames.add(starter.get(i).getProdName());
        }
        return itemNames;
    };

    public List<Float> getPrices() {
        List<Float> itemPrices = new ArrayList<>();
        for (int i = 0; i < starter.size(); i++) {
            itemPrices.add(starter.get(i).getProdPrice());
        }
        return itemPrices;
    };
}
