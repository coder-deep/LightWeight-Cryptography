import java.util.*;
class pair
{
	int n;
	int e;
}

class base
{
	private int a=59;
	private int b=53;
pair getPublicKey()
{
	pair p=new pair();
	p.n=a*b;
	p.e=3;
	return p;
}

int getPrivateKey()
{
	return a*b-a-b+1;
}
}

class privatekey
{
	base b=new base();
	pair p=b.getPublicKey();
	int phi=b.getPrivateKey();
	int k=2;
	int d=(k*phi + 1)/p.e;
}

class Encrypt
{
public int encr(int a)
{
	base b=new base();
	pair p=new pair();
	p=b.getPublicKey();
	return (((int)Math.pow((double)a,(double)p.e)) % p.n);
}

public int[] enc(int []a)
{
	for(int i=0;i<a.length;i++)
		a[i]=encr(a[i]);
	return a;
}
}

public class Encryptor
{
public static void main(String[] uday)
{
	Scanner sc=new Scanner(System.in);
	String message=sc.nextLine();
	char a[]=message.toCharArray();
	int []b=new int[message.length()+1];
	for(int i=0;i<message.length();i++)
	{
		b[i]=(int)a[i];
		// System.out.print(b[i]+" ");
	}
	Encrypt e=new Encrypt();
	int []encrypted=e.enc(b);
	for(int i=0;i<message.length();i++)
		System.out.print(encrypted[i]+" ");
}
}
