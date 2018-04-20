package edu.coursera.parallel;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class MemoizationFibonacci extends RecursiveTask<Integer>{
  final static int[] arr=new int[100];
  int num;
  final static Map<Integer,Integer> m=new HashMap<Integer,Integer>();
  static ForkJoinPool pool=new ForkJoinPool(10);
  MemoizationFibonacci(int num)
  {
    this.num=num;
  }
  public static void main(String[] args) {
    
    //System.out.println(new MemoizationFibonacci(10).getFibonnacciSequential(10));
    MemoizationFibonacci myRecursiveTask = new MemoizationFibonacci(10);

    int mergedResult = pool.invoke(myRecursiveTask);

    System.out.println("mergedResult = " + mergedResult);    
  }
int getFibonnacciSequential(int num)
{
  if(num == 0 || num ==1)
    return num;
  
  if(arr[num-1] != 0)
    return arr[num-1];
  int sum=getFibonnacciSequential(num-1) + getFibonnacciSequential(num-2);
  arr[num-1]=sum;
  return sum;
}
int getFibonnacciMeimozation(int num)
{
  if(num == 0 || num ==1)
    return num;
  
  if(arr[num-1] != 0)
    return arr[num-1];
  int sum=getFibonnacciSequential(num-1) + getFibonnacciSequential(num-2);
  arr[num-1]=sum;
  return sum;
}
@Override
protected Integer compute() {
  // TODO Auto-generated method stub
  if(num == 0 || num ==1)
    return num;
  //System.out.println("Value for num = "+num+" found in the map is "+m.get(num));
  if(null != m.get(num))
  {
    return m.get(num);
  }
  /*if(arr[num-1] !=0)
  {
    System.out.println("Value of num "+num+" as found in array is "+arr[num-1]);
    return arr[num-1];
  }*/
  //System.out.println("Value of num "+num+" not found in array is "+arr[num-1]);
  MemoizationFibonacci hi=new MemoizationFibonacci(num-1);
  MemoizationFibonacci lo=new MemoizationFibonacci(num-2);
  //ForkJoinPool pool=new ForkJoinPool(10);
  //Future<Integer> fHi=pool.submit(hi);
  //Future<Integer> fLo=pool.submit(lo);
  hi.fork();  // Makes a future object in hi this is why we were able to use hi.get() below.
  int sum=0;
//  try {
    //sum = fHi.get()+fLo.get();
    sum = lo.compute()+hi.join();
  /*} catch (InterruptedException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
  } catch (ExecutionException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
  }*/
  m.put(num,sum);
/*
  for(Map.Entry<Integer,Integer> entry:m.entrySet())
  {
    System.out.println(entry.getKey()+" has value "+entry.getValue());
  }*/
  //arr[num-1]=sum;
  
  return sum;
}
}

　
　
　
　
　
　
　
　
　
　
　
　
　
　
　
　
