
import java.util.Scanner;


public class BookingApp {
    
    public static Scanner takeInput = new Scanner(System.in);

    public static void main(String args[]){
        System.out.println("\n***************************  Welcome to Hatfield Junior Swimming School  *************************** ");
        System.out.println();
        int menu_option = displayMenu();
        
        /* Call Functions According to Menu Option Selected */
        while (menu_option != 9) {
            switch (menu_option)
            {
                case 1 -> Functions.bookSwimmingLesson();
                case 2 -> Functions.changeOrCancelBooking();
                case 3 -> Functions.attendSwimmingLesson();
                case 4 -> Functions.learnerReport();
                case 5 -> Functions.coachReport();
                case 6 -> Functions.registerLearner();
                case 7 -> Functions.allBookings();
                case 8 -> System.exit(0);
                default -> System.out.println("\nYou have entered an Invalid Option.\n");
            }
            menu_option = displayMenu();
        }
    }
        
    
    //Display Menu
    private static int displayMenu(){
        System.out.println("\nSelect an Option => ");
        System.out.println("1. Book a swimming lesson ");        
        System.out.println("2. Change/Cancel a booking");
        System.out.println("3. Attend a swimming lesson ");
        System.out.println("4. Monthly learner report ");
        System.out.println("5. Monthly coach report ");
        System.out.println("6. Register a new learner");
        System.out.println("7. All Bookings");
        System.out.println("8. Exit");
        
        String option  = "";
        
        /* Validation for Menu Options */
        while (true) {
            System.out.print("\nEnter Valid Option ==> ");
            option = takeInput.nextLine();
            if (option.isEmpty()) {
                System.out.println("\nPlease Select an Option. ");
            } else if (!isInputDigit(option)) {
                System.out.println("\nYou must enter an integer value. ");
            } else {
                break;
            }
        }
        return Integer.parseInt(option);
    }
    
            
    
    //Display Timetable Menu
    public static int checkTimetableMenu(){
        System.out.println("\nSelect an Option to Filter Timetable => ");
        System.out.println("1. By Day ");        
        System.out.println("2. By Coach");
        System.out.println("3. By Grade Level ");
        System.out.println("4. Back");
        
        String option  = "";
        
        /* Validation for Menu Options */
        while (true) {
            System.out.print("\nEnter Valid Option ==> ");
            option = takeInput.nextLine();
            if (option.isEmpty()) {
                System.out.println("\nPlease Select an Option. ");
            } else if (!isInputDigit(option)) {
                System.out.println("\nYou must enter an integer value. ");
            } else {
                break;
            }
        }
        return Integer.parseInt(option);
    }
    
                        
    // Validation for Menu Option for valid digit
    public static boolean isInputDigit(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
    
    
    
}
