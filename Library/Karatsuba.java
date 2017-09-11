import java.util.*;
public class Karatsuba
{
    public static ArrayList<Integer> bruteMul(ArrayList<Integer> a, ArrayList<Integer> b)
    {
        ArrayList<Integer> c = new ArrayList<>(a.size() + b.size() + 1);
        for (int i = 0; i < a.size() + b.size() + 1; i++)
            c.add(0);
        for (int i = 0; i < a.size(); i++)
            for (int j = 0; j < b.size(); j++)
                c.set(i + j, c.get(i+j) + a.get(i) * b.get(j));
        normalize(c);
        return c;
    }
    
    private static void normalize(ArrayList<Integer> c)
    {
        c.add(0);
        
        for (int i = 0; i < c.size() - 1; i++)
        {
            if (c.get(i) >= 0)
            {
                c.set(i+1, c.get(i+1) + c.get(i) / 10);
                c.set(i, c.get(i) % 10);
            }
            // 이 부분은 bruteMul에선 필요없음.
            // karatsuba 에서만 필요.
            else
            {
                int borrow = Math.abs(c.get(i) + 9) / 10;
                c.set(i + 1, c.get(i+1) - borrow);
                c.set(i, c.get(i) + borrow * 10);
            }
        }
        
        // 곱의 결과의 자릿수가
        // a.size() + b.size() + 1 에 미치지 못할 때,
        // 0인 부분을 없애준다.
        while (c.size() > 1 && c.get(c.size()-1) == 0)
            c.remove(c.size() -1);
    }
    
    public static ArrayList<Integer> karatsuba(ArrayList<Integer> a, ArrayList<Integer> b)
    {
        ArrayList<Integer> c = new ArrayList<>();
        
        int aN = a.size(), bn = b.size();
        
        if (aN < bN)
            return karatsuba(b, a);
            
        // assure(aN >= bN)
        
        // base case
        if (aN == 0 || bN == 0)
            return new ArrayList<Integer>();
        // n 이 비교적 작은 경우, 
        if (aN <= 50)
            return bruteMul(a, b);
        
        int half = aN / 2;
        
        ArrayList<Integer> a0 = new ArrayList<>(a.subList(0, half));
        ArrayList<Integer> a1 = new ArrayList<>(a.subList(half, a.size()));
        
        ArrayList<Integer> b0 = new ArrayList<>(b.subList(0, half));
        ArrayList<Integer> b1 = new ArrayList<>(b.subList(half, b.size());
        
        ArrayList<Integer> z2 = karatsuba(a1, b1);
        ArrayList<Integer> z0 = karatsuba(a0, b0);
        
        addTo(a0, a1, 0);
        addTo(b0, b1, 0);
        
        ArrayList<Integer> z1 = karatsuba(a0, b0);
        subFrom(z1, z0);
        subFrom(z1, z2);
        
        ArrayList<Integer> c = new ArrayList<>();
        addTo(c, z0, 0);
        addTo(c, z1, half);
        addTo(c, z2, half + half);
        
        normalize(c);
        return c;
    }
    
    // a += b * (10 ^ k)
    private static void addTo(ArrayList<Integer> a, ArrayList<Integer> b, int k)
    {
        
    }
    
    // a -= b; a >= b 라고 가정
    private static void subFrom(ArrayList<Integer> a, ArrayList<Integer> b)
    {
        
    }
    
    public static void main(String[] args)
    {
        String str1 = "2131321321321";
        String str2 = "134124124215";
        
        ArrayList<Integer> a = new ArrayList<>(str1.length());
        ArrayList<Integer> b = new ArrayList<>(str2.length());
        
        for (int i = str1.length() - 1; i >= 0; i--)
            a.add(str1.charAt(i) - '0');
        for (int i = str2.length() - 1; i >= 0; i--)
            b.add(str2.charAt(i) - '0');
        
        ArrayList<Integer> c = bruteMul(a, b);
        for (int i = c.size() -1; i >= 0; i--)
            System.out.print(c.get(i));
    }
}