package com.example.android.shreygarg_deltatask3;

public class crimes {
    String category;
    String persistent_id;
    String month;
    String id;
    String location_type;
    private loc location;

    public String getCategory() {
        return category;
    }

    public String getPersistent_id() {
        return persistent_id;
    }

    public String getId() {
        return id;
    }

    public String getLocation_type() {
        return location_type;
    }


    public loc getLocation() {
        return location;
    }

    public String getMonth() {
        return month;
    }

    public class loc {
        String latitude;
        String longitude;
        public streetname street;

        public String getLatitude() {
            return latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public streetname getStreet() {
            return street;
        }

        public class streetname{
            String name;

            public String getName() {
                return name;
            }
        }
    }

}
