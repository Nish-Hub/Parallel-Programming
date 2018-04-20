
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class FutureArraySumForkJoin extends RecursiveTask<Integer> {
  private static final ExecutorService threadpool = Executors.newFixedThreadPool(3);
int lo,hi;
int[] arr;
//int sum;
public static void main(String[] args) {
  int[] arr={1,1,1,1,1,1};
  FutureArraySumForkJoin f=new FutureArraySumForkJoin(arr);
  ForkJoinPool fp=new ForkJoinPool(4);
  int res=fp.invoke(f);
  System.out.println(res);
}
FutureArraySumForkJoin(int lo,int hi,int[] arr)
{
  this.lo=lo;
  this.hi=hi;
  this.arr=arr;
}
public FutureArraySumForkJoin(int[] arr) {
  // TODO Auto-generated constructor stub
  this(0,arr.length,arr);
}
  @Override
  protected Integer compute() {
    // TODO Auto-generated method stub
    int sum=0;
    if(lo > hi)
      return 0;
    if(lo == hi)
      return arr[lo];
    if(hi-lo < 10)
    {
      for(int i=lo;i<hi;i++)
        sum+=arr[i];
      return sum;
    }
    else
    {
      FutureArraySumForkJoin lTask=new FutureArraySumForkJoin(lo,(lo+hi)/2,arr);
      FutureArraySumForkJoin rTask=new FutureArraySumForkJoin((lo+hi)/2,hi,arr);
      /*lTask.fork();
      rTask.compute();
      lTask.join();
      sum=lTask.sum+rTask.sum;*/
      Future<Integer> lFTask=lTask;
      Future<Integer> rFTask=lTask;
      int ssum=0;
      try {
        ssum= lFTask.get()+rFTask.get();
      } catch (InterruptedException | ExecutionException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      return ssum;
      
    }
  
  }

}
