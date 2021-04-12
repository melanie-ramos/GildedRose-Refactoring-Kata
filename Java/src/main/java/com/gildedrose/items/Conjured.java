package com.gildedrose.items;

/**
 * User: melanie <melanie.ramosotero@unifiedpost.com>
 */
public class Conjured extends SellableItem {

    public Conjured(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    public void decreaseSellInDays() {
        super.decreaseSellInDays();
    }

    public void updateQuality() {
        super.updateQuality();

        // Conjured items degrade in quality twice as fast as normal items
        quality = Math.max(MINIMUM_QUALITY, quality - 2);
    }
}
