import java.util.*;
import java.io.*;

/**
 * problem 10814
 * 나이순 정렬
 * https://www.acmicpc.net/problem/10814
 * written by progresivoJS on 2017.10.28
 */
public class Main
{
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        Person[] people = new Person[n];
        for (int i = 0; i < n; i++)
            people[i] = new Person(In.nextInt(), In.next());
        Arrays.sort(people);
        
        StringBuilder str = new StringBuilder();
        for (Person p : people)
            str.append(p.age).append(' ').append(p.name).append('\n');
        System.out.println(str);
    }
    
    private static class Person implements Comparable<Person>
    {
        int age;
        String name;
        public Person(int age, String name)
        {
            this.age = age;
            this.name = name;
        }
        
        public int compareTo(Person other)
        {
            return this.age - other.age;
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