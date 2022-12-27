package Man;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package project;


import com.sun.opengl.util.*;
import java.awt.*;
import javax.media.opengl.*;
import javax.swing.*;
import com.sun.opengl.util.FPSAnimator;
public class Anim extends JFrame {


    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//            System.out.println(i%4);
//        }
        new Anim();

    }



    pac q=new pac();



    public Anim() {
        GLCanvas glcanvas;
        Animator animator;
//        GLEventListener listener = new BouncingCardsGLEventListener();

        
        AnimListener listener = new pac();
//        AnimListener listener =new ghost();
        


        glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(listener);
        glcanvas.addKeyListener(listener);
        getContentPane().add(glcanvas, BorderLayout.CENTER);
        animator = new FPSAnimator(15);
        animator.add(glcanvas);
        animator.start();

        setTitle("PAC-Man");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 420);
        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);
        glcanvas.requestFocus();
    }
}
