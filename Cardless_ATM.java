import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Cardless_ATM {

	public static void main(String[] args) {
		
		//Date and Time Format 
		LocalDateTime TD =LocalDateTime.now();
       	DateTimeFormatter FTD= DateTimeFormatter.ofPattern("M-dd-yyyy- HH:mm:ss");
		
		// Create a Scanner obj to be used in user inputs
		Scanner N= new Scanner(System.in);
		Scanner n= new Scanner (System.in);

		//Container of Client ID's and Demo Balance
		//element 1 is user 1
		int[] userID ={3789,9805,1462};
		int[] userBalance= {10000,5000,16800};
		int[] userPIN={792032,658479,188970};
		String[] USERname={"Ms. Gab","Mr. Lee","Mr. Jay"};
		

		
		
		//will be used in transaction option 
		int Option;
		
		//Prints the Logo
		System.out.println('\t'+"|    ______  _________    _____   ___    ___________________|");
		System.out.println('\t'+"|   /  _/  |/  / ____    /  _/ | / / |  / / ____/ ___/_  __/|");
		System.out.println('\t'+"|   / // /|_/ / / __     / //  |/ /| | / / __/  \\__ \\ / /   |");
		System.out.println('\t'+"| _/ // /  / / /_/ /   _/ // /|  / | |/ / /___ ___/ // /    |");
		System.out.println('\t'+"|/___/_/  /_/\\____/   /___/_/ |_/  |___/_____//____//_/     |");
		System.out.println('\t'+"     ║▌│║▌│║▌║▌█║  Enter Your Client ID  ▌│║▌║▌│║║▌█║");

		//Ask User for the input
		int inputUID=N.nextInt();

		// 6- Digit Pin Aunthenticator
		System.out.println('\t'+" ║▌│║▌│║▌║▌█║  Please Enter Your 6 Digit Pin   ▌│║▌║▌│║║▌█║"+'\n');
		int inputPIN=N.nextInt();
		
		//Authentication

		boolean gate=false;
		int D=0;
		while (userID.length>D){
			if ((inputUID==userID[D])&&(inputPIN==userPIN[D]))
			{gate=true;
			break;}
			D++;
		}
		 			
		if (gate==true){
			
			//Display the available transactions
			{System.out.println('\t'+"Please Choose a Transaction"+'\n'+
			"1. Cash withdrawal"+'\t'+'\t'+"2. Check Ballance"+'\n');

			//ask the user for input
			Option=N.nextInt();

			//switch to transaction inputed by the user
			switch (Option) {

				//Cash Withdrawal
				case (1):{ String Y_N="N";
					
					while (!Y_N.equalsIgnoreCase("y")){
						System.out.println("Enter the amount to withdraw:"+'\n'+
											"1. $1000"+'\n'+
											"2. $5000"+'\n'+
											"3. $10000"+'\n'+
											"4. $20000"+'\n'+
											"5. Enter Custom Amount");

						//ask the user for input
						int Option1;
						Option1=N.nextInt();

						int withdrawal=0;
						int balance1=0;

						//Switches based on the amount chosen
						switch (Option1){
							//Withdrawal
							case (1):{withdrawal=1000;
						break;}
							case (2):{withdrawal=5000;
						break;}
							case (3):{withdrawal=10000;
						break;}
							case (4):{withdrawal=20000;
						break;}
							
							//Custom amount
							case (5):{System.out.print("Enter the amount to withdraw:$");
							withdrawal=N.nextInt();
						break;}
						}
							balance1=userBalance[D]-withdrawal;

						if (balance1>0) {	
							System.out.print("Available Funds:$"+userBalance[D]+'\n'+"After Withdrawal:$"+balance1+'\n'+"Continiue?(Y/N): ");

								Y_N=n.nextLine();
							}
						else {
							System.out.println("Sorry you do not have sufficient ballance for this request"+'\n');
							}
					}
					
					//reciept
					System.out.println('\t'+" ║▌│║▌│║▌║▌█║  Withdrawal Successfull   ▌│║▌║▌│║║▌█║"+'\n');
					System.out.println(" ║▌│║▌│║▌║▌█║  Please wait for the cash and reciept   ▌│║▌║▌│║║▌█║"+'\n');
					System.out.println("Transaction date and time: "+TD.format(FTD));
					break;
				}
				
				case(2):{
					System.out.println("Current Balance: $"+userBalance[D]);
					System.out.println("Transaction date and time: "+TD.format(FTD));
					break;
				}
			}
		}
	}
	
		else{
			System.out.println("║▌│║▌│║▌║▌█║  Invalid Client PIN or Client ID Not Found  ▌│║▌║▌│║║▌█║");
		}
	}
}




