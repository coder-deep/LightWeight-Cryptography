import java.util.*;
import java.lang.Math;

public class Encryption
{
	double theta,alpha,base;
	double x,y,x_,y_,xnext,ynext;
	int xx,yy,key,n,l;
	char a,b;
	
	double[] Encrypt(String plain, Encryption en)
	{
		en.l=plain.length();
		double ciph[]=new double[en.l];
		for(int i=0;i<en.l;i+=2)
		{
			en.x=(int)plain.charAt(i);
			en.y=(int)plain.charAt(i+1);
			//System.out.print(en.x+" "+en.y+" ");
			if(i<en.l-2)
			{
			en.xnext=(int)plain.charAt(i+2);
			en.ynext=(int)plain.charAt(i+3);
			}
			else
			{
				en.xnext=en.key;
				en.ynext=en.key;
			}
			//System.out.println(en.x+" "+en.y+" ");
			en.x_+=en.xnext;
			en.y_+=en.ynext;
			en.x_=(en.x*Math.cos(en.theta))-(en.y*Math.sin(en.theta));
			en.y_=(en.x*Math.sin(en.theta))+(en.y*Math.cos(en.theta));
			
			//System.out.print(en.x_+" "+en.y_+" ");
			ciph[i]=en.x_;
			ciph[i+1]=en.y_;
			
		}
		System.out.println();
		return ciph;
	}
	
public static void main(String [] deep)
{
	int c;
	Scanner sc=new Scanner(System.in);
	Dcryption dry = new Dcryption();
	Encryption ery = new Encryption();
	Encryption en=new Encryption();
	System.out.print("enter key value :");
	en.key=sc.nextInt();
	
	for(int i=en.key+1;;i++)
	{
		c=0;
		for(int j=2;j<=i/2;j++)
		{
			if(i%j==0)
				c++;
		}
		if(c==0)
		{
			en.n=i;
			break;
		}
	}
	
	en.base=en.key^en.n;
	en.theta=Math.toRadians(en.n%360);
	en.alpha=Math.toRadians(360-en.n);
	System.out.print("enter key plain text :");
	String plain=sc.next();
	en.l=plain.length();
	double ciph[]=new double[en.l];
	ciph=ery.Encrypt(plain,en);
	System.out.print("Cipher text :");
	for(int i=0;i<en.l;i++)
	{
		System.out.print(ciph[i]+" ");
	}
	System.out.println();
	String deciph=dry.Decrypt(ciph,en);
	System.out.println("DeCipher text :"+deciph);
	sc.close();
}
}

class Dcryption
{
	static double[] reverse(double a[], int n) 
    { 
        double[] b = new double[n]; 
        int j = n; 
        for (int i = 0; i < n; i++) { 
            b[j - 1] = a[i]; 
            j = j - 1; 
        } 
        return b;
    } 
	String Decrypt(double ciph[],Encryption en)
	{
		String decipher="";
		char deci[] = new char[en.l];
		reverse(ciph, en.l);
	
		/*for(int i=0;i<en.l;i+=2)
		{
			en.x=ciph[i];
			en.y=ciph[i+1];
			en.x_=(en.x*Math.cos(en.alpha))-(en.y*Math.sin(en.alpha));
			en.y_=(en.x*Math.sin(en.alpha))+(en.y*Math.cos(en.alpha));
			//System.out.println(en.x_+" "+en.y_+" ");
			en.xx=(int)Math.round(en.x_);
			en.yy=(int)Math.round(en.y_);
			//System.out.println(en.xx+" "+en.yy+" ");
			en.a=(char)en.xx;
			en.b=(char)en.yy;
			//System.out.println(en.a+" "+en.b+" ");
			decipher+=en.a;
			decipher+=en.b;
		}*/
		
		for(int i=0;i<en.l;i+=2)
		{
			en.x=ciph[i];
			en.y=ciph[i+1];
			if(i==0)
			{
				en.xnext=en.key;
				en.ynext=en.key;
			}
			else
			{
				en.xnext=(int)decipher.charAt(i-2);//ciph[i+2];
				en.ynext=(int)decipher.charAt(i-1);//ciph[i+3];
			}
			//System.out.println();
			//System.out.println(en.xnext+" "+en.ynext);
			
			en.x_=(en.x*Math.cos(en.alpha))-(en.y*Math.sin(en.alpha));
			en.y_=(en.x*Math.sin(en.alpha))+(en.y*Math.cos(en.alpha));
			en.x-=en.xnext;
			en.y-=en.ynext;
			en.xx=(int)Math.round(en.x_);
			en.yy=(int)Math.round(en.y_);
			en.a=(char)en.xx;
			en.b=(char)en.yy;
			decipher+=en.a;
			decipher+=en.b;
		}
		return decipher;
	}
}