package PS5;

// Copy paste this Java Template and save it as "Bleeding.java"
import java.util.*;
import java.io.*;
import



// write your matric number here: A0192770Y
// write your name here: Eeshan Jaiswal
// write list of collaborators here: Geeks For Geeks
// year 2018 hash code: psJ6yCZMN7uwQv79EtpQ (do NOT delete this line)

class Bleeding {
    private int V; // number of vertices in the graph (number of junctions in Singapore map)
    private int Q; // number of queries
    private Vector < Vector < IntegerPair > > AdjList; // the weighted graph (the Singapore map), the length of each edge (road) is stored here too, as the weight of edge


    class IntegerPair implements Comparable<IntegerPair>{
        int weight ;
        int destination ;
        public IntegerPair(int destination , int weight){
            this.destination = destination ;
            this.weight = weight ;
        }
        public int getWeight() { return weight; }
        public int getDestination() { return destination; }
        @Override
        public int compareTo(IntegerPair o) {
            if  (this.getDestination() != (o.getDestination()))
                return this.getDestination() - o.getDestination();
            else
                return this.getWeight() - o.getWeight();
        }
    }



    class Edge {
        int source , destination , weight ;
        public Edge(int source , int destination ,int weight){
            this.destination  = destination ;
            this.source = source ;
            this.weight = weight ;
        }
        public Edge(){
            this.destination  = this.source = this.weight = 0 ;
        }
        public int getSource() { return source; }
        public void setSource(int source) { this.source = source; }
        public int getDestination() { return destination; }
        public void setDestination(int destination) { this.destination = destination; }
        public int getWeight() { return weight; }
        public void setWeight(int weight) { this.weight = weight; }
    }


/*
    class Node implements Comparable<Node>{
        int vertex ;
        int key ;
        Node previous ;
        public int getVertex() { return vertex; }
        public int getKey() { return key; }
        public int getPrevious(){return this.previous.getVertex() ; }
        @Override
        public int compareTo(Node o) {
            return this.getKey() - o.getKey();
        }
    }


*/

    public Bleeding() {

    }




    void Djikstra(int source){
        boolean[]shortestPath = new boolean[AdjList.size()] ;
        int[] distance = new int[AdjList.size()] ;
        for(int i: distance){
            i = Integer.MAX_VALUE ;
        }


        PriorityQueue<Has<>> queue = new HashMap<Integer,Integer>(AdjList.size() , new Comparator<HashMap<Integer, Integer>>(){
            @Override
            public int compare(HashMap<Integer, Integer> o1, HashMap<Integer, Integer> o2) {
                return  o1.
            }
        }) ;

        /*
        PriorityQueue<Edge> queue = new PriorityQueue<>(AdjList.size());
        Edge sourceNode = new Edge() ;
        sourceNode.source = source ;
        sourceNode.distance =  0 ;
        distance[source] = 0 ;
        */
        queue.offer(sourceNode) ;

        while(!queue.isEmpty()){

            Node extractedNode = queue.poll() ;
            int vertex = extractedNode.getVertex() ;
            if(!shortestPath[vertex]) {
                shortestPath[vertex] = true;
                Vector current = AdjList.get(vertex) ;

            }
        }



    }

    void PreProcess() {

    }

    int Query(int s, int t, int k) {
        int ans = -1;

        // You have to report the shortest path from Ket Fah's current position s
        // to reach the chosen hospital t, output -1 if t is not reachable from s
        // with one catch: this path cannot use more than k vertices
        //
        // write your answer here



        //-------------------------------------------------------------------------

        return ans;
    }

    // You can add extra function if needed
    // --------------------------------------------



    // --------------------------------------------

    void run() throws Exception {
        // you can alter this method if you need to do so
        IntegerScanner sc = new IntegerScanner(System.in);
        PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        int TC = sc.nextInt(); // there will be several test cases
        while (TC-- > 0) {
            V = sc.nextInt();

            // clear the graph and read in a new graph as Adjacency List
            AdjList = new Vector < Vector < IntegerPair > >();
            for (int i = 0; i < V; i++) {
                AdjList.add(new Vector < IntegerPair >());

                int k = sc.nextInt();
                while (k-- > 0) {
                    int j = sc.nextInt(), w = sc.nextInt();
                    AdjList.get(i).add(new IntegerPair(j, w)); // edge (road) weight (in minutes) is stored here
                }
            }

            PreProcess(); // optional

            Q = sc.nextInt();
            while (Q-- > 0)
                pr.println(Query(sc.nextInt(), sc.nextInt(), sc.nextInt()));

            if (TC > 0)
                pr.println();
        }

        pr.close();
    }

    public static void main(String[] args) throws Exception {
        // do not alter this method
        Bleeding ps5 = new Bleeding();
        ps5.run();
    }
}



class IntegerScanner { // coded by Ian Leow, using any other I/O method is not recommended
    BufferedInputStream bis;
    IntegerScanner(InputStream is) {
        bis = new BufferedInputStream(is, 1000000);
    }

    public int nextInt() {
        int result = 0;
        try {
            int cur = bis.read();
            if (cur == -1)
                return -1;

            while ((cur < 48 || cur > 57) && cur != 45) {
                cur = bis.read();
            }

            boolean negate = false;
            if (cur == 45) {
                negate = true;
                cur = bis.read();
            }

            while (cur >= 48 && cur <= 57) {
                result = result * 10 + (cur - 48);
                cur = bis.read();
            }

            if (negate) {
                return -result;
            }
            return result;
        }
        catch (IOException ioe) {
            return -1;
        }
    }
}

