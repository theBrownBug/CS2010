package Graph;

import java.util.LinkedList;

/**
 * Djikstra using a adjecency Matrix
 * */

public class Djikstra {
    int[][] adjecencyMatrix ;
    LinkedList<Edge>[] adjList ;
    public  int V  ;
    public Djikstra(int V) {
        if (V <= 0){
            System.out.println("Wrong number of vertices");
            System.exit(-1);
        }
        this.V = V ;
        this.adjList = new LinkedList[this.V] ;
        this.adjecencyMatrix = new int[V][V];
        for(int i = 0 ; i < this.V ; i++){
            adjList[i] = new LinkedList() ;
        }
    }

    int minDistance(int[] distance , Boolean[] shortestPathSet){
        int min = Integer.MAX_VALUE ;
        int minIndex = - 1;
        for(int i = 0; i< V ; i++){
            if(!shortestPathSet[i] && distance[i]<= min){
                min = distance[i] ;
                minIndex = i ;
            }
        }
        return minIndex ;
    }
    void PrintSolution(int source , int[] distances){
        for(int counter = 0;  counter< this.V ; counter++){

        }
    }
    void DjikstraImplemenation(int source){
        int distances[] = new int[this.V] ;
        Boolean[] shortestPathSet = new Boolean[V];
        for(int counter= 0 ; counter<V ; counter ++){
            shortestPathSet[counter] = false;
            distances[counter] = Integer.MAX_VALUE ;
        }
        distances[source]= 0 ;
        for(int counter = 0 ; counter< V-1 ; counter++){
            /*
            * pick the vertex with the least distance that has not been visited
            * the source vertex is the first one to be picked
            * */
            int lowestDistanceVertex = minDistance(distances , shortestPathSet) ;
            shortestPathSet[lowestDistanceVertex] = true ;
            for(int x = 0 ; x< V ; x++){
                /*
                * Update only if vertex is not in  sptSet
                * */
                if(!shortestPathSet[x] && adjecencyMatrix[counter][x] !=0 &&
                    distances[counter]!= Integer.MAX_VALUE &&
                    distances[counter] +adjecencyMatrix[counter][x]< distances[x])
                { distances[x]= distances[counter] + adjecencyMatrix[counter][x] ;  }
            }

        }
    }


    void DjikstraAlgorithmImplementationAdjList(int source){
        Node[] nodes = new Node[this.V] ;
        for(Node n: nodes)
            n = new Node() ;
        for(int counter = 0 ; counter< V; counter++){
            Node n = new Node();
            n.setDistance(Integer.MAX_VALUE);
            n.setVertex(counter);
        }

        // set the distance of the source node as 0
        nodes[source].setDistance(0);


    }

    class Node implements Comparable<Node>{
        int vertex ;
        int previous ;
        int distance ;
        public int getVertex() { return vertex; }
        public void setVertex(int vertex) { this.vertex = vertex; }
        public int getPrevious() { return previous; }
        public void setPrevious(int previous) { this.previous = previous; }
        public int getDistance() { return distance; }
        public void setDistance(int distance) { this.distance = distance; }

        @Override
        public int compareTo(Node o) {
            return this.getDistance() - o.getDistance() ;
        }
    }

    class Edge implements Comparable<Edge>{
        int source ;
        int destination;
        int weight;
        public int getDestination() { return destination; }
        public void setDestination(int destination) { this.destination = destination; }
        public int getWeight() { return weight; }
        public void setWeight(int weight){ this.weight = weight; }

        // constructor
        public Edge(int source, int destination , int weight){ this.source = source ; this.destination = destination ; this.weight  = weight  ; }

        @Override
        public int compareTo(Edge o) {
            if(this.getDestination() != o.getDestination()){
                return this.getDestination() - o.getDestination() ;
            }
            else{
                return this.getWeight() - o.getWeight() ;
            }

        }
    }

    public void addEdge(int source , int destination , int weight){
        Edge edge = new Edge(source, destination , weight) ;
        adjList[source].addFirst(edge) ; // add edge
        /*
        //uncomment for implementing undirected graph
        adjList[destination].addFirst(edge) ;
        */
    }




}
