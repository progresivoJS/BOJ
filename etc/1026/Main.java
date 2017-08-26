import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;

public class _1026
{
    public static void main (String[] args) {
        Scanner scan = new Scanner(System.in);
        int number = scan.nextInt();
        Integer[] A = new Integer[number];
        Integer[] B = new Integer[number];
        for ( int i = 0; i < number ; i++)
        {
            A[i] = scan.nextInt();
        }
        for ( int j = 0 ; j < number ; j++)
        {
            B[j] = scan.nextInt();
        }
        Arrays.sort(A);
        Arrays.sort(B, Collections.reverseOrder());
        int result = 0;
        for ( int i = 0 ; i < number ; i++)
        {
            result += A[i]*B[i];
        }
        System.out.println(result);
    }
}