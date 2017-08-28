import java.util.*;
import java.io.*;
public class Main
{
    public static void solve(int n)
    {
        ArrayList<Integer> list = new ArrayList<Integer>();
        int sum = 0;
        while (n > 0)
        {
            int digit = n % 10;
            list.add(digit);
            sum += digit;
            n /= 10;
        }
        
        if (!list.contains(0) || ((sum % 3) != 0))
        {
            System.out.println(-1);
            return;
        }
      
        Integer[] digits = (Integer[]) list.toArray(new Integer[list.size()]);
        Arrays.sort(digits);
        for (int i = digits.length - 1; i >= 0; i--)
            System.out.print(digits[i]);
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        solve(n);
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