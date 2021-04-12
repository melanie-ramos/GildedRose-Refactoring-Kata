package com.gildedrose.items;

import com.gildedrose.Item;

import java.security.InvalidParameterException;

/**
 * User: melanie <melanie.ramosotero@unifiedpost.com>
 */
public class SellableItem extends Item {
    public static final int EXPIRED = 0;
    public static final int MINIMUM_QUALITY = 0;
    public static final int MAXIMUM_QUALITY = 50;

    public SellableItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    public void endDay() {
        decreaseSellInDays();
        updateQuality();
    }

    public void decreaseSellInDays() {
        if(sellIn < EXPIRED) {
            throw new InvalidParameterException(String.format("The sell in of an item can't be negative: %s", toString()));
        }

        sellIn = Math.max(EXPIRED, sellIn - 1);
    }

    public void updateQuality() {
        if(quality < MINIMUM_QUALITY) {
            throw new InvalidParameterException(String.format("The quality of an item can't be negative: %s", toString()));
        }

        if(quality > MAXIMUM_QUALITY) {
            throw new InvalidParameterException(String.format("The quality of an item can't be higher than 50: %s", toString()));
        }
    }
}
