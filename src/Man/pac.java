package Man;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package project;

import Texture.TextureReader;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.media.opengl.*;

import java.sql.SQLOutput;
import java.util.*;
import javax.media.opengl.glu.GLU;

public class pac extends AnimListener {

    boolean balls [][];
    boolean states [][];
boolean creatEeat=true;

HashSet<Pair> foodmap=new HashSet<>();


    AnimGLEventListener3.Directions direction = AnimGLEventListener3.Directions.up;

    int animationIndex = 0;
    int maxWidth = 100;
    int maxHeight = 100;
    int n = maxWidth/2, m = maxHeight/2;
    int x =maxWidth-110/2, y = maxHeight-160/2; // 45 ,20

    String textureNames[] = {"pacman_3_3.png","pacman_3_2.png","pacman_3_0.png","pacman_3_3.png","point.png","maze.png"};
    TextureReader.Texture texture[] = new TextureReader.Texture[textureNames.length];
    int textures[] = new int[textureNames.length];

    /*
     5 means gun in array pos
     x and y coordinate for gun
     */
    public void init(GLAutoDrawable gld) {

        GL gl = gld.getGL();
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);    //This Will Clear The Background Color To Black

        gl.glEnable(GL.GL_TEXTURE_2D);  // Enable Texture Mapping
        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
        gl.glGenTextures(textureNames.length, textures, 0);

        for(int i = 0; i < textureNames.length; i++){
            try {
                texture[i] = TextureReader.readTexture(assetsFolderName + "//" + textureNames[i] , true);
                gl.glBindTexture(GL.GL_TEXTURE_2D, textures[i]);

//                mipmapsFromPNG(gl, new GLU(), texture[i]);
                new GLU().gluBuild2DMipmaps(
                        GL.GL_TEXTURE_2D,
                        GL.GL_RGBA, // Internal Texel Format,
                        texture[i].getWidth(), texture[i].getHeight(),
                        GL.GL_RGBA, // External format from image,
                        GL.GL_UNSIGNED_BYTE,
                        texture[i].getPixels() // Imagedata
                );
            } catch( IOException e ) {
                System.out.println(e);
                e.printStackTrace();
            }
        }

    }

    int r=50;

    public void display(GLAutoDrawable gld) {
        GL gl = gld.getGL();

        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        //Clear The Screen And The Depth Buffer
        gl.glLoadIdentity();
// a3ml el title Screen
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[texture.length-2]);


        animationIndex = animationIndex % 4;
//        DrawGraph(gl);
        drawPoints(gl);
        DrawBackground(gl);
        if (creatEeat) {

            points(gl);

        }
        DrawSprite(gl, x, y, animationIndex, 0.6f,direction);


        handleKeyPress();


        gl.glDisableClientState(GL.GL_TEXTURE_COORD_ARRAY);

        //drowOcircle(gl, 1,new Color(0,0,1,1),21,90,8);

        //foodmap.add(new Pair(1,1));
        //

        //foodmap.contains(new Pair(x,y));

        drowf(gl);
        iseat();
        }

        public void points(GL gl){
        creatEeat=false;

            loops();
            upleft();


                }
    public void loops(){

for (int i = 1;i < 90;i+=7) {
    foodmap.add(new Pair(i, 0));
}
        for (int i = 10 ; i <= 90;i+=7) {
            foodmap.add(new Pair(72, i));
        }

    }
    public void upleft(){
        foodmap.add(new Pair(1,4));
        foodmap.add(new Pair(1,9));

        foodmap.add(new Pair(1,19));
        foodmap.add(new Pair(1,24));
        foodmap.add(new Pair(1,29));

        foodmap.add(new Pair(1,68));
        foodmap.add(new Pair(1,73));
        foodmap.add(new Pair(1,78));
        foodmap.add(new Pair(1,83));
        foodmap.add(new Pair(1,88));


    }

int counter = 0;
        public void iseat(){

        if (foodmap.contains(new Pair(x,y))){

            foodmap.remove(new Pair(x,y));
            counter++;

        }
//System.out.println(counter);

        }

public void drowf(GL gl) {


    Iterator itr = foodmap.iterator();

    while (itr.hasNext()){

        Pair p = (Pair) itr.next();
        DrawFood(gl, p.getX(), p.getY(), .09f);
//
//        System.out.println(p.getX());
//       System.out.println(p.getY());

    }
}



   // boolean flag=true;
//    public void isvisibal(double i,double j){
////        if(x==i/0.1)
////            System.out.println(i);
////        {flag=false;}
//
//        if(x==5/i&&y==5/j) {
//            flag = false;
//        }
//
//    }

    public void DrawFood (GL gl,int x, int y, float scale ){
//        if(50==x&&y==50)
     double m = (x/(maxWidth/2.0) - 0.9);
      double n=(y/(maxHeight/2.0) - 0.9);



            gl.glEnable(GL.GL_BLEND);
            gl.glBindTexture(GL.GL_TEXTURE_2D, textures[4]);    // Turn Blending On


            gl.glPushMatrix();
            gl.glTranslated(m, n, 0);
         //   gl.glScaled(scale, scale, 1);
            gl.glScaled(0.1*scale, 0.1*scale, 1);

            //System.out.println(x +" " + y);
            gl.glBegin(GL.GL_QUADS);
            // Front Face
            gl.glTexCoord2f(0.0f, 0.0f);
            gl.glVertex3f(-1.0f, -1.0f, -1.0f);
            gl.glTexCoord2f(1.0f, 0.0f);
            gl.glVertex3f(1.0f, -1.0f, -1.0f);
            gl.glTexCoord2f(1.0f, 1.0f);
            gl.glVertex3f(1.0f, 1.0f, -1.0f);
            gl.glTexCoord2f(0.0f, 1.0f);
            gl.glVertex3f(-1.0f, 1.0f, -1.0f);
            gl.glEnd();
            gl.glPopMatrix();


            gl.glDisable(GL.GL_BLEND);

    }








    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
    void drowOcircle(GL gl, float r, Color c,double sides, double startAngle , int step){
        gl.glColor3fv(c.getColorComponents(null),0);
        gl.glColor3f(0,1,1);
        gl.glBegin(GL.GL_LINE_LOOP);
        for(int i=0 ;i<360;i+=1)
            gl.glVertex2i((int) (r*Math.cos(Math.toRadians(i))), (int) (r*Math.sin(Math.toRadians(i))));

        gl.glEnd();
        gl.glDisable(GL.GL_BLEND);
    }



    //    public void DrawSprite(GL gl,int x, int y, int index, float scale , int dir){

    public void DrawSprite(GL gl,int x, int y, int index, float scale , AnimGLEventListener3.Directions dir){
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[index]);	// Turn Blending On
        double w = (x/(maxWidth/2.0) - 0.9); //0
        double z=(y/(maxHeight/2.0) - 0.9);//-0.5

        gl.glPushMatrix();
        gl.glTranslated( w, z, 0);
        gl.glScaled(0.1*scale, 0.1*scale, 1);

///////////////////////////////
        int angle=0;
        switch (dir){
            case up:angle=0;break;
            case up_left:angle=45;break;
            case up_right:angle=-45;break;
            case down_left:angle=135;break;
            case down_right:angle=-135;break;
            case right:angle=-90;break;
            case down:angle=180;break;
            case left:angle=90;break;
            default:angle=0;

        }
        //angle
        gl.glRotated(angle,0,0,1);
        //System.out.println(x +" " + y);
        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();


        gl.glDisable(GL.GL_BLEND);
    }
    public void drawPoints(GL gl) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[5]);



                    gl.glPushMatrix();
                    gl.glTranslated(0.25, 2, 0);
                    gl.glScaled(0.25, 0.25, 1);
                    gl.glBegin(GL.GL_QUADS);
                    gl.glTexCoord2f(0.0f, 0.0f);
                    gl.glVertex2d(x, y + 2);
                    gl.glTexCoord2f(0.0f, 1.0f);
                    gl.glVertex2d(x, y + 3);
                    gl.glTexCoord2f(1.0f, 1.0f);
                    gl.glVertex2d(x + 1, y + 3);
                    gl.glTexCoord2f(1.0f, 0.0f);
                    gl.glVertex2d(x + 1, y + 2);
                    gl.glEnd();
                    gl.glPopMatrix();




        gl.glDisable(GL.GL_BLEND);
    }


    public void DrawBackground(GL gl){
        gl.glEnable(GL.GL_BLEND);

        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[texture.length-1]);	// Turn Blending On
//        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[texture.length-2]);

//        gl.glPushMatrix();

        gl.glBegin(GL.GL_QUADS);
        // Front Face
//        new SimpleJoglApp();
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
//        new SimpleJoglApp();

        gl.glEnd();
//        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);

    }


    /*
     * KeyListener
     */

    public void handleKeyPress() {

//        if(x==50  && y==50){
//            x=45;
//            y=19;
//        }
/*
        1 = Rigth , 2=Down  , 3 =left , 0= up

         */
        if (isKeyPressed(KeyEvent.VK_LEFT)&&isKeyPressed(KeyEvent.VK_DOWN)) {
            if (x > 0) {
                x--;
            }
            if (y > 0) {
                y--;
            }
            direction= AnimGLEventListener3.Directions.down_left;
            animationIndex++;
        }else if (isKeyPressed(KeyEvent.VK_RIGHT)&&isKeyPressed(KeyEvent.VK_DOWN)) {
            if (x < maxWidth-10) {
                x++;
            }
            if (y > 0) {
                y--;
            }
            direction= AnimGLEventListener3.Directions.down_right;
            animationIndex++;
        }
        else if (isKeyPressed(KeyEvent.VK_RIGHT)&&isKeyPressed(KeyEvent.VK_UP)) {
            if (x < maxWidth-10) {
                x++;
            }
            if (y < maxHeight-10) {
                y++;
            }
            direction= AnimGLEventListener3.Directions.up_right;
            animationIndex++;
        }
        else if (isKeyPressed(KeyEvent.VK_LEFT)&&isKeyPressed(KeyEvent.VK_UP)) {
            if (x > 0) {
                x--;
            }
            if (y < maxHeight-10) {
                y++;
            }
            direction= AnimGLEventListener3.Directions.up_left;
            animationIndex++;
        }
        else if (isKeyPressed(KeyEvent.VK_LEFT)) {
            if (x > -10) {
                x--;
            }

            else if (x == -10) {
                x=90;
            }
            direction= AnimGLEventListener3.Directions.left;
            animationIndex++;

        }else
        if (isKeyPressed(KeyEvent.VK_RIGHT)) {
            if (x < maxWidth+10) {
                x++;
            }
            else if (x == maxWidth+10) {
                x=0;
            }
            direction= AnimGLEventListener3.Directions.right;
            animationIndex++;
        }else
        if (isKeyPressed(KeyEvent.VK_DOWN)) {
            if (y > 0) {
                y--;
            }
            direction= AnimGLEventListener3.Directions.down;
            animationIndex++;
        }else
        if (isKeyPressed(KeyEvent.VK_UP)) {
            if (y < maxHeight-10) {
                y++;
            }
            direction= AnimGLEventListener3.Directions.up;
            animationIndex++;
        }

        //elbab
//        if (x >= 31&& x<=59 && y==57 ) {
//
//            y++;
//        }
        if (x >= 31&& x<=43 && y==57 ) {

            y++;

        }
        if (x >= 48&& x<=59 && y==57 ) {

            y++;
        }
        if (x >= 31&& x<=43 && y==52 ) {

            y--;
        }
        if (x >= 48&& x<=59 && y==52 ) {

            y--;
        }
        //////////////////////
        if (y >= 40&& y<=56 && x==36) {
            x++;
        }
        /////
        if (y >= 40&& y<=56&& x==31) {
            x--;
        }
        //////////////////////////////////
        if (y >= 40&& y<=56 && x==59) {
            x++;


        }
        /////
        if (y >= 40&& y<=56&& x==55) {
            x--;
        }
        //taht el bab 2
        ////////////////////////////////////////////////
        if (x >= 31&& x<=59 && y==37  ) {
            y++;
        }

        if (x >= 31&& x<=59 && y==30  ) {
            y--;
        }
        //el2fla
        /////////////////////////////////////////////////////
        if (x >= 31&& x<=59 && y==46  ) {
            y++;
        }
        if (x >= 31&& x<=59 && y==40  ) {
            y--;
        }
        ///////////////////////////////////////////////////////
        if (x >= 31&& x<=59 && y==18  ) {
            y++;
        }
        if (x >= 31&& x<=59 && y==11  ) {
            y--;
        }
        ////////////////////////////////////////////////
        //el etnin elly taht
        if (x >= 3&& x<=37 && y==1 ) {
            y--;
        }
        if (x >= 52&& x<=88 && y==1 ) {
            y--;
        }
        if (x >= 52&& x<=88 && y==8 ) {
            y++;
        }
        if (x >= 3&& x<=37 && y==8 ) {
            y++;
        }
        /////////////////////elly fo2///////////////////////////
        if (x >= 31&& x<=59 && y==76 ) {
            y++;
        }
        if (x >= 31&& x<=59 && y==69 ) {
            y--;
        }
        //////////////////////////////////
        if (x >= 3&& x<=17 && y==89 ) {
            y++;
        }
        if (x >= 3&& x<=17 && y==79 ) {
            y--;
        }
        /////////////////////////////////////////////
        if (x >= 21&& x<=38 && y==89 ) {
            y++;
        }
        if (x >= 21&& x<=38 && y==79 ) {
            y--;
        }
        //////////////////////////////////////

        if (x >= 52&& x<=70 && y==89 ) {
            y++;
        }
        if (x >= 52&& x<=70 && y==79 ) {
            y--;
        }
        ///////////////////////////////////////////////
        if (x >= 74&& x<=88 && y==89 ) {
            y++;
        }
        if (x >= 74&& x<=88 && y==79 ) {
            y--;
        }
        /////////////////////////////////////////////////////
        if (x >= 74&& x<=88 && y==76 ) {
            y++;
        }
        if (x >= 74&& x<=88 && y==69 ) {
            y--;
        }
        ////////////////////////////////////////
        if (x >= 3&& x<=16 && y==76 ) {
            y++;
        }
        if (x >= 3&& x<=16 && y==69 ) {
            y--;
        }///////////////////////////////////////////
        if (x >= 74&& x<=90 && y==66) {
            y++;
        }
        if (x >= 74&& x<=90 && y==50 ) {
            y--;
        }//////////////////////////////////////
        if (x >= 0&& x<=16 && y==66) {
            y++;
        }
        if (x >= 0&& x<=16 && y==50 ) {
            y--;
        }
        //////////////////////////////////////////
        if (x >= 0&& x<=16 && y==46) {
            y++;
        }
        if (x >= 0&& x<=16 && y==31 ) {
            y--;
        }
        /////////////////////////////////////////////////
        if (x >= 74&& x<=90 && y==46) {
            y++;
        }
        if (x >= 74&& x<=90 && y==31 ) {
            y--;
        }
        ////////////////////////////////////////////////////
        if (x >= 74&& x<=88 && y==27) {
            y++;
        }
        if (x >= 74&& x<=88  && y==21 ) {
            y--;
        }
        ////////////////////////////////////////////////////
        if (x >= 3&& x<=16 && y==27) {
            y++;
        }
        if (x >= 3&& x<=16  && y==21 ) {
            y--;
        }
        ////////////////////////////////////////////////////
        if (x >= 20&& x<=38 && y==27) {
            y++;
        }
        if (x >= 20&& x<=38  && y==21 ) {
            y--;
        }
        ////////////////////////////////////////////////////
        if (x >= 52&& x<=70 && y==27) {
            y++;
        }
        if (x >= 52&& x<=70  && y==21 ) {
            y--;
        }
        ////////////////////////////////////////////////////
        if (x >= 0&& x<=6 && y==18) {
            y++;
        }
        if (x >= 0&& x<=6  && y==11 ) {
            y--;
        }
        ////////////////////////////////////////////////////
        if (x >= 84&& x<=90 && y==18) {
            y++;
        }
        if (x >= 84&& x<=90  && y==11 ) {
            y--;
        }
        ///////////////////////////////////Y////////////////////////////////////////
        if (y >= 50&& y<=65 && x==74) {
            x--;
        }
        //////////////////////////////////////
        if (y >= 31&& y<=46 && x==74) {
            x--;
        }
        ///////////////////////////////////Y////////////////////////////////////////
        if (y >= 50&& y<=65 && x==17) {
            x++;
        }
        //////////////////////////////////////
        if (y >= 31&& y<=46 && x==17) {
            x++;
        }
        //////////////////////////////////////////////////
        if (y >= 50&& y<=75 && x==27) {
            x++;
        }
        //////////////////////////////////////
        if (y >= 50&& y<=75 && x==20) {
            x--;
        }
        //////////////////////////////////////////////////
        if (y >= 50&& y<=75 && x==70) {
            x++;
        }
        //////////////////////////////////////
        if (y >= 50&& y<=75 && x==63) {
            x--;
        }

        ///////////////////
        if (y >= 60&& y<=72 && x==48) {
            x++;
        }
        //////////////////////////////////////
        if (y >= 60&& y<=72 && x==42) {
            x--;
        }

        ///////////////////
        if (y >= 79&& y<=90 && x==48) {
            x++;
        }
        //////////////////////////////////////
        if (y >= 79&& y<=90 && x==42) {
            x--;
        }
        //////////////////////////////////////
        if (y >= 31&& y<=46 && x==70) {
            x++;
        }
        //
        if (y >= 31&& y<=46 && x==63) {
            x--;
        }
        //////////////////////////////////////
        if (y >= 31&& y<=46 && x==27) {
            x++;
        }
        /////
        if (y >= 31&& y<=46 && x==20) {
            x--;
        }
        /////////////////////////////////////
        if (y >= 21&& y<=34 && x==49) {
            x++;
        }
        /////
        if (y >= 21&& y<=34 && x==42) {
            x--;
        }

        /////////////////////////////////////
        if (y >= 11&& y<=26 && x==16) {
            x++;
        }
        /////
        if (y >= 11&& y<=26 && x==9) {
            x--;
        }
        /////////////////////////////
        if (y >= 11&& y<=26 && x==81) {
            x++;
        }
        /////
        if (y >= 11&& y<=26 && x==74) {
            x--;
        }
        /////////////////////////////
        if (y >= 5&& y<=17 && x==27) {
            x++;
        }
        /////
        if (y >= 5&& y<=17&& x==20) {
            x--;
        }
        /////////////////////////////
        if (y >= 2&& y<=17 && x==49) {
            x++;
        }
        /////
        if (y >= 2&& y<=17&& x==42) {
            x--;
        }
        /////////////////////////////
        if (y >= 5&& y<=17 && x==70) {
            x++;
        }
        /////
        if (y >= 5&& y<=17&& x==63) {
            x--;
        }
        /////////////////////////////////////////////////
        if (x >= 25&& x<=38 && y==66) {
            y++;
        }
        if (x >= 25&& x<=38  && y==59 ) {
            y--;
        }
        /////////////////////
        if (x >= 52&& x<=67 && y==66) {
            y++;
        }
        if (x >= 52&& x<=67 && y==59 ) {
            y--;
        }
        ////////////////////smalls one
        if (y >= 80&& y<=88 && x==88) {
            x++;
        }
        if (y >= 80&& y<=88 && x==74) {
            x--;
        }
        if (y >= 80&& y<=88 && x==70) {
            x++;
        }
        if (y >= 80&& y<=88 && x==52) {
            x--;
        }

        if (y >= 80&& y<=88 && x==38) {
            x++;
        }
        if (y >= 80&& y<=88 && x==20) {
            x--;
        }
        if (y >= 80&& y<=88 && x==16) {
            x++;
        }
        if (y >= 80&& y<=88 && x==2) {
            x--;
        }
//
        if (y >= 70&& y<=75 && x==16) {
            x++;
        }
        if (y >= 70&& y<=75 && x==2) {
            x--;
        }
        //
        if (y >= 70&& y<=75 && x==88) {
            x++;
        }
        if (y >= 70&& y<=75 && x==74) {
            x--;
        }
        //
        if (y >= 70&& y<=75 && x==59) {
            x++;
        }
        if (y >= 70&& y<=75 && x==31) {
            x--;
        }
        ///
        if (y >= 60&& y<=66 && x==38) {
            x++;
        }
        if (y >= 60&& y<=66&& x==52) {
            x--;
        }
        ///
        if (y >= 31&& y<=37 && x==59) {
            x++;
        }
        if (y >= 31&& y<=37 && x==31) {
            x--;
        }
        ////
        if (y >= 21&& y<=27 && x==38) {
            x++;
        }
        if (y >= 21&& y<=27  && x==20) {
            x--;
        }
////
        if (y >= 21&& y<=27  && x==2) {
            x--;
        }
        if (y >= 21&& y<=27  && x==88) {
            x++;
        }
        ////
        if (y >= 21&& y<=27 && x==70) {
            x++;
        }
        if (y >= 21&& y<=27  && x==52) {
            x--;
        }
        ///
        if (y >= 11&& y<=18  && x==85) {
            x--;
        }
        if (y >= 11&& y<=18&& x==5) {
            x++;
        }
        /////
        if (y >= 11&& y<=18 && x==59) {
            x++;
        }
        if (y >= 11&& y<=18  && x==31) {
            x--;
        }
        ///
        if (y >= 2&& y<=8 && x==38) {
            x++;
        }
        if (y >= 2&& y<=8  && x==3) {
            x--;
        }
        ///
        if (y >= 2&& y<=8 && x==87) {
            x++;
        }
        if (y >= 2&& y<=8  && x==52) {
            x--;
        }
        //////////
        if (y >= 53&& y<=57 && x==42) {
            x++;
        }
        if (y >= 53&& y<=57  && x==48) {
            x--;
        }

        //////////
        if (x >= 21&& x<=27 && y==75) {
            y++;
        }
        if (x >= 21&& x<=27 && y==51) {
            y--;
        }

        //
        if (x >= 21&& x<=27 && y==47) {
            y++;
        }
        if (x >= 21&& x<=27 && y==30) {
            y--;
        }
        ////
        if (x >= 21&& x<=27 && y==17) {
            y++;
        }
        ////
        if (x >= 41&& x<=50 && y==2) {
            y--;
        }

        if (x >= 41&& x<=50 && y==21) {
            y--;
        }
        if (x >= 41&& x<=50 && y==60) {
            y--;
        }
        if (x >= 41&& x<=50 && y==80) {
            y--;

        }
        if (x >= 63&& x<=70 && y==75) {
            y++;
        }
        if (x >= 63&& x<=70 && y==50) {
            y--;
        }
        ////
        if (x >= 63&& x<=70 && y==47) {
            y++;
        }
        if (x >= 63&& x<=70 && y==31) {
            y--;
        }
        if (x >= 63&& x<=70 && y==18) {
            y++;
        }
        if (x >= 74&& x<=81 && y==11) {
            y--;
        }

        if (y >= 60&& y<=90 && x==0) {
            x++;
        }
        if (y >= 0&& y<=40 && x==0) {
            x++;
        }

        if (y >= 60&& y<=90 && x==90) {
            x--;

        }
        if (y >= 0&& y<=40 && x==90) {
            x--;
        }




    }

    public BitSet keyBits = new BitSet(256);

    @Override
    public void keyPressed(final KeyEvent event) {
        int keyCode = event.getKeyCode();
        keyBits.set(keyCode);
        System.out.println(x+"X"+" " +y+" Y");
    }

    @Override
    public void keyReleased(final KeyEvent event) {
        int keyCode = event.getKeyCode();
        keyBits.clear(keyCode);
    }

    @Override
    public void keyTyped(final KeyEvent event) {
        // don't care
    }

    public boolean isKeyPressed(final int keyCode) {
        return keyBits.get(keyCode);
    }
}
