import java.util.Scanner;
public class Test {
	public static void main(String[] args) {
		Transfer t=new Transfer();
		int acc,ch;
		String pass,ans;
		double amt;
		Scanner sc=new Scanner(System.in);
		System.out.println("\n Enter account number and password to login");
		acc=sc.nextInt();
		pass=sc.next();
		t.login(acc, pass);
		//System.out.println(acc+pass);
		if(t.verify())
		{
			do
			{
				System.out.println(acc+" "+pass);
					System.out.println("\n !.Credit in account \n 2.Debit From account \n 3.Showstatus of account \n 4.Send Amount ");
					System.out.println("\n Enter your choice!!");
					ch=sc.nextInt();
					switch(ch)
					{
					case 1:
						System.out.println("\n Enter amount to credit.");
						amt=sc.nextDouble();
						if(t.credit((float) amt))
						{
							System.out.println("\n Amount credited succesfully");
							t.showstatus();
						}
						else
						{
							System.out.println("\n transaction failed");
						}
						break;
					case 2:
						System.out.println("\n Enter amount to debit ");
						amt=sc.nextDouble();
						if(t.debit(amt))
						{
							System.out.println("\n Amount deibited!");
							t.showstatus();
						}
						else
							System.out.println("\n Transaction cant be completed");
						break;
					case 3:
						t.showstatus();
						break;
					case 4:
						System.out.println("\n Enter amount to credit.");
						amt=sc.nextDouble();
						System.out.println("\n Enter account number to send money");
						int acc2 = sc.nextInt();
						if(t.send(amt,acc2))
							System.out.println("\n AMount transfered !!!!!!");
						else
							System.out.println("\n Transaction failed!!!!");
						t.showstatus();
						break;
						
					default :
						System.out.println("\n Enter valid choice!!!!!!!");
						break;
					}
					System.out.println("\n Do you want to do another transaction ??");
					ans=sc.next();
			}while(ans.equals("Yes")||ans.equals("yes"));
		}
		else
			System.out.println("\n Wrong credentials!!!");
	}

}
