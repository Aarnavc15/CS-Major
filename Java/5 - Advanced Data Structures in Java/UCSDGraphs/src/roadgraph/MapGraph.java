/**
 * @author UCSD MOOC development team and YOU
 * 
 * A class which reprsents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
package roadgraph;


import java.util.List;

import java.util.Queue;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.function.Consumer;
import java.util.Comparator;
import java.util.PriorityQueue;
import geography.GeographicPoint;
import util.GraphLoader;

/**
 * @author UCSD MOOC development team and YOU
 * 
 * A class which represents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
public class MapGraph {
	//TODO: Add your member variables here in WEEK 3
	
	private HashMap<GeographicPoint, MapVertex> graph;
	private HashMap<String, MapEdge> streets;
	/** 
	 * Create a new empty MapGraph 
	 */
	public MapGraph()
	{
		graph = new HashMap<GeographicPoint, MapVertex>();
		streets = new HashMap<String, MapEdge>();
	}
	
	/**
	 * Get the number of vertices (road intersections) in the graph
	 * @return The number of vertices in the graph.
	 */
	public int getNumVertices()
	{
		//TODO: Implement this method in WEEK 3
		return graph.size();
	}
	
	/**
	 * Return the intersections, which are the vertices in this graph.
	 * @return The vertices in this graph as GeographicPoints
	 */
	public Set<GeographicPoint> getVertices()
	{
		//TODO: Implement this method in WEEK 3
		return graph.keySet();
	}
	
	/**
	 * Get the number of road segments in the graph
	 * @return The number of edges in the graph.
	 */
	public int getNumEdges()
	{
		//TODO: Implement this method in WEEK 3
		return streets.size();
	}

	public boolean printGraph() {
		for(GeographicPoint g : getVertices()) {
			System.out.println(g.getX() + ", " + g.getY());
		}
		return false; 
	}
	
	/** Add a node corresponding to an intersection at a Geographic Point
	 * If the location is already in the graph or null, this method does 
	 * not change the graph.
	 * @param location  The location of the intersection
	 * @return true if a node was added, false if it was not (the node
	 * was already in the graph, or the parameter is null).
	 */
	public boolean addVertex(GeographicPoint location)
	{
		graph.put(location, new MapVertex(location));
		return false;
	}
	
	/**
	 * Adds a directed edge to the graph from pt1 to pt2.  
	 * Precondition: Both GeographicPoints have already been added to the graph
	 * @param from The starting point of the edge
	 * @param to The ending point of the edge
	 * @param roadName The name of the road
	 * @param roadType The type of the road
	 * @param length The length of the road, in km
	 * @throws IllegalArgumentException If the points have not already been
	 *   added as nodes to the graph, if any of the arguments is null,
	 *   or if the length is less than 0.
	 */
	public void addEdge(GeographicPoint from, GeographicPoint to, String roadName,
			String roadType, double length) throws IllegalArgumentException {
		if(!graph.containsKey(to) || !graph.containsKey(from)) {
			throw new IllegalArgumentException("Enter Valid vertex");
		}
		
		else {
			MapVertex v1 = graph.get(from);
			MapVertex v2 = graph.get(to);
			double d = from.distance(to);
			MapEdge eNew = new MapEdge(v1, v2, roadName, roadType, d);
			streets.put(roadName, eNew);
			v1.addEdge(eNew);
		}

		//TODO: Implement this method in WEEK 3
		
	}
	

	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return bfs(start, goal, temp);
	}
	
	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, 
			 					     GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 3
		
		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());
		LinkedList<GeographicPoint> path = new LinkedList<GeographicPoint>();
		if(!graph.containsKey(start) || !graph.containsKey(goal)) {
			System.out.println("No Path Exists");
			return path;
		}
		HashMap<GeographicPoint, GeographicPoint> parentMap = new HashMap<GeographicPoint
				, GeographicPoint>();
		
		boolean found = breathSearch(start, goal, parentMap, nodeSearched);
		if(!found) {
			System.out.println("No Path Exists00");
			return path;
		}

		else {
			return reconstruct(start, goal, parentMap);
		}
		
	}
	
	private boolean breathSearch(GeographicPoint start, 
		     GeographicPoint goal, HashMap<GeographicPoint, GeographicPoint> parentMap,
		     Consumer<GeographicPoint> nodeSearched) {
		
		
		Queue<GeographicPoint> toSearch = new LinkedList<GeographicPoint> ();
		HashSet<GeographicPoint> visited = new HashSet<GeographicPoint>();
		toSearch.add(start);
		visited.add(start);
		boolean found = false;
		
		while(!toSearch.isEmpty()) {
			GeographicPoint curr = toSearch.remove();
			if(curr.equals(goal)) {
				found = true;
				break;
			}
			List<GeographicPoint> neighbours = graph.get(curr).getNeighboursLoc();
			visited.add(curr);
				for(GeographicPoint g : neighbours) {
				if(!visited.contains(g)) {
					toSearch.add(g);
					parentMap.put(g, curr);
					visited.add(g);
					nodeSearched.accept(g);
				}
			}
			
		}
		
		return found;
	}
	
	private LinkedList<GeographicPoint> reconstruct(GeographicPoint start, 
		     GeographicPoint goal, HashMap<GeographicPoint, GeographicPoint> parentMap){
		LinkedList<GeographicPoint> path = new LinkedList<GeographicPoint>();
		GeographicPoint curr = goal;
		while(curr != start) {
			path.add(curr);
			curr = parentMap.get(curr); 
		}
		
		path.add(start);
		
		return path;
	}
	
	public void test1(GeographicPoint g) {
		System.out.println(graph.containsKey(g));
	}

	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	
	public List<GeographicPoint> dijkstra(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
		// You do not need to change this method.
        Consumer<GeographicPoint> temp = (x) -> {};
        return dijkstra(start, goal, temp);
	}
	
	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, 
										  GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		LinkedList<GeographicPoint> path = new LinkedList<GeographicPoint>();
		if(!graph.containsKey(start) || !graph.containsKey(goal)) {
			System.out.println("No Path Exists");
			return path;
		}
		HashMap<GeographicPoint, GeographicPoint> parentMap = new HashMap<GeographicPoint, GeographicPoint>();
		boolean pathVar = dijk(start, goal, parentMap, nodeSearched);
		if(pathVar) {
			path = reconstruct(start, goal, parentMap);
			return path;
		}
		else {
			System.out.println("No Path Exists");
			return path;
		}
		
	}
	
	private boolean dijk(GeographicPoint start, 
		     GeographicPoint goal, HashMap<GeographicPoint, GeographicPoint> parentMap,
		     Consumer<GeographicPoint> nodeSearched) {
		
		int initialCapacity = 10;
		PriorityQueue<GeographicPoint> toVisit = new PriorityQueue<>(initialCapacity,
				new Comparator<GeographicPoint> () {

		    @Override
		    public int compare(GeographicPoint e1, GeographicPoint e2) {
		        return graph.get(e1).compareTo(graph.get(e2));
		        }
		    }

		);
		
		HashSet<GeographicPoint> visited = new HashSet<GeographicPoint>();
		for(MapVertex v : graph.values()) {
			if(!v.equals(graph.get(start))) {
				v.setDistance(Double.POSITIVE_INFINITY);
			}
		}
		GeographicPoint currNode;
		MapVertex vert;
		toVisit.add(start);
		while(!toVisit.isEmpty()) {
			currNode = toVisit.remove();
			vert = graph.get(currNode);
			if(!visited.contains(currNode)) {
				nodeSearched.accept(currNode);
				visited.add(currNode);
				System.out.println(currNode + " ");
				if(currNode.equals(goal)) {return true;}
				for(MapVertex g : vert.getNeighbours()) {
					if(!visited.contains(g.getLoc())) {
						double newDist = vert.getDistance() 
								+ vert.getPath(g).getDist();
						if(newDist < g.getDistance()) {
							g.setDistance(newDist);
							parentMap.put(g.getLoc(), currNode);
							toVisit.add(g.getLoc());
						}
					}
				}
				// I am Here Right Now, I need to finish Dijkstra
			}
		}
		return false;
	}

	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return aStarSearch(start, goal, temp);
	}
	
	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, 
											 GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		LinkedList<GeographicPoint> path = new LinkedList<GeographicPoint>();
		if(!graph.containsKey(start) || !graph.containsKey(goal)) {
			System.out.println("No Path Exists");
			return path;
		}
		HashMap<GeographicPoint, GeographicPoint> parentMap = new HashMap<GeographicPoint, GeographicPoint>();
		boolean pathVar = aStar(start, goal, parentMap, nodeSearched);
		if(pathVar) {
			path = reconstruct(start, goal, parentMap);
			return path;
		}
		else {
			System.out.println("No Path Exists");
			return path;
		}
		
	}
	

	private boolean aStar(GeographicPoint start, 
		     GeographicPoint goal, HashMap<GeographicPoint, GeographicPoint> parentMap,
		     Consumer<GeographicPoint> nodeSearched) {
		
		int initialCapacity = 10;
		PriorityQueue<GeographicPoint> toVisit = new PriorityQueue<>(initialCapacity,
				new Comparator<GeographicPoint> () {

		    @Override
		    public int compare(GeographicPoint e1, GeographicPoint e2) {
		        return graph.get(e1).compareTo(graph.get(e2));
		        }
		    }

		);
		
		HashSet<GeographicPoint> visited = new HashSet<GeographicPoint>();
		for(MapVertex v : graph.values()) {
			if(!v.equals(graph.get(start))) {
				v.setDistance(Double.POSITIVE_INFINITY);
			}
			if(!v.equals(graph.get(goal))) {
				v.setPred(Double.POSITIVE_INFINITY);
			}
		}
		GeographicPoint currNode;
		MapVertex vert;
		toVisit.add(start);
		while(!toVisit.isEmpty()) {
			currNode = toVisit.remove();
			vert = graph.get(currNode);
			if(!visited.contains(currNode)) {
				nodeSearched.accept(currNode);
				visited.add(currNode);
				System.out.println(currNode + " ");
				if(currNode.equals(goal)) {return true;}
				for(MapVertex g : vert.getNeighbours()) {
					if(!visited.contains(g.getLoc())) {
						double newDist = vert.getDistance() 
								+ vert.getPath(g).getDist();
						double newPred = goal.distance(g.getLoc());
						if(newDist + newPred < g.getDistance() + g.getPred()) {
							g.setDistance(newDist);
							g.setPred(newPred);
							parentMap.put(g.getLoc(), currNode);
							toVisit.add(g.getLoc());
						}
					}
				}
				// I am Here Right Now, I need to finish Dijkstra
			}
		}
		return false;
	}
	
	public static void main(String[] args)
	{
		/* System.out.print("Making a new map...");
		MapGraph firstMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", firstMap);
		System.out.println("DONE.");
		System.out.println(firstMap.bfs(new GeographicPoint(1.0, 1.0), 
				new GeographicPoint(4.0,1.0)));
		// You can use this method for testing.  
		
		
		/* Here are some test cases you should try before you attempt 
		 * the Week 3 End of Week Quiz, EVEN IF you score 100% on the 
		 * programming assignment.
		 */
		
		MapGraph simpleTestMap = new MapGraph();
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", simpleTestMap);
		
		GeographicPoint testStart = new GeographicPoint(1.0, 1.0);
		GeographicPoint testEnd = new GeographicPoint(8.0, -1.0);
		
		System.out.println("Test 1 using simpletest: Dijkstra should be 9 and AStar should be 5");
		List<GeographicPoint> testroute = simpleTestMap.dijkstra(testStart,testEnd);
		System.out.println("Hola pause");
		List<GeographicPoint> testroute2 = simpleTestMap.aStarSearch(testStart,testEnd);
		System.out.println(testroute);
		System.out.println(testroute2);
		
		MapGraph testMap = new MapGraph();
		GraphLoader.loadRoadMap("data/maps/utc.map", testMap);
		
		// A very simple test using real data
		testStart = new GeographicPoint(32.869423, -117.220917);
		testEnd = new GeographicPoint(32.869255, -117.216927);
		System.out.println("Test 2 using utc: Dijkstra should be 13 and AStar should be 5");
		testroute = testMap.dijkstra(testStart,testEnd);
		System.out.println("Hola pause");
		testroute2 = testMap.aStarSearch(testStart,testEnd);
		
		
		// A slightly more complex test using real data
		testStart = new GeographicPoint(32.8674388, -117.2190213);
		testEnd = new GeographicPoint(32.8697828, -117.2244506);
		System.out.println("Test 3 using utc: Dijkstra should be 37 and AStar should be 10");
		testroute = testMap.dijkstra(testStart,testEnd);
		System.out.println("Hola pause");
		testroute2 = testMap.aStarSearch(testStart,testEnd);
		
		
		/* Use this code in Week 3 End of Week Quiz */
		/*MapGraph theMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/maps/utc.map", theMap);
		System.out.println("DONE.");

		GeographicPoint start = new GeographicPoint(32.8648772, -117.2254046);
		GeographicPoint end = new GeographicPoint(32.8660691, -117.217393);
		
		
		List<GeographicPoint> route = theMap.dijkstra(start,end);
		List<GeographicPoint> route2 = theMap.aStarSearch(start,end);

		*/
		
	}
	
}
