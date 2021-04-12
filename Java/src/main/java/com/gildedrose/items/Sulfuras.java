package com.gildedrose.items;

/**
 * User: melanie <melanie.ramosotero@unifiedpost.com>
 */
public class Sulfuras extends SellableItem {

    private static final int SULFURAS_QUALITY = 80;

    private final int initialSellIn;

    public Sulfuras(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
        initialSellIn = sellIn;
    }

    public void decreaseSellInDays() {
        // Sulfuras never sell
        super.decreaseSellInDays();
        sellIn = initialSellIn;
    }

    public void updateQuality() {
        // Sulfuras quality is 80 and it never alters
        quality = SULFURAS_QUALITY;
    }
}
