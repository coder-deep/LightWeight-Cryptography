import java.util.Scanner;
import java.math.*;

class pair1
{
	int n;
	int e;
}

class base1
{
	private int a=59;
	private int b=53;
	
pair1 getPublicKey()
{
	pair1 p=new pair1();
	p.n=a*b;
	p.e=3;
	return p;
}

int getPrivateKey()
{
	return (a*b-a-b+1);
}
}

class privatekey1
{
	base1 b;
	pair1 p;
	int phi;
	int k;
	int d;
privatekey1()
{
	b=new base1();
	p=b.getPublicKey();
	phi=b.getPrivateKey();
	k=2;
	d=(k*phi + 1)/p.e;
}
}

class Decryption
{
int decr(int a)
{
	base1 b=new base1();
	pair1 p=new pair1();
	p=b.getPublicKey();
	privatekey1 d=new privatekey1();
	//System.out.println(a);
	BigInteger bi1,bi2,bi3,exp;
	bi1=new BigInteger(Integer.toString(a));
	bi2=new BigInteger(Integer.toString(p.n));
	exp=new BigInteger(Integer.toString(d.d));
	bi3=bi1.modPow(exp, bi2);
	return bi3.intValue();
}

int[] dec(int[] a,int n)
{
	for(int i=0;i<n;i++)
	{
		a[i]=decr(a[i]);
	//System.out.print(a[i]+" ");
	}
	return a;
}
}

public class Decrypt
{
public static void main(String []deep)
{
	Scanner sc=new Scanner(System.in);
	int []b=new int[10000];
	int ii=0,flag=-1;
	while(flag==-1)
	{
		b[ii]=sc.nextInt();
		//System.out.print(b[ii]+" ");
		if(b[ii]==-1)
			flag=0;
		ii++;
		//System.out.print("a");
	}
	ii--;
	Decryption d=new Decryption();
	int []done=d.dec(b,ii);
	for(int i=0;i<ii;i++)
		System.out.print((char)(done[i]));
}
}