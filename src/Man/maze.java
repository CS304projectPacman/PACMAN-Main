package Man;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

import static Man.maze.tile.*;

public class maze implements GLEventListener {
    public int maze_tex;
    public int pill_tex;
    public int bigPill_tex;
    public int gameTick;
    public int fruitSpawned;
    public int fruitCounter;

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
//        drowOcircle(50,0.5f,0.9f,8);

    }

//    void drowOcircle(int r, double sides, double startAngle , int step){
////        gl.glColor3fv(c.getColorComponents(null),0);
//        GL gl = gld.getGL();
//        gl.glColor3f(0,0,1);
//        gl.glBegin(GL.GL_LINE_LOOP);
//        for(int i=0 ;i<360;i+=1)
//            gl.glVertex2i((int) (r*Math.cos(Math.toRadians(i))+202), (int) (r*Math.sin(Math.toRadians(i))));
//        gl.glEnd();
//
//    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }

    @Override
    public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b1) {

    }

    /*
     *       W: Wall
     *      G: Ghost Pen Gate
     *      P: Portal
     *      u: Empty path
     *      o: Pill
     *      e: Eaten Pill
     *      O: Big Pill
     *      E: Eaten Big Pill*/
    enum tile {W, G, P, u, o, e, O, E, F}

    ;

    tile maze[][] =
            {
                    {W, W, W, W, W, W, W, W, W, W, W, W, u, u, u, W, P, W, u, u, u, W, W, W, W, W, W, W, W, W, W},
                    {W, o, o, o, o, W, W, O, o, o, o, W, u, u, u, W, u, W, u, u, u, W, o, o, o, o, O, o, o, o, W},
                    {W, o, W, W, o, W, W, o, W, W, o, W, u, u, u, W, u, W, u, u, u, W, o, W, W, o, W, W, W, o, W},
                    {W, o, W, W, o, o, o, o, W, W, o, W, u, u, u, W, u, W, u, u, u, W, o, W, W, o, W, W, W, o, W},
                    {W, o, W, W, o, W, W, W, W, W, o, W, u, u, u, W, u, W, u, u, u, W, o, W, W, o, W, W, W, o, W},
                    {W, o, W, W, o, W, W, W, W, W, o, W, W, W, W, W, u, W, W, W, W, W, o, W, W, o, W, W, W, o, W},
                    {W, o, W, W, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, W},
                    {W, o, W, W, W, W, W, o, W, W, o, W, W, W, W, W, u, W, W, W, W, W, W, W, W, o, W, W, W, o, W},
                    {W, o, W, W, W, W, W, o, W, W, o, W, W, W, W, W, u, W, W, W, W, W, W, W, W, o, W, W, W, o, W},
                    {W, o, W, W, o, o, o, o, W, W, o, u, u, u, u, u, u, u, u, u, W, W, o, o, o, o, W, W, W, o, W},
                    {W, o, W, W, o, W, W, o, W, W, o, W, W, u, W, W, W, W, W, u, W, W, o, W, W, o, W, W, W, o, W},
                    {W, o, W, W, o, W, W, o, W, W, o, W, W, u, W, u, u, u, W, u, W, W, o, W, W, o, W, W, W, o, W},
                    {W, o, o, o, o, W, W, o, o, o, o, W, W, u, W, u, u, u, W, u, u, u, o, W, W, o, o, o, o, o, W},
                    {W, o, W, W, W, W, W, u, W, W, W, W, W, u, W, u, u, u, G, u, W, W, W, W, W, o, W, W, W, W, W},
                    {W, o, W, W, W, W, W, u, W, W, W, W, W, u, W, u, u, u, G, u, W, W, W, W, W, o, W, W, W, W, W},
                    {W, o, o, o, o, W, W, o, o, o, o, W, W, u, W, u, u, u, W, u, u, u, o, W, W, o, o, o, o, o, W},
                    {W, o, W, W, o, W, W, o, W, W, o, W, W, u, W, u, u, u, W, u, W, W, o, W, W, o, W, W, W, o, W},
                    {W, o, W, W, o, W, W, o, W, W, o, W, W, u, W, W, W, W, W, u, W, W, o, W, W, o, W, W, W, o, W},
                    {W, o, W, W, o, o, o, o, W, W, o, u, u, u, u, u, u, u, u, u, W, W, o, o, o, o, W, W, W, o, W},
                    {W, o, W, W, W, W, W, o, W, W, o, W, W, W, W, W, u, W, W, W, W, W, W, W, W, o, W, W, W, o, W},
                    {W, o, W, W, W, W, W, o, W, W, o, W, W, W, W, W, u, W, W, W, W, W, W, W, W, o, W, W, W, o, W},
                    {W, o, W, W, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, o, W},
                    {W, o, W, W, o, W, W, W, W, W, o, W, W, W, W, W, u, W, W, W, W, W, o, W, W, o, W, W, W, o, W},
                    {W, o, W, W, o, W, W, W, W, W, o, W, u, u, u, W, u, W, u, u, u, W, o, W, W, o, W, W, W, o, W},
                    {W, o, W, W, o, o, o, o, W, W, o, W, u, u, u, W, u, W, u, u, u, W, o, W, W, o, W, W, W, o, W},
                    {W, o, W, W, o, W, W, o, W, W, o, W, u, u, u, W, u, W, u, u, u, W, o, W, W, o, W, W, W, o, W},
                    {W, o, o, o, o, W, W, O, o, o, o, W, u, u, u, W, u, W, u, u, u, W, o, o, o, o, O, o, o, o, W},
                    {W, W, W, W, W, W, W, W, W, W, W, W, u, u, u, W, P, W, u, u, u, W, W, W, W, W, W, W, W, W, W}
            };
}
