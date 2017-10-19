import java.util.*;
import java.io.*;

/**
 * problem 1991
 * 트리 순회
 * https://www.acmicpc.net/problem/1991
 * written by progresivoJS on 2017.10.19
 */
public class Main
{
    private static StringBuilder str;
    public static void main(String[] args)
    {
        In.init();
        str = new StringBuilder();
        int n = In.nextInt();
        Node[] tree = new Node[n];
        for (int i = 0; i < n; i++)
            tree[i] = new Node();
        while (n-- > 0)
        {
            int parent = In.next().charAt(0) - 'A';
            int left = In.next().charAt(0);
            int right = In.next().charAt(0);
            
            tree[parent].value = (char)(parent + 'A');
            if (left != '.')
                tree[parent].left = tree[left - 'A'];
            if (right != '.')
                tree[parent].right = tree[right - 'A'];
        }
        
        preorderTraverse(tree[0]);
        str.append('\n');
        inorderTraverse(tree[0]);
        str.append('\n');
        postorderTraverse(tree[0]);
        System.out.println(str);
    }
    
    private static void preorderTraverse(Node root)
    {
        if (root == null) return;
        str.append(root.value);
        preorderTraverse(root.left);
        preorderTraverse(root.right);
    }
    
    private static void inorderTraverse(Node root)
    {
        if (root == null) return;
        inorderTraverse(root.left);
        str.append(root.value);
        inorderTraverse(root.right);
    }
    
    private static void postorderTraverse(Node root)
    {
        if (root == null) return;
        postorderTraverse(root.left);
        postorderTraverse(root.right);
        str.append(root.value);
    }
    
    private static class Node
    {
        Node left, right;
        char value;
        public Node()
        {
            
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