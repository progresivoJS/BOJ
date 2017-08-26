import java.util.Scanner;
import java.util.Arrays;

public class _10817
{
    public static void main (String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] number = new int[3];
        
        for ( int i = 0 ; i < number.length ; i++ )
        {
            number[i] = scan.nextInt();
        }
        Arrays.sort(number);
        System.out.println(number[1]);
        
        
    }
}