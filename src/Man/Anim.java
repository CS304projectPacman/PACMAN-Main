package Man;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package project;

import com.sun.opengl.util.*;
import org.w3c.dom.css.Counter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.media.opengl.*;
import javax.swing.*;

public class Anim extends JFrame implements ActionListener {
    int counter=0;
pac p=new pac();
    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//            System.out.println(i%4);
//        }
     //   JFrame jFrame=new JFrame();
        new Anim();

    }


    public Anim()  {
        GLCanvas glcanvas;
        Animator animator;
//        GLEventListener listener = new maze();
   AnimListener listener = new pac();
   // JLabel jLabel=new JLabel("Score");
JLabel jLabel=new JLabel();
       // JTextField jTextField=new JTextField("Score",p.counter);
       // jButton.add();


        glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(listener);
      glcanvas.addKeyListener(listener);
        getContentPane().add(glcanvas, BorderLayout.CENTER);
     getContentPane().add(jLabel, BorderLayout.SOUTH);
        //getContentPane().add(jTextField, BorderLayout.SOUTH);
        animator = new FPSAnimator(15);
        animator.add(glcanvas);
        animator.start();

        setTitle("Anim Test");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 420);
        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);
        glcanvas.requestFocus();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
