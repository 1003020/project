package a;

import java.awt.geom.Point2D;

public class ComponentStorageUnit {
	
	private Component component;
	private Point2D.Double topCorner;
	private Point2D.Double bottomCorner;
	private int identifier;
	
	public ComponentStorageUnit(Component c, Point2D.Double tc, Point2D.Double bc, int id){
		component = new Component(c);
		topCorner = tc;
		bottomCorner = bc;
		identifier = id;
	}
	
	
	public Component getComponent() {
		return component;
	}
	public void setComponent(Component component) {
		this.component = component;
	}
	
	public Point2D getTopCorner() {
		return topCorner;
	}
	public void setTopCorner(Point2D.Double topCorner) {
		this.topCorner = topCorner;
	}
	
	public Point2D getBottomCorner() {
		return bottomCorner;
	}
	public void setBottomCorner(Point2D.Double bottomCorner) {
		this.bottomCorner = bottomCorner;
	}
	
	public int getIdentifier() {
		return identifier;
	}
	public void setIdentifier(int identifier) {
		this.identifier = identifier;
	}
	
	
}
