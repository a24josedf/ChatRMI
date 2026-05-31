/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatrmi.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Carlos Dominguez Figueiras <carlosdominguezfigueiras@gmail.com>
 */
public class ChatServiceImpl extends UnicastRemoteObject implements ChatService {

    public interface MessageListener {

        void onMessage(String from, String message);
    }

    private final MessageListener listener;

    public ChatServiceImpl(MessageListener listener) throws RemoteException {
        this.listener = listener;
    }

    @Override
    public void receiveMessage(String from, String message) throws RemoteException {
        listener.onMessage(from, message);
    }
}
