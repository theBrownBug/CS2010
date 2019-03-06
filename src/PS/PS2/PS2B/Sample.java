package PS.PS2.PS2B;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

class Sample{

    public static int countDuplicates(List<Integer> numbers) {
        // Write your code here
        int[] list = new int[numbers.size()] ;
        int counter = 0 ;
        for(Integer i:numbers){
            list[counter] = i.intValue() ;
        }
        int duplicates = 0 ;
        for(int i = 0 ; i< numbers.size();i++){
           if(list[Math.abs(list[i])]>=0){
               list[Math.abs(list[i])] = - list[Math.abs(list[i])] ;
           }else{

           }
        }
        return duplicates;
    }
    public static void main(String args[]){
        Integer a = 1 ;
        Integer b = 1;
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());


    }
}
