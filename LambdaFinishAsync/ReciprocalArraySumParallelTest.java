/*
This code shows the comparision and time difference of two same methods but running one asynchronously and other sequentially. 
*/

import static edu.rice.pcdp.PCDP.finish;
import static edu.rice.pcdp.PCDP.async;

class ReciprocalArraySumParallel {

	int l, r;

	int computeSumSequential(int[] arr) {
		int mid = arr.length / 2;
		l = 0;
		r = 0;
		int sum = 0;
		for (int i = 0; i < mid; i++)
			l += 1 / arr[i];
		for (int i = 0; i < mid; i++)
			r += 1 / arr[i];
		return l + r;
	}

	int computeSumParallel(int[] arr) {
		int mid = arr.length / 2;
		l = 0;
		r = 0;
		int sum = 0;
		// earlier coded without using finish but this lead to a data race
		// condition
		// as return l+r executed before l finished.
		/*
		 * async(() -> { for (int i = 0; i < mid; i++) l += 1 / arr[i]; });
		 */
		// finish makes it sure that l completes then only return statement
		// executes
		finish(() -> {
			async(() -> {
				for (int i = 0; i < mid; i++)
					l += 1 / arr[i];
			});
		});

		for (int i = 0; i < mid; i++)
			r += 1 / arr[i];
		return l + r;
	}
}

public class ReciprocalArraySumParallelTest {
	public static void main(String[] args) {
		ReciprocalArraySumParallel ras = new ReciprocalArraySumParallel();
		int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6,
				7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5,
				6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4,
				5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3,
				4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2,
				3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1,
				2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9,
				1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8,
				9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7,
				8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6,
				7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

		int sum = 0;

		long t1 = System.currentTimeMillis();
		sum = ras.computeSumSequential(arr);
		long t2 = System.currentTimeMillis();
		System.out.println("Sequential Time taken for reciprocal sum of " + sum + "  is  :  " + ((t2 - t1)));

		long t3 = System.currentTimeMillis();
		sum = ras.computeSumParallel(arr);
		long t4 = System.currentTimeMillis();
		System.out.println("Parallel Time taken for reciprocal sum of " + sum + "  is  : " + ((t4 - t3)));

		/*
		 * Output : Sequential Time taken for reciprocal sum of 42 is : 15080
		 * Parallel Time taken for reciprocal sum of 42 is : 3025
		 * 
		 */
	}
}
