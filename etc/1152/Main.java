import java.util.Scanner;
import java.util.StringTokenizer;

public class _1152
{
    public static void main (String[] args) {
        Scanner scan = new Scanner(System.in);
        String str;
        str = scan.nextLine();
        StringTokenizer st = new StringTokenizer(str," ");
        int number=0;
        while ( st.hasMoreTokens() )
        {
            number++;
            st.nextToken();
        }
        System.out.println(number);
    }
}