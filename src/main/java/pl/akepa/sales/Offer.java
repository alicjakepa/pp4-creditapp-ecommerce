package pl.akepa.sales;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Offer {
    BigDecimal total;
    List<OfferItem> items;

    public Offer() {
        this.total = BigDecimal.ZERO;
        this.items = new ArrayList<>();

    }

    public static Offer emptyOffer() {
        return new Offer();
    }

    public BigDecimal getTotal() {
        return total;
    }

    public int getItemsCount() {
        return items.size();
    }

}
