package Practice;

import java.util.* ;
class Solution2{

    public static void selectionSort(int[] array){
        int smallest = 0 ;
        int exchangeElement = 0 ;
        int arraySize = array.length ;
        for(int counter = 0; counter<arraySize -1 ; counter++){
            smallest = counter ;
            for(int counter2 = counter+1 ; counter2< arraySize ; counter2++){
                if(array[counter2]< array[smallest]){
                    smallest = counter2 ;
                }
            }

            exchangeElement = array[smallest] ;
            array[smallest] = array[counter] ;
            array[counter] = exchangeElement ;
        }
    }
    public static void main(String args[]){
        Scanner input = new Scanner(System.in) ;
        String[] elements = input.nextLine().trim().split("\\s") ;
        int common  = 0 ;
        int[] array = new int[elements.length] ;
        for(int counter = 0 ; counter< elements.length ; counter++){
            array[counter] = Integer.parseInt(elements[counter].trim()) ;
        }
        Solution2.selectionSort(array) ;
        int max = (int)Math.floor(array[array.length-1]/2);
        for(int counter= 1; counter< max; counter++){
            if(array[0]%counter == 0 && array[1]%counter ==0){
                common++ ;
            }
        }

        System.out.println(common);
    }
}