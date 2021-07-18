/**
 * @class: Number
 * @author : Daniel Leibner 
 * @author id : #########
 * @last update: 09/03/21
 * 
 * the class "Number" manipulate 4 digit number by spesipic rools 
 */

import java.util.Scanner;
public class Number
  {//start of class Number    
    /**
     * the main mathood takes a 4 digit number input from user.
     * if the number is not 4 digit it will return error message and will end her life
     * the program can do 3 actions on the number:
     *  1. reverse the sign of the number.
     *  2. reverse the number digits.
     *  3. Reverse the sign and number digits.
     *  
     *  return void
     */
    public static void main(String[] args)
    {//start of main method
        //declere and enitialize final ints for the 3 options of the program.
        final int FIRST_OPTION = 1;
        final int SECOND_OPTION = 2;
        final int THEERD_OPTION = 3;
        final int TOP_ABS_LIMIT = 10000;
        final int BOTTOM_ABS_LIMIT = 999;
        final int SIGN_CHANGER =-1;//reverse the sign of the number
        
        Scanner scan = new Scanner(System.in); // initial a new scanner so we could get input from user
        
        System.out.println("Please enter a 4 digit number:");
        int userNumber = scan.nextInt(); // the number from the user to do the action on.

        //check if the number have more then 4 digits.
        //the check is with the function abs from class math(learnd in lecture 2.4)
        //so the program will cover the positive and negative numbers.
        if(Math.abs(userNumber)<TOP_ABS_LIMIT && Math.abs(userNumber)>BOTTOM_ABS_LIMIT)
            {//runs only if the number have 4 digits
            System.out.println("1. Reverse sign.\n" +
                    "2. Reverse number.\n" +
                    "3. Reverse sign and number.\n" +
                    "Please choose an option: ");
                    
            int userChoice = scan.nextInt();//the choice of the user to manipulate the number
            int adaptiveSignIndicator = 1;//the sign indicator will determine if the number will revers sign or not.
            switch (userChoice) {
                case FIRST_OPTION://reverse the sign of the number
                    userNumber=(userNumber*SIGN_CHANGER);
                    System.out.println("The result is \n" + userNumber);
                    break;//end of case 1
                case THEERD_OPTION: //case 3 is to reverse the sign and the digits of the number
                    adaptiveSignIndicator = -1;// so the sign of the number will reverse
                    //the action will continue to the 2 case so the digit number will reverse as well.
                case SECOND_OPTION: // case 2 is to reverse the digit in user number.
                    int mirrorToUserNumber = adaptiveSignIndicator*(((userNumber%10)*1000)+
                        ((userNumber%100)/10*100)+ ((userNumber%1000)/100*10)+(userNumber/1000));
                    //the adaptiveSignIndicator in default equals to 1,
                    // if case 3 was activate the sign indicator will be -1 and the sign of user number will reverse
                    System.out.println("The result is \n" + mirrorToUserNumber);
                    break; //end of case 3 and 2.
                default: //if the user enter illegal option.
                    System.out.println("Illegal option-you must choose 1, 2 or 3");
                    break;//end of default case.
            }//end of switch(userChoice)
        }//end of if statement
        else{//the user input number dont have 4 digits
            System.out.println("Illegal number - you must enter a 4 digit number ");
        }//end of else.
    }//end of methode main

}//end of class Number