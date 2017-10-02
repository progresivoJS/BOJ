import java.util.*;
import java.io.*;

/**
 * problem 2150
 * Strongly Connected Component
 * implemented using kosaraju-sharir algorithm.
 * https://www.acmicpc.net/problem/2150
 * written by progresivoJS on 2017.10.02
 */
public class Main
{
    private static Deque<Integer> reversePostOrder;
    private static boolean[] marked;
    private static int[] id;
    private static int count;
    public static void solve(LinkedList<Integer>[] adj)
    {
        int n = adj.length;
        LinkedList<Integer>[] adjReverse = reverse(adj);
        
        reversePostOrder = new ArrayDeque<>();
        marked = new boolean[n];
        for (int v = 0; v < n; v++)
            if (!marked[v])
                dfsReverse(adjReverse, v);
        
        marked = new boolean[n];
        id = new int[n];
        count = 0;
        for (int v : reversePostOrder)
            if (!marked[v])
            {
                dfs(adj, v);
                count++;
            }
        
        int[] order = new int[count];
        for (int i = 0; i < count; i++)
            for (int j = 0; j < n; j++)
                if (id[j] == i)
                {
                    order[i] = j;
                    break;
                }
        Arrays.sort(order);
        
        StringBuilder str = new StringBuilder();
        str.append(count).append('\n');
        for (int i : order)
        {
            for (int j = 0; j < n; j++)
                if (id[j] == id[i])
                    str.append(j + 1).append(' ');
            str.append(-1).append('\n');
        }
        
        System.out.println(str.toString());
    }
    
    private static void dfs(LinkedList<Integer>[] adj, int v)
    {
        marked[v] = true;
        id[v] = count;
        for (int w : adj[v])
            if (!marked[w])
                dfs(adj, w);
    }
    
    private static void dfsReverse(LinkedList<Integer>[] adj, int v)
    {
        marked[v] = true;
        for (int w : adj[v])
            if (!marked[w])
                dfsReverse(adj, w);
        reversePostOrder.push(v);
    }
    
    private static LinkedList<Integer>[] reverse(LinkedList<Integer>[] adj)
    {
        int n = adj.length;
        LinkedList<Integer>[] adjReverse = (LinkedList<Integer>[]) new LinkedList[n];
        for (int i = 0; i < n; i++)
            adjReverse[i] = new LinkedList<>();
        
        for (int v = 0; v < n; v++)
            for (int w : adj[v])
                adjReverse[w].add(v);
        return adjReverse;
    }
    
    public static void main(String[] args)
    {
        In.init();
        int v = In.nextInt();
        int e = In.nextInt();
        
        LinkedList<Integer>[] adj = (LinkedList<Integer>[]) new LinkedList[v];
        for (int i = 0; i < v; i++)
            adj[i] = new LinkedList<>();
        
        while (e-- > 0)
        {
            int from = In.nextInt() - 1;
            int to = In.nextInt() - 1;
            
            adj[from].add(to);
        }
        
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