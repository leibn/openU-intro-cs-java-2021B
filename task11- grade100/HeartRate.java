/**
 * @class: HeartRate
 * @author : Daniel Leibner
 * @author id : #########
 * @last update: 09/03/21
 * 
 * the class "HeartRate" calculate the desired heart rate range for trainers. 
 */
import java.util.Scanner;
public class HeartRate{
    
    /**
     * the main methode calculate the desired heart rate range for trainers
     * the equation for the program is:
     *     top limit = (85 percent from (220 minus the age of the trainer)),
     *     bottom limit = (65 percent from (220 minus the age of the trainer)). 
     */
    public static void main (String [] args)
    {
        final int CONST = 220;//the initial num to subtract from in thr heart rate function
        final int TOP_PERCENTAGE_LIMIT = 85;//the top limit Heart rate percentage
        final int BOTTOM_PERCENTAGE_LIMIT = 65;//the bottom limit Heart rate percentage
        final int ONE_HUNDRED_PERCENT = 100;//initial precenteg to the program 

        Scanner scan = new Scanner (System.in); // initial a new scanner so we could get input from user
        System.out.println ("This program calculates your " +
                "target heart rate while exercising. ");
        System.out.print ("Enter your age: ");
        int age = scan.nextInt();
        int concreteBottomLimit = ( CONST - age ) * BOTTOM_PERCENTAGE_LIMIT / ONE_HUNDRED_PERCENT;//calculate and store the bottom limit heart rate as a whole number
        int concreteTopLimit = ( CONST - age ) * TOP_PERCENTAGE_LIMIT / ONE_HUNDRED_PERCENT;//calculate and store the top limit heart rate as a whole number
        System.out.println("Your estimated target heart rate zone is "+ concreteBottomLimit + " - " + concreteTopLimit + " beats per minute.");
    } // end of method main
} //end of class HeartRate

