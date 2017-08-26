import java.util.Scanner;

public class _10871
{
    public static void main (String[] args) {
        Scanner scan = new Scanner(System.in);
        int number,pinpoint;
        number = scan.nextInt();
        pinpoint = scan.nextInt();
        int[] array = new int[number];
        for ( int i = 0 ; i < number ; i++ )
        {
            array[i]=scan.nextInt();
            if ( array[i] < pinpoint )
            {
                System.out.print(array[i]+" ");
            }
        }
    }
}