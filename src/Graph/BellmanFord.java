package Graph;


/*
*shortest distance between 2 points is the found
* by using BFS
* */

import java.util.Iterator;
import java.util.LinkedList;

public class BellmanFord {
    Graph graph ;
    double[] distances;
    public BellmanFord(Graph graph){
        this.graph = graph ;
        distances = new double[graph.getV()] ;
        for(double d: distances)
            d = Double.MAX_VALUE ;
    }

    void BellmanFordImplementation(int source){
        // initialise the source as 0 ;
        distances[source] = 0 ;

    }

    class Node {
        int vertex;
        int distance ;
        Node previous ;
        protected int getVertex() { return vertex; }
        public int getDistance() { return distance; }
        public void setVertex(int vertex) { this.vertex = vertex; }
        public void setDistance(int distance) { this.distance = distance; }
        public void setPrevious(Node node){ this.previous = node ; }
    }


    public class Graph{

        class Edge{
            Node destination ;
            int distance ;
            public Edge(){
                this.destination = null ;
                this.distance = 0 ;
            }
            public Edge(Node destination , int distance){
                this.destination = destination ;
                this.distance = distance ;
            }
        }

        LinkedList adjList[] ;
        int[][] adjMatrix ;
        protected final int V ;
        protected final int E ;
        public Edge[] edges ;
        public Graph(int V, int E){
            this.V = V ;
            this.E = E ;
            this.adjMatrix = new int[V][V] ;
            adjList = new LinkedList[V] ;
            for(int counter = 0 ; counter< V ; counter++){
                adjList[counter] = new LinkedList()  ;
            }
            // the adjMatrix is automatically assigned to 0
            for(int counter = 0 ; counter< E ; counter++){
                edges[counter] = new Edge() ;
            }
        }
        public LinkedList[] getAdjList() { return adjList; }
        public int[][] getAdjMatrix() { return adjMatrix; }
        protected int getV() { return V; }

        public void BFS(Node source){
            boolean[] visited = new boolean[this.V] ;
            LinkedList<Node> queue  = new LinkedList<>() ;
            queue.add(source);
            while(!queue.isEmpty()){
                // returns the first element of the list
                int node = queue.poll().getVertex() ;
                // if the node is not visited add all the adjacent nodes
                if(!visited[node]){
                    Iterator i = adjList[node].listIterator() ;
                    while(i.hasNext()){
                        Node nextNode = (Node)i.next() ;
                        visited[nextNode.getVertex()]=true ;
                        queue.add(nextNode) ;
                    }
                }
            }
        }

    }


}
