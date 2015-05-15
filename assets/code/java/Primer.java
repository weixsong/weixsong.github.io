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

	public static void main(String[] args) {
		// get prime by Erichsen Sieve
		List<Integer> list = Primer.getPrimebyErichsenSieve(100);
		System.out.println("number: " + list.size());
		System.out.println(list);
		System.out.flush();

		// get prime by simple method
		List<Integer> list2 = Primer.getPrimebySimpleMethod(100);
		System.out.println("number: " + list2.size());
		System.out.println(list2);
		System.out.flush();

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
	}
}
