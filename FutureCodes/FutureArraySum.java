package edu.coursera.parallel;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureArraySum
{
  private static final ExecutorService threadpool = Executors.newFixedThreadPool(3);
  public static void main(String[] args) throws InterruptedException, ExecutionException {
    int[] arr={1,1,1,1,1,1};
     ArraySum l=new ArraySum(0,arr.length/2,arr);
     ArraySum r=new ArraySum(arr.length/2,arr.length,arr);
     Future<Integer> lTask=threadpool.submit(l);
     Future<Integer> rTask=threadpool.submit(r);
     
     int sum=lTask.get()+rTask.get();
     System.out.println("sum is "+sum);
     
  }
}
class ArraySum implements Callable<Integer>{

  int lo;
  int high;
  int[] arr;
  ArraySum(int lo,int high,int[] arr)
  {
    this.lo=lo;
    this.high=high;
    this.arr=arr;
  }
  ArraySum(int[] arr)
  {
    this(0,arr.length,arr);
  }
  @Override
  public Integer call() throws Exception {
    // TODO Auto-generated method stub
    return calcSum(arr);
  }
  public int calcSum(int[] arr)
  {
    if(lo == high)
      return arr[lo];
    if(lo > high)
      return 0;
    int sum=0;
    for(int i=lo;i<high;i++)
      sum+=arr[i];
    return sum;
  }
  
}
