package roadgraph;

import geography.GeographicPoint;

public class MapEdge {
	private MapVertex start;
	private MapVertex stop;
	private String street;
	private String streetType;
	private double dist;
	
	public MapEdge(MapVertex e1, MapVertex e2, 
			String roadName, String roadType, double length) {
		start = e1;
		stop = e2;
		street = roadName;
		streetType = roadType;
		dist = length;
	}
	
	public MapVertex getStart() {
		return start;
	}
	public MapVertex getStop() {
		return stop;
	}
	public double getDist(){
		return dist;
	}
}
