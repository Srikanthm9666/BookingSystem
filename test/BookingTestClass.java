

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.junit.Test;

public class BookingTestClass {
    
    public static int bookingCode = 0;
   
    @Test
    public void testGradeLevelTimetable() {
        
        int gradeNo = 4;
        System.out.println("\n\nTest Case to Filter Timetable By Grade Level : "+gradeNo);
        
        List<LessonDetails> lessonDetails = LessonDetails.getLessonDetails();
        List<CoachDetails> coachDetails = CoachDetails.getCoachDetails();
        
        System.out.println("\n--------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-10s | %-30s | %-10s | %-10s | %-12s | %-20s | %-15s | %-15s | %-12s |\n",
                "Code", "Name", "Weekday", "Time", "Date", "Teach By", "No. of Hours", "Vacant Seat", "Grade Level");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        Set<String> uniqueClassCode = new HashSet<>(); 
        for (LessonDetails obj : lessonDetails) {
            if (!uniqueClassCode.contains(obj.getCode()) && obj.getGradeLevel() == gradeNo){
                uniqueClassCode.add(obj.getCode());

                String coachName = "";
                //Coach Name
               for(CoachDetails cobj : coachDetails){
                    if(obj.getTeachBy().equalsIgnoreCase(cobj.getCoachUniqueNo())){
                        coachName = cobj.getUsername();
                        break;
                    }
               }
                System.out.printf("| %-10s | %-30s | %-10s | %-10s | %-12s | %-20s | %-15s | %-15s | %-12d |\n",
                        obj.getCode(), obj.getName(), obj.getWeekDay(), obj.getTime(), obj.getDate(),
                        coachName, obj.getNoOfHours(), (obj.getIsVacantSeat() == 1 ? "Available" : "Not Available"), obj.getGradeLevel());
            }
        }
    }
    
   
    @Test
    public void testBookClass() {
        System.out.println("\n\nTest Case to Book a class :");
        String learnerUniqueNo = "Jor_456";
        String lessonCode = "114";
         //Learner 
         String learnerName ="";
         List<LearnerDetails> learnerDetails = LearnerDetails.getLearnerDetails();
         for (LearnerDetails learnerDetail : learnerDetails) {
             if (learnerDetail.getLearnerUniqueNo().equalsIgnoreCase(learnerUniqueNo)) {
                 learnerName = learnerDetail.getUsername();
                 break;
             }
         }
         //Lesson name
         String lessonName = LessonDetails.getLessonName(lessonCode);
         //Coach Name
         String coachUniqueCode = CoachDetails.getCoachName(lessonCode);
         //Booking no. Generated
         Random rand = new Random();
         bookingCode = rand.nextInt(90000) + 10000;
         //Current datetime
         LocalDateTime now = LocalDateTime.now();
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY 'at' h:mm:ss a");
         String formattedDateTime = now.format(formatter);
         //Add Booking
         BookedClasses  obj = new BookedClasses(bookingCode,learnerUniqueNo, lessonCode,lessonName,coachUniqueCode, Functions.BOOK,
                 formattedDateTime);
         BookedClasses.bookedClassesDetails.add(obj);
         //Update vacant space for lesson
         LessonDetails.decreaseVacantSeatSpace(lessonCode);
         System.out.println("\n\nBooking Confirmed for Lesson Code '"+lessonCode+"' with Lesson Name '"+lessonName+"' by Learner '"+learnerName+"'");
         BookedClasses.viewUpdatedBookingDetails(bookingCode);
    }
    
   
    @Test
    public void testChangeClass() {
        System.out.println("\n\nTest Case to Change a class :");
        List<BookedClasses> bookedClasses = BookedClasses.getBookedClassesDetails();
        List<LearnerDetails> learnerDetails = LearnerDetails.getLearnerDetails();
        String existingLessonCode = "";
        for(BookedClasses obj : bookedClasses){
            if(obj.getBookingUniqueCode() == bookingCode){
                existingLessonCode = obj.getLessonCode();
                break;
            }
        }
        //Lesson Code
        String newlessonCode = "136";
        //Lesson name
        String lessonName = LessonDetails.getLessonName(newlessonCode);
        //Coach Name
        String coachUniqueCode = CoachDetails.getCoachName(newlessonCode);
        //Update booking Details
        String learnerName = "";
         for(BookedClasses obj : bookedClasses){
            if(obj.getBookingUniqueCode() == bookingCode){
                obj.setLessonCode(newlessonCode);
                obj.setLessonName(lessonName);
                obj.setBookingStatus(Functions.CHANGE);
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
        LessonDetails.decreaseVacantSeatSpace(newlessonCode);
        //Increase Seats after changing lesson
        LessonDetails.increaseVacantSeatSpace(existingLessonCode);
        System.out.println("\n\nBooking Changed with Lesson Code '"+newlessonCode+"' with Lesson Name '"+lessonName+"' by Learner '"+learnerName+"'");
        BookedClasses.viewUpdatedBookingDetails(bookingCode);
    }
    
   
    @Test
    public void testCancelClass() {
        System.out.println("\n\nTest Case to Cancel a class :");
        List<BookedClasses> bookedClasses = BookedClasses.getBookedClassesDetails();
        List<LearnerDetails> learnerDetails = LearnerDetails.getLearnerDetails();
        //Update Booking Details
        String existingLessonCode = "";
        String lessonName = "";
        String learnerName = "";
        for(BookedClasses obj : bookedClasses){
            if(obj.getBookingUniqueCode() == bookingCode){
                
                if(obj.getBookingStatus().equalsIgnoreCase(Functions.ATTEND) || obj.getBookingStatus().equalsIgnoreCase(Functions.CANCEL)){
                    System.out.println("Booking has been cancelled or attended");
                    return;
                }
                
                obj.setBookingStatus(Functions.CANCEL);
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
        BookedClasses.viewUpdatedBookingDetails(bookingCode);
    }
    
   
    @Test
    public void testToAvoidSameBooking() {
        System.out.println("\n\nTest Case to Avoid User to book class that is lower or higher than his/her current grade level :");
        String learnerUniqueNo = "Jor_456";
        String lessonCode = "144";
              
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

         //Is Booking Same Lesson
         boolean isBookingSame = BookedClasses.isBookingSame(lessonCode,learnerUniqueNo);
         if(isBookingSame){
             System.out.println("\nYou are trying to book same lesson again.");
         }        
    }
    
    
    
}
