import java.util.*;
import java.util.regex.*;
interface Main{
          
          void deposit();
          void withDraw();
          void balanceCheck();
}
abstract class Adapter implements Main{
         
          public void deposit(){}
          public void withDraw(){}
          public void balanceCheck(){}
}
class Deposit extends Adapter{
          private static double balance=100;
          private double dAmt;
          Deposit(double dAmt){
                    this.dAmt=dAmt;
          }
          Deposit(){}
          @Override
          public void deposit(){
                  balance+=dAmt;  
          }
          //Getter method for Balance
          public double getBalance(){
                    return balance;
          }
          //Setter method for balance
          public void setBalance(double balance){
                    this.balance=balance;
          }
}
class WithDraw extends Adapter{
          private double wAmt;
          WithDraw(double wAmt){
                    this.wAmt=wAmt;
          }
          Deposit d=new Deposit();
          double balance=d.getBalance();
          @Override
          public void withDraw(){
                    balance-=wAmt;
                    d.setBalance(balance);
          }
          //Getter method for balance after withdraw
          public double getBalance(){
                    return balance;
          }
}
class Project{
          public static void main(String[] args){
                    Scanner sc=new Scanner(System.in);
                    System.out.println("Welcome to the ATM");
                    System.out.println("---------------------------------------");
                    System.out.println("Enter your pin:");
                    String pin=sc.next();
                    Pattern p=Pattern.compile("[0-9]{4}");
                    Matcher m=p.matcher(pin);
                    if(m.find() && m.group().equals(pin)){
                    while(true){
                    System.out.println("Select the option");
                    System.out.println("1.Deposit\t\t2.Withdraw");
                    System.out.println("3.Check Balance\t\t4.Generate OTP");
                    System.out.println("5.Exit");
                    System.out.println("_______________________________________");                   
                    int choice=sc.nextInt();
                    try{                    
                    // To check the valid choice 
                    if(choice<1 || choice >5){
                              throw new IllegalArgumentException("Entered option is invalid");
                    }
                    //To perform neccessary operation
                    switch(choice){
                              case 1:
                                        System.out.println("You Choose Deposit Option");
                                        System.out.println("Enter the amount to deposit:");
                                        double dAmt=sc.nextDouble();
                                        //check if the amount is less than 100 ,if it is then shows error message
                                        if(dAmt<100){throw new IllegalArgumentException("The Amount you entered can't be deposit!!!");}
                                        Deposit deposit=new Deposit(dAmt);
                                        deposit.deposit();
                                        System.out.println("Amount Deposited Successfully!");
                                        System.out.println("Do you want to see your current balance?");
                                        System.out.println("1.Yes\t2.No");
                                        int ch=sc.nextInt();
                                        switch(ch){
                                                  case 1:
                                                             System.out.println("Current Balance:"+deposit.getBalance());
                                                             System.out.println("_______________________________________");  
                                                             break;
                                                  case 2:
                                                             System.out.println("Thnak You!...");
                                                             System.out.println("_______________________________________");  
                                                             break;
                                         }
                                         break;
                               case 2:
                                        System.out.println("You Choose Withdraw Option");
                                        System.out.println("Enter amount to withdraw:");
                                        double wAmt=sc.nextInt();    
                                        WithDraw withdraw=new WithDraw(wAmt);
                                        if(wAmt<100){throw new IllegalArgumentException("Can't Withdraw !!!");}
                                        // check the balance is not empty   
                                        if(withdraw.balance==0){throw new IllegalArgumentException("Your Current balance is 0 !!!");}     
                                        else if(withdraw.balance<wAmt){
                                                  throw new IllegalArgumentException("Sorry Insufficient Balance !!");
                                        }     
                                        withdraw.withDraw();
                                        System.out.println("Amount Withdrawn Successfully!"); 
                                        System.out.println("Do you want to see your current balance?");
                                        System.out.println("1.Yes\t2.No");
                                        int ch1=sc.nextInt();
                                        switch(ch1){
                                                  case 1:
                                                             System.out.println("Current Balance:"+withdraw.getBalance());
                                                             System.out.println("_______________________________________");  
                                                             break;
                                                  case 2:
                                                             System.out.println("Thnak You!...");
                                                             System.out.println("_______________________________________");  
                                                             break;
                                         }  
                                         break;
                                case 3:
                                         System.out.println("You Choose Balance Checking Option");
                                         System.out.println("Your Balance is:"+ new Deposit().getBalance());
                                         System.out.println("_______________________________________");  
                                         break;
                                case 4:
                                         Random r=new Random();
                                         //Generating OTP with 6 digit
                                         int otp=Math.abs(r.nextInt(1000)+r.nextInt(1000000));
                                         System.out.println("Your One Time Password Is:"+otp);
                                         System.out.println("_______________________________________");  
                                         break;
                                case 5:
                                        System.out.println("Thank you !!!");
                                        System.out.println("_______________________________________");  
                                        System.exit(0);
                       }
                    }
                    catch(IllegalArgumentException e){
                              System.out.println("**************************************");
                              System.out.println("* Error:"+e.getMessage());
                              System.out.println("**************************************");
                    }
                  }
              }
              else{
                    System.out.println("Error:Invalid Pin Format!!");
              }
          }
}
