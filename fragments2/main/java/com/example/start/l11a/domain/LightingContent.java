package com.example.start.l11a.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LightingContent {

    public static final List<LightingItem> ITEMS = new ArrayList<LightingItem>();
    public static final Map<String, LightingItem> ITEM_MAP = new HashMap<String, LightingItem>();
    private static int count = 0;

    static {
        addItem(new LightingItem("1", "Lampka", "31", "10.10.61.196"));
    }

    private static void addItem(LightingItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
        count++;
    }

    public static void addOrModifyItem(LightingItem item) {
        if (ITEM_MAP.containsKey(item.id)) {
            LightingItem foundItem = ITEM_MAP.get(item.id);
            ITEMS.remove(foundItem);

            ITEMS.add(item);
            ITEM_MAP.put(item.id, item);

        } else {
            addItem(item);
        }
    }



    public static class LightingItem {
        public final String id;
        public final String content;
        public final String details;
        public final String ip;
        public Byte red;
        public Byte green;
        public Byte blue;
        public Byte brightness;


        public LightingItem(String id, String content, String details, String ip) {
            if (id.isEmpty()) {
                this.id = Integer.toString(count+1);
            } else {
                this.id = id;
            }
            this.content = content;
            this.details = details;
            this.ip = ip;
            this.red = 0;
            this.green = 0;
            this.blue = 0;
            this.brightness = 0;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
