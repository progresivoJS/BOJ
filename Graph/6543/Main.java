import java.util.*;
import java.io.*;

/**
 * problem 6543
 * The Bottom of a Graph
 * https://www.acmicpc.net/problem/6543
 * written by progresivoJS on 2017.10.09
 */
public class Main
{
    private static StringBuilder str;
    private static void solve(LinkedList<Integer>[] adj)
    {
        int n = adj.length;
        LinkedList<Integer>[] rAdj = reverse(adj);
        
        Deque<Integer> reversePostOrder = new ArrayDeque<>();
        boolean[] marked = new boolean[n];
        
        // find reverse post order.
        for (int v = 0; v < n; v++)
            if (!marked[v])
                rDfs(rAdj, v, marked, reversePostOrder);
        
        // name the scc id.
        int[] id = new int[n];
        Arrays.fill(marked, false);
        int count = 0;
        for (int v : reversePostOrder)
            if (!marked[v])
                sDfs(adj, v, marked, count++, id);
        
        // construct scc graph.
        boolean[] out = new boolean[count];
        for (int v = 0; v < n; v++)
            for (int w : adj[v])
            {
                int from = id[v];
                int to = id[w];
                if (from == to) continue;
                out[from] = true;
            }
        
        for (int j = 0; j < n; j++)
            if (!out[id[j]])
                str.append(j + 1).append(' ');
        str.append('\n');
    }
    
    private static void sDfs(LinkedList<Integer>[] adj, int v, boolean[] marked, int count, int[] id)
    {
        marked[v] = true;
        id[v] = count;
        for (int w : adj[v])
            if (!marked[w])
                sDfs(adj, w, marked, count, id);
    }
    
    private static void rDfs(LinkedList<Integer>[] adj, int v, boolean[] marked, Deque<Integer> reversePostOrder)
    {
        marked[v] = true;
        for (int w : adj[v])
            if (!marked[w])
                rDfs(adj, w, marked, reversePostOrder);
        reversePostOrder.push(v);
    }
    
    private static LinkedList<Integer>[] reverse(LinkedList<Integer>[] adj)
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
        str = new StringBuilder();
        while (true)
        {
            int n = In.nextInt();
            if (n == 0) break;
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