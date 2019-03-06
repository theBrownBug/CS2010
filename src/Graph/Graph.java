package Graph;

import java.util.LinkedList;

public class Graph {
    private int V ;
    private LinkedList<Integer> adjList[] ;

    public Graph(int V){
        this.V = V ;
        this.adjList = new LinkedList[V] ;
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
