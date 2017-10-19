import java.util.*;
import java.io.*;

/**
 * problem 11725
 * 트리의 부모 찾기
 * https://www.acmicpc.net/problem/11725
 * written by progresivoJS on 2017.10.19
 */
public class Main
{
    private static int[] parent;
    private static LinkedList<Integer>[] adj;
    private static boolean[] marked;
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        
        adj = (LinkedList<Integer>[]) new LinkedList[n];
        for (int i = 0; i < n; i++)
            adj[i] = new LinkedList<>();
        
        parent = new int[n];
        marked = new boolean[n];
        
        for (int i = 0; i < n - 1; i++)
        {
            int a = In.nextInt() - 1;
            int b = In.nextInt() - 1;
            adj[a].add(b);
            adj[b].add(a);
        }
        
        dfs(0);
        
        StringBuilder str = new StringBuilder();
        for (int i = 1; i < n; i++)
            str.append(parent[i] + 1).append('\n');
        System.out.println(str);
    }
    
    private static void dfs(int v)
    {
        marked[v] = true;
        for (int w : adj[v])
            if (!marked[w])
            {
                parent[w] = v;
                dfs(w);
            }
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