package roadgraph;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import geography.GeographicPoint;

public class MapVertex {
	 private  GeographicPoint loc;
	 private HashSet<MapEdge> neighbours;
	 private Double dist;
	 private Double pred;
	 
	 public MapVertex(GeographicPoint l) {
		 loc = l;
		 neighbours = new HashSet<MapEdge>();
		 dist = 0.;
		 pred = 0.;
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
	 
	 public MapEdge getPath(MapVertex v) {
		 for(MapEdge e : neighbours) {
			 if(e.getStop().equals(v)) {
				 return e;
			 }
		 }
		 return null;
	 }
	 
	 public List<GeographicPoint> getNeighboursLoc(){
		 List<GeographicPoint> temp = new ArrayList<GeographicPoint>();
		 for(MapEdge a : neighbours) {
			 temp.add(a.getStop().getLoc());
		 }
		 return temp;
	 }
	 public double getDistance() {
		 return dist;
	 }
	 public void setDistance(double a) {
		 dist = a;
	 }
	 public double getPred() {
		 return pred;
	 }
	 public void setPred(double a) {
		 pred = a;
	 }
	 public int compareTo(MapVertex e) {
		 if(dist + pred>e.getDistance() + e.getPred()) {
			 return 1;
		 }
		 else if(dist + pred<e.getDistance()+ e.getPred()) {
			 return -1;
		 }
		 else {
			 return 0;
		 }
	 }
}
