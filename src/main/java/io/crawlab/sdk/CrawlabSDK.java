package io.crawlab.sdk;

import io.crawlab.sdk.entity.IPCMessage;

public class CrawlabSDK {
    public static void saveItem(Object... items) {
        // Save items using IPC
        IPCMessage msg = new IPCMessage(IPCMessage.MessageType.DATA, items);
        System.out.println(msg.toJSON());
    }
}
