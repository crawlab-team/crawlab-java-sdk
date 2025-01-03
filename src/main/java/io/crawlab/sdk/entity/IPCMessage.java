package io.crawlab.sdk.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class IPCMessage {
    public enum MessageType {
        DATA("data"),
        LOG("log");

        private final String value;

        MessageType(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    private final String type;
    private final Object payload;
    protected final Boolean ipc = true;

    public IPCMessage(MessageType type, Object payload) {
        this.type = type.toString().toLowerCase();
        this.payload = payload;
    }

    public String toJSON() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert message to JSON", e);
        }
    }

    public String getType() {
        return type;
    }

    public Object getPayload() {
        return payload;
    }

    public Boolean getIpc() {
        return ipc;
    }
}
