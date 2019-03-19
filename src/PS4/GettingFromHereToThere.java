package PS4;


import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

// write your matric number here:
// write your name here:
// write list of collaborators here:
// year 2019 hash code: fHsMPpQw4arRphSwbU8Q (do NOT delete this line) <-- change this

    class GettingFromHereToThere {
        private int V; // number of vertices in the graph (number of rooms in the building)
        private Vector < Vector < IntegerPair > > AdjList; // the weighted graph (the building), effort rating of each corridor is stored here too

        // if needed, declare a private data structure here that
        // is accessible to all methods in this class
        // integer pair is a Node for the weighed graph in the adjacency list



        // edge class
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






        // key is the min weight to the particular vertex in the mst
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
        int PrimMST(int source , int target) {
            Boolean[] mstSet = new Boolean[AdjList.size()];
            Node[] node = new Node[AdjList.size()];
            int[] parent = new int[AdjList.size()];
            //for (Node e : node)
              //  e = new Node();

            for (int counter = 0; counter < AdjList.size(); counter++) {
                mstSet[counter] = false;
                node[counter] = new Node() ;
                node[counter].key = Integer.MAX_VALUE;
                node[counter].vertex = counter;
                parent[counter] = -1;
            }

            // beginning point of the mst Tree
            mstSet[source] = true;
            // give min value to the beginning point of the min heap
            node[source].key = 0;
            node[source].previous= null ;

            PriorityQueue<Node> queue = new PriorityQueue<>(AdjList.size());
            // add all the nodes in the priority queue
            for (Node n : node)
                queue.add(n);

            while (!queue.isEmpty()) {
                Node extract = queue.poll();
                // include the vertex in the MST SET
                mstSet[extract.vertex] = true;
                Vector current = AdjList.get(extract.vertex);
                for (int counter = 0; counter < current.size(); counter++) {
                    IntegerPair i = (IntegerPair) current.get(counter);
                    if (!mstSet[i.getDestination()]) {
                        // update the weights
                        if (node[i.getDestination()].getKey() > i.getWeight()) {
                            queue.remove(node[i.getDestination()]);
                            node[i.getDestination()].key = i.weight;
                            node[i.getDestination()].previous = extract;
                            queue.add(node[i.getDestination()]);
                            parent[i.getDestination()] = extract.vertex;
                        }
                    }
                }

            }
            Node t = null;
            for (Node n : node){
                if (n.getVertex() == target)
                    t = n;
            }
            int highestEffort = 0 ;
            if(t!=null){
                for (Node n = t ; n!= null ; n = n.previous){
                    if(highestEffort< n.getKey()){
                        highestEffort = n.getKey() ;
                    }
                }
            }

            return highestEffort ;
        }



        // --------------------------------------------

        public GettingFromHereToThere() {
            // Write necessary codes during construction;
            //
            // write your answer here



        }

        void PreProcess() {
            // write your answer here
            // you can leave this method blank if you do not need it


        }

        int Query(int source, int destination) {
            int ans = 0;

            ans = PrimMST(source, destination);

            // You have to report the weight of a corridor (an edge)
            // which has the highest effort rating in the easiest path from source to destination for the wheelchair bound
            //
            // write your answer here



            return ans;
        }

        // You can add extra function if needed
        // --------------------------------------------



        // --------------------------------------------

        void run() throws Exception {
            // do not alter this method
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
                        AdjList.get(i).add(new IntegerPair(j, w)); // edge (corridor) weight (effort rating) is stored here
                    }
                }

                PreProcess(); // you may want to use this function or leave it empty if you do not need it

                int Q = sc.nextInt();
                while (Q-- > 0)
                    pr.println(Query(sc.nextInt(), sc.nextInt()));
                pr.println(); // separate the answer between two different graphs
            }

            pr.close();
        }

        public static void main(String[] args) throws Exception {
            // do not alter this method
            GettingFromHereToThere ps4 = new GettingFromHereToThere();
            ps4.run();
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


    /*
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



    class IntegerTriple implements Comparable < IntegerTriple > {
        Integer _first, _second, _third;

        public IntegerTriple(Integer f, Integer s, Integer t) {
            _first = f;
            _second = s;
            _third = t;
        }

        public int compareTo(IntegerTriple o) {
            if (!this.first().equals(o.first()))
                return this.first() - o.first();
            else if (!this.second().equals(o.second()))
                return this.second() - o.second();
            else
                return this.third() - o.third();
        }

        Integer first() { return _first; }
        Integer second() { return _second; }
        Integer third() { return _third; }
    }

    */
