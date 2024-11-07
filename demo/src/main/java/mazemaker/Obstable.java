package mazemaker;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Obstable extends JLabel{
    private ImageIcon name;
    private int type;
    private boolean passable;

    public Obstable(){
        super();
        name = new ImageIcon("path_to_your_image.png");
        type = 0;
        passable = false;

        this.setIcon(name);
        this.setOpaque(true);
    }
    public Obstable(ImageIcon name, int type, boolean passable) {
        this.name = name;
        this.type = type;
        this.passable = passable;

        this.setIcon(name);
        this.setOpaque(true);
    }
    public boolean isPassable() {
        return passable;
    }

    public void setPassable(boolean passable) {
        this.passable = passable;
    }

    public ImageIcon getNameIcon() {
        return name;
    }

    public void setNameIcon(ImageIcon name) {
        this.name = name;
        this.setIcon(name);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
