import java.util.*;
import java.io.*;

/**
 * problem 10942
 * 팰린드롬?
 * https://www.acmicpc.net/problem/10942
 * written by progresivoJS on 2017.09.13
 */
public class Main
{
    private static int[] seq;
    private static int[][] cache;
    public static void solve(int n, int m, int[] seq, int[][] question)
    {
        Main.seq = seq;
        cache = new int[n][n];
        for (int i = 0; i < cache.length; i++)
            Arrays.fill(cache[i], -1);
        
        for (int i = 0; i < m; i++)
            System.out.println(isPalindrome(question[i][0], question[i][1]));
    }
    
    /**
     * [from, to] 까지의 seq가 palindrome인지 반환.
     */
    private static int isPalindrome(int from, int to)
    {
        if (from == to)
            return 1;
            
        if (to - from + 1 == 2)
            return seq[from] == seq[to] ? 1 : 0;
            
        if (cache[from][to] != -1)
            return cache[from][to];
        
        if (seq[from] != seq[to])
            return cache[from][to] = 0;
        else
            return cache[from][to] = isPalindrome(from + 1, to - 1);
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int[] seq = new int[n];
        for (int i = 0; i < n; i++)
            seq[i] = In.nextInt();
        
        int m = In.nextInt();
        int[][] question = new int[m][2];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < 2; j++)
                question[i][j] = In.nextInt() - 1;
        
        solve(n, m, seq, question);
    }
    
    private static class In
    {
        private static BufferedReader br;
        private static StringTokenizer st;
    
        public static void init()
        {
            br = new BufferedReader(new InputStreamReader(System.in));
            try
            {
                br = new BufferedReader(new FileReader("/home/ubuntu/workspace/data.txt"));
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
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