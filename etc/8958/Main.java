import java.util.Scanner;

public class _8958
{
    public static void main (String[] args) {
        Scanner scan = new Scanner(System.in);
        int number = scan.nextInt();
        scan.nextLine();
        String str;
        for ( int i = 0 ; i < number ; i ++ )
        {
            int result = 0;
            int dividend = 0;
            str = scan.nextLine();
            for ( int j = 0; j < str.length() ; j++ )
            {
                if ( str.charAt(j) == 'O' )
                {
                    dividend++;
                    result+=dividend;
                }
                else
                {
                    dividend = 0;
                }
            }
            System.out.println(result);
        }
    }
}