package ostadbank.com.utils;


import ostadbank.com.R;

/**
 * Created by Mohammadloo on 2017-07-29.
 */

public class Markets {

    private static Market[] markets = new Market[]{
            new Market(0, R.string.cafe_bazaar, "bazaar", "com.farsitel.bazaar", "ir.cafebazaar.pardakht.InAppBillingService.BIND", "https://cafebazaar.ir/install/?l=fa")
    };

    //ToDo change
    private static int default_market = 0;

    public static Market getDefault() {
        return markets[default_market];
    }

    public static class Market {
        int id, resName;
        String Name, Package, Url, IntentUri;

        public Market(int id, int resName, String Name, String Package, String intentUri, String url) {
            this.id = id;
            this.resName = resName;
            this.Name = Name;
            this.Package = Package;
            this.IntentUri = intentUri;
            Url = url;
        }

        public int getResourceName() {
            return resName;
        }

        public void setResourceName(int resName) {
            this.resName = resName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getPackage() {
            return Package;
        }

        public void setPackage(String aPackage) {
            Package = aPackage;
        }

        public String getIntent() {
            return IntentUri;
        }

        public void setIntent(String intentUri) {
            IntentUri = intentUri;
        }

        public String getUrl() {
            return Url;
        }

        public void setUrl(String url) {
            Url = url;
        }
    }
}
