/*
Async implementation of recursive array summation code
*/

public class ArraySum
{
public static void findSum(int low,int high,int[] arr)
{
if(low == high)
return arr[low];
if(low>high)
return 0;
int sum=0,Lsum=0,Rsum=0;
async((){   
Lsum=findSum(low,((low+high)/2),arr);
}
Rsum=findSum(((low+high)/2),high,arr);
sum=Lsum+Rsum;
}
}
