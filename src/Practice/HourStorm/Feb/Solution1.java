package Practice.HourStorm.Feb;

import java.util.* ;
import java.util.regex.Pattern;

class Solution1{
    public static void main(String args[]){
        Scanner input = new Scanner(System.in) ;
        int sum = 0 ;
        String[] timeInputs = input.nextLine().trim().split("\\:");
        for(int counter = 0 ; counter< timeInputs.length ;counter++){
            sum+= Integer.parseInt(timeInputs[counter]);
        }
        int divisor = Integer.parseInt(input.nextLine());
        int remainder = 0 ;
        if(sum%divisor!=0){
            remainder = divisor - (sum% divisor) ;
        }
        int limit = 2+3+5+9 ;
        if(remainder>= limit){
            System.out.println(-1) ;
        }

        System.out.println(remainder) ;
    }
}