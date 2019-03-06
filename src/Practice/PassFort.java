package Practice;

import java.io.FileOutputStream;

public class PassFort {

    public static Long Fibonacci( Long i){
        if(i<=1){return i ; }
        double numberOfDigits = Math.ceil(Math.log10(i+1)) ;
        if(numberOfDigits>=1000){
            System.out.println(i);
            System.exit(0);
        }
        return Fibonacci(i-1) + Fibonacci(i-2) ;
    }
}
