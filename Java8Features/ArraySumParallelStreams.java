import java.util.stream.IntStream;

public class ArraySumParallelStreams {
	public static void main(String[] args) {
		int[] arr1 = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		int[] arr2 = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };


		IntStream.range(0, arr.length).parallel().forEach(i -> System.out.println(arr1[i] + arr2[i]));

	}
}
