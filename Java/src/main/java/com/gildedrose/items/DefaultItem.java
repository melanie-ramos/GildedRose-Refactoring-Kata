package com.gildedrose.items;

/**
 * User: melanie <melanie.ramosotero@unifiedpost.com>
 */
public class DefaultItem extends SellableItem {

    public DefaultItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    public void decreaseSellInDays() {
        super.decreaseSellInDays();
    }

    public void updateQuality() {
        super.updateQuality();

        if(sellIn == EXPIRED) {
            // Once the sell by date has passed, quality degrades twice as fast
            quality = Math.max(MINIMUM_QUALITY, quality - 2);
        } else {
            // At the end of each day, quality decreases by 1
            quality = Math.max(MINIMUM_QUALITY, quality - 1);
        }
    }
}
