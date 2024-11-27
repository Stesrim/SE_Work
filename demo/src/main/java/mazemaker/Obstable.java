package mazemaker;

import javax.swing.JLabel;

public class Obstable extends JLabel{
    // 0 = player 1 = obstable 2 = endspot
    private int type;
    private boolean passable;

    public Obstable(){
        super();
        type = 0;
        passable = false;
        this.setOpaque(false);
    }
    public Obstable(int type, boolean passable) {
        super();
        this.type = type;
        this.passable = passable;
    }
    // //deep copy
    // public Obstable(Obstable otherObstable){
    //     super();
    //     type = otherObstable.type;
    //     this.type = otherObstable.type;
    //     this.passable = otherObstable.passable;
    // }
    public boolean isPassable() {
        return passable;
    }

    public void setPassable(boolean passable) {
        this.passable = passable;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
