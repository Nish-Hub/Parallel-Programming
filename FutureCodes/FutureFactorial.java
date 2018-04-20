package edu.coursera.parallel;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureDemo
{
  private static final ExecutorService threadpool = Executors.newFixedThreadPool(3);
  public static void main(String[] args) throws InterruptedException, ExecutionException {
    FactorialCalculator task=new FactorialCalculator(10);
    System.out.println("Submitting task ... ");
    Future<Long> future=threadpool.submit(task);
    System.out.println("Task is submitted ... ");
    System.out.println("Task in progress");
    System.out.println( "Printing value of future object before isDone() check "+future.get()); // waits 
    // for the future object task to finish 
    while(!future.isDone())
    {
      System.out.print("..");
      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    System.out.println("Task is completed .");
    try {
      long factorial=(long) future.get();
      System.out.println("Factorial of 10 is "+factorial);
    } catch (InterruptedException | ExecutionException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    threadpool.shutdown();
  }
}
class FactorialCalculator implements Callable<Long>{

  private int number=0;
  
  public FactorialCalculator(int number) {
    // TODO Auto-generated constructor stub
    this.number=number;
  }
  @Override
  public Long call() throws Exception {
    // TODO Auto-generated method stub
    long output=0;
    try{
      output=factorial(number);
    }catch(InterruptedException ex){
      ex.printStackTrace();
    }
    return output;
  }
  private long factorial(int number)throws InterruptedException 
  {
    if(number<0)
      throw new IllegalArgumentException("Number must be positive");
    
    long result=1; 
    while(number>0)
    {
      result=result*number;
      number--;
    }
    Thread.sleep(10);
    return result;
  }
}
