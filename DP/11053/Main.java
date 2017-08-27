import java.util.*;
import java.io.*;
public class Main
{
    private static int[] seq;
    private static int[] cache;
    
    public static int solve(int[] seq)
    {
        Main.seq = seq;
        int n = seq.length;
        
        cache = new int[n];
        Arrays.fill(cache, -1);
        
        int result = 0;
        for (int i = 0; i < n; i++)
            result = Math.max(result, increasing(i));
        return result;
    }
    
    /**
     * 0 ~ index 까지의 seq에서 가장 긴 증가하는 부분 수열의 길이
     */
    private static int increasing(int index)
    {
        // 기저사례가 필요가 없다. 아래에 result 값을 1로 지정.
        // if (index == 0)
        //     return 1;
        
        if (cache[index] != -1)
            return cache[index];
        
        // seq[index] 는 항상 1 이상이다.
        int result = 1;
        for (int next = index -1; next >= 0; next--)
            if (seq[index] > seq[next])
                result = Math.max(result, 1 + increasing(next));
        
        return cache[index] = result;
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int[] seq = new int[n];
        for (int i = 0; i < n; i++)
            seq[i] = In.nextInt();
        System.out.println(solve(seq));
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