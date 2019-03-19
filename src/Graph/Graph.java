package Graph;


/**l
 * Contains methods to detect bipartite graphs , detect cycle
 * */
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
    
    
    
     public void BFS(int source){
        boolean[] visited = new boolean[V];

        LinkedList<Integer> queue = new LinkedList<Integer>() ;
        visited[source]= true ;
        queue.add(source) ;
        while(queue.size()!=0){

            int currentNode = queue.poll() ;
            System.out.println(currentNode);

            Iterator<Integer> iterator = adjListArray[currentNode].listIterator() ;
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
        Iterator<Integer> iterator = adjListArray[source].listIterator() ;
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
        Iterator<Integer> it = adjListArray[node].iterator() ;
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
    
    




    public static void main(String args[]){

    }

}
