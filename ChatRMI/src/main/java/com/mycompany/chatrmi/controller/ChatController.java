/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatrmi.controller;

import com.mycompany.chatrmi.model.ChatConfig;
import com.mycompany.chatrmi.rmi.ChatService;
import com.mycompany.chatrmi.rmi.ChatServiceImpl;
import com.mycompany.chatrmi.view.ChatJFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.ConnectException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Carlos Dominguez Figueiras <carlosdominguezfigueiras@gmail.com>
 */
public class ChatController {

    private final ChatJFrame view;
    private ChatConfig config;
    private ChatService servicioLocal;
    private ChatService servicioRemoto;
    private Registry registryLocal;

    public ChatController(ChatJFrame view, ChatConfig config) {
        this.view = view;
        this.config = config;
        initEventHandlers();
    }

    public void publicarServicioLocal() {
        try {
            this.config = view.getChatConfig();
            if (!config.hasLocalData()) {
                throw new IllegalArgumentException("Introduce usuario, puerto local y nombre local.");
            }
            this.servicioLocal = new ChatServiceImpl(new ChatServiceImpl.MessageListener() {
                @Override
                public void onMessage(String from, String message) {
                    mostrarMensajeRecibido(from, message);
                }
            });

            try {
                this.registryLocal = LocateRegistry.createRegistry(config.getLocalPort());
                view.addSystemMessage("Registry creado en el puerto local " + config.getLocalPort());
            } catch (RemoteException ex) {
                this.registryLocal = LocateRegistry.getRegistry(config.getLocalPort());
                view.addSystemMessage("Usando registry existente en el puerto local " + config.getLocalPort());
            }

            this.registryLocal.rebind(config.getLocalServiceName(), servicioLocal);
            view.addSystemMessage("Servicio publicado como " + config.getLocalServiceName());
            view.setPublished(true);
        } catch (Exception ex) {
            view.setPublished(false);
            mostrarError("No se pudo publicar el servicio local", ex);
        }
    }

    private void conectarConNodoRemoto() {
        try {
            this.config = view.getChatConfig();
            if (!config.hasRemoteData()) {
                throw new IllegalArgumentException("Introduce host remoto, puerto remoto y nombre remoto.");
            }
            Registry registryRemoto = LocateRegistry.getRegistry(config.getRemoteHost(), config.getRemotePort());
            this.servicioRemoto = (ChatService) registryRemoto.lookup(config.getRemoteServiceName());
            view.addSystemMessage("Conectado con "
                    + config.getRemoteHost() + ":" + config.getRemotePort()
                    + "/" + config.getRemoteServiceName());
            view.setConnected(true);
        } catch (Exception ex) {
            view.setConnected(false);
            if (ex instanceof ConnectException) {
                mostrarError("No se pudo conectar con el otro nodo. Arranca primero la otra instancia y revisa el puerto remoto", ex);
            } else {
                mostrarError("No se pudo conectar con el otro nodo", ex);
            }
        }
    }

    private void enviarMensaje() {
        String message = view.getMessage();
        if (message.isBlank()) {
            return;
        }

        try {
            this.config = view.getChatConfig();
        } catch (Exception ex) {
            mostrarError("Revisa los datos de configuracion", ex);
            return;
        }

        if (servicioRemoto == null) {
            conectarConNodoRemoto();
        }

        if (servicioRemoto == null) {
            return;
        }

        try {
            servicioRemoto.receiveMessage(config.getUserName(), message);
            view.addOwnMessage(config.getUserName(), message);
            view.clearMessage();
        } catch (Exception ex) {
            servicioRemoto = null;
            view.setConnected(false);
            mostrarError("No se pudo enviar el mensaje", ex);
        }
    }

    private void mostrarMensajeRecibido(String from, String message) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                view.addRemoteMessage(from, message);
            }
        });
    }

    private void initEventHandlers() {
        view.publishButtonAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                publicarServicioLocal();
            }
        });

        view.connectButtonAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conectarConNodoRemoto();
            }
        });

        view.sendButtonAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enviarMensaje();
            }
        });

        view.exitMenuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void mostrarError(String text, Exception ex) {
        view.addSystemMessage(text + ": " + ex.getMessage());
        JOptionPane.showMessageDialog(view, text + "\n" + ex.getMessage(), "Error RMI",
                JOptionPane.ERROR_MESSAGE);
    }
}
