package com.gildedrose.items;

/**
 * User: melanie <melanie.ramosotero@unifiedpost.com>
 */
public class AgedBrie extends BackstagePass {

    public AgedBrie(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    public void decreaseSellInDays() {
        super.decreaseSellInDays();
    }

    public void updateQuality() {
        super.updateQuality();

        if(sellIn > 10) {
            // Aged Brie actually increases in Quality the older it gets
            quality = Math.min(MAXIMUM_QUALITY, quality + 1);
        }
    }
}
