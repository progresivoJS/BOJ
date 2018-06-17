import sys
from collections import deque
read = sys.stdin.readline

class Man:
    def __init__(self, index):
        self.index = index
        self.preference = None
        self.partner = -1

class Woman:
    def __init__(self, index):
        self.index = index
        self.ranking = None
        self.partner = -1


n = int(read())

man_list = [Man(i) for i in range(n)]
for i in range(n):
    girls = list(map(int, read().split()))
    man_list[i].preference = deque([girl - 1 for girl in girls])

woman_list = [Woman(i) for i in range(n)]

for woman in woman_list:
    woman.ranking = [None] * n
    woman_preference = list(map(int, read().split()))
    for j in range(n):
        woman.ranking[woman_preference[j] - 1] = j

queue = deque(man_list)
while (queue):
    man = queue.popleft()
    woman = woman_list[man.preference.popleft()]
    
    # when the woman is single.
    if woman.partner == -1:
        woman.partner = man.index
        man.partner = woman.index
    else:
        woman_current_partner = woman.partner
        
        # Replace
        if woman.ranking[woman_current_partner] > woman.ranking[man.index]:
            woman.partner = man.index
            man.partner = woman.index
            queue.append(man_list[woman_current_partner])
        
        # Reject
        else:
            queue.append(man)

for man in man_list:
    print(man.partner + 1)