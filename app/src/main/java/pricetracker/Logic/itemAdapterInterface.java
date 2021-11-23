package pricetracker.Logic;

import java.util.List;

public interface itemAdapterInterface {
    int getItemCount();
    List<String> getItems();
    List<Float> getPrices();
}
