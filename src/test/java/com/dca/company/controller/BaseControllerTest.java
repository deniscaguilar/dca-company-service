package com.dca.company.controller;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;

import java.io.IOException;

public abstract class BaseControllerTest {

    protected static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }

    protected static <T> T convertJsonToObject(String json, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, clazz);
    }

    protected static SetContentTypeResultHandler setContentType(String contentType) {
        return new SetContentTypeResultHandler(contentType);
    }

    protected static class SetContentTypeResultHandler implements ResultHandler {
        private final String contentType;

        private SetContentTypeResultHandler(String contentType) {
            this.contentType = contentType;
        }

        @Override
        public void handle(MvcResult result) throws Exception {
            result.getResponse().setContentType(contentType);
        }
    }
}
