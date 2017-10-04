import java.util.*;
import java.io.*;

/**
 * problem 6264
 * Sub-dictionary
 * https://www.acmicpc.net/problem/6264
 * written by progresivoJS on 2017.10.04
 */
public class Main
{
    private static StringBuilder str;
    private static TreeMap<String, Integer> map;
    public static void solve(LinkedList<Integer>[] adj, String[] keys)
    {
        int n = adj.length;
        LinkedList<Integer>[] reverseAdj = reverse(adj);
        
        // find reverse post order.
        boolean[] marked = new boolean[n];
        Deque<Integer> reversePostOrder = new ArrayDeque<>();
        for (int v = 0; v < n; v++)
            if (!marked[v])
                dfs(reverseAdj, v, marked, reversePostOrder);
        
        // name scc id.
        int[] id = new int[n];
        Arrays.fill(marked, false);
        int count = 0;
        for (int v : reversePostOrder)
            if (!marked[v])
                dfsForSCC(adj, v, marked, count++, id);
        
        // construct scc graph
        LinkedList<Integer>[] sccAdj = (LinkedList<Integer>[]) new LinkedList[count];
        int[] indegree = new int[count];
        for (int i = 0; i < count; i++)
            sccAdj[i] = new LinkedList<>();
        for (int v = 0; v < n; v++)
            for (int w : adj[v])
            {
                int from = id[v];
                int to = id[w];
                if (from == to) continue;
                sccAdj[from].add(to);
                indegree[to]++;
            }
        
        int[] sccSize = new int[count];
        for (int v = 0; v < n; v++)
            sccSize[id[v]]++;
        
        // topological sort
        boolean[] sMarked = new boolean[count];
        LinkedList<String> wordSet = new LinkedList<String>();
        Queue<Integer> q = new LinkedList<>();
        for (int v = 0; v < count; v++)
            if (indegree[v] == 0)
            {
                sMarked[v] = true;
                q.add(v);
                for (int i = 0; i < n; i++)
                    if (id[i] == v)
                        wordSet.add(keys[i]);
            }
        
        while (!q.isEmpty())
        {
            int v = q.poll();
            for (int w : sccAdj[v])
            {
                if (!sMarked[w])
                {
                    if (sccSize[w] != 1)
                    {
                        for (int i = 0; i < n; i++)
                            if (id[i] == w)
                                wordSet.add(keys[i]);
                    }
                    sMarked[w] = true;
                }
                
                if (--indegree[w] == 0)
                    q.add(w);
            }
        }
        
        
        str.append(wordSet.size()).append('\n');
        Collections.sort(wordSet);
        for (String word : wordSet)
            str.append(word).append(' ');
        str.append('\n');
    }
    
    private static void dfsForSCC(LinkedList<Integer>[] adj, int v, boolean[] marked, int count, int[] id)
    {
        marked[v] = true;
        id[v] = count;
        for (int w : adj[v])
            if (!marked[w])
                dfsForSCC(adj, w, marked, count, id);
    }
    
    private static void dfs(LinkedList<Integer>[] adj, int v, boolean[] marked, Deque<Integer> reversePostOrder)
    {
        marked[v] = true;
        for (int w : adj[v])
            if (!marked[w])
                dfs(adj, w, marked, reversePostOrder);
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
        str = new StringBuilder();
        while (true)
        {
            int n = In.nextInt();
            if (n == 0) break;
            
            LinkedList<String>[] dic = (LinkedList<String>[]) new LinkedList[n];
            for (int i = 0; i < n; i++)
                dic[i] = new LinkedList<>();
            for (int i = 0; i < n; i++)
            {
                String line = In.readLine();
                StringTokenizer st = new StringTokenizer(line);
                while (st.hasMoreElements())
                    dic[i].add(st.nextToken());
            }
            
            map = new TreeMap<>();
            for (int i = 0; i < n; i++)
                map.put(dic[i].get(0), i);
            
            // construct graph
            LinkedList<Integer>[] adj = (LinkedList<Integer>[]) new LinkedList[n];
            for (int i = 0; i < n; i++)
                adj[i] = new LinkedList<>();
            for (int i = 0; i < n; i++)
            {
                int to = map.get(dic[i].get(0));
                for (String j : dic[i])
                {
                    int from = map.get(j);
                    if (from == to)
                        continue;
                    adj[from].add(to);
                }
            }
            
            // keys[i] = value가 i인 key String.
            String[] keys = new String[n];
            for (String key : map.keySet())
                keys[map.get(key)] = key;
            
            solve(adj, keys);
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
        
        public static String readLine()
        {
            String str = new String();
            try
            {
                str = br.readLine();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            
            return str;
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