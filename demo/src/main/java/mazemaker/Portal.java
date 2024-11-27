package mazemaker;

public class Portal extends Obstable{
    private int PortalId;//目標傳送門
    private int Id;//傳送門
    public Portal() {
        super();
        super.setPassable(true);
        this.Id = 0;
        this.PortalId = 0;
    }
    public Portal( int type, boolean passable, int Id) {
        super( type, passable);
        super.setPassable(true);
        this.Id = Id;
        this.PortalId = 0;
    }
    // //deep copy
    // public Portal(Portal otherPortal) {
    //     super();
    //     super.setPassable(true);
    //     this.Id = otherPortal.Id;
    //     this.PortalId = otherPortal.PortalId;
    // }
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
