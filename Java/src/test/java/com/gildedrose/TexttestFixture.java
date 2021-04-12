package com.gildedrose;

import com.gildedrose.items.AgedBrie;
import com.gildedrose.items.BackstagePass;
import com.gildedrose.items.Conjured;
import com.gildedrose.items.DefaultItem;
import com.gildedrose.items.SellableItem;
import com.gildedrose.items.Sulfuras;

public class TexttestFixture {
    public static void main(String[] args) {
        System.out.println("OMGHAI!");

        SellableItem[] items = new SellableItem[] {
                new DefaultItem("+5 Dexterity Vest", 10, 20), //
                new AgedBrie("Aged Brie", 2, 0), //
                new DefaultItem("Elixir of the Mongoose", 5, 7), //
                new Sulfuras("Sulfuras, Hand of Ragnaros", 0, 80), //
                new Sulfuras("Sulfuras, Hand of Ragnaros", 0, 80),
                new AgedBrie("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new AgedBrie("Backstage passes to a TAFKAL80ETC concert", 10, 49),
                new BackstagePass("Backstage passes to a TAFKAL80ETC concert", 5, 49),
                new Conjured("Conjured Mana Cake", 3, 6) };

        GildedRose app = new GildedRose(items);

        int days = 2;
        if (args.length > 0) {
            days = Integer.parseInt(args[0]) + 1;
        }

        for (int i = 0; i < days; i++) {
            System.out.println("-------- day " + i + " --------");
            System.out.println("name, sellIn, quality");
            for (Item item : items) {
                System.out.println(item);
            }
            System.out.println();
            app.updateQuality();
        }
    }

}
