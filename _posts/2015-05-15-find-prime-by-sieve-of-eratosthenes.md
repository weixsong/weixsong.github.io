---
layout: post
title: "find prime by Sieve of Eratosthenes"
description: ""
category: 
tags: [algorithm]
---
{% include JB/setup %}

## Problem define

We want to find all the primes between 2~N, then how to find all the primes efficiently?

### Sieve of Eratosthenes

Sieve of Eratosthenes method, is very efficient, the algorithm is:

1. Create a list of consecutive integers from 2 through n: (2, 3, 4, ..., n).
2. Initially, let p equal 2, the first prime number.
3. Starting from p, enumerate its multiples by counting to n in increments of p, and mark them in the list (these will be 2p, 3p, 4p, ... ; the p itself should not be marked).
4. Find the first number greater than p in the list that is not marked. If there was no such number, stop. Otherwise, let p now equal this new number (which is the next prime), and repeat from step 3.

And, there are some small tricks that could refine the algorithm.
As a refinement, it is sufficient to mark the numbers in step 3 starting from p<sup>2</sup>, as all the smaller multiples of p will have already been marked at that point. This means that the algorithm is allowed to terminate in step 4 when p<sup>2</sup> is greater than n.

## Java code

The jave implement of Sieve of Eratosthenes is as:
{% highlight java %}

	public static List<Integer> getPrimebyErichsenSieve(int range) {
		if (range < 2) {
			throw new IllegalArgumentException("range should >= 2");
		}

		boolean[] isPrime = new boolean[range + 1];

		// init
		for (int i = 0; i <= range; i++) {
			isPrime[i] = true;
		}

		isPrime[0] = false;
		isPrime[1] = false;

		// sieve
		for (int i = 2; i * i <= range; i++) {
			if (isPrime[i]) {
				for (int j = i * i; j <= range; j += i) {
					isPrime[j] = false;
				}
			}
		}

		// collect results
		List<Integer> primeList = new ArrayList<Integer>();
		for (int i = 0; i <= range; i++) {
			if (isPrime[i]) {
				primeList.add(i);
			}
		}

		return primeList;
	}

{% endhighlight %}

## Complexity

Time complexity for Sieve of Eratosthenes is **O(nloglogn)**, and Space complexity is **O(n)**. **O(nloglogn)** is nearly a linear algorithm, and is much faster than the other function I wrote in the java code.

In the above java code, I also implemented another brute-force algorithm <code>getPrimebySimpleMethod()</code> to find primes, by running the algorithm to generate all primes between 0~1000000, 

		// time for Erichsen Sieve, range: 1000000
		long startMili=System.currentTimeMillis();
		List<Integer> list3 = Primer.getPrimebyErichsenSieve(1000000);
		long endMili=System.currentTimeMillis();
		System.out.println("time："+(endMili - startMili) + "ms");
		System.out.flush();

		// time for simple method, range: 1000000
		startMili=System.currentTimeMillis();
		List<Integer> list4 = Primer.getPrimebySimpleMethod(1000000);
		endMili=System.currentTimeMillis();
		System.out.println("time："+(endMili - startMili) + "ms");
		System.out.flush();

we could see the running time differnece is:


    time：28ms
    time：319ms


Sieve of Eratosthenes is much better than <code>getPrimebySimpleMethod()</code> brute-force method.

For the above brute-force algorithm, the time complexity is: **O(n<sup>3/2</sup>)**.

### Complexity Analysis

For the **O(nloglogn)** analysis for Sieve of Eratosthenes, please refer to [this](http://en.wikipedia.org/wiki/Sieve_of_Eratosthenes) and [this](http://en.wikipedia.org/wiki/Divergence_of_the_sum_of_the_reciprocals_of_the_primes).

I found it's a little hard to write equation in YAML Markdown, so sorry for no equation in this post.

Because Sieve of Eratosthenes need  **O(n)** Space complexity, so if you want to get a very very large range primes, you need to segment the range and process each segment separately, if not, you will run into out of memory.

## Find the largest prime that less than given x

Sometimes maybe we need to find the largest prime less than a given number, such as **x**, then how to do this? Actually I have made some search and did not found perfect solution.

### by using Sieve of Eratosthenes

By this method, we still find the primes from 2 to **x**, and until we found the largest prime that less than **x**. But remember that the space requirement is **O(n)**, be careful for large **x**.

{% highlight java %}

    public static int findLPTGbyES(int n) {
		if (n < 2) {
			throw new IllegalArgumentException("range should >= 2");
		}

		boolean[] isPrime = new boolean[n + 1];

		// init
		for (int i = 0; i <= n; i++) {
			isPrime[i] = true;
		}

		isPrime[0] = false;
		isPrime[1] = false;

		int largestPrime = 2;
		// sieve
		for (int i = 2; i <= n; i++) {
			if (isPrime[i]) {
				if (i >= n) {
					break;
				}

				largestPrime = i;
				for (int j = 2 * i; j <= n; j += i) {
					isPrime[j] = false;
				}
			}
		}

		return largestPrime;
	}

{% endhighlight %}

Time complexity for this is **O(nloglogn)**.

### find largest prime from back-end

Begin from x - 1, check if x - 1 is a prime, if not, check x - 2, until find a prime.
Time complexity for this method is **O(n<sup>3/2</sup>)**.

{% highlight java %}

	public static boolean isPrime(int num) {
		if (num < 2) {
			throw new IllegalArgumentException("num should >=2");
		}

		if (num == 2) {
			return true;
		}

		for (int i = 2; i * i <= num; i++) {
			if (num % i == 0) {
				return false;
			}
		}

		return true;
	}

{% endhighlight %}

{% highlight java %}

	public static int findLargestPrimeLessThanGivenNum(int n) {
		if (n < 2) {
			throw new IllegalArgumentException("range should >= 2");
		}
		
		for (int i = n - 1; i >= 2; i--) {
			if (isPrime(i)) {
				return i;
			}
		}
		
		return 2;
	}

{% endhighlight %}

### Compare

Theoretically **O(nloglogn)** is much better than **O(n<sup>3/2</sup>)**.
**but**  for small x, such as <code>Integer.MAX_VALUE</code>, in my experiment It seems find largest prime from back-end is faster, maybe this is because the x is relatively small for prime. Another point you need to pay attention is for using Sieve of Eratosthenes, the space complexity is **O(n)**, be careful about your memory.

		// find largest prime by brute-force from back end
		startMili=System.currentTimeMillis();
		int prime = Primer.findLargestPrimeLessThanGivenNum(1000000);
		System.out.println("largest prime: " + prime);
		endMili=System.currentTimeMillis();
		System.out.println("time："+(endMili - startMili) + "ms");
		System.out.flush();

		// find largest prime by Erichsen Sieve
		startMili=System.currentTimeMillis();
		int prime2 = Primer.findLPTGbyES(1000000);
		System.out.println("largest prime: " + prime2);
		endMili=System.currentTimeMillis();
		System.out.println("time："+(endMili - startMili) + "ms");
		System.out.flush();

The test results:

    largest prime: 999983
    time：0ms
    largest prime: 999983
    time：15ms

## Source Code

Source code <code>Primer.java</code> is [here]({{ site.url }}/assets/code/java/Primer.java).
