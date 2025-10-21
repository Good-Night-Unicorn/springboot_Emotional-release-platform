package com.ws;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 不同的userId会创建不同的实例
 * 相同的userId在@OnMessage每次请求接收到消息时，使用的同一个实例接收
 */
@ServerEndpoint(value = "/ws")
@Component
public class WebSocket {
    /**
     * 存放所有在线的客户端
     */
    private static ConcurrentHashMap<String, WebSocket> webSocketMap = new ConcurrentHashMap<>();
    /**
     * 连接uid和连接会话
     */
    private String fromUserId;

    private String toUserId;
    private Session session;

    //新增一个方法用于主动向客户端发送消息
    public static void sendMessage(String message, String fromUserId, String toUserId) {
        if (toUserId == null || toUserId.isEmpty()) {
            // toUserId为空，发送消息给所有在线用户
            for (Map.Entry<String, WebSocket> entry : webSocketMap.entrySet()) {
                try {
                    if (!entry.getKey().equals(fromUserId)) {
                        entry.getValue().session.getBasicRemote().sendText(message);
                        System.out.println("【websocket消息】发送消息成功,用户id=" + entry.getKey() + ",消息内容：" + message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            // 发送消息给指定用户
            WebSocket webSocket = webSocketMap.get(toUserId);
            if (webSocket != null) {
                try {
                    webSocket.session.getBasicRemote().sendText(message);
                    System.out.println("【websocket消息】发送消息成功,用户id=" + toUserId + ",消息内容：" + message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    //前端请求时一个websocket时
    @OnOpen
    public void onOpen(Session session) {
        Map<String, String> param = parseQueryString(session.getQueryString());
        this.fromUserId = param.get("user_id");
        this.toUserId = param.get("to_id");
        this.session = session;
        webSocketMap.put(fromUserId, this);
        System.out.println("【websocket消息】有新的连接,连接id=" + fromUserId + ":" + this);
    }

    //前端关闭时一个websocket时
    @OnClose
    public void onClose(Session session) {
        Map<String, String> param = parseQueryString(session.getQueryString());
        String fromUserId = param.get("user_id");
        String toUserId = param.get("to_id");
        webSocketMap.remove(fromUserId);
        System.out.println("【websocket消息】连接断开:" + fromUserId);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("【websocket消息】WebSocket发生错误，错误信息为：" + error.getMessage());
        error.printStackTrace();
    }

    //前端向后端发送消息
    @OnMessage
    public void onMessage(String message) {
        System.out.println("【websocket实例】" + this);
        if ("ping".equals(message)) {
            sendMessage("pong", fromUserId, toUserId);
        } else {
            System.out.println("【websocket消息】收到客户端发来的消息:" + message);
            sendMessage(message, fromUserId, toUserId);
        }

    }

    private Map<String, String> parseQueryString(String queryString) {
        Map<String, String> query_pairs = new HashMap<>();
        String[] pairs = queryString.split("&");
        try {
            for (String pair : pairs) {
                int idx = pair.indexOf("=");
                String key = (idx > 0) ? URLDecoder.decode(pair.substring(0, idx), "UTF-8") : pair;
                String value = (idx > 0 && pair.length() > idx + 1) ? URLDecoder.decode(pair.substring(idx + 1), "UTF-8") : null;
                query_pairs.put(key, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return query_pairs;
    }
}
 