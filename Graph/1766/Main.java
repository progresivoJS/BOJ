import java.util.*;
import java.io.*;

/**
 * problem 1766
 * 문제집
 * https://www.acmicpc.net/problem/1766
 * written by progresivoJS on 2017.09.28
 */
public class Main
{
    private static Deque<Integer> reversePostOrder;
    private static boolean[] marked;
    public static void solve(LinkedList<Integer>[] adj)
    {
        reversePostOrder = new ArrayDeque<>();
        marked = new boolean[adj.length];
        
        for (int v = adj.length - 1; v >= 0; v--)
            if (!marked[v])
                dfs(adj, v);
                
        StringBuilder str = new StringBuilder();
        for (int i : reversePostOrder)
            str.append(i + 1).append(' ');
            
        System.out.println(str.toString());
    }
    
    private static void dfs(LinkedList<Integer>[] adj, int v)
    {
        marked[v] = true;
        for (int w : adj[v])
            if (!marked[w])
                dfs(adj, w);
        reversePostOrder.push(v);
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int m = In.nextInt();
        LinkedList<Integer>[] adj = (LinkedList<Integer>[]) new LinkedList[n];
        for (int i = 0; i < adj.length; i++)
            adj[i] = new LinkedList<>();
        for (int i = 0; i < m; i++)
            adj[In.nextInt() - 1].add(In.nextInt() - 1);
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