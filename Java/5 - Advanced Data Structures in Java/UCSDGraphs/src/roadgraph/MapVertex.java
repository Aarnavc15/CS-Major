package roadgraph;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import geography.GeographicPoint;

public class MapVertex {
	 private  GeographicPoint loc;
	 private HashSet<MapEdge> neighbours;
	 
	 public MapVertex(GeographicPoint l) {
		 loc = l;
		 neighbours = new HashSet<MapEdge>();
	 }
	 
	 public void addEdge(MapEdge street) {
		 neighbours.add(street);
	 }
	 
	 public GeographicPoint getLoc() {
		 return loc;
	 }
	 
	 public List<MapVertex> getNeighbours(){
		 List<MapVertex> temp = new ArrayList<MapVertex>();
		 for(MapEdge a : neighbours) {
			 temp.add(a.getStop());
		 }
		 return temp;
	 }
	 
	 public List<GeographicPoint> getNeighboursLoc(){
		 List<GeographicPoint> temp = new ArrayList<GeographicPoint>();
		 for(MapEdge a : neighbours) {
			 temp.add(a.getStop().getLoc());
		 }
		 return temp;
	 }
}