import java.util.Scanner;

public class _10809
{
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        
        int i,result;
        result = 0;
        
        for ( int a = 'a' ; a <= 'z' ; a++ )
        {
            for ( i = 0 ; i < str.length() ; i++ )
            {
                if ( a == str.charAt(i) )
                {
                    result = i;
                    break;
                }
            }
            if ( i == str.length() )
            {
                result = -1;
            }
            
            System.out.print(result+ " ");
        }
        
    }
}