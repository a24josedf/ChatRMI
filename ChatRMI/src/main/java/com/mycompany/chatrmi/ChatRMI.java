/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.chatrmi;

import com.mycompany.chatrmi.controller.ChatController;
import com.mycompany.chatrmi.model.ChatConfig;
import com.mycompany.chatrmi.view.ChatJFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Carlos Dominguez Figueiras <carlosdominguezfigueiras@gmail.com>
 */
public class ChatRMI {

    public static void main(String[] args) {
        ChatConfig config = ChatConfig.fromArgs(args);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ChatJFrame view = new ChatJFrame(config);
                ChatController controller = new ChatController(view, config);
                view.setVisible(true);
            }
        });
    }
}
