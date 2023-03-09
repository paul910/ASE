package org.planner.helper;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseHelper {
    private final String JSONString;

    public ParseHelper(String JSONString) {
        this.JSONString = JSONString;
    }

    public String getValueByRegex(String value) {
        Matcher matcher = regexMatcher(value);
        if (matcher.find()) {
            return matcher.group(1).replace("\"", "");
        }
        return "";
    }

    public Double getDoubleValueByRegex(String value) {
        Matcher matcher = regexMatcher(value);
        if (matcher.find()) {
            return Double.parseDouble(matcher.group(1));
        }
        return 0.0;
    }

    public Integer getIntegerValueByRegex(String value) {
        Matcher matcher = regexMatcher(value);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        return 0;
    }

    public Date getDateValueByRegex(String value) {
        Matcher matcher = regexMatcher(value);
        if (matcher.find()) {
            return new Date(Long.parseLong(matcher.group(1)) * 1000);
        }
        return new Date();
    }

    public Matcher regexMatcher(String value) {
        Pattern pattern = Pattern.compile("\"" + value + "\"\\s*:\\s*((\"([^\"]*)\")|((\\d+(?:\\.\\d+)?)))");
        Matcher matcher = pattern.matcher(this.JSONString);
        return matcher;
    }
}
