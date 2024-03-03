package com.kit.integrationmanager.model;


import lombok.Getter;
import lombok.Setter;


public class ServerInfo {
    private String protocol;
    private String host_name;
    private int port =8090;

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getHost_name() {
        return host_name;
    }

    public void setHost_name(String host_name) {
        this.host_name = host_name;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
