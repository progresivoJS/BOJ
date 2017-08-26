import java.util.Scanner;
import java.util.StringTokenizer;

public class _10953
{
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        sc.nextLine();
        int a,b;
        String str;
        StringTokenizer st;
        for ( int i = 0 ; i < number ; i++)
        {
            st = new StringTokenizer(sc.nextLine(),",");
            System.out.println(st.nextToken()+(int)st.nextToken());
        }
    }
}