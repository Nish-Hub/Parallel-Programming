/*
The key points to be taken from here is how the result is being returned directly from function calls like compute() and join()
which is where recursive tasks differ from recursive action. 
*/

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ArraySumRecursiveTask extends RecursiveTask<Integer> {
static int SEQUENTIAL_THRESHOLD=4;	
int lo=0,hi=0;
int[] arr=null;
public ArraySumRecursiveTask(int[] arr,int lo,int hi) {
	// TODO Auto-generated constructor stub
	this.arr=arr;
	this.lo=lo;
	this.hi=hi;
}
public ArraySumRecursiveTask(int[] arr)
{
	this(arr,0,arr.length);
}
public int computeSumParallel(int[] arr)
{
	
	return ForkJoinPool.commonPool().invoke(new ArraySumRecursiveTask(arr));
}
@Override
protected Integer compute() {
	// TODO Auto-generated method stub
	if(lo>hi)
		return 0;
	if(lo==hi)
		return arr[lo];
	if(hi-lo <SEQUENTIAL_THRESHOLD)
	{
		int sum=0;
		for(int i=lo;i<hi;i++)
			sum+=arr[i];
		return sum;
	}
	ArraySumRecursiveTask astLeft=new ArraySumRecursiveTask(arr,0,arr.length/2);
	ArraySumRecursiveTask astRight=new ArraySumRecursiveTask(arr,arr.length/2,arr.length);
	astLeft.fork();
	
	return astRight.compute()+astLeft.join();
}
public static void main(String[] args) {
	int[] arr= {1,2,3,4};
	ArraySumRecursiveTask ast=new ArraySumRecursiveTask(arr);
	System.out.println(ast.computeSumParallel(arr));
}
}
