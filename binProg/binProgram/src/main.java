import java.util.InputMismatchException;
import java.util.Scanner;

public class main {

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int userNum = 0;    // to keep the compiler happy
        
        boolean done = false;
            
        while(!done){
            try
            {
                System.out.println();
                System.out.print("Please enter a Decimal number to convert to Binary in between -1023 and 1023 -->  ");
                userNum = scan.nextInt();
                System.out.println();

                if (userNum > 1023 || userNum < -1023){
                    throw new IllegalArgumentException("Please enter a number in the right range");
                }
                
                done = true;
            }
            catch(InputMismatchException e) {
                scan.nextLine(); // go to the next line 
                System.out.println("Error: Not a correct input. This requires and integer");
                System.out.println();
            }
            catch(IllegalArgumentException e){
                System.out.println();
                System.out.println("Error: " + e.getMessage());
            }
        }   
       

        test something = new test();

        something.printAnswer(userNum, userNum < 0);

        scan.close();

    }
    
}
