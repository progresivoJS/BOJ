# Karatsuba

큰 수의 곱셈을 위해 사용한다.

[위키참고](https://ko.wikipedia.org/wiki/%EC%B9%B4%EB%9D%BC%EC%B6%94%EB%B0%94_%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98#.EC.95.8C.EA.B3.A0.EB.A6.AC.EC.A6.98)


## BigInteger

java 의 경우, BigInteger를 쓸 수있다. Karatsuba와 성능 비교는 아래와 같다.

그런데, Java SE7 까지는 naive한 방법으로 multiplication이 구현되어 있었는데,
[Karatsuba Benchmark](https://www.nayuki.io/page/karatsuba-multiplication)에 따르면,
Java SE8 부터는 Karatsuba와 Toom-Cook 방법을 이용했다고 한다.

아래 그림은 SE7까지의 multiplication과 Karatsuba 알고리즘과 비교한 것이다.

![benchmark](https://www.nayuki.io/res/karatsuba-multiplication/benchmark.png)