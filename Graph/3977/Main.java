import java.util.*;
import java.io.*;
import java.lang.*;

/**
 * problem 3977
 * 축구 전술
 * https://www.acmicpc.net/problem/3977
 * written by progresivoJS on 2017.10.02
 */
public class Main
{
    private static StringBuilder str;
    
    private static boolean[] marked;
    private static Deque<Integer> reversePostOrder;
    private static int[] id;
    
    public static void solve(LinkedList<Integer>[] adj)
    {
        int n = adj.length;
        LinkedList<Integer>[] adjReverse = reverse(adj);
        
        // find reversePostOrder
        marked = new boolean[n];
        reversePostOrder = new ArrayDeque<>();
        for (int v = 0; v < n; v++)
            if (!marked[v])
                dfs(adjReverse, v);
        
        // name scc id
        id = new int[n];
        Arrays.fill(marked, false);
        int count = 0;
        for (int v : reversePostOrder)
            if (!marked[v])
                dfsForSCC(adj, v, count++);
        
        // make scc graph
        LinkedList<Integer>[] sccAdj = (LinkedList<Integer>[]) new LinkedList[count];
        for (int i = 0; i < count; i++)
            sccAdj[i] = new LinkedList<>();
        int[] ind = new int[count];
        
        for (int v = 0; v < n; v++)
            for (int w : adj[v])
            {
                if (id[v] == id[w]) continue;
                sccAdj[id[v]].add(id[w]);
                ind[id[w]]++;
            }
        
        // count scc component of indegree is zero.
        Queue<Integer> q = new LinkedList<>();
        for (int v = 0; v < count; v++)
            if (ind[v] == 0)
                q.add(v);
        
        if (q.size() > 1)
        {
            str.append("Confused").append('\n').append('\n');
            return;
        }
        
        // print result
        int startPoint = q.poll();
        for (int i = 0; i < n; i++)
            if (id[i] == startPoint)
                str.append(i).append('\n');
        str.append('\n');
    }
    
    private static void dfsForSCC(LinkedList<Integer>[] adj, int v, int count)
    {
        marked[v] = true;
        id[v] = count;
        for (int w : adj[v])
            if (!marked[w])
                dfsForSCC(adj, w, count);
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
    
    public static void main(String[] args) throws java.lang.Exception
    {
        In.init();
        str = new StringBuilder();
        
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
                int from = In.nextInt();
                int to = In.nextInt();
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