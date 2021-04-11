import os
import shutil


if __name__ == '__main__':
    destination = r'/home/jan/Development/train-ticket'
    parent_dir = "/home/jan/Development/stress-tests"
    files = os.listdir(destination)
    dir_list = []
    for f in files:
        if os.path.isdir(os.path.join(destination, f)) and f.startswith('ts'):
            dir_list.append(f)
    pivot = 1
    while pivot != len(dir_list) + 1:
        print('---- new repo')
        ctr = 0
        p = os.path.join(parent_dir, str(pivot))
        os.mkdir(p, 0o777)
        for i in range(0, pivot):
            print(dir_list[i])
            f = dir_list[i]
            src = os.path.join(destination, f)
            dst = os.path.join(p, f)
            shutil.copytree(src, dst)
            ctr += 1
        print('---- ' + str(ctr))
        pivot += 1

