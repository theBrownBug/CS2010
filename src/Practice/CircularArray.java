package Practice;

public class CircularArray {

    /*
    * run time : O(n)
    * Extra space : O(n)
    * */
    public static void method1(char[] input , int originalArraySize , int index ){

        // create the aux array to be the double size of the original array
        char[] auxArray = new char[2*originalArraySize] ;

        // copy all the elements from the original array to the aux array 2 times
        for(int counter = 0 ; counter< originalArraySize ; counter++){
            auxArray[counter] = auxArray[counter+originalArraySize]= input[counter] ;
        }

        for(int counter = index ; counter <index+originalArraySize ; counter++){
            System.out.println(auxArray[counter] +" ");
        }
    }

    /*
     * run Time : O(n)
     * Extra space: O(1)
     */

    public static void method2(char[] input , int arraySize , int index){
        for(int counter = index  ; counter< arraySize+ index ; counter++){
            System.out.println(input[index%arraySize]+" ");
        }
    }

    public static void main(String args[]){

    }
}
