import java.util.*;
import java.io.*;

/**
 * problem Codeforces #444
 * Div. 64
 * written by progresivoJS on 2017.11.04
 */
public class Main
{
    private static void solve(String s)
    {
        char[] array = s.toCharArray();
        int n = array.length;
        
        boolean isOneExist = false;
        int firstOneIndex = 987654321;
        for (int i = 0; i < n; i++)
            if (array[i] == '1')
            {
                isOneExist = true;
                firstOneIndex = i;
                break;
            }
        
        int oneCount = 0;
        for (int i = firstOneIndex + 1; i < n; i++)
            if (array[i] == '0')
                oneCount++;
        
        
        if (isOneExist && oneCount >= 6)
            System.out.println("yes");
        else
            System.out.println("no");
    }
    
    public static void main(String[] args)
    {
        In.init();
        String s = In.next();
        solve(s);
    }
    
    private static class In
    {
        private static BufferedReader br;
        private static StringTokenizer st;
    
        public static void init()
        {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
    
        public static String next()
        {
            while (st == null || !st.hasMoreElements())
            {
                try
                {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException  e)
                {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
    
        public static int nextInt()
        {
            return Integer.parseInt(next());
        }
    }
}