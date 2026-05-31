/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatrmi.model;

/**
 *
 * @author Carlos Dominguez Figueiras <carlosdominguezfigueiras@gmail.com>
 */
public class ChatConfig {

    private final String userName;
    private final int localPort;
    private final String localServiceName;
    private final String remoteHost;
    private final int remotePort;
    private final String remoteServiceName;

    public ChatConfig(String userName, int localPort, String localServiceName,
            String remoteHost, int remotePort, String remoteServiceName) {
        this.userName = userName;
        this.localPort = localPort;
        this.localServiceName = localServiceName;
        this.remoteHost = remoteHost;
        this.remotePort = remotePort;
        this.remoteServiceName = remoteServiceName;
    }

    public static ChatConfig fromArgs(String[] args) {
        if (args.length >= 6) {
            return new ChatConfig(args[0], Integer.parseInt(args[1]), args[2],
                    args[3], Integer.parseInt(args[4]), args[5]);
        }

        return new ChatConfig("", 0, "", "", 0, "");
    }

    public boolean hasLocalData() {
        return !userName.isBlank() && localPort > 0 && !localServiceName.isBlank();
    }

    public boolean hasRemoteData() {
        return !remoteHost.isBlank() && remotePort > 0 && !remoteServiceName.isBlank();
    }

    public String getUserName() {
        return userName;
    }

    public int getLocalPort() {
        return localPort;
    }

    public String getLocalServiceName() {
        return localServiceName;
    }

    public String getRemoteHost() {
        return remoteHost;
    }

    public int getRemotePort() {
        return remotePort;
    }

    public String getRemoteServiceName() {
        return remoteServiceName;
    }
}
