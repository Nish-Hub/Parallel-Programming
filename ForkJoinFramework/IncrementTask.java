import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/*
This class is like a single forkjoin task as it extends RecursiveAction class. The main task performed by this class is incrementing the values
of the array hence we write the main implementation in the compute() . The objects that we make inside compute() is like dividing the task into smaller tasks
async using invokeAll() which forks or invokes all the forkjoin tasks supplied to it .

Key Point : Here the objects that we create are objects of a class ForkJoin type due to which these objects behave like a task,
the implementation here is divide and conquer but inside the Object's overridden compute() . In this code , the task is broken down
to array size of 4 or less. The implementation is done via sequential method as defined in the code but the task breaking is
done by fork join framework. 
 */

public class IncrementTask extends RecursiveAction {
	final long[] array;
	final int lo, hi;
	static final int THRESHOLD = 4;

	public static void main(String[] args) {
		long[] array = { 1, 1, 1, 1, 1, 1 };
		IncrementTask tt = new IncrementTask(array, 0, array.length);
		ForkJoinPool fp = new ForkJoinPool();
		fp.invoke(tt);   // This is how this task is forked or invoked in the pool
		for (int i = 0; i < array.length; i++)
			System.out.println(array[i]);
	}

	IncrementTask(long[] array, int lo, int hi) {
		this.array = array;
		this.lo = lo;
		this.hi = hi;
	}
// The gist of the task.
	protected void compute() { // Overridden method invoked by Recursiveaction's
								// execute() .
		if (hi - lo < THRESHOLD) {
			for (int i = lo; i < hi; ++i)
				array[i]++;
		} else {
			int mid = (lo + hi) >>> 1;
			invokeAll(new IncrementTask(array, lo, mid), new IncrementTask(array, mid, hi)); //The division of tasks
		}
	}
}
