import java.util.Scanner;

public class _1008
{
    public static void main (String[] args) {
        Scanner scan = new Scanner(System.in);
        int a,b;
        a = scan.nextInt();
        b = scan.nextInt();
        
        // System.out.print((double)((double)a/(double)b));
        System.out.printf("%.9f", (double)a/b);
    }
}