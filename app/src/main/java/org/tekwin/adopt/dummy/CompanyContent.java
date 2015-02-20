package org.tekwin.adopt.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CompanyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static List<CompanyListItem> ITEMS = new ArrayList<CompanyListItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, CompanyListItem> ITEM_MAP = new HashMap<String, CompanyListItem>();

    static {
        // Add 3 sample items.
        addItem(new CompanyListItem("1", "intuit","http://cdn.toptenreviews.com/rev/articles/4575-accounting-software-and-more-top-10-things-you-didn-039-t-know-you-could-do-with-intuit.jpg",""));
        addItem(new CompanyListItem("2", "Gilt","",""));
        addItem(new CompanyListItem("3", "Snapfish","",""));
        addItem(new CompanyListItem("4", "SameSurf","",""));
        addItem(new CompanyListItem("5", "SayIt","",""));
        addItem(new CompanyListItem("6", "Watchittoo","",""));
    }

    private static void addItem(CompanyListItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }


    public static class CompanyListItem {
        public String id;
        public String comanyName;
        public String url;
        public String siteNumber;



        public CompanyListItem(String id,String comanyName,String url,String siteNumber) {
            this.id = id;
            this.url = url;
            this.comanyName = comanyName;
            this.siteNumber = siteNumber;
        }

        public String getComanyName() {
            return comanyName;
        }

        @Override
        public String toString() {
            return comanyName;
        }
    }
}
