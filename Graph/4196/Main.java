import java.util.*;
import java.io.*;

/**
 * problem 4196
 * 도미노
 * https://www.acmicpc.net/problem/4196
 * written by progresivoJS on 2017.10.02
 */
public class Main
{
    private static Deque<Integer> reversePostOrder;
    private static boolean[] marked;
    private static int count;
    private static int[] id;
    public static void solve(LinkedList<Integer>[] adj)
    {
        int n = adj.length;
        LinkedList<Integer>[] adjReverse = reverse(adj);
        
        // find reverse post order.
        reversePostOrder = new ArrayDeque<>();
        marked = new boolean[n];
        for (int v = 0; v < n; v++)
            if (!marked[v])
                dfs(adjReverse, v);
        
        // naming scc id.
        marked = new boolean[n];
        id = new int[n];
        count = 0;
        for (int v : reversePostOrder)
            if (!marked[v])
            {
                dfsForSCC(adj, v);
                count++;
            }
        
        // construct SCC graph.    
        int[] ind = new int[count];
        LinkedList<Integer>[] sccAdj = (LinkedList<Integer>[]) new LinkedList[count];
        for (int i = 0; i < count; i++)
            sccAdj[i] = new LinkedList<>();
        
        for (int v = 0; v < n; v++)
            for (int w : adj[v])
            {
                sccAdj[id[v]].add(id[w]);
                ind[id[w]]++;
            }
        
        int result = 0;
        for (int i : ind)
            if (i == 0)
                result++;
        System.out.println(result);
    }
    
    private static void dfsForSCC(LinkedList<Integer>[] adj, int v)
    {
        marked[v] = true;
        id[v] = count;
        for (int w : adj[v])
            if (!marked[w])
                dfsForSCC(adj, w);
    }
    
    private static void dfs(LinkedList<Integer>[] adj, int v)
    {
        marked[v] = true;
        for (int w : adj[v])
            if (!marked[w])
                dfs(adj, w);
        reversePostOrder.push(v);
    }
    
    public static LinkedList<Integer>[] reverse(LinkedList<Integer>[] adj)
    {
        int n = adj.length;
        LinkedList<Integer>[] reverse = (LinkedList<Integer>[]) new LinkedList[n];
        for (int i = 0; i < n; i++)
            reverse[i] = new LinkedList<>();
        
        for (int v = 0; v < n; v++)
            for (int w : adj[v])
                reverse[w].add(v);
        return reverse;
    }
    
    public static void main(String[] args)
    {
        In.init();
        int test = In.nextInt();
        while (test-- > 0)
        {
            int n = In.nextInt();
            int m = In.nextInt();
            LinkedList<Integer>[] adj = (LinkedList<Integer>[]) new LinkedList[n];
            for (int i = 0; i < n; i++)
                adj[i] = new LinkedList<>();
            
            while (m-- > 0)
            {
                int from = In.nextInt() - 1;
                int to = In.nextInt() - 1;
                adj[from].add(to);
            }
            
            solve(adj);
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