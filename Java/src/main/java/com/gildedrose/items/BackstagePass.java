package com.gildedrose.items;

/**
 * User: melanie <melanie.ramosotero@unifiedpost.com>
 */
public class BackstagePass extends SellableItem {

    public BackstagePass(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    public void decreaseSellInDays() {
        super.decreaseSellInDays();
    }

    public void updateQuality() {
        super.updateQuality();

        if (sellIn == EXPIRED) {
            // Backstage pass quality drops to 0 after the concert
            quality = MINIMUM_QUALITY;
        } else if (sellIn <= 5) {
            // Backstage pass quality increases by 3 when there are 5 days or less
            quality = Math.min(MAXIMUM_QUALITY, quality + 3);
        } else if (sellIn <= 10) {
            //  Backstage pass quality increases by 2 when there are 10 days or less
            quality = Math.min(MAXIMUM_QUALITY, quality + 2);
        }
    }
}
