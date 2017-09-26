import java.util.*;
import java.io.*;

/**
 * problem 9466
 * 텀 프로젝트
 * https://www.acmicpc.net/problem/9466
 * written by progresivoJS on 2017.09.26
 */
public class Main
{
    private static int n;
    private static boolean[] marked;
    private static int[] edgeTo;
    private static boolean[] finished;
    private static int count;
    private static StringBuilder str;
    
    public static void solve(int[] preference)
    {
        n = preference.length;
        marked = new boolean[n];
        edgeTo = new int[n];
        finished = new boolean[n];
        
        count = 0;
        
        for (int v = 0; v < n; v++)
            if (!marked[v])
                dfs(preference, v);
            
        str.append(n - count).append('\n');
    }
    
    private static void dfs(int[] preference, int v)
    {
        marked[v] = true;
        int w = preference[v];
        
        if (!marked[w])
        {
            edgeTo[w] = v;
            dfs(preference, w);
        }
        else if (!finished[w])
        {
            for (int x = v; x != w; x = edgeTo[x])
                count++;
            count++;
        }
        
        finished[v] = true;
    }
    
    public static void main(String[] args)
    {
        In.init();
        str = new StringBuilder();
        
        int test = In.nextInt();
        while (test-- > 0)
        {
            int n = In.nextInt();
            int[] preference = new int[n];
            for (int i = 0; i < preference.length; i++)
                preference[i] = In.nextInt() - 1;
            solve(preference);
        }
        
        System.out.println(str.toString());
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