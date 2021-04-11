import os
inconsistencies_man = open('inconsistencies-man.txt', 'r')
inconsistencies_aut = open('inconsistencies-aut.txt', 'r')
lines_man = inconsistencies_man.readlines()
lines_aut = inconsistencies_aut.readlines()
tp = 0
for man in lines_man:
    for aut in lines_aut:
        if man == aut:
            tp += 1
print(tp)
