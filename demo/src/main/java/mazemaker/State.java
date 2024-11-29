package mazemaker;

import java.io.Serializable;

public enum State implements Serializable{
	active, inactive, drawing, ready2drawRectangle, creatingRectangle, ready2Move, moving,portalstate,obstablestate,ready2resize,resizing
}