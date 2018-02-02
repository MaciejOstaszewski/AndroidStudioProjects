package com.example.start.l10.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class SensorContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<SensorItem> ITEMS = new ArrayList<SensorItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, SensorItem> ITEM_MAP = new HashMap<String, SensorItem>();

    public static int COUNT = 0;

    public static void addItem(SensorItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
        COUNT += 1;
    }

    public static void removeItem(SensorContent.SensorItem item) {
        ITEMS.remove(ITEM_MAP.get(item.id));
    }
    public static class SensorItem {
        public final String id;
        public final String content;
        public final String details;


        public SensorItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
