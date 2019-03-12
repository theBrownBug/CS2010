package Graph;

import java.util.HashMap;
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
    /*
    * It is not possible to detect bipartite with odd cycles with 2 colors
    *
    * */
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

    /*
    *
    * using BFS and adjMatrix
    * */
    public boolean isBipartiteBFSAdjMatrix(int source , int[][] adjMatrix){
        boolean[] visited = new boolean[V] ;
        int[] colorArray = new int[V] ;
        for(int counter = 0 ; counter< V ; counter++){ colorArray[counter] = -1 ; }
        LinkedList<Integer> queue = new LinkedList<>() ;
        visited[source] = true;

        colorArray[source] = 1 ;
        queue.add(source) ;

        while(queue.size()!=0){
            int i = queue.poll();
            // if there are self loops, the graph is not bipartite
            if(adjMatrix[i][i] ==1){return false ;}
            // iterating over all the neighbors of the current vertex
            for(int counter = 0 ; counter< V ; counter++){
                // if the child vertex was unvisited till now and uncolored
                if(adjMatrix[source][counter] ==1 && colorArray[counter]==-1){
                    colorArray[counter] = 1 - colorArray[source] ;
                    queue.add(counter);
                }
                // if the child node is visited and the color of the child is the same as
                else if(adjMatrix[source][counter]==1 && colorArray[counter]== colorArray[source]){
                    return false ;
                }
            }
        }
        // if we reach here, its a bipartite graph
        return  true ;
    }

    /*
    *
    *
    * */
    public boolean isBipartiteBFSAdjList(int source , LinkedList[] adjList){
        boolean[] visited = new boolean[V] ;
        int[] colorArray = new int[V] ;
        for(int i = 0; i<V ; i++){ colorArray[i] = -1; }
        LinkedList<Integer> queue = new LinkedList<>();
        visited[source] = true;
        queue.add(source) ;

        while(queue.size()!=0){
            int nextSource = queue.poll()  ;
            Iterator<Integer> it = adjList[nextSource].listIterator() ;
            while(it.hasNext()){
                int nextNeighbour=  it.next() ;
                // check for self loops
                if(nextNeighbour==nextSource)
                        return false ;
                if(!visited[nextNeighbour] && colorArray[nextNeighbour]==-1){
                    colorArray[nextNeighbour]= 1 - colorArray[nextSource] ;
                    queue.add(nextNeighbour) ;
                }
                else if(adjList[nextSource].contains(nextNeighbour) && colorArray[nextNeighbour]== colorArray[nextSource]) {
                    return false;
                }
            }
        }
        return false ;
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
