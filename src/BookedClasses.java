
import java.util.ArrayList;
import java.util.List;


public class BookedClasses {
    
    private int bookingUniqueCode;
    private String bookedByLearner;
    private String lessonCode;
    private String lessonName;
    private String coachName;
    private String bookingStatus;
    private String bookingConfirmedOn;
    
    public static int TEMP_BOOKING_CODE = 0;

    public static ArrayList <BookedClasses> bookedClassesDetails = new ArrayList<>();

    public BookedClasses(int bookingUniqueCode, String bookedByLearner, String lessonCode, String lessonName, String coachName, String bookingStatus, String bookingConfirmedOn) {
        this.bookingUniqueCode = bookingUniqueCode;
        this.bookedByLearner = bookedByLearner;
        this.lessonCode = lessonCode;
        this.lessonName = lessonName;
        this.coachName = coachName;
        this.bookingStatus = bookingStatus;
        this.bookingConfirmedOn = bookingConfirmedOn;
    }

    public int getBookingUniqueCode() {
        return bookingUniqueCode;
    }

    public String getBookedByLearner() {
        return bookedByLearner;
    }

    public String getLessonCode() {
        return lessonCode;
    }

    public String getLessonName() {
        return lessonName;
    }

    public String getCoachName() {
        return coachName;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public String getBookingConfirmedOn() {
        return bookingConfirmedOn;
    }

    public static ArrayList<BookedClasses> getBookedClassesDetails() {
        return bookedClassesDetails;
    }

    public void setLessonCode(String lessonCode) {
        this.lessonCode = lessonCode;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }    

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    
    // View Details of booking
    public static void viewUpdatedBookingDetails(int bookingCode) {
        List<BookedClasses> bookedClasses = BookedClasses.getBookedClassesDetails();
        List<LessonDetails> lessonDetails = LessonDetails.getLessonDetails();
        List<CoachDetails> coachDetails = CoachDetails.getCoachDetails();
        List<LearnerDetails> learnerDetails = LearnerDetails.getLearnerDetails();
       

        for (int i = 0; i < bookedClasses.size(); i++) {
            if (bookingCode == bookedClasses.get(i).getBookingUniqueCode()) {
                // Retrieve Lesson Details for the Booked Lesson
                String day = "";
                String lessonOn = "";
                String lessonTime = "";
                String lesson = "";
                String coach = "";
                String coachContact = "";
                String coachGender = "";
                int gradeLevel = 0;
                int coachAge = 0;

                for (int j = 0; j < lessonDetails.size(); j++) {
                    if (lessonDetails.get(j).getCode().equalsIgnoreCase(bookedClasses.get(i).getLessonCode())) {
                        day = lessonDetails.get(j).getWeekDay();
                        lessonOn = lessonDetails.get(j).getDate();
                        lessonTime = lessonDetails.get(j).getTime();
                        lesson = lessonDetails.get(j).getName();
                        gradeLevel = lessonDetails.get(j).getGradeLevel();
                        
                        for(CoachDetails cObj : coachDetails){
                            if(cObj.getCoachUniqueNo().equalsIgnoreCase(lessonDetails.get(j).getTeachBy())){
                                coach = cObj.getUsername();
                                coachContact = cObj.getPhoneNo();
                                coachGender = cObj.getGender();
                                coachAge = cObj.getAge();
                            }
                        }
                    }
                }
                
                String learnerUniqueCode = "";
                String learnerName = "";
                int learnerGrade = 0;
                int learnerAge = 0;
                String learnerAddress = "";
                String learnerContact = "";
                String learnerGender = "";
                for(LearnerDetails lObj : learnerDetails){
                    if(lObj.getLearnerUniqueNo().equalsIgnoreCase(bookedClasses.get(i).getBookedByLearner())){
                        learnerUniqueCode = lObj.getLearnerUniqueNo();
                        learnerName = lObj.getUsername();
                        learnerGrade = lObj.getCurrentGradeLevel();
                        learnerAge = lObj.getAge();
                        learnerAddress = lObj.getAddress();
                        learnerContact = lObj.getContactNo();
                        learnerGender = lObj.getGender();
                        break;
                    }
                }
                                
                System.out.println("\n----------------------------------");
                System.out.println("Booking Details as follows : ");
                System.out.println("----------------------------------");
                System.out.printf("Booking Code: %-13s\n", bookingCode);
                System.out.printf("Status: %-12s\n", bookedClasses.get(i).getBookingStatus());
                System.out.printf("Confirmed On: %-20s\n", bookedClasses.get(i).getBookingConfirmedOn());
                
                System.out.println("\n----------------------------------");
                System.out.println("Lesson Details as follows :");
                System.out.println("----------------------------------");
                System.out.printf("Lesson Code: %-12s\n", bookedClasses.get(i).getLessonCode());
                System.out.printf("Name: %-30s\n", lesson);
                System.out.printf("Date: %-15s\n", lessonOn);
                System.out.printf("Time: %-15s\n", lessonTime);
                System.out.printf("Day: %-15s\n", day);
                System.out.printf("Grade Level: %-12s\n", gradeLevel);
                System.out.println("\n----------------------------------");
                System.out.println("Coach Details as follows :");
                System.out.println("----------------------------------");
                System.out.printf("Unqiue Code: %-15s\n", bookedClasses.get(i).getBookingUniqueCode());
                System.out.printf("Name: %-15s\n", coach);
                System.out.printf("Contact: %-15s\n", coachContact);
                System.out.printf("Age: %-10s\n", coachAge);
                System.out.printf("Gender: %-10s\n", coachGender);
                System.out.println("\n----------------------------------");
                System.out.println("Learner Details as follows :");
                System.out.println("----------------------------------");
                System.out.printf("Unique Code: %-15s\n", learnerUniqueCode);
                System.out.printf("Name: %-15s\n", learnerName);
                System.out.printf("Contact: %-15s\n", learnerContact);
                System.out.printf("Age: %-10s\n", learnerAge);
                System.out.printf("Gender: %-10s\n", learnerGender);
                System.out.printf("Current Grade Level: %-10s\n", learnerGrade);
                System.out.printf("Address: %-10s\n", learnerAddress);

            }
        }
    }

    
    //Is Booking Same class
    public static boolean isBookingSame(String lessonCode, String learnerUniqueNo){
        boolean isSame = false;
        List<BookedClasses> bookedClasses = BookedClasses.getBookedClassesDetails();
        for(BookedClasses obj : bookedClasses){
            if(obj.getBookedByLearner().equalsIgnoreCase(learnerUniqueNo) && obj.getLessonCode().equalsIgnoreCase(lessonCode)){
                return true;
            }
        }
        return isSame;
    }
    
   
    
    //Is Booking Code Exist
    public static boolean isBookingCodeExist(int bookingCode){
        boolean isExist = false;
        List<BookedClasses> bookedClasses = BookedClasses.getBookedClassesDetails();
        for(BookedClasses obj : bookedClasses){
            if(obj.getBookingUniqueCode() == bookingCode){
                return true;
            }
        }
        return isExist;
    }
    
   
    
}
