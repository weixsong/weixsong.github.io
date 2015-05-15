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
package com.wesong.algs;

import java.util.ArrayList;
import java.util.List;

public class Primer {

	/**
	 * verify if a given number is prime
	 * @param num: input number
	 * @return
	 */
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

	/**
	 * Get a list of prime by Erichsen Sieve
	 * Time complexity: O(nloglogn), Space complexity: O(n)
	 * range: [2, range]
	 * @param range
	 * @return
	 */
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
	
	/**
	 * get a list of Prime by simple method
	 * range: [2, range]
	 * @param range
	 * @return
	 */
	public static List<Integer> getPrimebySimpleMethod(int range) {
		if (range < 2) {
			throw new IllegalArgumentException("range should >= 2");
		}

		List<Integer> primeList = new ArrayList<Integer>();
		for (int i = 2; i <= range; i++) {
			if (isPrime(i)) {
				primeList.add(i);
			}
		}

		return primeList;
	}

	/**
	 * find the largest prime that less than given n
	 * @param n
	 * @return
	 */
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
	
	/**
	 * find largest prime that less than given n, by Erichsen Sieve
	 * this method is really time consuming. And if for large n, will lead to
	 * out of memory.
	 * @param n
	 * @return
	 */
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
		for (int i = 2; i * i <= n; i++) {
			if (isPrime[i]) {
				if (i >= n) {
					break;
				}

				largestPrime = i;
				for (int j = i * i; j <= n; j += i) {
					isPrime[j] = false;
				}
			}
		}

		return largestPrime;
	}

	public static void main(String[] args) {
		List<Integer> list = Primer.getPrimebyErichsenSieve(100);
		System.out.println("number: " + list.size());
		System.out.println(list);

		List<Integer> list2 = Primer.getPrimebySimpleMethod(100);
		System.out.println("number: " + list2.size());
		System.out.println(list2);

		long startMili=System.currentTimeMillis();
		List<Integer> list3 = Primer.getPrimebyErichsenSieve(1000000);
		long endMili=System.currentTimeMillis();
		System.out.println("time："+(endMili - startMili) + "ms");

		startMili=System.currentTimeMillis();
		List<Integer> list4 = Primer.getPrimebySimpleMethod(1000000);
		endMili=System.currentTimeMillis();
		System.out.println("time："+(endMili - startMili) + "ms");

		startMili=System.currentTimeMillis();
		int prime = Primer.findLargestPrimeLessThanGivenNum(Integer.MAX_VALUE);
		System.out.println("largest prime: " + prime);
		endMili=System.currentTimeMillis();
		System.out.println("time："+(endMili - startMili) + "ms");

		// following will lead to out of memory
//		startMili=System.currentTimeMillis();
//		int prime2 = Primer.findLPTGbyES(Integer.MAX_VALUE - 1);
//		System.out.println("largest prime: " + prime2);
//		endMili=System.currentTimeMillis();
//		System.out.println("time："+(endMili - startMili) + "ms");
	}
}

{% endhighlight %}

## Complexity

Time complexity for Sieve of Eratosthenes is **O(nloglogn)**, and Space complexity is **O(n)**. **O(nloglogn)** is nearly a linear algorithm, and is much faster than the other function I wrote in the java code.

In the above java code, I also implemented another brute-force algorithm <code>getPrimebySimpleMethod()</code> to find primes, by running the algorithm to generate all primes between 0~1000000, we could see the running time differnece is:


    time：28ms
    time：319ms


Sieve of Eratosthenes is much better than <code>getPrimebySimpleMethod()</code> brute-force method.

For the above brute-force algorithm, the time complexity is: **O(n<sup>3/2</sup>)**.

\\(x=\frac{-b\pm\sqrt{b^2-4ac}}{2a}\\)
