package Graph;

import javax.sound.sampled.Line;
import java.util.Iterator;
import java.util.LinkedList;

public class Graph {
    private int V ;
    private LinkedList<Integer> adjListArray[] ;
    private int[][] adjMatrix ;

    // implement an edge list too


    public Graph(int V){
        this.V = V ;
        if(V<= 0 ){
            System.out.println(" Invavlid number of vertices") ;
            System.exit(-1) ;
        }
        // initialising the the adjecency Matrix
        this.adjListArray = new LinkedList[V] ;
        for(int counter= 0 ; counter< V ; counter++){
            adjListArray[V] = new LinkedList<>() ;
        }

        // initialise the matrix
        adjMatrix = new int[V][V] ;
        for(int row= 0 ; row< V ; row++){
            for(int column = 0 ; column<V ; column++){
                adjMatrix[row][column] =  0;
            }
        }
    }


    public int[][] adjListToMatrix(LinkedList adjList[]){
        int[][] adjMatrix = new int[adjList.length][adjList.length] ;
        for(int counter = 0 ; counter< adjList.length ; counter++){
            Iterator iterator = adjList[counter].listIterator() ;
            int index = 0 ;
            while(iterator.hasNext()){
                index++ ;
                int nextEntry = (Integer) iterator.next() ;
                adjMatrix[counter][index] = 1 ;
                adjMatrix[index][counter]= 1 ;

            }
        }

        return adjMatrix ;
    }

    public LinkedList[] adjMatrixToList(int[][] adjMatrix){
        // initialise
        LinkedList adjList[] = new LinkedList[adjMatrix.length] ;
        for(int counter = 0 ; counter< adjList.length ; counter++){
            adjList[counter] = new LinkedList() ;
        }
        for(int row = 0 ; row < V ; row++){
            for(int col = 0 ; col<V ; col++){
                if(adjMatrix[row][col]==1){
                    adjList[row].add(col) ;
                }
            }
        }

        return adjList ;
    }

    static void AddEdge(Graph graph, int source , int destination){
        graph.adjListArray[source].add(destination) ;
        // because the graph is undirected, insert an symmetric entry
        graph.adjListArray[destination].add(source);

        // add an edge to the matrix too
        graph.adjMatrix[source][destination]= graph.adjMatrix[destination][source] = 1 ;

    }




    public static void main(String args[]){

        int val = 9 ;
        double a = 290.05 ;
        int b = 100 ;
        byte c = (byte) a  ;
        byte d = (byte) b ;

        System.out.println(c+" "+d);
    }

}
