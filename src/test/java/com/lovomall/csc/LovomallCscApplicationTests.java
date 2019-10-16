package com.lovomall.csc;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;

//@SpringBootTest
class LovomallCscApplicationTests {

    @Test
    void demo() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        String string = "{\"userId\": 123}";

        JsonNode jsonNode = mapper.readTree(string);

        System.out.println(jsonNode.get("userId").toString());
    }

}
