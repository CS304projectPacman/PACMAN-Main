package Man;

import Man.AnimGLEventListener3.Directions;
public class Bullet {


    Directions direction;
        int x ,y;
        boolean fired;
        int initx , intity;

        public Bullet (Directions direction ,int x, int y){
            this.direction=direction;
            this.x=initx=x;
            this.y=intity=y;
            this.fired=true;
            invalidate ();
        }
        public void invalidate (){
            if(x-initx==10){
                fired=false;

            }
            if(y-intity==10){
                fired=false;

            }
        }
    }



