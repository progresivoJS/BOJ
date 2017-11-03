import java.util.*;
import java.io.*;

/**
 * problem 14725
 * 개미굴
 * https://www.acmicpc.net/problem/14725
 * written by progresivoJS on 2017.11.02
 */
public class Main
{
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        
        Node root = new Node();
        
        for (int i = 0; i < n; i++)
        {
            Node parent = root;
            
            int chain = In.nextInt();
            for (int j = 0; j < chain; j++)
            {
                Node child = new Node(In.next());
                parent.children.put(child.value, child);
                parent = child;
            }
        }
        
        for (String child : root.children.keySet())
        {
            System.out.println(child);
        }
        
        track(root);
    }
    
    private static void track(Node parent)
    {
        if (parent.children.size() == 0)
            return;
        
        for (Node child : parent.children.values())
        {
            System.out.println(child.value);
            track(child);
        }
    }
    
    private static class Node implements Comparable<Node>
    {
        String value;
        TreeMap<String, Node> children;
        
        public Node()
        {
            children = new TreeMap<>();
        }
        
        public Node(String value)
        {
            this.value = value;
            children = new TreeMap<>();
        }
        
        public int compareTo(Node other)
        {
            return this.value.compareTo(other.value);
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