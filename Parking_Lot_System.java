import java.util.*;
import java.io.*;
import java.lang.*;
class park
{
//	String Name="MiniParking";
	public int reg_no,slot_no;
	long start_time,end_time;
	public static int t_slots[]=new int[10]; 
	static int f_slots[]=new int[10];
}
class T_WheelerPark extends park
{
	T_WheelerPark()
	{
		reg_no=0;
		start_time=0;
		end_time=0;		
	}
	static void available()
	{
		int f=0;
		for(int i=0;i<10;i++)
		{
			if(t_slots[i]==0)
			{	System.out.println(i+1);	f=1;	}
		}
		if(f==0)
			System.out.println("Oops!!! Parking space is full!!!");
	}
	void check_in()
	{
		int flag=0;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter vehicles last 4 digits:");
		reg_no=sc.nextInt();
		for(int i=0;i<10;i++)
		{	
			if(t_slots[i]==0)
			{
				slot_no=i+1;
				flag=1;
				start_time=System.currentTimeMillis();
				System.out.println("Vehicle should be parked at slot number "+slot_no);
				t_slots[i]=1;
				break;
			}
			else
			{
				continue;
			}
		}
		if(flag==0)
			System.out.println("Oops!!! Parking space is full!!!");
	}
	void check_out()
	{
		long amount;
		end_time=System.currentTimeMillis();
		amount=(end_time-start_time)/60000;
		System.out.println("Vehicle is checked out from slot number "+(slot_no+1)+"\nAmount to be paid:Rs."+amount);
		t_slots[slot_no]=0;
	}
}
class F_WheelerPark extends park
{
	F_WheelerPark()
	{
		reg_no=0;
		start_time=0;
		end_time=0;
	}
	static void available()
	{
		int f=0;
		for(int i=0;i<10;i++)
		{
			if(f_slots[i]==0)
			{	System.out.println(i+1);	f=1;	}
		}
		if(f==0)
			System.out.println("Oops!!! Parking space is full!!!");
	}
	void check_in()
	{
		int flag=0;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter vehicles last 4 digits:");
		reg_no=sc.nextInt();
		for(int i=0;i<10;i++)
		{	
			if(f_slots[i]==1)
				continue;
			else
			{
				slot_no=i+1;
				flag=1;
				start_time=System.currentTimeMillis();
				System.out.println("Vehicle should be parked at slot number "+slot_no);
				f_slots[i]=1;
				break;
			}
		}
		if(flag==0)
			System.out.println("Oops!!! Parking space is full!!!");
	}
	void check_out()
	{
		long amount;
		end_time=System.currentTimeMillis();
		amount=(end_time-start_time)/60000;
		System.out.println("Vehicle is checked out from slot number "+(slot_no+1)+"\nAmount to be paid:Rs."+amount);
		f_slots[slot_no]=0;
	}
}
public class Parking_Lot_System
{
	public static void main(String args[])
	{
		T_WheelerPark[] obj1=new T_WheelerPark[10];
		F_WheelerPark[] obj2=new F_WheelerPark[10];
		int ch,n;
		String type;						//t for 2 wheeler & f for 4 wheeler
		for(int i=0;i<10;i++)
		{
			obj1[i]=new T_WheelerPark();
			obj1[i].slot_no=i;
			obj2[i]=new F_WheelerPark();
			obj2[i].slot_no=i;
		}		
		try
		{
			Scanner sc1=new Scanner(System.in);
			System.out.println("Enter type of vehicle(t/f):");
			type=sc1.next();
			do
			{
			System.out.println("\n1.Check availability\n2.Check in.\n3.Check out\n4.Check slot number\n5.Exit");
			System.out.println("Enter your choice:");
			ch=sc1.nextInt();
			switch(ch)
			{
				case 1:
					System.out.println("Available Slots are...");
					if(type=="t")
						T_WheelerPark.available();
					else
						F_WheelerPark.available();
					break;
				case 2:
					if(type=="t")	
					{
						for(int i=0;i<10;i++)
						{
							if(obj1[i].t_slots[i]==0)
							{
								obj1[i].check_in();
								break;
							}
						}
					}
					else
					{	
						for(int i=0;i<10;i++)
						{
							if(obj1[i].f_slots[i]==0)
							{
								obj2[i].check_in();
								break;
							}
						}
					}
					break;
				case 3:
					int f3=0;
					System.out.println("Enter vehicle's last 4 digits:");
					n=sc1.nextInt();
					if(type=="t")
					{
						for(int i=0;i<10;i++)
						{
							if(obj1[i].reg_no==n)
							{	obj1[i].check_out();	f3=1;	}
						}
					}
					else
					{
						for(int i=0;i<10;i++)
						{
							if(obj2[i].reg_no==n)
							{	obj2[i].check_out();	f3=1;	}
						}
					}
					if(f3==0)
						System.out.println("Vehicle is not parked!!!");
					break;
				case 4:
					int f4=0;
					System.out.println("Enter vehicles last 4 digits:");
					n=sc1.nextInt();
					if(type=="t")
					{
						for(int i=0;i<10;i++)
						{
							if(obj1[i].reg_no==n && obj1[i].t_slots[i]==1)
							{
								f4=1;
								System.out.println("Vehicle is parked at slot number:"+(obj1[i].slot_no));
							}
						}
					}
					else
					{
						for(int i=0;i<10;i++)
						{
							if(obj2[i].reg_no==n && obj2[i].f_slots[i]==1)
							{
								f4=1;
								System.out.println("Vehicle is parked at slot number:"+(obj2[i].slot_no));
							}
						}
					}
					if(f4==0)
						System.out.println("Vehicle is not parked!!!");
					break;
				default:
					if(ch!=5)
						System.out.println("Wrong Choice...");
			}
			}while(ch!=5);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
