package Graph;

import java.util.Scanner;

public class Sample3 {

    public static boolean isPrime(int number){
        if (number == 2)
            return true ;
        if(number==3)
            return true ;
        if (number % 2 == 0)
            return false ;
        if (number % 3 == 0)
            return false ;

        int i = 5;
        int w = 2 ;

        while (i * i <= number) {
            if (number % i == 0)
                return false ;
            i += w ;
            w = 6 - w ;

        }

        return true ;
    }

    public static void mainFunction(int[] array){

    }

    public static void main(String args[]){
        int counter = 0;
        Scanner input= new Scanner(System.in) ;
        int numberOfTimes = Integer.parseInt(input.nextLine().trim()) ;
        while(counter<numberOfTimes){



            counter ++ ;

        }


    }
}
