package Man;

 public class Pair {
    int x;
    int y;
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
     @Override
     public int hashCode() {
         int hash = 3;
         hash = 47 * hash + this.x;
         hash = 47 * hash + this.y;
         return hash;
     }

     @Override
     public boolean equals(Object obj) {
         if (obj == null) {
             return false;
         }
         if (getClass() != obj.getClass()) {
             return false;
         }
         final Pair other = (Pair) obj;
         if (this.x != other.x) {
             return false;
         }
         if (this.y != other.y) {
             return false;
         }
         return true;
     }
}