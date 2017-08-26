import java.util.Scanner;

public class _8393
{
    public static void main (String[] args) {
        Scanner scan = new Scanner(System.in);
        int number = scan.nextInt();
        int sum = 0;
        for ( int i = 0 ; i < number ; i++ )
        {
            sum += (i+1);
        }
        System.out.println(sum);   
    }
}