package PS3;
// Copy paste this Java Template and save it as "HospitalRenovation.java"
import java.util.*;
import java.io.*;

// write your matric number here:
// write your name here:
// write list of collaborators here:
// year 2019 hash code: Ax9c4nys2yoKvYjQL3TF (do NOT delete this line) <- generate a new hash code

class HospitalRenovation {
    private int V; // number of vertices in the graph (number of rooms in the hospital)
    private int[][] AdjMatrix; // the graph (the hospital)
    private int[] RatingScore; // the weight of each vertex (rating score of each room)
    int time = 0 ;


    // if needed, declare a private data structure here that
    // is accessible to all methods in this class

    public HospitalRenovation() {
        // Write necessary code during construction
        //
        // write your answer here



    }

    int Query() {
        int ans = 0;
        boolean[] visited = new boolean[V] ;
        int[] timeOfDiscovery = new int[V] ;
        int[] lowestTOD = new int[V] ;
        int[] parent = new int[V] ;
        boolean[] criticalPoints = new boolean[V] ;// to store all the critical points

        for(int i = 0  ; i <V ; i++){
            parent[i] = -1 ;
            visited[i] = false ;
            criticalPoints[i] = false ;

        }
        for(int i = 0 ;  i< V ; i++){
            if(visited[i]==false){
                criticalPointsFind(i, visited, timeOfDiscovery, lowestTOD , parent, criticalPoints) ;
            }
        }

        ans = Integer.MAX_VALUE ;
        for(int i = 0 ; i< V ; i++){
            if(criticalPoints[i]){
                if(RatingScore[i]< ans)
                    ans = RatingScore[i] ;
            }
        }

        return ans;
    }

    // extra function
    public void criticalPointsFind(int i , boolean[] visited , int[] tod , int[] lowestTod , int[] parent , boolean[] cp){

        int childrenCount = 0 ;
        visited[i] = true;
        tod[i] = ++ this.time;
        for(int row = 0 ; row< V ; row++){
            for(int column = 0; column<V ; column++){
                int v = AdjMatrix[row][column] ;
                if (!visited[v]){
                    childrenCount++ ;
                    parent[v] = i;
                    criticalPointsFind(i, visited , tod, lowestTod , parent, cp);
                    lowestTod[i] = Math.min(lowestTod[i] , lowestTod[v]) ;
                    // if the current node is the root node and has 2 children
                    if(parent[i]==-1 && childrenCount>1){ cp[i] = true ; }

                    if(parent[i]!= -1 && lowestTod[v]>= tod[i]){
                        cp[i] = true;
                    }
                }

                else if(v!=parent[i]){
                    lowestTod[i] = Math.min(lowestTod[i] , lowestTod[v])  ;
                }
            }
        }


    }

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
            RatingScore = new int[V];
            for (int i = 0; i < V; i++)
                RatingScore[i] = Integer.parseInt(st.nextToken());

            // clear the graph and read in a new graph as Adjacency Matrix
            AdjMatrix = new int[V][V];
            for (int i = 0; i < V; i++) {
                st = new StringTokenizer(br.readLine());
                int k = Integer.parseInt(st.nextToken());
                /*
                *
                * Doesn't k < V needs to be true always?
                *
                * */
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