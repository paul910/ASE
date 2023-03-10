package org.planner.domain;

import java.util.Map;

public class Activity {
    private final String phone;
    private final String display_phone;
    private final String id;
    private final String alias;
    private final String name;
    private final String image_url;
    private final double rating;
    private final long reviewCount;
    private final String price;
    private final String country;
    private final String state;
    private final String city;
    private final String address1;
    private final String address2;
    private final String address3;
    private final String zipCode;
    private final double latitude;
    private final double longitude;
    private final String url;

    public Activity(Map<String, Object> activity) {
        this.id = checkIfStringIsEmpty(activity.get("id"));
        this.alias = checkIfStringIsEmpty(activity.get("alias"));
        this.name = checkIfStringIsEmpty(activity.get("name"));
        this.image_url = checkIfStringIsEmpty(activity.get("image_url"));
        this.url = checkIfStringIsEmpty(activity.get("url"));
        this.rating = (double) activity.get("rating");
        this.reviewCount = (long) activity.get("review_count");
        this.price = checkIfStringIsEmpty(activity.get("price"));
        Map<String, Object> location = (Map<String, Object>) activity.get("location");
        {
            this.country = checkIfStringIsEmpty(location.get("country"));
            this.state = checkIfStringIsEmpty(location.get("state"));
            this.city = checkIfStringIsEmpty(location.get("city"));
            this.address1 = checkIfStringIsEmpty(location.get("address1"));
            this.address2 = checkIfStringIsEmpty(location.get("address2"));
            this.address3 = checkIfStringIsEmpty(location.get("address3"));
            this.zipCode = checkIfStringIsEmpty(location.get("zip_code"));
        }
        Map<String, Object> coordinates = (Map<String, Object>) activity.get("coordinates");
        {
            this.latitude = (double) coordinates.get("latitude");
            this.longitude = (double) coordinates.get("longitude");
        }
        this.phone = checkIfStringIsEmpty(activity.get("phone"));
        this.display_phone = checkIfStringIsEmpty(activity.get("display_phone"));
    }

    public Activity(String id, String alias, String name, String image_url, double rating, long reviewCount, String price, String country, String state, String city, String address1, String address2, String address3, String zipCode, double latitude, double longitude, String url, String phone, String display_phone) {
        this.id = checkIfNull(id);
        this.alias = checkIfNull(alias);
        this.name = checkIfNull(name);
        this.image_url = checkIfNull(image_url);
        this.rating = rating;
        this.reviewCount = reviewCount;
        this.price = checkIfNull(price);
        this.country = checkIfNull(country);
        this.state = checkIfNull(state);
        this.city = checkIfNull(city);
        this.address1 = checkIfNull(address1);
        this.address2 = checkIfNull(address2);
        this.address3 = checkIfNull(address3);
        this.zipCode = checkIfNull(zipCode);
        this.latitude = latitude;
        this.longitude = longitude;
        this.url = checkIfNull(url);
        this.phone = checkIfNull(phone);
        this.display_phone = checkIfNull(display_phone);
    }

    public static String getColumns() {
        return "id, alias, name, image_url, rating, review_count, price, country, state, city, address1, address2, address3, zip_code, latitude, longitude, url, phone, display_phone";
    }

    private String checkIfStringIsEmpty(Object value) {
        if (value == null || !(value instanceof String) || ((String) value).isBlank()) {
            return "-";
        }
        return value.toString();
    }

    private String checkIfNull(String value) {
        return value.equals("null") ? null : value;
    }

    public String getId() {
        return id;
    }

    public String getAlias() {
        return alias;
    }

    public String getName() {
        return name;
    }

    public String getImage_url() {
        return image_url;
    }

    public double getRating() {
        return rating;
    }

    public long getReviewCount() {
        return reviewCount;
    }

    public String getPrice() {
        return price;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getAddress3() {
        return address3;
    }

    public String getZipCode() {
        return zipCode;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getUrl() {
        return url;
    }

    public String getPhone() {
        return phone;
    }

    public String getDisplay_phone() {
        return display_phone;
    }

    @Override
    public String toString() {
        return this.id + "," + this.alias + "," + this.name + "," + this.image_url + "," + this.rating + "," + this.reviewCount + "," + this.price + "," + this.country + "," + this.state + "," + this.city + "," + this.address1 + "," + this.address2 + "," + this.address3 + "," + this.zipCode + "," + this.latitude + "," + this.longitude + "," + this.url + "," + this.phone + "," + this.display_phone;
    }
}