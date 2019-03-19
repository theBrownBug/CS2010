package Graph;

public class Prim
{
    // number of edges
    public static int V  ;
    public static void setV(int V){ Prim.V= V ; }

    int[][] adjMatrix ;

    int minKey(int key[] , boolean[] mstSet)
    {
        int min = Integer.MAX_VALUE ;
        int minIndex = -1 ;
        for(int counter = 0 ; counter< V ; counter++)
        {
            if(!mstSet[counter] && key[counter]< min)
            {
                min = key[counter] ;
                minIndex = counter ;

            }
        }
        return minIndex ;
    }


    void PrimImplementation()
    {
        int[] parent = new  int[V] ;
        int[] key = new int[V] ;
        boolean[] mstSet = new boolean[V] ;
        for(int i: key)
            i = Integer.MAX_VALUE ;
        for(boolean b : mstSet)
            b= false;
        key[0] = 0 ; parent[0] = -1 ;
        for(int counter = 0 ; counter< V-1 ; counter++)
        {
            int u = minKey(key , mstSet) ;
            mstSet[u] = true ;
            for(int i = 0 ; i< V ; i++)
            {
                if(adjMatrix[u][i]!= 0 && !mstSet[i] && adjMatrix[u][i]<key[i])
                {
                    parent[i] = u ;
                    key[i] = adjMatrix[u][i] ;
                }
            }
        }

        printMST(parent , V , adjMatrix);
    }

    void printMST(int[] parent , int n , int graph[][])
    {


    }




}
