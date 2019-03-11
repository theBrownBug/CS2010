package Graph;

import java.util.Iterator;
import java.util.LinkedList;
/*
*
* Assuming that the graph provided is a connected graph with only 1 component
* */
public class DiGraph {

    LinkedList adjList[] ;
    int[][] adjMatrix ;
    int V ;

    public DiGraph(int v){
        this.V = v ;
        this.adjList = new LinkedList[V] ;
        // initialise adjlist
        for(int counter = 0 ; counter< V ; counter++){
            adjList[counter] = new LinkedList();
        }
        adjMatrix = new int[V][V]  ;
    }

    public LinkedList[] getAdjList() { return adjList; }
    public int[][] getAdjMatrix() { return adjMatrix; }
    public int getV() { return V; }


    public static void addEdge(DiGraph graph , int source, int destination){
        graph.adjMatrix[source][destination] = 1 ;
        graph.adjList[source].add(destination) ;
        // not adding symmetric entries as this is a directed graph
    }



    public void BFS(int source){
        boolean[] visited = new boolean[V];

        LinkedList<Integer> queue = new LinkedList<Integer>() ;
        visited[source]= true ;
        queue.add(source) ;
        while(queue.size()!=0){

            int currentNode = queue.poll() ;
            System.out.println(currentNode);

            Iterator<Integer> iterator = adjList[currentNode].listIterator() ;
            while(iterator.hasNext()){
                int childNode = iterator.next() ;
                visited[childNode] = true ;
                queue.add(childNode) ;
            }
        }
    }
    public void DFS(int source){
        boolean[] visited = new boolean[V] ;
        DFSHelper(source, visited);
    }

    public void DFSHelper(int source , boolean[] visited){

        visited[source] = true ;
        System.out.println(source);
        Iterator<Integer> iterator = adjList[source].listIterator() ;
        // implicit stack
        while(iterator.hasNext()){
            int nextNode = iterator.next() ;
            if(!visited[nextNode]){
                DFSHelper(nextNode, visited);
            }
        }
    }
}
