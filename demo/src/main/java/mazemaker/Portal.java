package mazemaker;

import javax.swing.ImageIcon;

public class Portal extends Obstable{
    private int PortalId;//目標傳送門
    private int Id;//傳送門
    public Portal() {
        super();
        super.setPassable(true);
        this.Id = 0;
        this.PortalId = 0;
    }
    public Portal(ImageIcon name, int type, boolean passable, int Id) {
        super(name, type, passable);
        super.setPassable(true);
        this.Id = Id;
        this.PortalId = 0;
    }
    public void setPortalId(int PortalId){
        this.PortalId = PortalId;
    }
    public void setId(int Id){
        this.Id = Id;
    }
    public int getPortalId(){
        return this.PortalId;
    }
    public int getId(){
        return Id;
    }
}
