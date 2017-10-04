import java.util.*;
import java.io.*;

/**
 * problem 2152
 * 여행 계획 세우기
 * https://www.acmicpc.net/problem/2152
 * written by progresivoJS on 2017.10.03
 */
public class Main
{
    private static Deque<Integer> reversePostOrder;
    private static boolean[] marked;
    private static int[] id;
    
    public static void solve(LinkedList<Integer>[] adj, int start, int end)
    {
        int n = adj.length;
        LinkedList<Integer>[] reverseAdj = reverse(adj);
        
        // find reverse post order.
        reversePostOrder = new ArrayDeque<>();
        marked = new boolean[n];
        for (int v = 0; v < n; v++)
            if (!marked[v])
                dfs(reverseAdj, v);
        
        // name scc id.
        Arrays.fill(marked, false);
        id = new int[n];
        int count = 0;
        for (int v : reversePostOrder)
            if (!marked[v])
                dfsForSCC(adj, v, count++);
        
        // construct scc graph.
        start = id[start];
        end = id[end];
        LinkedList<Integer>[] sccAdj = (LinkedList<Integer>[]) new LinkedList[count];
        for (int i = 0; i < count; i++)
            sccAdj[i] = new LinkedList<>();
        
        int[] cityCount = new int[count];
        int[] ind = new int[count];
        
        for (int v = 0; v < n; v++)
        {
            cityCount[id[v]]++;
            for (int w : adj[v])
            {
                int from = id[v];
                int to = id[w];
                
                if (from == to) continue;
                sccAdj[from].add(to);
                ind[to]++;
            }
        }
        
        // topological sort
        int[] totalCityCount = new int[count];
        for (int i = 0; i < count; i++)
            totalCityCount[i] = cityCount[i];
        
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < count; i++)
            if (ind[i] == 0)
                q.add(i);
        
        boolean[] sccMarked = new boolean[count];
        sccMarked[start] = true;
        
        while (!q.isEmpty())
        {
            int v = q.poll();
            for (int w : sccAdj[v])
            {
                if (sccMarked[v])
                {
                    totalCityCount[w] = Math.max(totalCityCount[w], totalCityCount[v] + cityCount[w]);
                    sccMarked[w] = true;
                }
                
                if (--ind[w] == 0)
                    q.add(w);
            }
        }
        
        if (!sccMarked[end])
            System.out.println(0);
        else
            System.out.println(totalCityCount[end]);
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
    
    private static void dfs(LinkedList<Integer>[] adj, int v)
    {
        marked[v] = true;
        for (int w : adj[v])
            if (!marked[w])
                dfs(adj, w);
        reversePostOrder.push(v);
    }
    
    private static void dfsForSCC(LinkedList<Integer>[] adj, int v, int count)
    {
        marked[v] = true;
        id[v] = count;
        for (int w : adj[v])
            if (!marked[w])
                dfsForSCC(adj, w, count);
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int m = In.nextInt();
        int start = In.nextInt() - 1;
        int end = In.nextInt() - 1;
        
        LinkedList<Integer>[] adj = (LinkedList<Integer>[]) new LinkedList[n];
        for (int i = 0; i < n; i++)
            adj[i] = new LinkedList<>();
        
        while (m-- > 0)
        {
            int from = In.nextInt() - 1;
            int to = In.nextInt() - 1;
            
            adj[from].add(to);
        }
        
        solve(adj, start, end);
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