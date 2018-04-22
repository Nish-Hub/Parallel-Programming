/*
 * Point to note here is we are defining the method function variables and using them directly. The method refernces created
 * can be used directly like they are used as interface references pointing to its impl object
 */

public class LambdaCalculator {
public static void main(String[] args) {
	char a[] = { 'a', 'b', 'c', 'd' };
	// Using the calculator
	add.add(1, 2);
	mul.mul(1, 2);
	sub.subtract(1, 2);
	lFinder.stringLength(a.toString());
	fChar.findChar(a, 'c');
}
	
// Defining the lambda functional variables	
static Add add =(int a,int b)-> a+b;
static Mul mul=(int a,int b)-> a*b;
static Subtract sub=(int a,int b)->Math.abs(a-b);
static LengthFinder lFinder=(String s)->s.length();
static FindCharacter fChar=(char [] a,char c) ->
{

	for (int i = 0; i < a.length; i++)
		if (a[i] == c)
			return i;
	return -1;

};
}
//Functional Interfaces defined to be used as lambda function target types
interface Add
{
	public int add(int a,int b);
}
interface Mul
{
	public int mul(int a,int b);
}
interface Subtract
{
public int subtract(int a,int b);
}
interface LengthFinder
{
public int stringLength(String s);
}
interface FindCharacter
{
	public int findChar(char[] a,char c);
}
