package pl.akepa.sales;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Offer {
    BigDecimal total;
    private int size;
    List<OfferItem> items;

    public Offer() {
        this.total = BigDecimal.ZERO;
        this.items = new ArrayList<>();
        this.size = 0;
    }

    public Offer(BigDecimal total, int size) {
        this.total = total;
        this.items = new ArrayList<>();
        this.size = size;
    }

    public static Offer emptyOffer() {
        return new Offer();
    }

    public static Offer of(BigDecimal total, int size) {
        return new Offer(total, size);
    }

    public BigDecimal getTotal() {
        return total;
    }

    public int getItemsCount() {
        return size;
    }

}
