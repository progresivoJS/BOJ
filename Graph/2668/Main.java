import java.util.*;
import java.io.*;

/**
 * problem 2668
 * 숫자고르기
 * https://www.acmicpc.net/problem/2668
 * written by progresivoJS on 2017.09.26
 */
public class Main
{
    private static int n;
    private static boolean[] marked;
    private static int[] edgeTo;
    private static boolean[] finished;
    private static LinkedList<Integer> cycle;
    
    public static void solve(int[] adj)
    {
        n = adj.length;
        marked = new boolean[n];
        edgeTo = new int[n];
        finished = new boolean[n];
        cycle = new LinkedList<>();
        
        for (int i = 0; i < n; i++)
            if (!marked[i])
                dfs(adj, i);
        
        StringBuilder str = new StringBuilder();
        str.append(cycle.size()).append('\n');
        Collections.sort(cycle);
        for (Integer i : cycle)
            str.append(i + 1).append('\n');
        System.out.println(str.toString());
    }
    
    private static void dfs(int[] adj, int v)
    {
        marked[v] = true;
        
        int w = adj[v];
        
        if (!marked[w])
        {
            edgeTo[w] = v;
            dfs(adj, w);
        }
        else if (!finished[w])
        {
            for (int x = v; x != w; x = edgeTo[x])
            {
                cycle.add(x);
            }
            cycle.add(w);
        }
        
        finished[v] = true;
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int[] adj = new int[n];
        for (int i = 0; i < n; i++)
            adj[i] = In.nextInt() - 1;
        solve(adj);
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