---
layout: post
title: "sequence,permutation,combination in python"
description: ""
category: 
tags: []
---


# 获取指定长度得所有序列

通过事件来表述这个序列，即n重伯努利实验（二项分布）的所有可能结果。例如时间a表示为： **a = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]**, 假设每次实验为从a中选择一个数字，那么进行n次实验，获得所有可能得序列。

比如，进行两次实验， n=2, 那么可能得结果有100个。这里因为每次实验都是相对独立的，所以每次实验的结果可能出现重复，也就是说在获得所有可能的序列中，可以存在重复得值。



## 递归实现，DFS（深度优先遍历）
```python
def gen_all_sequence_dfs(outcomes, length):
    """
    generate all sequence by dfs
    
    outcomes: all the possible event, a list
    length: how many times does the sequence repeat, sequence length
    """
    
    res = []
    seq = []
    dfs_sequence(outcomes, length, seq, res)
        
    return res
        
def dfs_sequence(outcomes, length, seq, res):
    """
    deep first search
    """
    if 0 == length:
        res.append(tuple(seq[:]))
        return
    
    for key in outcomes:
        seq.append(key)
        dfs_sequence(outcomes, length - 1, seq, res)
        seq.pop()
        
def run_dfs_example1():
    """
    Example of all sequences
    """
    print 'dfs gen all sequence'
    outcomes = set([0, 1, 2, 3, 4, 5, 6, 7, 8, 9])
    
    length = 2
    seq_outcomes = gen_all_sequence_dfs(outcomes, length)
    print "Computed", len(seq_outcomes), "sequences of", str(length), "outcomes"
    print "Sequences were", seq_outcomes

run_dfs_example1()
```

运行输出结果：


    dfs gen all sequence
    Computed 100 sequences of 2 outcomes
    Sequences were [(0, 0), (0, 1), (0, 2), (0, 3), (0, 4), (0, 5), (0, 6), (0, 7), (0, 8), (0, 9), (1, 0), (1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (1, 8), (1, 9), (2, 0), (2, 1), (2, 2), (2, 3), (2, 4), (2, 5), (2, 6), (2, 7), (2, 8), (2, 9), (3, 0), (3, 1), (3, 2), (3, 3), (3, 4), (3, 5), (3, 6), (3, 7), (3, 8), (3, 9), (4, 0), (4, 1), (4, 2), (4, 3), (4, 4), (4, 5), (4, 6), (4, 7), (4, 8), (4, 9), (5, 0), (5, 1), (5, 2), (5, 3), (5, 4), (5, 5), (5, 6), (5, 7), (5, 8), (5, 9), (6, 0), (6, 1), (6, 2), (6, 3), (6, 4), (6, 5), (6, 6), (6, 7), (6, 8), (6, 9), (7, 0), (7, 1), (7, 2), (7, 3), (7, 4), (7, 5), (7, 6), (7, 7), (7, 8), (7, 9), (8, 0), (8, 1), (8, 2), (8, 3), (8, 4), (8, 5), (8, 6), (8, 7), (8, 8), (8, 9), (9, 0), (9, 1), (9, 2), (9, 3), (9, 4), (9, 5), (9, 6), (9, 7), (9, 8), (9, 9)]



## 非递归实现
利用动态规划的原理（这里我也不太熟悉是不是动态规划，暂且这么叫，如果有错误，请大家帮忙更正），动态的计算第k次实验后获得得所有得序列。根据第k-1次实验的所有得序列得结果，然后把每一次结果拿出来计算这一次结果再加上一次实验（即第k次实验）能够获得的结果。
{% highlight python %}
def gen_all_sequences(outcomes, length):
    """
    Iterative function that enumerates the set of all sequences of
    outcomes of given length
    
    Permutation allow repeat
    """
    
    ans = set([()])
    for dummy_idx in range(length):
        temp = set()
        for seq in ans:
            for item in outcomes:
                new_seq = list(seq)
                new_seq.append(item)
                temp.add(tuple(new_seq))
        ans = temp
    return ans
    
# example for digits
def run_example1():
    """
    Example of all sequences
    """
    print 'gen all sequence'
    outcomes = set([0, 1, 2, 3, 4, 5, 6, 7, 8, 9])
    #outcomes = set(["Red", "Green", "Blue"])
    #outcomes = set(["Sunday", "Mondy", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"])
    
    length = 2
    seq_outcomes = gen_all_sequences(outcomes, length)
    print "Computed", len(seq_outcomes), "sequences of", str(length), "outcomes"
    print "Sequences were", seq_outcomes
    

print '#############################################'
run_example1()
{% endhighlight %}
代码运行结果：


    gen all sequence
    Computed 100 sequences of 2 outcomes
    Sequences were set([(7, 3), (6, 9), (0, 7), (1, 6), (3, 7), (2, 5), (8, 5), (5, 8), (4, 0), (9, 0), (6, 7), (5, 5), (7, 6), (0, 4), (1, 1), (3, 2), (2, 6), (8, 2), (4, 5), (9, 3), (6, 0), (7, 5), (0, 1), (3, 1), (9, 9), (7, 8), (2, 1), (8, 9), (9, 4), (5, 1), (7, 2), (1, 5), (3, 6), (2, 2), (8, 6), (4, 1), (9, 7), (6, 4), (5, 4), (7, 1), (0, 5), (1, 0), (0, 8), (3, 5), (2, 7), (8, 3), (4, 6), (9, 2), (6, 1), (5, 7), (7, 4), (0, 2), (1, 3), (4, 8), (3, 0), (2, 8), (9, 8), (8, 0), (6, 2), (5, 0), (1, 4), (3, 9), (2, 3), (1, 9), (8, 7), (4, 2), (9, 6), (6, 5), (5, 3), (7, 0), (6, 8), (0, 6), (1, 7), (0, 9), (3, 4), (2, 4), (8, 4), (5, 9), (4, 7), (9, 1), (6, 6), (5, 6), (7, 7), (0, 3), (1, 2), (4, 9), (3, 3), (2, 9), (8, 1), (4, 4), (6, 3), (0, 0), (7, 9), (3, 8), (2, 0), (1, 8), (8, 8), (4, 3), (9, 5), (5, 2)])




# Permutation 获取所有排列
给定一个序列，例如上面给出得 **a = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]**, 如果按照上面获取序列的算法，那么每次实验都作为独立得实现，序列中可以出现重复实验结果。但是再获取排列的时候则不能按照n重伯努利实验得思想进行了，获取排列不允许有重复得结果，即一个元素只能被选择一次。但是在排列中是存在元素得顺序因素得，也就是说同样得两个元素，不同得顺序为不同得排列。

## 非递归实现
在上面非递归算法得基础上，增加一个判断，判断该元素是否已经选择过就可以实现排列得获取。
{% highlight python %}
def gen_permutations(outcomes, length):
    """
    Iterative function that enumerates the set of all sequences of
    outcomes of given length
    Permutation not allow repeat
    """
    
    ans = set([()])
    for dummy_idx in range(length):
        temp = set()
        for seq in ans:
            for item in outcomes:
                if item in seq:
                    continue
                new_seq = list(seq)
                new_seq.append(item)
                temp.add(tuple(new_seq))
        ans = temp
    return ans

# example for digits
def run_example1():
    """
    Example of all sequences
    """
    outcomes = set([0, 1, 2, 3, 4, 5, 6, 7, 8, 9])
    #outcomes = set(["Red", "Green", "Blue"])
    #outcomes = set(["Sunday", "Mondy", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"])
    
    length = 2
    seq_outcomes = gen_permutations(outcomes, length)
    print "Computed", len(seq_outcomes), "sequences of", str(length), "outcomes"
    print "Sequences were", seq_outcomes

run_example1()

{% endhighlight %}
代码运行结果：


    Computed 90 sequences of 2 outcomes
    Sequences were set([(7, 3), (6, 9), (0, 7), (1, 6), (3, 7), (2, 5), (8, 5), (5, 8), (4, 0), (9, 0), (6, 7), (7, 6), (0, 4), (3, 2), (2, 6), (8, 2), (4, 5), (9, 3), (6, 0), (7, 5), (0, 1), (3, 1), (7, 8), (2, 1), (8, 9), (9, 4), (5, 1), (7, 2), (1, 5), (3, 6), (8, 6), (4, 1), (9, 7), (6, 4), (5, 4), (7, 1), (0, 5), (1, 0), (0, 8), (3, 5), (2, 7), (8, 3), (4, 6), (9, 2), (6, 1), (5, 7), (7, 4), (0, 2), (1, 3), (4, 8), (3, 0), (2, 8), (9, 8), (8, 0), (6, 2), (5, 0), (1, 4), (3, 9), (2, 3), (1, 9), (8, 7), (4, 2), (9, 6), (6, 5), (5, 3), (7, 0), (6, 8), (0, 6), (1, 7), (0, 9), (3, 4), (2, 4), (8, 4), (5, 9), (4, 7), (9, 1), (5, 6), (0, 3), (1, 2), (4, 9), (2, 9), (8, 1), (6, 3), (7, 9), (3, 8), (2, 0), (1, 8), (4, 3), (9, 5), (5, 2)])




## 递归实现
{% highlight python %}
def gen_permutations_dfs(outcomes, length):
    """
    Iterative function that enumerates the set of all sequences of
    outcomes of given length
    Permutation not allow repeat, DFS
    """

    res = []
    seq = []
    dfs_permutation(outcomes, length, seq, res)
        
    return res

def dfs_permutation(outcomes, length, seq, res):
    """
    deep first search
    """

    if 0 == length:
        res.append(tuple(seq[:]))
        return
    
    for key in outcomes:
        if key in seq:
            continue
        seq.append(key)
        dfs_permutation(outcomes, length - 1, seq, res)
        seq.pop()

# example for digits
def run_example2():
    """
    Example of all sequences
    """
    outcomes = set([0, 1, 2, 3, 4, 5, 6, 7, 8, 9])
    #outcomes = set(["Red", "Green", "Blue"])
    #outcomes = set(["Sunday", "Mondy", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"])
    
    length = 2
    seq_outcomes = gen_permutations_dfs(outcomes, length)
    print "Computed", len(seq_outcomes), "sequences of", str(length), "outcomes"
    print "Sequences were", seq_outcomes

run_example2()
{% endhighlight %}
运行结果：


    Computed 90 sequences of 2 outcomes
    Sequences were [(0, 1), (0, 2), (0, 3), (0, 4), (0, 5), (0, 6), (0, 7), (0, 8), (0, 9), (1, 0), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (1, 8), (1, 9), (2, 0), (2, 1), (2, 3), (2, 4), (2, 5), (2, 6), (2, 7), (2, 8), (2, 9), (3, 0), (3, 1), (3, 2), (3, 4), (3, 5), (3, 6), (3, 7), (3, 8), (3, 9), (4, 0), (4, 1), (4, 2), (4, 3), (4, 5), (4, 6), (4, 7), (4, 8), (4, 9), (5, 0), (5, 1), (5, 2), (5, 3), (5, 4), (5, 6), (5, 7), (5, 8), (5, 9), (6, 0), (6, 1), (6, 2), (6, 3), (6, 4), (6, 5), (6, 7), (6, 8), (6, 9), (7, 0), (7, 1), (7, 2), (7, 3), (7, 4), (7, 5), (7, 6), (7, 8), (7, 9), (8, 0), (8, 1), (8, 2), (8, 3), (8, 4), (8, 5), (8, 6), (8, 7), (8, 9), (9, 0), (9, 1), (9, 2), (9, 3), (9, 4), (9, 5), (9, 6), (9, 7), (9, 8)]



# 组合
组合与排列最大得区别是组合不关心顺序，所以组合得数量要比排列少。
，组合可以表示为$$ c_n^m $$，从n个元素中选择m个元素，并且不关心顺序。
组合可以简单得在排列得结果得基础上去除不考虑顺序得情况下重复得结果即可获得。
组合的计算公式：
$$ c_n^m = \dfrac{n!}{m!(n-m)!} $$

## 非递归实现
{% highlight python %}
def gen_combination(outcomes, length):
    """
    Iterative function that enumerates the set of all sequences of
    outcomes of given length
    Permutation not allow repeat
    """
    
    ans = set([()])
    for dummy_idx in range(length):
        temp = set()
        for seq in ans:
            for item in outcomes:
                if item in seq:
                    continue
                new_seq = list(seq)
                new_seq.append(item)
                temp.add(tuple(sorted(new_seq)))
        ans = temp
    return ans


def run_example():
    """
    Examples of sorted sequences of outcomes
    """
    # example for digits
    outcomes = set([0, 1, 2, 3, 4, 5, 6, 7, 8, 9])
    #outcomes = set(["Red", "Green", "Blue"])
    #outcomes = set(["Sunday", "Mondy", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"])
    
    length = 2
    seq_outcomes = gen_combination(outcomes, length)
    print "Computed", len(seq_outcomes), "sorted sequences of", str(length) ,"outcomes"
    print "Sequences were", seq_outcomes

run_example()
{% endhighlight %}
运行结果：

    Computed 45 sorted sequences of 2 outcomes
    Sequences were set([(5, 9), (6, 9), (1, 3), (4, 8), (5, 6), (2, 8), (4, 7), (0, 7), (4, 6), (8, 9), (1, 6), (3, 7), (2, 5), (0, 3), (5, 8), (1, 2), (6, 7), (2, 9), (1, 5), (3, 6), (0, 4), (3, 5), (2, 7), (2, 6), (4, 5), (1, 4), (3, 9), (2, 3), (1, 9), (4, 9), (0, 8), (7, 9), (0, 1), (6, 8), (3, 4), (5, 7), (2, 4), (3, 8), (0, 6), (1, 8), (1, 7), (0, 9), (0, 5), (7, 8), (0, 2)])



## 组合的递归实现
{% highlight python %}
def gen_combination_dfs(outcomes, length):
    """
    Iterative function that enumerates the set of all sequences of
    outcomes of given length
    Permutation not allow repeat, DFS
    """

    res = []
    seq = []
    idx = 0
    dfs_combination(outcomes, length, idx, seq, res)
        
    return res

def dfs_combination(outcomes, length, idx, seq, res):
    """
    deep first search
    """
    if idx + length > len(outcomes):
        return

    if 0 == length:
        res.append(tuple(seq[:]))
        return
    
    for i in range(idx, len(outcomes) - length + 1):
        seq.append(outcomes[i])
        dfs_combination(outcomes, length - 1, i + 1, seq, res)
        seq.pop()

def run_example2():
    """
    Examples of sorted sequences of outcomes
    """
    # example for digits
    print "dfs combination"
    outcomes = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
    #outcomes = set(["Red", "Green", "Blue"])
    #outcomes = set(["Sunday", "Mondy", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"])
    
    length = 2
    seq_outcomes = gen_combination_dfs(outcomes, length)
    print "Computed", len(seq_outcomes), "sorted sequences of", str(length) ,"outcomes"
    print "Sequences were", seq_outcomes

run_example2()
{% endhighlight %}
运行结果：


    Computed 45 sorted sequences of 2 outcomes
    Sequences were [(0, 1), (0, 2), (0, 3), (0, 4), (0, 5), (0, 6), (0, 7), (0, 8), (0, 9), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (1, 8), (1, 9), (2, 3), (2, 4), (2, 5), (2, 6), (2, 7), (2, 8), (2, 9), (3, 4), (3, 5), (3, 6), (3, 7), (3, 8), (3, 9), (4, 5), (4, 6), (4, 7), (4, 8), (4, 9), (5, 6), (5, 7), (5, 8), (5, 9), (6, 7), (6, 8), (6, 9), (7, 8), (7, 9), (8, 9)]




