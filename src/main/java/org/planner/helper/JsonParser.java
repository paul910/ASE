package org.planner.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonParser {
    private final String jsonString;
    private int index;

    public JsonParser(String jsonString) {
        this.jsonString = jsonString;
        this.index = 0;
    }

    public Map<String, Object> parseObject() {
        Map<String, Object> map = new HashMap<>();

        consume('{');
        while (peek() != '}') {
            String key = parseString();
            consume(':');
            Object value = parseValue();
            map.put(key, value);
            if (peek() == ',') {
                consume(',');
            }
        }
        consume('}');
        return map;
    }

    public List<Object> parseArray() {
        List<Object> list = new ArrayList<>();

        consume('[');
        while (peek() != ']') {
            Object value = parseValue();
            list.add(value);
            if (peek() == ',') {
                consume(',');
            }
        }
        consume(']');
        return list;
    }

    private Object parseValue() {
        char nextChar = peek();
        if (nextChar == '{') {
            return parseObject();
        } else if (nextChar == '[') {
            return parseArray();
        } else if (nextChar == '"') {
            return parseString();
        } else if (nextChar == 't' || nextChar == 'f') {
            return parseBoolean();
        } else if (nextChar == 'n') {
            return parseNull();
        } else {
            return parseNumber();
        }
    }

    private String parseString() {
        StringBuilder sb = new StringBuilder();
        consume('"');
        while (peek() != '"') {
            if (peek() == '\\') {
                consume('\\');
            }
            sb.append(consume());
        }
        consume('"');
        return sb.toString();
    }

    private Number parseNumber() {
        StringBuilder sb = new StringBuilder();
        while (Character.isDigit(peek()) || peek() == '-' || peek() == '+' || peek() == '.') {
            sb.append(consume());
        }
        String strValue = sb.toString();
        if (strValue.contains(".")) {
            return Double.parseDouble(strValue);
        } else {
            return Long.parseLong(strValue);
        }
    }

    private Boolean parseBoolean() {
        if (consume() == 't') {
            consume('r');
            consume('u');
            consume('e');
            return true;
        } else {
            consume('a');
            consume('l');
            consume('s');
            consume('e');
            return false;
        }
    }

    private Object parseNull() {
        consume('n');
        consume('u');
        consume('l');
        consume('l');
        return null;
    }

    private char peek() {
        skipWhiteSpace();
        return jsonString.charAt(index);
    }

    private char consume() {
        skipWhiteSpace();
        char nextChar = peek();
        index++;
        return nextChar;
    }

    private void consume(char expectedChar) {
        skipWhiteSpace();
        char nextChar = consume();
        if (nextChar != expectedChar) {
            throw new RuntimeException("Expected " + expectedChar + " but got " + nextChar);
        }
    }

    private void skipWhiteSpace() {
        while (index < jsonString.length() && Character.isWhitespace(jsonString.charAt(index))) {
            index++;
        }
    }
}