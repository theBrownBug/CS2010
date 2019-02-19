package Practice;

public class Tutorial1 {

    public static void insertionSort(int[] array){

        for(int counter = 1; counter < array.length ; counter++){
            int element = array[counter] ;
            int smaller = counter -1 ;
            while(smaller>0 && array[smaller]> element){
                array[smaller+1] = array[smaller] ;
                smaller-- ;
            }
            array[smaller+1] = element;
        }
    }

    public static void selectionSort(int[] array){
        int length = array.length;
        int smallest = 0;
        int exchangeVar =  0;
        for(int counter = 0 ; counter<length-1 ; counter++){
            smallest = counter ;
            for(int counter2 = counter+1 ; counter2<length;counter2++){
                if(array[smallest]> array[counter2]){
                    smallest = counter2;
                }
            }
            exchangeVar = array[counter] ;
            array[counter] = array[smallest] ;
            array[smallest] = exchangeVar ;
        }
    }


    public static void bubbleSort(int[] array){
        for(int counter= 0; counter< array.length ; counter++){

        }
    }
    public static void mergeSort(){

    }
}
