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
public class LightingContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<LightnigItem> ITEMS = new ArrayList<LightnigItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, LightnigItem> ITEM_MAP = new HashMap<String, LightnigItem>();

    public static int COUNT = 0;

    public static void addItem(LightnigItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
        COUNT += 1;
    }

    public static void removeItem(LightingContent.LightnigItem item) {
        ITEMS.remove(ITEM_MAP.get(item.id));
    }





    public static class LightnigItem {


        public final String id;
        public final String content;
        public final String details;
        int brightness;
        int red;
        int green;
        int blue;
        String ip;

        public LightnigItem(String id, String content, String details, int brightness, int red, int green, int blue, String ip) {
            this.id = id;
            this.content = content;
            this.details = details;
            this.brightness = brightness;
            this.red = red;
            this.green = green;
            this.blue = blue;
            this.ip = ip;
        }

        public LightnigItem(String id, String content, String details) {
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
