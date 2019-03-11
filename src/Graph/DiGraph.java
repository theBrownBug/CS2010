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

    // for bipartite graphs
    enum Color{ RED , BLUE};

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



    // cycle detection using DFS for undirected graphs
    boolean cycleDetection(int node , boolean[] visited , int parent){

        visited[node] = true ;
        Integer i ;
        Iterator<Integer> it = adjList[node].iterator() ;
        while(it.hasNext()){
            i = it.next() ;
            if(!visited[i]){
                if (cycleDetection(i, visited , node))
                    return true ;
            }
            else if(parent!= i )
                return  true ;
        }
        return false ;
    }

    boolean isCyclic(){
        boolean[] visited = new boolean[V] ;
        for(int i = 0; i<V ; i++)
            visited[i] = false ;
        for(int i= 0 ; i<V ; i++){
            if(!visited[i])
                if (cycleDetection(i,visited , -1) )
                    return true ;
        }
        return  false ;
    }



}
