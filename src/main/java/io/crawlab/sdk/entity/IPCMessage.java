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
    private final Object data;
    protected final Boolean ipc = true;

    public IPCMessage(MessageType type, Object data) {
        this.type = type.toString().toLowerCase();
        this.data = data;
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

    public Object getData() {
        return data;
    }

    public Boolean getIpc() {
        return ipc;
    }
}
