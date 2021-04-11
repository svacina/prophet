# This is a sample Python script.

# Press Shift+F10 to execute it or replace it with your code.
# Press Double Shift to search everywhere for classes, files, tool windows, actions, and settings.
from random import *




def print_hi(name):
    # Use a breakpoint in the code line below to debug your script.
    print(f'Hi, {name}')  # Press Ctrl+F8 to toggle the breakpoint.
    print(randint(1, 100))    # Pick a random number between 1 and 100.


interval_max1 = 40
interval_max2 = 50
interval_max3 = 60
interval_v1_min = 30
interval_v2_min = 40
interval_v3_min = 45


def random_nr(min_v, max_v):
    return str(randint(min_v, max_v))

# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    for i in range(1, 47):
        print(random_nr(interval_v1_min, interval_max1) + " " + random_nr(interval_v2_min, interval_max2) + " " + random_nr(interval_v3_min, interval_max3))

# See PyCharm help at https://www.jetbrains.com/help/pycharm/
