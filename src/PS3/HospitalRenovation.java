//package PS3;
// Copy paste this Java Template and save it as "HospitalRenovation.java"
import java.util.*;
import java.io.*;

// write your matric number here:A0192770Y
// write your name here: Eeshan Jaiswal
// write list of collaborators here: HackerEarth
// year 2019 hash code: Ax9c4nys2yoKvYjQL3TF (do NOT delete this line) <- generate a new hash code

class HospitalRenovation {
    private int V; // number of vertices in the graph (number of rooms in the hospital)
    private int[][] AdjMatrix; // the graph (the hospital)
    private int[] RatingScore; // the weight of each vertex (rating score of each room)
    int time = 0 ;
    int[] timeOfDisc ;
    int[]low ;
    boolean[] visited  ;
    int[] parent ;
    boolean[] AP ;


    // for normal approach
    boolean[] visitedDFSNormal ;
    boolean[] criticalPointArray ;
    // contains all the neighbor lists of the each node
    public LinkedList<Integer> adjListArray[] ;




    // if needed, declare a private data structure here that
    // is accessible to all methods in this class





    public HospitalRenovation() {

    }



    public void ArticulationPoints(int[][] adjMatrix , int[] timeOfDisc , int[] low , boolean[] visited , int[] parent , boolean[] AP , int vertex , int numVertices){

        visited[vertex] = true;
        timeOfDisc[vertex] = low[vertex] = ++time ;
        int numberOfChildren = 0;
        for(int counter = 0; counter< numVertices ; counter++){
            if(adjMatrix[vertex][counter] == 1){
                if(!visited[counter]){
                    numberOfChildren+=1 ;
                    parent[counter] = vertex ;
                    //time+= 1 ;
                    ArticulationPoints(adjMatrix , timeOfDisc , low , visited , parent , AP , counter,numVertices);
                    low[vertex]= Math.min(low[vertex] , low[counter]) ;

                    // if the current vertex is the root vertex and has more than 1 children
                    if(parent[vertex]== Integer.MIN_VALUE  && numberOfChildren> 1){
                        AP[vertex] = true ;
                    }
                    if(parent[vertex]!=Integer.MIN_VALUE && low[counter]>= timeOfDisc[vertex]){
                        AP[vertex] = true ;
                    }
                }
                else  if(parent[vertex]!= counter){
                    low[vertex] = Math.min(low[vertex] , timeOfDisc[counter]) ;
                }
            }

        }

    }


    int Query(){
        if(V==0){
            return  -1 ;
        }

        // articulation points approach(O(V+E))

        int ans = Integer.MAX_VALUE;
        ArticulationPoints(AdjMatrix , timeOfDisc , low , visited , parent , AP , 0 , V);

        for(int counter = 0 ; counter< V ; counter ++){
            if(AP[counter]){
                if(RatingScore[counter]< ans){
                    ans = RatingScore[counter] ;
                }
            }
        }

        if(ans == Integer.MAX_VALUE)
            ans= -1 ;

        return  ans ;


        /*
        // normal approach
        int ans = 0;
        convertToLinkedList(AdjMatrix);
        DFS(0);
        int lowestRating = Integer.MAX_VALUE ;
        for(int counter = 0 ; counter < V ; counter++){
            if(criticalPointArray[counter]) {
                if (RatingScore[counter]< lowestRating){
                    lowestRating = RatingScore[counter] ;
                }
            }
        }
        ans = lowestRating ;
        if (lowestRating== Integer.MAX_VALUE){
            ans = -1 ;
        }

        return ans;
        */
    }


    /*

    public void convertToLinkedList(int[][] adjMatrix){
        for(int row = 0 ; row<V ; row++){
            for(int column = 0; column < V; column++){
                if(adjMatrix[row][column]==1)
                    adjListArray[row].add(column) ;
            }
        }
    }
    */

    /**
     * v        int         current node
     * visited  boolean     array representing whether the node has been visited or not
     *
     * */
    /*
    void DFSHelper(int v , boolean[] visited , int toBeSkipped){

        visited[v] = true ;
        Iterator<Integer> iterator = adjListArray[v].listIterator() ;
        while(iterator.hasNext()){
            int nextNode = iterator.next() ;
            if(nextNode==toBeSkipped){
                continue;
            }
            if(!visited[nextNode])
                DFSHelper(nextNode , visited , toBeSkipped);
        }
    }

    */

    /*
    void DFS(int v ){
        /*
        *
        * for every vertex to be skipped ini initialise the visited array again ,and after that
        * calculate the number of true in each iteration of DFS
        *
        * */
    /*
        for(int toBeSkipped = 0 ; toBeSkipped<V ; toBeSkipped++) {
            //reset count for each iteration
            int count = 0;

            // reset the visited array for each iteration
            for(int i = 0; i < V ; i++){
                visitedDFSNormal[i] = false ;
            }

            if (v != toBeSkipped){
                DFSHelper(v, visitedDFSNormal, toBeSkipped);
            }
            else{
                DFSHelper(v+1 , visitedDFSNormal , toBeSkipped);
            }

            for(int i = 0; i< V ; i++){
                if(visitedDFSNormal[i]){
                    count += 1;
                }
            }
            // if the number of vertices visited using DFS and skipping an element is less than V-1 then it creates
            // at least 1 components
            if(count<V-1){
                criticalPointArray[toBeSkipped] = true ;
            }
        }
    }

    */

    void run() throws Exception {
        // for this PS3, you can alter this method as you see fit

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int TC = Integer.parseInt(br.readLine()); // there will be several test cases
        while (TC-- > 0) {
            br.readLine(); // ignore dummy blank line
            V = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());
            // read rating scores, A (index 0), B (index 1), C (index 2), ..., until the V-th index

            /*
            // normal approach
            // initialise the adj list
            //this is a vector of the size of the number of vertices
            adjListArray = new LinkedList[V] ;
            visitedDFSNormal = new boolean[V] ;
            criticalPointArray = new boolean[V] ;
            for(int counter = 0 ; counter< V ; counter++){
                adjListArray[counter] = new LinkedList<>() ;
            }
            */
            // uncomment for articulation point approach


            timeOfDisc = new int[V] ;
            low = new int[V];
            visited = new boolean[V] ;
            parent = new int[V] ;
            AP = new boolean[V] ;


            for(int i = 0 ; i<V ; i++){
                visited[i] = AP[i] = false ;
                timeOfDisc[i] = 0 ;
                low[i] = Integer.MAX_VALUE ;
                parent[i] = Integer.MIN_VALUE ;
            }




            RatingScore = new int[V];
            for (int i = 0; i < V; i++)
                RatingScore[i] = Integer.parseInt(st.nextToken());

            // clear the graph and read in a new graph as Adjacency Matrix
            AdjMatrix = new int[V][V];
            for (int i = 0; i < V; i++) {
                st = new StringTokenizer(br.readLine());
                int k = Integer.parseInt(st.nextToken());
                while (k-- > 0) {
                    int j = Integer.parseInt(st.nextToken());
                    AdjMatrix[i][j] = 1; // edge weight is always 1 (the weight is on vertices now)
                }
            }

            pr.println(Query());
        }
        pr.close();
    }

    public static void main(String[] args) throws Exception {
        // do not alter this method
        HospitalRenovation ps3 = new HospitalRenovation();
        ps3.run();
    }
}



class IntegerPair implements Comparable < IntegerPair > {
    Integer _first, _second;

    public IntegerPair(Integer f, Integer s) {
        _first = f;
        _second = s;
    }

    public int compareTo(IntegerPair o) {
        if (!this.first().equals(o.first()))
            return this.first() - o.first();
        else
            return this.second() - o.second();
    }

    Integer first() { return _first; }
    Integer second() { return _second; }
}