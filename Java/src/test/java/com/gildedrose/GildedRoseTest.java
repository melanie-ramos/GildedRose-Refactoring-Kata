package com.gildedrose;

import com.gildedrose.items.AgedBrie;
import com.gildedrose.items.BackstagePass;
import com.gildedrose.items.Conjured;
import com.gildedrose.items.DefaultItem;
import com.gildedrose.items.SellableItem;
import com.gildedrose.items.Sulfuras;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void exception_is_thrown_when_quality_is_negative() {
        //given
        SellableItem[] items = new SellableItem[] { new DefaultItem("foo", 20, -1) };
        GildedRose app = new GildedRose(items);

        //when, then expect exception
        Assertions.assertThrows(InvalidParameterException.class, app::updateQuality);
    }

    @Test
    void exception_is_thrown_when_quality_is_higher_than_50() {
        //given
        SellableItem[] items = new SellableItem[] { new DefaultItem("foo", 20, 51) };
        GildedRose app = new GildedRose(items);

        //when, then expect exception
        Assertions.assertThrows(InvalidParameterException.class, app::updateQuality);
    }

    @Test
    void exception_is_thrown_when_sellIn_is_negative() {
        //given
        SellableItem[] items = new SellableItem[] { new DefaultItem("foo", -1, 51) };
        GildedRose app = new GildedRose(items);

        //when, then expect exception
        Assertions.assertThrows(InvalidParameterException.class, app::updateQuality);
    }

    @Test
    void quality_is_never_negative() {
        //given
        SellableItem[] items = new SellableItem[] { new DefaultItem("foo", 20, 0) };
        GildedRose app = new GildedRose(items);

        //when
        app.updateQuality();

        // then
        assertEquals(19, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void quality_is_never_higher_than_50() {
        //given
        SellableItem[] items = new SellableItem[] { new AgedBrie("Aged Brie", 20, 50) };
        GildedRose app = new GildedRose(items);

        //when
        app.updateQuality();

        // then
        assertEquals(19, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void sellIn_and_quality_values_decrease_by_1_each_day() {
        //given
        SellableItem[] items = new SellableItem[] { new DefaultItem("foo", 20, 50) };
        GildedRose app = new GildedRose(items);

        //when
        app.updateQuality();

        // then
        assertEquals(19, app.items[0].sellIn);
        assertEquals(49, app.items[0].quality);
    }

    @Test
    void quality_decreases_by_2_when_expired() {
        //given
        SellableItem[] items = new SellableItem[] { new DefaultItem("foo", 0, 50) };
        GildedRose app = new GildedRose(items);

        //when
        app.updateQuality();

        // then
        assertEquals(0, app.items[0].sellIn);
        assertEquals(48, app.items[0].quality);
    }

    @Test
    void aged_brie_increases_quality_with_time() {
        //given
        SellableItem[] items = new SellableItem[] { new AgedBrie("Aged Brie", 20, 20) };
        GildedRose app = new GildedRose(items);

        //when
        app.updateQuality();

        // then
        assertEquals(19, app.items[0].sellIn);
        assertEquals(21, app.items[0].quality);
    }

    @Test
    void backstage_pass_increases_quality_by_2_with_10_days_or_less_to_sell_in() {
        //given
        SellableItem[] items = new SellableItem[] {
                new AgedBrie("Aged Brie", 10, 20),
                new BackstagePass("Backstage passes to a TAFKAL80ETC concert", 10, 20)
        };
        GildedRose app = new GildedRose(items);

        //when
        app.updateQuality();

        // then
        Arrays.stream(app.items).forEach(item -> {
            assertEquals(9, item.sellIn);
            assertEquals(22, item.quality);
        });
    }

    @Test
    void backstage_pass_increases_quality_by_3_with_5_days_or_less_to_sell_in() {
        //given
        SellableItem[] items = new SellableItem[] {
                new AgedBrie("Aged Brie", 5, 20),
                new BackstagePass("Backstage passes to a TAFKAL80ETC concert", 5, 20)
        };
        GildedRose app = new GildedRose(items);

        //when
        app.updateQuality();

        // then
        Arrays.stream(app.items).forEach(item -> {
            assertEquals(4, item.sellIn);
            assertEquals(23, item.quality);
        });
    }

    @Test
    void backstage_pass_quality_drops_to_0_after_the_concert() {
        //given
        SellableItem[] items = new SellableItem[] {
                new AgedBrie("Aged Brie", 0, 20),
                new BackstagePass("Backstage passes to a TAFKAL80ETC concert", 0, 20)
        };
        GildedRose app = new GildedRose(items);

        //when
        app.updateQuality();

        // then
        Arrays.stream(app.items).forEach(item -> {
            assertEquals(0, item.sellIn);
            assertEquals(0, item.quality);
        });
    }

    @Test
    void sulfuras_never_sell() {
        //given
        SellableItem[] items = new SellableItem[] { new Sulfuras("Sulfuras, Hand of Ragnaros", 20, 80) };
        GildedRose app = new GildedRose(items);

        //when
        app.updateQuality();

        // then
        assertEquals(20, app.items[0].sellIn);
    }

    @Test
    void sulfuras_quality_is_always_80() {
        //given
        SellableItem[] items = new SellableItem[] { new Sulfuras("Sulfuras, Hand of Ragnaros", 20, 80) };
        GildedRose app = new GildedRose(items);

        //when
        app.updateQuality();

        // then
        assertEquals(80, app.items[0].quality);
    }

    @Test
    void conjured_items_degrade_twice_as_fast() {
        //given
        SellableItem[] items = new SellableItem[] { new Conjured("Conjured", 20, 50) };
        GildedRose app = new GildedRose(items);

        //when
        app.updateQuality();

        // then
        assertEquals(19, app.items[0].sellIn);
        assertEquals(48, app.items[0].quality);
    }

}
