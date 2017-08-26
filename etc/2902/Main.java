import java.util.Scanner;
import java.util.StringTokenizer;

public class _2902
{
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        
        StringTokenizer st = new StringTokenizer(str,"-");
        
        String result ="";
        while(st.hasMoreTokens())
        {
            result+=st.nextToken().charAt(0);
        }
        System.out.println(result);
        
    }
}