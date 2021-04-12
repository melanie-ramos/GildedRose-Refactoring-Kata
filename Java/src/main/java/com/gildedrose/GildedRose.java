package com.gildedrose;

import com.gildedrose.items.SellableItem;

import java.util.Arrays;

class GildedRose {
    SellableItem[] items;

    public GildedRose(SellableItem[] items) {
        this.items = items;
    }

    public void updateQuality() {
        Arrays.stream(items).forEach(SellableItem::endDay);
    }
}