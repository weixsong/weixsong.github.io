``` java
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
		for (int i = 2; i <= range; i++) {
			if (isPrime[i]) {
				for (int j = 2 * i; j <= range; j += i) {
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
```
