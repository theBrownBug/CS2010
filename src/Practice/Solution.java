package Practice;
/* IMPORTANT: Multiple classes and nested static classes are supported */
import java.util.* ;

class Solution{

    public static void main(String args[]){
        int arraySize = 0 ;
        double[] array ;
        double sum = 0 ;
        double[] finalArray ;
        Scanner input = new Scanner(System.in) ;

        try{
            arraySize = Integer.parseInt(input.nextLine().trim());
            array = new double[arraySize] ;
            String[] elements = input.nextLine().trim().split("\\s") ;
            if(elements.length!=arraySize){ System.exit(-1);}
            for(int counter= 0 ; counter<arraySize; counter++){
                array[counter] = Double.parseDouble(elements[counter]) ;
                sum+= array[counter] ;
            }
            double valueOfEach = Math.ceil((sum)/arraySize) ;
            double outPut = valueOfEach * arraySize ;
            if(sum==outPut){
                valueOfEach++ ;
            }
            System.out.println(valueOfEach);
        }catch(NumberFormatException e){
            System.out.println(e+" is not a number") ;
        }

    }
}