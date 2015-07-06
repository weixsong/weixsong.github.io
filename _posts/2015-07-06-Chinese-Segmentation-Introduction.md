---
layout: post
title: "Chinese Segmentation Introduction"
description: ""
category: algorithm
tags: [NLP]
---

# 1. Chinese Segmentation Introduction
最近两天系统的研究了一下中文分词算法，在这里做个简单的总结。

中文分词可以分为（1）基于词典的分词  和（2）非基于词典的分词。
基于词典的分词包括：
 * MMSEG
 * Forward/Backward matching
 * 最小切分

非基于词典的分词主要是通过统计学计算概率的方法进行中文分词，例如CRF, is probability based. And some other machine learning based segmentation algorithm.

Advantage and disadvantage of different segment algorithm:
Dict-based:
 * esay to implement
 * could not handle un-board word
 * precision not ver good.

Non-dict based:
 * high segment speed
 * able to handle un-board word.
 * high precision
 * complex to implement

(不好意思，写着写着就写成英文了，下面可能会有中英文混合的　^_^)

# 2. Maximun Matching Background
In this chapter I will introduce some Maximun Matching backgroud that would be helpful in next chapter.
Different studies differ in their ambiguity resolution algorithms. A very simple one which has been demonstrated to be very effective is the maximum matching algorithm (Chen & Liu, 1992). Maximum matching can take many forms.


## 2.1 Simple maximum matching
The basic form is to resolve the ambiguity of a single word (Yi-Ru Li, personal communication, January 14, 1995). For example, suppose C1, C2,... Cn represent characters in a string. We are at the beginning of the string and want to know where the words are. We first search the lexicon to see if _C1_ is a one-character word, then search _C1C2_ to see if it is a two-character word, and so on, until the combination is longer the longest words in the lexicon. The most plausible word will be the longest match. We take this word, then continue this process until the last word of the string is identified.

例如：　“一个劲的说话”
从第一个字开始分词，我们可以得到下面的多个结果　（都在字典中出现）：
 * 一个
 * 一个劲
 * 一个劲的
 从第一个字开始分词，我们可以有多种分词的结果，那么应该选择哪个分词结果作为最终的分词结果呢？　这个问题就是在中文分词中涉及到的歧义问题，解决歧义问题有多种方法，比较简单得一种就是在上面得分词结果中选择最长得一个分词结果，然后继续剩下得字符串得分词操作。
 这里，我们如果选择“一个劲的”最为一个分词，然后我们就需要在剩下得字符串“说话”重复进行分词操作，直到所有得字符串都处理完毕。
 当然，还有其他的歧义处理办法。例如我们可以把上面得到得所有得结果都作为分词结果，然后继续从下一个字符进行分词，即“个劲的说话”。

## 2.2 Complex maximum matching
Another variant of maximum matching done by Chen and Liu (1992) is more complex than the basic form. Their maximum matching rule says that the most plausible segmentation is the three-word chunk with maximum length. Again, we are at the beginning of a string and want to know where the words are. If there are ambiguous segmentations (e.g., _C1_ is a word, but _C1C2_ is also a word, and so on), then we look ahead two more words to find all possible three-word chunks beginning with _C1_ or _C1C2_. For example, if these are possible three-word chunks:

```
1. C1／C2／C3C4
2. C1C2／C3C4／C5
3. C1C2／C3C4／C5C6
```

```
1. 眼看／就要／来了
2. 眼看／就要／来
3. 眼看／就／要
4. 眼／看／就要
5. 眼／看／就
```
The chunk with maximum length is the third one. The first word, _*C1C2*_ of the third chunk, will be considered as the correct one. We take this word, proceed to repeat this process from character C3, until the last word of the string is identified. Chen and Liu (1992) reported that this rule achieved 99.69% accuracy and 93.21% of the ambiguities were resolved by this rule.

这种分词方法是基于一个非常重要的假设的：　**most plausible segmentation is the three-word chunk with maximum length**,  可以理解为，对于中文分词来说，最有可能得分词结果就是使得三个连接的词得长度最长。

# 3. MMSEG
The MMSEG system implemented both simple and complex forms of the maximum matching algorithm discussed earlier. Furthermore, to resolve ambiguities not resolved by the complex maximum matching algorithm, three more ambiguity resolution rules have been implemented.

One of which was proposed by Chen and Liu (1992), and the rest two were new. These rules will be discussed later. The system did not have special rules to handle proper names and specific morphological structures such as reduplication and A-not-A construction.

It has to be noted that MMSEG was not designed to be a "professional level" system whose goal is 100% correct identification. Rather, MMSEG should be viewed as a general platform on which new ambiguity resolution algorithms can be tested. Nevertheless, we will see that even the current version of MMSEG achieved very high percentage of accuracy, which was as high as those published on academic journals.

MMSEG分词算法，主要包含了Maximum Matching的（１）简单匹配和（２）复杂匹配两种Maximum Matching 算法，同时包含了４种歧义消除算法。
MMSEG算法是基于词典得，主要采用Forward Matching算法，并且采用４中歧义消除算法的中文分词算法。

## 3.1 Maximum Matching Algorithm
### 3.1.1 Simple Maximum Matching
For character Cn in a string of characters, match the sub-string beginning with Cn with the lexicon and find all possible matches.

例如：　“一个劲的说话”
从第一个字开始分词，我们可以得到下面的多个结果　（都在字典中出现）：
 * 一个
 * 一个劲
 * 一个劲的

这种情况下，就有了分词歧义了，就需要下面提到的歧义消除算法。

### 3.1.2 Complex Maximum Matching
For character Cn in a string of characters, find all possible three-word chunks beginning with Cn, no matter whether there is any ambiguity with the first word. The three-word chunks were formed only when there was an ambiguity of the first word.
```
1. C1／C2／C3C4
2. C1C2／C3C4／C5
3. C1C2／C3C4／C5C6
```

## 3.2 Eliminate Ambiguity
上面看到，分词必然会出现歧义，那么就需要进行歧义处理了。
Four ambiguity resolution rules were used. The maximum matching rules applied to ambiguous segmentations from both simple and complex matching algorithms. The rest three rules did not (and could not) apply to ambiguous segmentations from the simple matching algorithm.

这里提到了４中歧义处理方法，其中第一种即适用于Simple Maximum Matching，也适用于Complex Maximum Matching，　剩下得其它三种歧义处理方式只适用于Complex Maximum Matching。

### 3.2.1 Rule 1: Maximum matching (Chen & Liu 1992)
 * (a) Simple maximum matching: Pick the word with the maximum length. 
 * (b) Complex maximum matching: Pick the first word from the chunk with maximum length. If there are more than one chunks with maximum length, apply the next rule .  

#### 3.2.1.1 Simple maximum matching
假设我们对句子　S=C1C2C3C4C5C6C7 进行分词，从第一个字符开发，有以下分词结果：
 * c1c2
 * c1c2c3
 * c1c2c3c4

那么这个时候消除歧义的方法就是选择最长得分词结果作为分词，这里我们选择c1c2c3c4,　然后继续对剩下得字符串c5c6c7进行分词。

具体得例子：　乒乓球拍卖完了
可以分为：
 * 乒乓
 * 乒乓球
 * 乒乓球拍
 
这个时候我们就选择　“乒乓球拍”最为一个分词结果，然后继续对“卖完了”进行分词。

#### 3.2.1.2 Complex maximum matching

 假设得到下面得分词结果：
```
1. C1／C2／C3C4
2. C1C2／C3C4／C5
3. C1C2／C3C4／C5C6
```

那么这里我们选择长度最长的　three-word chunk，　即第三个，并将C1C2作为分词结果，然后继续对C3C4C5C6进行分词。

但是如果我们得到两个three-word chunk长度一样怎么办？，如
```
1. C1C2／C3C4／C5C6
2. C1／C2C3C4／C5C6
```

**这个时候就需要使用下面的歧义消除方法**
### 3.2.2 Largest average word length (Chen & Liu, 1992)
At the end of each string, it is very likely to have chunks with only one or two words. For example, the following chunks have the same length and the same variance of word lengths.
```
1. C1/C2/C3
2. C1C2C3
```
Rule 2 picks the first word from the chunk with largest average word length. In the above example, it picks *C1C2C3* from the second chunk. The assumption of this rule is that it is more likely to encounter multi-character words than one-character words.

This rule is useful only for condition in which one or more word position in the chunks are empty. When the chunks are real three-word chunks, this rule is not useful. Because three-word chunks with the same total length will certainly have the same average length. Therefore we need another solution.

### 3.2.3 Smallest variance of word lengths (Chen & Liu, 1992)
There are quite a few ambiguous conditions in which the Rule 1 and Rule 2 cannot resolve. For example, these two chunks have the same length:
```
1. C1C2/C3C4/C5C6
2. C1C2C3/C4/C5C6
```
Rule 3 picks the first of the chunk with smallest variance of word lengths. In the above example, it picks _C1C2_ from the first chunk. This rule is exactly them as the one proposed by Chen and Liu (1992). (However, they applied this rule immediately after Rule 1.) The assumption of this rule is that word lengths are usually evenly distributed. If there are more than one chunks with the same value of smallest variance of word lengths, apply the next rule.

### 3.2.4 Largest sum of degree of morphemic freedom of one-character words

This example shows two chunks with the same length, variance, and average word length:
```
1. C1/C2/C3C4
2. C1/C2C3/C4
```
Both chunks have two one-character words and one two-character word. Which one is more likely to be the correct one? Here we will focus on one-character words. Chinese characters differ in their degree of morphemic freedom. Some characters are rarely used as free morphemes, but others have larger degree of freedom. The frequency of occurrence of a character can serve as an index of its degree of morphemic freedom. A high frequency character is more likely to be a one-character word, and vice versa.

The formula used to calculate the sum of degree of morphemic freedom is to sum log(frequency) of all one-character word(s) in a chunk. The rationale for logarithm transformation is that the same amount of frequency difference does not have a consistent effect across all frequency ranges.

Rule 4 than picks the first word of the chunk with largest sum of log(frequency). Since it is very unlikely that two characters will have exactly the same frequency value, there should be no ambiguity after this rule has been applied.

这里得word frequency是通过词典中得统计得到的。


# 4. Non-dict based Chinese Segmentation Algorithm
非基于词典的中文分词算法比较有代表性的是CRF，通过统计学进行中文分词，即使得分词的概率最大。CRF暂时还没有学习过，就不介绍了。简单的介绍通过贝叶斯公式计算最佳分词的算法。

首先，给出一个例子：“学历史知识”
可以分为：
 * 1. "学／历史／知识"
 * 2. "学历／史／知识"

由我们的主管知识可以知道第一种分词结果更靠谱，因为第二种分词组成一个句子的用法不常见，也就是说第二种分词结果的概率比较低。
那么我们就可以通过计算分词的概率来处理歧义问题，概率最大的分词结果即为最好得分词方式。

假设句子表示为：　**S=A1A2A3....Ak**, Ak表示一个可能得分词
我们的目标是给定一个句子，使得分词的概率最大，分词的概率可以表示为：
$$p(A|S)$$ 
上面这个公式本身是非常难计算的，但是我们可以把它通过贝叶斯公式进行计算：
$$p(A|S)=\dfrac{p(S|A)*P(A)}{p(S)}$$ 
其中，$$p(S)$$ 可以理解为这个句子生成的概率，因为句子已经存在了，并且对于不同的分词结果这个概率都是一样了，所以可以把p(s)当做一个常量，不去管它，这样，公式可以写成：
$$p(A|S)=\dfrac{p(S|A)*P(A)}{p(S)}　∝ p(S|A)*P(A)$$ 
p(S|A)表示的是给定分词结果生成句子S的概率，根据分词结果Ａ肯定能够得到S，所以这里p(S|A)可以简单的标记为１．
然后就剩下计算P(A)的概率了。

$$P(A)=p(A1)*p(A2|A1)*p(A3|A2,A1)*p(A4|A3,A2,A1)*...*p(An|An-1,An-2, ..., A1)$$

这里可以理解为：　每个词的出现，都会收到之前已经出现过的词得影响。这里可以成为Language Model.

但是如果计算上面公式的每一个概率，是不可能的。太复杂。所以我们要介绍两个常用得language Model.

(1) Unigram Model
把每一个词都看做相互独立的，互不影响，所以上面得P(A)的概率公式可以重新表示为：
$$P(A)=p(A1)*p(A2)*p(A3)*p(A4)*...*p(An)$$
通过这个公式就可以非常容易的计算P(A)的概率，其中p(Ai)表示词Ai出现的概率，
$$p(Ai)=\dfrac{count(Ai)}{\sum\limits_{k=1}^V count(Ak) }$$

(2) Bigram Model
在Unigram Model中，把每一个单词都看做相对独立的，他们之间没有任何关系。但是在实际应用中，往往很多情况下一个单词会对下一个单词的出现得概率有比较大的影响，例如“吃／饺子”　和“吃／汽车”　相比，在“吃”之后“饺子”出现的概率应该远远高于“汽车”出现的概率。
所以，在Bigram Model中，考虑了每个单词在之前一个单词的影响下出现的概率。所以上面得P(A)的概率公式可以重新表示为：
$$P(A)=p(A1)*p(A2|A1)*p(A3|A2)*p(A4|A3)*...*p(An|An-1)$$
其中，p(Ai|Ai-1)可以表示为：
$$p(Ai|Ai-1)=\dfrac{p(Ai-1, Ai)}{p(Ai-1)}=\dfrac{count(Ai-1,Ak) }{count(Ai-1) }$$
count(Ai-1,Ak)表示单词Ai-1和Ai同时出现的次数。


