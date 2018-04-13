package com.shrimp.web.httpclient;

import org.apache.http.conn.HttpClientConnectionManager;

/**
 * 功能说明: 定期关闭http无效连接<br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-04-12<br>
 */
public class IdleConnectionEvictor extends Thread {

    private final HttpClientConnectionManager connectionManager;

    private volatile boolean shutdown;

    public IdleConnectionEvictor(HttpClientConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
        // 开启线程
        this.start();
    }

    @Override
    public void run() {
        try {
            while (!shutdown) {
                synchronized (this) {
                    wait(5000);
                    connectionManager.closeExpiredConnections();
                }
            }
        } catch (InterruptedException e) {
            // 结束
        }
    }

    public void shutdown() {
        shutdown = true;
        synchronized (this) {
            notifyAll();
        }
    }
}
