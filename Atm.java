import java.util.*;
public class Atm{
	String cardnum;
	int pin;
	String name;
	float balance;
	static String transaction="";
	static public int c=0;
    public	ArrayList<Atm> list = new ArrayList<>();
	Atm()
	{
	}
	Atm(String cardnum,int pin,String name,float balance)
	{
		this.cardnum=cardnum;
		this.pin=pin;
		this.name=name;
		this.balance=balance;
	}
	public String getcardnum()
	{
		return cardnum;
	}
	public int getpin()
	{
		return pin;
	}
	public String getname()
	{
		return name;
	}
	public float getbalance()
	{
		return balance;
	}
	public void setcardnum(String s1)
	{
		cardnum=s1;
	}
	public void setpin(int p1)
	{
		pin=p1;
	}
	public void setname(String n1)
	{
		name=n1;
	}
	public void setbalance(float b1)
	{
		balance=b1;	
	} 
	public void addValues(String num[], int pin[],String name[], float baln[])
    {
        for (int i = 0; i <4; i++) 
        {
            list.add(new Atm(num[i], pin[i],name[i],baln[i]));
        }
    }
	 public  static void transfer(Atm obj,String[] nums,float baln[])
    {
    	Scanner sc=new Scanner(System.in);
    	System.out.println("Enter Account number:");
    	String acctno=sc.nextLine();
    	int i=0;
    	int flag=0;
    	for( i=0;i<nums.length;i++)
    	{
    		if(acctno.equals(nums[i]))
    		{
    			flag=1;
    			break;
    		}	    		
    	}
    	if(flag==0)
    	{
    		System.out.println("Wrong Account number");
    		return;
    	}
    	System.out.println("Enter amount to transfer :");
        float amount = sc.nextFloat();   
        if(obj.balance >= amount)
        {
            c++;
            obj.balance -= amount;
            System.out.println("Successfully Transferd ");
            c++;
            baln[i]+=amount;
            transaction+="\n"+Float.toString(amount)+" rupees transferred from your account to "+acctno+" \n";
        }
        else
            System.out.println("Insufficient balance");
    }   
	public static void main(String args[])
	{
		String num[]={"1234567890","1234567891","1234567892","1234567893","1234567894"};
		int pin[]={1230,1231,1232,1233,1234};
		String name[]={"Dhruvv","Royya","Nitya","Sravv","Rani"};
		float baln[]={200000,28000,25000,12000,240000};
		Atm data=new Atm();
		data.addValues(num,pin,name,baln);
		System.out.println("Welcome to ATM");
		System.out.println("Please insert your card: ");
		String debitcardnum="";
		Scanner sc3=new Scanner(System.in);
		Atm obj=null;
		int no_tries=0;
		while(true)
		{
			System.out.println("Enter CardNum:");
			debitcardnum=sc3.nextLine();
			for(int i=0;i<4;i++)
			{
				Atm s1=data.list.get(i);
				if(s1.getcardnum().equals(debitcardnum))
				{
					obj=s1;
					break;
				}
			}
			if(obj!=null||no_tries>3)
			{
				break;
			}
			else
			{
				no_tries=0;
				System.out.println("User Not Recognized... Please Try again");
			}
		}
		int userpin=0;
		System.out.println("Enter pin:");
	    no_tries=0;
		while(true)
		{
			userpin=sc3.nextInt();
			if(obj.getpin()==userpin || no_tries>3)
			{
				break;
			}
			else
			{
				no_tries++;
				System.out.println("User Not Recognized... Please Try again");
			}
		}
		System.out.println("Welcome "+obj.getname());
		System.out.println("********************Enter Your Option********************");   
        System.out.println("1. Check balance");
        System.out.println("2. Deposite");
        System.out.println("3. Withdraw");
        System.out.println("4. Transfer");
        System.out.println("5. Transaction History"); 
        System.out.println("6. Exit");     
		int option=sc3.nextInt();
		while(option<=5)
		{
			if(option==1)
				System.out.println("Your Account balance is "+obj.getbalance());
			else if(option==2)
			{
				System.out.println("How much would you like to deposit:");
		        Scanner sc2=new Scanner(System.in);
		        float amount=sc2.nextFloat();
		        float temp=amount+obj.getbalance();
		        obj.setbalance(temp);
		        System.out.println("Your account balance is "+obj.getbalance());
		        c++;
                transaction+="\n"+Float.toString(amount)+ " rupees deposited into your account"+"\n";
			}
			else if(option==3)
			{
				System.out.println("How much would you like to withdraw");
		        Scanner ss1=new Scanner(System.in);
		        float amount=ss1.nextFloat();
		        if(amount>obj.getbalance())
			        System.out.println("Insufficient balance!!");
		        else
		        {
			        while(amount%10!=0)
                    {
                        System.out.println("Enter amount in terms of 10's");
                        amount = ss1.nextFloat();
                    }
			        float temp=obj.getbalance()-amount;
			        obj.setbalance(temp);
			        System.out.println("Collect you cash!! Thank You");
			        c++;
			        transaction+="\n"+Float.toString(amount)+" rupees withdraw from your account"+"\n";
		        }
			}
			else if(option==4)
			{
				transfer(obj,num,baln);
			}
			else if(option==5)
			{
				if(c>0)
                {
                    System.out.println("You have performed "+c+" transactions");
                    System.out.println("Transaction Details :");
                    System.out.println(transaction);
                }
                else
                {
                    System.out.println("No transactions");
                }
			}
			else
			{
				break;
			}
	        System.out.println("*******************Enter Your Option*******************");   
            System.out.println("1. Check balance");
            System.out.println("2. Deposite");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Transaction History"); 
            System.out.println("6. Exit");     
     	    option=sc3.nextInt();
		}
		System.out.println("Thank you! Have a great day!");
	}
}