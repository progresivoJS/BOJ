# Pisano Period

## 정의
피보나치 수를 M로 나눈 나머지는 항상 주기를 갖는다. 이 주기를 pisano period라고 한다.

M = 10 ^ k일 때, 주기 P 는 15 * 10^k-1 이다.

## 적용
주기가 p라고 하면, N 번째 피보나치 수를 M으로 나눈 나머지는 N % p 번째 피보나치 수를 M으로 나눈 나머지와 같다.

즉, fibo(N) % M == fibo(N % p) % M.

## 증명
증명은 [wiki](https://en.wikipedia.org/wiki/Pisano_period) 참조
