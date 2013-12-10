package a;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;

public class Wire {
	private Port sink;
	private Port nub;
	private ArrayList<Point2D.Double> path;
	
	public Wire(){
		path = new ArrayList<Point2D.Double>();
	}

	public Wire(Port sink, Port nub, ArrayList<Double> path) {
		super();
		this.sink = sink;
		this.nub = nub;
		this.path = path;
	}
	
	
	
}
