

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;


public class Functions {
    
    public static Scanner takeInput = new Scanner(System.in);
    
    //Booking Status
    public static final String BOOK = "Booked";
    public static final String ATTEND = "Attended";
    public static final String CANCEL = "Cancelled";
    public static final String CHANGE = "Changed";
    

    //Book Swimming Lesson
    public static void bookSwimmingLesson(){
        //Check Yimetable
        checkTimetable();
    }
    
    //Change or cancel Swimming Lesson
    public static void changeOrCancelBooking(){
        System.out.println("\nEnter 1 to Update Booking");
        System.out.println("Enter 2 to Cancel Booking");
        
        System.out.print("\nInput Your Option : ");
        String choice = takeInput.nextLine();
        
        if (!choice.equalsIgnoreCase("1") && !choice.equalsIgnoreCase("2") && !BookingApp.isInputDigit(choice)) {
            System.out.println("You have selected an invalid choice");
            return;
        }
        
        BookingClient client = new BookingClient();
        client.processBookingChangeOrCancel(choice);      
    }
    
    //Attend Swimming Lesson
    public static void attendSwimmingLesson(){
        System.out.print("\nInput Your Booking Code to Attend Lesson : ");
        String bookingCode = takeInput.nextLine();
        
         if(!BookingApp.isInputDigit(bookingCode) || bookingCode.equalsIgnoreCase("")){
            System.out.println("Enter Valid Booking Code");
            return;
        }
        
        boolean isBookingCodeExist = BookedClasses.isBookingCodeExist(Integer.parseInt(bookingCode));
        if(!isBookingCodeExist){
             System.out.println("\nBooking Code entered by you is not correct");
             return;
        }
        
        List<BookedClasses> bookedClasses = BookedClasses.getBookedClassesDetails();
        List<LearnerDetails> learnerDetails = LearnerDetails.getLearnerDetails();
        List<CoachDetails> coachDetails = CoachDetails.getCoachDetails();

        //Update Booking Details
        String existingLessonCode = "";
        String learnerUniqueCode = "";
        String coachUniqueCode = "";
        String coachName = "";
        String lessonName = "";
        String bookingBy = "";
        for(BookedClasses obj : bookedClasses){
            if(obj.getBookingUniqueCode() == Integer.parseInt(bookingCode)){
                
                if(obj.getBookingStatus().equalsIgnoreCase(ATTEND) || obj.getBookingStatus().equalsIgnoreCase(CANCEL)){
                    System.out.println("Booking has been cancelled or attended");
                    return;
                }
                
                coachName = obj.getCoachName();
                learnerUniqueCode = obj.getBookedByLearner();
                existingLessonCode = obj.getLessonCode();
                lessonName = obj.getLessonName();
                
                //Coach Details
                for(CoachDetails cobj : coachDetails){
                    if(cobj.getUsername().equalsIgnoreCase(obj.getCoachName())){
                        coachUniqueCode = cobj.getCoachUniqueNo();
                    }
                }
                
                //Learner details
                for (LearnerDetails lObj : learnerDetails) {
                    if(lObj.getLearnerUniqueNo().equalsIgnoreCase(obj.getBookedByLearner())){
                        bookingBy = lObj.getUsername();
                    }
                }
                
                //Update status to attend
                obj.setBookingStatus(ATTEND);
                break;
            }
        }
        
        //Take Review
        System.out.print("\nWrite Your Review : ");
        String review = takeInput.nextLine();
        
        //Take Rating
        System.out.print("\nInput Your Rating that you want to give coach '"+coachName+"' : ");
        String rating = takeInput.nextLine();
        
        if(rating.equalsIgnoreCase("") || !BookingApp.isInputDigit(rating) || (Integer.parseInt(rating) < 1 || Integer.parseInt(rating) > 5)){
            System.out.println("Enter Valid Rating between 1 to 5");
            return;
        }
        
        //Add review
        CoachRatings obj = new CoachRatings(coachUniqueCode,coachName,learnerUniqueCode,review,Integer.parseInt(rating));
        CoachRatings.coachRatingDetails.add(obj);
        
        //Increase Seats after attending lesson
        LessonDetails.increaseVacantSeatSpace(existingLessonCode);
        
        //Update Current Grade level of learner if attends higher class grade   
        //Get Learner Grade
        int currentGrade = 0;
        for(LearnerDetails obj2 : learnerDetails){
            if(obj2.getLearnerUniqueNo().equalsIgnoreCase(learnerUniqueCode)){
                currentGrade = obj2.getCurrentGradeLevel();
                break;
            }
        }
        
        //Get Class Grade
        List<LessonDetails> lessonDetails = LessonDetails.getLessonDetails();
        int lessonGrade = 0;
        for(LessonDetails obj2 : lessonDetails){
            if(obj2.getCode().equalsIgnoreCase(existingLessonCode)){
                lessonGrade = obj2.getGradeLevel();
                break;
            }
        }
        boolean isUpdated = false;
        
        if(lessonGrade > currentGrade){
            isUpdated = true;
        }
        
        if(isUpdated){
            LearnerDetails.increaseGradeLevel(learnerUniqueCode);
        }
       
        System.out.println("\n\nBooking Attended with Booking Code '"+bookingCode+"' having Lesson Name '"+lessonName+"' by Learner '"+bookingBy+"'");
        BookedClasses.viewUpdatedBookingDetails(Integer.parseInt(bookingCode));
        return;
    }
    
    
    //Learner Report
    public static void learnerReport(){
        System.out.print("\nEnter Month No. : ");
        String userMonth = takeInput.nextLine();
        
        if(!BookingApp.isInputDigit(userMonth) || (Integer.parseInt(userMonth) < 1 || Integer.parseInt(userMonth) > 12)){
            System.out.println("\nMonth Number should be valid integer between 1 to 12");
            return;
        }
         
        List<BookedClasses> bookedClasses = BookedClasses.getBookedClassesDetails();
        List<LearnerDetails> learnerDetails = LearnerDetails.getLearnerDetails();
        List<LessonDetails> lessonDetails = LessonDetails.getLessonDetails();
       
        System.out.println();

        HashMap<String, int[]> bookedClassesBylearners = new HashMap<>();
        
        Set<String> uniqueLearnerCode = new HashSet<>(); 
        if(userMonth.equalsIgnoreCase("5")){
            
            for (int j = 0; j < bookedClasses.size(); j++) {
                for (int i = 0; i < learnerDetails.size(); i++) {

                    String uniqueKey = learnerDetails.get(i).getLearnerUniqueNo() + bookedClasses.get(j).getBookingUniqueCode();

                    if (!uniqueLearnerCode.contains(uniqueKey)) {
                        uniqueLearnerCode.add(uniqueKey);

                        if (learnerDetails.get(i).getLearnerUniqueNo().equalsIgnoreCase(bookedClasses.get(j).getBookedByLearner())) {


                            String uniqueCode = learnerDetails.get(i).getLearnerUniqueNo();
                            int[] lessonCounts = bookedClassesBylearners.getOrDefault(uniqueCode, new int[3]);

                            if (bookedClasses.get(j).getBookingStatus().equalsIgnoreCase(BOOK) 
                                    || bookedClasses.get(j).getBookingStatus().equalsIgnoreCase(CHANGE)) {
                                lessonCounts[0]++;
                            }
                            if (bookedClasses.get(j).getBookingStatus().equalsIgnoreCase(ATTEND)) {
                                lessonCounts[1]++;
                            }
                            if (bookedClasses.get(j).getBookingStatus().equalsIgnoreCase(CANCEL)) {
                                lessonCounts[2]++;
                            }
                            bookedClassesBylearners.put(uniqueCode, lessonCounts);
                        }
                    }
                }
            }
        }

        
        if(!bookedClassesBylearners.isEmpty()){
            for (Map.Entry<String, int[]> entry : bookedClassesBylearners.entrySet()) {
                String learnerUniqueCode = entry.getKey();
                int[] lessonCounts = entry.getValue();

                //Learenr details
                String learnerName = "";
                for (int i = 0; i < learnerDetails.size(); i++) {
                   if(learnerDetails.get(i).getLearnerUniqueNo().equalsIgnoreCase(learnerUniqueCode)){
                       learnerName = learnerDetails.get(i).getUsername();
                   }
                }

                System.out.println("\nLearner Name : "+ learnerName+"\n");
                System.out.println("\n----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.printf("| %-13s | %-30s | %-20s | %-25s | %-12s | %-30s | %-15s | %-20s |\n",
                        "Booking Code", "Booking By", "Booking Status", "Booking Date",  "Lesson Code", "Lesson Name", "Grade Level","Lesson By");
                System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                for (int j = 0; j < bookedClasses.size(); j++) {
                    
                    if(bookedClasses.get(j).getBookedByLearner().equalsIgnoreCase(learnerUniqueCode)){
                        

                        //Lesson Grade Level
                        int lessonGrade = 0;
                        for (LessonDetails lObj : lessonDetails) {
                            if(lObj.getCode().equalsIgnoreCase(bookedClasses.get(j).getLessonCode())){
                                lessonGrade = lObj.getGradeLevel();
                            }
                        }
                        System.out.printf("| %-13s | %-30s | %-20s | %-25s | %-12s | %-30s | %-15s | %-20s |\n",
                                bookedClasses.get(j).getBookingUniqueCode(), learnerName, bookedClasses.get(j).getBookingStatus(), 
                                bookedClasses.get(j).getBookingConfirmedOn(), bookedClasses.get(j).getLessonCode(),
                                bookedClasses.get(j).getLessonName(), lessonGrade, bookedClasses.get(j).getCoachName());
                    }
                }

                System.out.println("\nNo Of Booked Lessons : "+lessonCounts[0]);
                System.out.println("No Of Attended Lessons : "+lessonCounts[1]);
                System.out.println("No Of Cancelled Lessons : "+lessonCounts[2]);
                System.out.println("\n#################################################################################################");
            }
        }else{
             System.out.println("No Record Found");
        }
    }
    
    //Coach Report
    public static void coachReport(){
        System.out.print("\nEnter Month No. : ");
        String userMonth = takeInput.nextLine();
        
        if(!BookingApp.isInputDigit(userMonth) || (Integer.parseInt(userMonth) < 1 || Integer.parseInt(userMonth) > 12)){
            System.out.println("\nMonth Number should be valid integer between 1 to 12");
            return;
        }
        
        List<CoachRatings> coachRatings = CoachRatings.getCoachRatingDetails();
        List<LessonDetails> lessonDetails = LessonDetails.getLessonDetails();
         
        HashMap<String, Integer> numOfRate = new HashMap<>();
        HashMap<String, Integer> totalrows = new HashMap<>();
        HashMap<String, Double> finalRating = new HashMap<>();
       
        System.out.println();
       
        for (CoachRatings obj : coachRatings){
            for (LessonDetails lobj : lessonDetails){
                
                 //Parse date
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");
                
                String monthNumber = "";
                try {
                    // Parse the formatted datetime string back to LocalDateTime using the correct formatter
                    LocalDateTime parsedDateTime = LocalDate.parse(lobj.getDate(), formatter).atStartOfDay();
                    // Extract and format the month number from the parsed datetime
                    monthNumber = parsedDateTime.format(DateTimeFormatter.ofPattern("M"));

                } catch (DateTimeParseException e) {
                    System.out.println("Error parsing datetime: " + e.getMessage());
                }
                    
                if(lobj.getTeachBy().equalsIgnoreCase(obj.getCoachUniqueNo()) && monthNumber.equalsIgnoreCase(userMonth)){
                    String coachName = obj.getCoachName();
                    int givenRating = obj.getRating();

                    numOfRate.put(coachName, numOfRate.getOrDefault(coachName, 0) + givenRating);
                    totalrows.put(coachName, totalrows.getOrDefault(coachName, 0) + 1);
                }
            }
        }

        for (String coachName : numOfRate.keySet()) {
            int ratings = numOfRate.get(coachName);
            int rows = totalrows.get(coachName);

            if (rows > 0) {
                double avgRate = (double) ratings / rows;
                double roundOffrate = Math.round(avgRate * 10.0) / 10.0; 
                finalRating.put(coachName, roundOffrate);
            }
        }
        if(!finalRating.isEmpty()){
            System.out.println("----------------------------------------------");
            System.out.printf("| %-20s | %-15s |\n", "CoachName", "AvgRating");
            System.out.println("----------------------------------------------");
            for (String coachName : finalRating.keySet()) {
                double averageRating = finalRating.get(coachName);
                System.out.printf("| %-20s | %-15s |\n", coachName, averageRating);
            }
        }else{
            System.out.println("No Record Found");
        }
    }
    
    //Register Learner
    public static void registerLearner(){
        
        //Take Username
        System.out.print("\nEnter Learner Username and must be atleast 3 characters long : ");
        String username = takeInput.nextLine();
        
        if(username.equalsIgnoreCase("") || username.length() < 4){
            System.out.println("\nEnter Learner Username and must be atleast 3 characters long.");
            return;
        }
        
        //Is username exists or not
        boolean isFound = LearnerDetails.isLearnerExist(username);
        if(isFound){
            System.out.println("\nLearner with this username already exists");
            return;
        }
        
        //Take Gender
        System.out.print("\nEnter Gender (Male/Female) : ");
        String gender = takeInput.nextLine();
        
        if(gender.equalsIgnoreCase("") || !gender.equalsIgnoreCase("male") && !gender.equalsIgnoreCase("Female")){
            System.out.println("\nEnter Valid Gender Value");
            return;
        }
        
        //Take Contact No
        System.out.print("\nEnter Contact No and must be atleast 11 characters long : ");
        String contact = takeInput.nextLine();        
        
        if(contact.equalsIgnoreCase("") || contact.length() < 11){
            System.out.println("\nEnter Contact No and must be atleast 11 characters long.");
            return;
        }
        
        //Take Age
        System.out.print("\nEnter Age [4 to 11] ");
        String age = takeInput.nextLine();
        
        while (age.equalsIgnoreCase("") || !BookingApp.isInputDigit(age) || Integer.parseInt(age) < 4 || Integer.parseInt(age) > 11)
        {
            System.out.println("\nEnter Valid Age Value [4 to 11]");

            System.out.print("\nEnter Age [4 to 11] : ");

            age = takeInput.nextLine();
        }
        
        //Take Grade Level
        System.out.print("\nEnter Grade Level [1 to 5] ");
        String gradeLevel = takeInput.nextLine();
        
        while (gradeLevel.equalsIgnoreCase("") || !BookingApp.isInputDigit(gradeLevel) || 
                (Integer.parseInt(gradeLevel) < 1 || Integer.parseInt(gradeLevel) > 5))
         {
             System.out.println("\nEnter Valid Grade Level Value [1 to 5]");

             System.out.print("\nEnter Grade Level [1 to 5] ");
             gradeLevel = takeInput.nextLine();
         }
        
         
        //Take Address
        System.out.print("\nEnter Address : ");
        String address = takeInput.nextLine();
         
        if(address.equalsIgnoreCase("") || contact.length() < 5){
            System.out.println("\nEnter Address and must be atleast 5 characters long.");
            return;
        }
        
        //Add Learner Details
        LearnerDetails obj = new LearnerDetails(username,gender,Integer.parseInt(age),contact,address,Integer.parseInt(gradeLevel));
        LearnerDetails.learnerDetails.add(obj);
        
        //Get Learner uniqueNo
        String uniqueNo = LearnerDetails.generateLearnerUniqueNo(username,contact);
        
        //Update Learner Unique No
        LearnerDetails.updateUniqueNo(uniqueNo,username);
        
        System.out.println("\nStudent Registered with the username : "+username+"\n");
    }
    
      
    //Check Timetable
    private static void checkTimetable(){
         int menu_option = BookingApp.checkTimetableMenu();
        
        /* Call Functions According to Menu Option Selected */
        while (menu_option != 9) {
            switch (menu_option)
            {
                case 1 -> {
                        LessonDetails.dayTimetable();
                        return; 
                    }
                case 2 -> {
                        LessonDetails.coachTimetable();
                        return; 
                    }
                case 3 -> {
                        LessonDetails.gradeLevelTimetable();
                        return; 
                    }
                 case 4 -> {
                    return; 
                }
                default -> System.out.println("\nYou have entered an Invalid Option.\n");
            }
            menu_option = BookingApp.checkTimetableMenu();
        }
    }
    
        
    //All Bookings
    public static void allBookings(){
       
        List<BookedClasses> bookedClasses = BookedClasses.getBookedClassesDetails();
        List<LessonDetails> lessonDetails = LessonDetails.getLessonDetails();
        List<LearnerDetails> learnerDetails = LearnerDetails.getLearnerDetails();
       
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-13s | %-30s | %-20s | %-25s | %-12s | %-30s | %-15s | %-20s |\n",
                "Booking Code", "Booking By", "Booking Status", "Booking Date",  "Lesson Code", "Lesson Name", "Grade Level","Lesson By");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        Set<String> uniqueBookingCode = new HashSet<>(); 
        for (BookedClasses obj : bookedClasses) {
            if (!uniqueBookingCode.contains(String.valueOf(obj.getBookingUniqueCode()))){
                uniqueBookingCode.add(String.valueOf(obj.getBookingUniqueCode()));
                
                //Booking By
                String bookingBy ="";
                int currentGrade = 0;
                for (LearnerDetails lObj : learnerDetails) {
                    if(lObj.getLearnerUniqueNo().equalsIgnoreCase(obj.getBookedByLearner())){
                        bookingBy = lObj.getUsername();
                        currentGrade = lObj.getCurrentGradeLevel();
                        break;
                    }
                }
                
                //Lesson Grade Level
                int lessonGrade = 0;
                for (LessonDetails lObj : lessonDetails) {
                    if(lObj.getCode().equalsIgnoreCase(obj.getLessonCode())){
                        lessonGrade = lObj.getGradeLevel();
                        break;
                    }
                }
                
                String learner = bookingBy +" (Grade : "+currentGrade+")";
                                
                System.out.printf("| %-13s | %-30s | %-20s | %-25s | %-12s | %-30s | %-15s | %-20s |\n",
                        obj.getBookingUniqueCode(), learner, obj.getBookingStatus(), obj.getBookingConfirmedOn(), obj.getLessonCode(),
                        obj.getLessonName(), lessonGrade, obj.getCoachName());
            }
        }
    }
    
    
    //Take lesson details to book
    public static void takeLessonToBook(){
        System.out.print("\nDo you want to book any of the above lessons (y/n): ");
        String choice = takeInput.nextLine();
         //Book Lesson
        if(choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("Yes")){
            
            //Take Username
            List<LearnerDetails> learnerDetails = LearnerDetails.getLearnerDetails();
            Set<String> uniqueLearnerCode = new HashSet<>(); 
                
            System.out.println("---------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-12s | %-20s | %-20s | %-12s | %-15s | %-10s |\n", "Option No", "Username", "Current Grade Level","Gender", 
                    "Phone Number", "Age");
            System.out.println("--------------------------------------------------------------------------------------------------------------");

            int i = 1;
            for (LearnerDetails obj : learnerDetails) {
                if(!uniqueLearnerCode.contains(String.valueOf(obj.getLearnerUniqueNo()))){
            
                   uniqueLearnerCode.add(String.valueOf(obj.getLearnerUniqueNo()));

                   System.out.printf("| %-12s | %-20s | %-20s | %-12s | %-15s | %-10d |\n", i, obj.getUsername(),
                           obj.getCurrentGradeLevel(),obj.getGender(), obj.getContactNo(), obj.getAge());
                   i++;
                }
            }

            System.out.print("\nInput Learner Option : ");
            String learner = takeInput.nextLine();
            
            if (!BookingApp.isInputDigit(learner) || Integer.parseInt(learner) > (i-1)) {
                System.out.println("Enter Valid Option to Select Learner");
                return;
            }           

            String learnerUniqueNo = "";
            String learnerName = "";
            int selectedOption = Integer.parseInt(learner);
            if (selectedOption > 0 && selectedOption <= learnerDetails.size()) {
                int index = 0;
                for (LearnerDetails learnerDetail : learnerDetails) {
                    if (index == selectedOption - 1) {
                        learnerUniqueNo = learnerDetail.getLearnerUniqueNo();
                        learnerName = learnerDetail.getUsername();
                        break;
                    }
                    index++;
                }
            }
                        
            //Username
            boolean isExist = LearnerDetails.isLearnerExist(learnerUniqueNo);
            if(!isExist){
                System.out.println("\nLearner does not exist");
                return;
            }

            //Lesson Code
            System.out.print("\nEnter Lesson Code : ");
            String lessonCode = takeInput.nextLine();
            boolean isSlotExist = LessonDetails.isSlotExist(lessonCode);
            if(!isSlotExist){
                System.out.println("\nLesson with given code does not exist");
                return;
            }
            
            //Lesson name
            String lessonName = LessonDetails.getLessonName(lessonCode);
            
            //Has vacant Seats
            int hasVacantSeats = LessonDetails.hasVacantSeats(lessonCode);
            if(hasVacantSeats <= 0){
                System.out.println("\nYou are trying to book lesson having no seat available.");
                return;
            }
            
            //Is Booking Same Lesson
            boolean isBookingSame = BookedClasses.isBookingSame(lessonCode,learnerUniqueNo);
            if(isBookingSame){
                System.out.println("\nYou are trying to book same lesson again.");
                return;
            }
            
            //Is Booking Higher / Lower Grade Level
            boolean isNotBookingCurrentGradeLevel = LessonDetails.isNotBookingCurrentGradeLevel(lessonCode,learnerUniqueNo);
            int currentGrade = LearnerDetails.getCurrentGradeLevelOfLearner(learnerUniqueNo);
            if(isNotBookingCurrentGradeLevel){
                if((currentGrade+1) < 5){
                    System.out.println("\nYou have to book lesson with grade "+currentGrade+" or "+(currentGrade+1));
                }else{
                    System.out.println("\nYou have to book lesson with grade "+currentGrade);
                }
                return;
            }
            
            //Coach Name
            String coachUniqueCode = CoachDetails.getCoachName(lessonCode);
            
            //Booking no. Generated
            Random rand = new Random();
            int bookingCode = rand.nextInt(90000) + 10000;
                     
            //Current datetime
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY 'at' h:mm:ss a");
            String formattedDateTime = now.format(formatter);
            
            //Add Booking
            BookedClasses  obj = new BookedClasses(bookingCode,learnerUniqueNo, lessonCode,lessonName,coachUniqueCode, BOOK, formattedDateTime);
            BookedClasses.bookedClassesDetails.add(obj);
            
            //Update vacant space for lesson
            LessonDetails.decreaseVacantSeatSpace(lessonCode);
                        
            System.out.println("\n\nBooking Confirmed for Lesson Code '"+lessonCode+"' with Lesson Name '"+lessonName+"' by Learner '"+learnerName+"'");
            BookedClasses.viewUpdatedBookingDetails(bookingCode);
            return;
        }
    }
    
    
    //Update Lesson
    public static void updateLesson(){
        
        System.out.print("\nInput Your Booking Code to Update Lesson : ");
        String bookingCode = takeInput.nextLine();
        
        if(!BookingApp.isInputDigit(bookingCode) || bookingCode.equalsIgnoreCase("")){
            System.out.println("Enter Valid Booking Code");
            return;
        }
        
        boolean isBookingCodeExist = BookedClasses.isBookingCodeExist(Integer.parseInt(bookingCode));
        if(!isBookingCodeExist){
             System.out.println("\nBooking Code entered by you is not correct");
             return;
        }
         List<BookedClasses> bookedClasses = BookedClasses.getBookedClassesDetails();

         for(BookedClasses obj : bookedClasses){
            if(obj.getBookingUniqueCode() == Integer.parseInt(bookingCode)){
                
                if(obj.getBookingStatus().equalsIgnoreCase(ATTEND) || obj.getBookingStatus().equalsIgnoreCase(CANCEL)){
                    System.out.println("\nBooking has been cancelled or attended");
                    return;
                }
                break;
            }
        }
         
        LessonDetails.UPDATE_LESSON = 1;
        BookedClasses.TEMP_BOOKING_CODE = Integer.parseInt(bookingCode);

        //Check Yimetable
        checkTimetable();
    }
    
    
     //Cancel Lesson
    public static void cancelLesson(){
         
        System.out.print("\nInput Your Booking Code to Cancel Lesson : ");
        String bookingCode = takeInput.nextLine();
        
        if(!BookingApp.isInputDigit(bookingCode) || bookingCode.equalsIgnoreCase("")){
            System.out.println("Enter Valid Booking Code");
            return;
        }
         boolean isBookingCodeExist = BookedClasses.isBookingCodeExist(Integer.parseInt(bookingCode));
        if(!isBookingCodeExist){
             System.out.println("\nBooking Code entered by you is not correct");
             return;
        }
        List<BookedClasses> bookedClasses = BookedClasses.getBookedClassesDetails();
        List<LearnerDetails> learnerDetails = LearnerDetails.getLearnerDetails();
        
        //Update Booking Details
        String existingLessonCode = "";
        String lessonName = "";
        String learnerName = "";
        for(BookedClasses obj : bookedClasses){
            if(obj.getBookingUniqueCode() == Integer.parseInt(bookingCode)){
                
                if(obj.getBookingStatus().equalsIgnoreCase(ATTEND) || obj.getBookingStatus().equalsIgnoreCase(CANCEL)){
                    System.out.println("Booking has been cancelled or attended");
                    return;
                }
                
                obj.setBookingStatus(CANCEL);
                existingLessonCode = obj.getLessonCode();
                lessonName = obj.getLessonName();
                
                for (LearnerDetails lObj : learnerDetails) {
                    if (lObj.getLearnerUniqueNo().equalsIgnoreCase(obj.getBookedByLearner())) {
                        learnerName = lObj.getUsername();
                    }
                }
                break;
            }
        }
        
        //Increase Seats after cancelling lesson
        LessonDetails.increaseVacantSeatSpace(existingLessonCode);

        System.out.println("\n\nBooking Cancelled with Booking Code '"+bookingCode+"' having Lesson Name '"+lessonName+"' by Learner '"+learnerName+"'");
        BookedClasses.viewUpdatedBookingDetails(Integer.parseInt(bookingCode));
        return;
       
    }
    
    
    //Take lesson details to update
    public static void takeLessonToUpdateBooking(){
            
        int bookingCode = BookedClasses.TEMP_BOOKING_CODE;
        String learnerUniqueNo = "";
        List<BookedClasses> bookedClasses = BookedClasses.getBookedClassesDetails();
        List<LearnerDetails> learnerDetails = LearnerDetails.getLearnerDetails();
        
        String existingLessonCode = "";
        for(BookedClasses obj : bookedClasses){
            if(obj.getBookingUniqueCode() == bookingCode){
                learnerUniqueNo = obj.getBookedByLearner();
                existingLessonCode = obj.getLessonCode();
                break;
            }
        }
        
        //Lesson Code
        System.out.print("\nEnter Lesson Code to Update : ");
        String lessonCode = takeInput.nextLine();
        boolean isSlotExist = LessonDetails.isSlotExist(lessonCode);
        if(!isSlotExist){
            System.out.println("\nLesson with given code does not exist");
            return;
        }

        //Lesson name
        String lessonName = LessonDetails.getLessonName(lessonCode);
        
        
        //Coach Name
        String coachUniqueCode = CoachDetails.getCoachName(lessonCode);
            

        //Has vacant Seats
        int hasVacantSeats = LessonDetails.hasVacantSeats(lessonCode);
        if(hasVacantSeats <= 0){
            System.out.println("\nYou are trying to book lesson having no seat available.");
            return;
        }

        //Is Booking Same Lesson
        boolean isBookingSame = BookedClasses.isBookingSame(lessonCode,learnerUniqueNo);
        if(isBookingSame){
            System.out.println("\nYou are trying to book same lesson again.");
            return;
        }

        //Is Booking Higher / Lower Grade Level
        boolean isNotBookingCurrentGradeLevel = LessonDetails.isNotBookingCurrentGradeLevel(lessonCode,learnerUniqueNo);
        int currentGrade = LearnerDetails.getCurrentGradeLevelOfLearner(learnerUniqueNo);
        if(isNotBookingCurrentGradeLevel){
            if((currentGrade+1) < 5){
                System.out.println("\nYou have to book lesson with grade "+currentGrade+" or "+(currentGrade+1));
            }else{
                System.out.println("\nYou have to book lesson with grade "+currentGrade);
            }
            return;
        }
        
        //Update booking Details
        String learnerName = "";
         for(BookedClasses obj : bookedClasses){
            if(obj.getBookingUniqueCode() == bookingCode){
                obj.setLessonCode(lessonCode);
                obj.setLessonName(lessonName);
                obj.setBookingStatus(CHANGE);
                obj.setCoachName(coachUniqueCode);
                        
                for (LearnerDetails lObj : learnerDetails) {
                    if (lObj.getLearnerUniqueNo().equalsIgnoreCase(obj.getBookedByLearner())) {
                        learnerName = lObj.getUsername();
                    }
                }
                break;
            }
        }

        //Update vacant space for lesson
        LessonDetails.decreaseVacantSeatSpace(lessonCode);
        
        //Increase Seats after changing lesson
        LessonDetails.increaseVacantSeatSpace(existingLessonCode);

        System.out.println("\n\nBooking Changed with Lesson Code '"+lessonCode+"' with Lesson Name '"+lessonName+"' by Learner '"+learnerName+"'");
        BookedClasses.viewUpdatedBookingDetails(bookingCode);
        
        LessonDetails.UPDATE_LESSON = 0;
        BookedClasses.TEMP_BOOKING_CODE = 0;

        return;
    }    
    
}
