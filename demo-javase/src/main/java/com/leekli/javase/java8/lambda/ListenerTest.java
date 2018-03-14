package com.leekli.javase.java8.lambda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * 
 * Listener Lambda
 * 
 * @author media-liwei
 *
 */
public class ListenerTest {
    public static void main(String[] args) {
   
        JButton testButton = new JButton("Test Button");
        testButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Click Detected by Anon Class");
            }
        });
        
        testButton.addActionListener( e-> System.out.println("Click Detected by Anon Class"));
        
        
    }
    
}
