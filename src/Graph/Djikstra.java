package Graph;
/**
 * Djikstra using a adjecency Matrix
 * */

public class Djikstra {
    int[][] adjecencyMatrix ;
    public  int V  ;
    public Djikstra(int V){
        this.V = V ;
        this.adjecencyMatrix = new int[V][V];
    }

    int minDistance(int[] distance , Boolean[] shortestPathSet){
        int min = Integer.MAX_VALUE ;
        int minIndex = - 1;
        for(int i = 0; i< V ; i++){
            if(!shortestPathSet[i] && distance[i]<= min){
                min = distance[i] ;
                minIndex = i ;
            }
        }
        return minIndex ;
    }
    void PrintSolution(int source , int[] distances){
        for(int counter = 0;  counter< this.V ; counter++){

        }
    }
    void DjikstraImplemenation(int source){
        int distances[] = new int[this.V] ;
        Boolean[] shortestPathSet = new Boolean[V];
        for(int counter= 0 ; counter<V ; counter ++){
            shortestPathSet[counter] = false;
            distances[counter] = Integer.MAX_VALUE ;
        }
        distances[source]= 0 ;
        for(int counter = 0 ; counter< V-1 ; counter++){
            /*
            * pick the vertex with the least distance that has not been visited
            * the source vertex is the first one to be picked
            * */
            int lowestDistanceVertex = minDistance(distances , shortestPathSet) ;
            shortestPathSet[lowestDistanceVertex] = true ;
            for(int x = 0 ; x< V ; x++){
                /*
                * Update only if vertex is not in  sptSet
                * */
                if(!shortestPathSet[x] && adjecencyMatrix[counter][x] !=0 &&
                    distances[counter]!= Integer.MAX_VALUE &&
                    distances[counter] +adjecencyMatrix[counter][x]< distances[x])
                { distances[x]= distances[counter] + adjecencyMatrix[counter][x] ;  }
            }

        }


    }
}
