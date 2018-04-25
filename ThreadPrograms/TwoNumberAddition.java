
public class TwoNumberAddition{
	int a=0,b=0,c=0,d=0;
	int sumLeft=0,sumRight=0;
	public TwoNumberAddition(int a,int b,int c,int d) {
		// TODO Auto-generated constructor stub
		this.a=a;
		this.b=b;
		this.c=c;
		this.d=d;
	}
	int sum(int e,int f)
	{
		return e+f;
	}
	protected String compute() {

		TwoNumberAddition twLeft=new TwoNumberAddition(a,b,0,0);
		Thread thLeft=new Thread(() ->sumLeft=twLeft.sum(a, b));
		thLeft.start();
		
		TwoNumberAddition twRight=new TwoNumberAddition(0,0,c,d);
		Thread thRight=new Thread(() ->sumRight=twRight.sum(c, d));
		thRight.start();
		
		//To avoid data race
		try {
			thRight.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return sumLeft+" : "+sumRight;
	}
public static void main(String[] args) {
	TwoNumberAddition tw=new TwoNumberAddition(1,2,3,4);
	System.out.println(tw.compute());
	
}
}
