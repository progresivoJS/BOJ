import java.util.*;
import java.math.BigInteger;

public class Main
{ 
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String n1 = sc.next();
        String n2 = sc.next();
        BigInteger a = new BigInteger(n1);
        BigInteger b = new BigInteger(n2);
        
        System.out.println(a.multiply(b));
    }
}