package Graph;

import javax.swing.text.StyleContext;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

public class Sample2  {

    public  static  void permutation(String s , String p){

        char[] pCharArr = p.toCharArray() ;

        char[] charArr= s.toCharArray() ;
        char[] newArr = new char[charArr.length] ;


        int counter = 0 ;
        while(counter < charArr.length){

            int oldPos = iterator(p, charArr[counter]) ;
            int newPos ;


            if(oldPos>=0){
               newPos  =  (int)Math.pow(2,oldPos) % 26 ;
               newArr[counter] = pCharArr[newPos] ;
            }

            counter ++ ;
        }

        String out = String.copyValueOf(newArr) ;
        System.out.println(out);
    }


    public static int iterator(String s , char c){
        char[] sArr = s.toCharArray() ;
        int counter = 0;
        int ans =-1 ;
        while(counter<sArr.length){
            if(sArr[counter]==c){
                ans=counter ;
                break;
            }
            counter++ ;
        }
      return ans ;
    }
    public static void main(String args[]){

        Scanner input = new Scanner(System.in) ;
        String string = input.nextLine().toString().trim() ;
        String permutation = input.nextLine().trim()  ;
        Sample2.permutation(string,permutation) ;
        //System.out.println(out);

    }

}
