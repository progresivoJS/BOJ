import sys
import operator
read = sys.stdin.readline

n = int(read())
intervals = []
for i in range(n):
    s, e = map(int, read().split()) 
    intervals.append((s, e))

intervals.sort(key=operator.itemgetter(1, 0))

right_end = intervals[0][1]
count = 1
for s, e in intervals[1:]:
    if right_end <= s:
        right_end = e
        count += 1

print(count)