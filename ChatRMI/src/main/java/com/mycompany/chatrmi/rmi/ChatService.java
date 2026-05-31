/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.chatrmi.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Carlos Dominguez Figueiras <carlosdominguezfigueiras@gmail.com>
 */
public interface ChatService extends Remote {

    void receiveMessage(String from, String message) throws RemoteException;
}
