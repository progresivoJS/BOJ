import java.util.*;
import java.io.*;

/**
 * problem 4013
 * ATM
 * https://www.acmicpc.net/problem/4013
 * written by progresivoJS on 2017.10.02
 */
public class Main
{
    private static boolean[] marked;
    private static int count;
    private static int[] id;
    public static void solve(LinkedList<Integer>[] adj, int[] cash, int start, boolean[] isRestaurant)
    {
        int n = adj.length;
        nameSCCId(adj);
        
        // make new SCC graph
        LinkedList<Integer>[] newAdj = (LinkedList<Integer>[]) new LinkedList[count];
        for (int i = 0; i < count; i++)
            newAdj[i] = new LinkedList<>();
        
        for (int v = 0; v < n; v++)
            for (int w : adj[v])
                if (id[v] != id[w]) // this line assures no parallel edges.
                    newAdj[id[v]].add(id[w]);
        
        boolean[] newIsRestaurant = new boolean[count];
        for (int i = 0; i < n; i++)
            if (isRestaurant[i])
                newIsRestaurant[id[i]] = true;
        
        int[] newCash = new int[count];
        for (int i = 0; i < n; i++)
            newCash[id[i]] += cash[i];
        
        start = id[start];
        
        bfs(newAdj, start, newIsRestaurant, newCash);
    }
    
    private static void bfs(LinkedList<Integer>[] adj, int start, boolean[] isRestaurant, int[] cash)
    {
        int n = adj.length;
        boolean[] marked = new boolean[n];
        int[] totalCash = new int[n];
        totalCash[start] = cash[start];
        
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        
        while (!q.isEmpty())
        {
            int v = q.poll();
            for (int w : adj[v])
            {
                totalCash[w] = Math.max(totalCash[w], totalCash[v] + cash[w]);
                if (!marked[w])
                {
                    q.add(w);
                    marked[w] = true;
                }
            }
        }
        
        int max = 0;
        for (int i = 0; i < n; i++)
            if (isRestaurant[i])
                max = Math.max(max, totalCash[i]);
        for (int i : totalCash)
        {
            System.out.println(i);
        }
        // System.out.println(max);
    }
    
    private static void nameSCCId(LinkedList<Integer>[] adj)
    {
        int n = adj.length;
        LinkedList<Integer>[] reverseAdj = makeReverseGraph(adj);
        
        // make topological order.
        marked = new boolean[n];
        Deque<Integer> reversePostOrder = new ArrayDeque<Integer>();
        for (int v = 0; v < n; v++)
            if (!marked[v])
                dfsForTopological(reverseAdj, v, reversePostOrder);
                
        // naming SCC id.
        marked = new boolean[n];
        count = 0;
        id = new int[n];
        for (int v : reversePostOrder)
            if (!marked[v])
            {
                dfsForSCC(adj, v);
                count++;
            }
    }
    
    private static void dfsForSCC(LinkedList<Integer>[] adj, int v)
    {
        marked[v] = true;
        id[v] = count;
        for (int w : adj[v])
            if (!marked[w])
                dfsForSCC(adj, w);
    }
    
    private static void dfsForTopological(LinkedList<Integer>[] adj, int v, Deque<Integer> reversePostOrder)
    {
        marked[v] = true;
        for (int w : adj[v])
            if (!marked[w])
                dfsForTopological(adj, w, reversePostOrder);
        reversePostOrder.push(v);
    }
    
    private static LinkedList<Integer>[] makeReverseGraph(LinkedList<Integer>[] adj)
    {
        int n = adj.length;
        LinkedList<Integer>[] reverseAdj = (LinkedList<Integer>[]) new LinkedList[n];
        for (int i = 0; i < n; i++)
            reverseAdj[i] = new LinkedList<>();
        
        for (int v = 0; v < n; v++)
            for (int w : adj[v])
                reverseAdj[w].add(v);
        
        return reverseAdj;
    }
    
    public static void main(String[] args)
    {
        In.init();
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
        
        int[] cash = new int[n];
        for (int i = 0; i < n; i++)
        {
            cash[i] = In.nextInt();
        }
        
        int start = In.nextInt() - 1;
        int restCount = In.nextInt();
        
        boolean[] isRestaurant = new boolean[n];
        for (int i = 0; i < restCount; i++)
            isRestaurant[In.nextInt() - 1] = true;
            
        solve(adj, cash, start, isRestaurant);
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