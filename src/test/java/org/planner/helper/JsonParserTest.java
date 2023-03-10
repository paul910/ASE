package org.planner.helper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonParserTest {

    @Test
    void parse() {
        String expectedObj = "{cars=[Audi, BMW, Fiat], name=John, age=30}";
        String response = "{\"name\":\"John\",\"age\":30,\"cars\":[\"Audi\",\"BMW\",\"Fiat\"]}";
        Object obj = new JsonParser(response).parse();
        assertEquals(expectedObj, obj.toString());
    }
}