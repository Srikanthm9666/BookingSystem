
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class LessonDetails {
    
    
    private String code;
    private String name;
    private String weekDay;
    private String time;
    private String date;
    private String teachBy;
    private String noOfHours;
    private int vacantSeat;
    private int gradeLevel;
    
    public static int UPDATE_LESSON = 0;

    public static ArrayList <LessonDetails> lessonDetails = new ArrayList<>();

    public LessonDetails(String code, String name, String weekDay, String time, String date, String teachBy, String noOfHours, int vacantSeat, int gradeLevel) {
        this.code = code;
        this.name = name;
        this.weekDay = weekDay;
        this.time = time;
        this.date = date;
        this.teachBy = teachBy;
        this.noOfHours = noOfHours;
        this.vacantSeat = vacantSeat;
        this.gradeLevel = gradeLevel;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public int getGradeLevel() {
        return gradeLevel;
    }
    
    public String getDate() {
        return date;
    }

    public String getTeachBy() {
        return teachBy;
    }

    public String getNoOfHours() {
        return noOfHours;
    }

    public int getIsVacantSeat() {
        return vacantSeat;
    }

    public void setIsVacantSeat(int vacantSeat) {
        this.vacantSeat = vacantSeat;
    }

    public static ArrayList<LessonDetails> getLessonDetails() {
        
        LessonDetails o1 = new LessonDetails("101","Water Entry and Exit","Mon","4-5pm","6-May-2024","anth_201","1 hr.",4,1);
        LessonDetails o2 = new LessonDetails("102","Safety Awareness","Mon","5-6pm","6-May-2024","henr_202","1 hr.",4,2);
        LessonDetails o3 = new LessonDetails("103","Additional Skills","Mon","6-7pm","6-May-2024","rich_203","1 hr.",4,3);
        
        LessonDetails o4 = new LessonDetails("104","General Water Safety","Wed","4-5pm","8-May-2024","adel_205","1 hr.",4,4);
        LessonDetails o5 = new LessonDetails("105","Jellyfish and tuck floats","Wed","5-6pm","8-May-2024","anth_201","1 hr.",4,5);
        LessonDetails o6 = new LessonDetails("106","Breath Control and Submerging","Wed","6-7pm","8-May-2024","lewi_204","1 hr.",4,1);
        
        LessonDetails o7 = new LessonDetails("107","Bobbing in the water","Fri","4-5pm","10-May-2024","henr_202","1 hr.",4,2);
        LessonDetails o8 = new LessonDetails("108","Front and back glides","Fri","5-6pm","10-May-2024","rich_203","1 hr.",4,4);
        LessonDetails o9 = new LessonDetails("109","Coordinating front crawl","Fri","6-7pm","10-May-2024","adel_205","1 hr.",4,1);
        
        LessonDetails o10 = new LessonDetails("110","Swimming underwater","Sat","2-3pm","11-May-2024","lewi_204","1 hr.",4,3);
        LessonDetails o11 = new LessonDetails("111","Performing flip turns","Sat","3-4pm","11-May-2024","anth_201","1 hr.",4,5);
               
        
        
        LessonDetails o12 = new LessonDetails("112","Water Entry and Exit","Mon","4-5pm","6-May-2024","anth_201","1 hr.",4,1);
        LessonDetails o13 = new LessonDetails("113","Safety Awareness","Mon","5-6pm","6-May-2024","henr_202","1 hr.",4,2);
        LessonDetails o14 = new LessonDetails("114","Additional Skills","Mon","6-7pm","6-May-2024","rich_203","1 hr.",4,3);
        
        LessonDetails o15 = new LessonDetails("115","General Water Safety","Wed","4-5pm","8-May-2024","adel_205","1 hr.",4,4);
        LessonDetails o16 = new LessonDetails("116","Jellyfish and tuck floats","Wed","5-6pm","8-May-2024","anth_201","1 hr.",4,5);
        LessonDetails o17 = new LessonDetails("117","Breath Control and Submerging","Wed","6-7pm","8-May-2024","lewi_204","1 hr.",4,1);
        
        LessonDetails o18 = new LessonDetails("118","Bobbing in the water","Fri","4-5pm","10-May-2024","henr_202","1 hr.",4,2);
        LessonDetails o19 = new LessonDetails("119","Front and back glides","Fri","5-6pm","10-May-2024","rich_203","1 hr.",4,4);
        LessonDetails o20 = new LessonDetails("120","Coordinating front crawl","Fri","6-7pm","10-May-2024","adel_205","1 hr.",4,1);
        
        LessonDetails o21 = new LessonDetails("121","Swimming underwater","Sat","2-3pm","11-May-2024","lewi_204","1 hr.",4,3);
        LessonDetails o22 = new LessonDetails("122","Performing flip turns","Sat","3-4pm","11-May-2024","anth_201","1 hr.",4,5);
               
        
        LessonDetails o23 = new LessonDetails("123","Water Entry and Exit","Mon","4-5pm","6-May-2024","anth_201","1 hr.",4,1);
        LessonDetails o24 = new LessonDetails("124","Safety Awareness","Mon","5-6pm","6-May-2024","henr_202","1 hr.",4,2);
        LessonDetails o25 = new LessonDetails("125","Additional Skills","Mon","6-7pm","6-May-2024","rich_203","1 hr.",4,3);
        
        LessonDetails o26 = new LessonDetails("126","General Water Safety","Wed","4-5pm","8-May-2024","adel_205","1 hr.",4,4);
        LessonDetails o27 = new LessonDetails("127","Jellyfish and tuck floats","Wed","5-6pm","8-May-2024","anth_201","1 hr.",4,5);
        LessonDetails o28 = new LessonDetails("128","Breath Control and Submerging","Wed","6-7pm","8-May-2024","lewi_204","1 hr.",4,1);
        
        LessonDetails o29 = new LessonDetails("129","Bobbing in the water","Fri","4-5pm","10-May-2024","henr_202","1 hr.",4,2);
        LessonDetails o30 = new LessonDetails("130","Front and back glides","Fri","5-6pm","10-May-2024","rich_203","1 hr.",4,4);
        LessonDetails o31 = new LessonDetails("131","Coordinating front crawl","Fri","6-7pm","10-May-2024","adel_205","1 hr.",4,1);
        
        LessonDetails o32 = new LessonDetails("132","Swimming underwater","Sat","2-3pm","11-May-2024","lewi_204","1 hr.",4,3);
        LessonDetails o33 = new LessonDetails("133","Performing flip turns","Sat","3-4pm","11-May-2024","anth_201","1 hr.",4,5);
               
        
        
        LessonDetails o34 = new LessonDetails("134","Water Entry and Exit","Mon","4-5pm","6-May-2024","anth_201","1 hr.",4,1);
        LessonDetails o35 = new LessonDetails("135","Safety Awareness","Mon","5-6pm","6-May-2024","henr_202","1 hr.",4,2);
        LessonDetails o36 = new LessonDetails("136","Additional Skills","Mon","6-7pm","6-May-2024","rich_203","1 hr.",4,3);
        
        LessonDetails o37 = new LessonDetails("137","General Water Safety","Wed","4-5pm","8-May-2024","adel_205","1 hr.",4,4);
        LessonDetails o38 = new LessonDetails("138","Jellyfish and tuck floats","Wed","5-6pm","8-May-2024","anth_201","1 hr.",4,5);
        LessonDetails o39 = new LessonDetails("139","Breath Control and Submerging","Wed","6-7pm","8-May-2024","lewi_204","1 hr.",4,1);
        
        LessonDetails o40 = new LessonDetails("140","Bobbing in the water","Fri","4-5pm","10-May-2024","henr_202","1 hr.",4,2);
        LessonDetails o41 = new LessonDetails("141","Front and back glides","Fri","5-6pm","10-May-2024","rich_203","1 hr.",4,4);
        LessonDetails o42 = new LessonDetails("142","Coordinating front crawl","Fri","6-7pm","10-May-2024","adel_205","1 hr.",4,1);
        
        LessonDetails o43 = new LessonDetails("143","Swimming underwater","Sat","2-3pm","11-May-2024","lewi_204","1 hr.",4,3);
        LessonDetails o44 = new LessonDetails("144","Performing flip turns","Sat","3-4pm","11-May-2024","anth_201","1 hr.",4,5);
         
        LessonDetails.lessonDetails.add(o1);
        LessonDetails.lessonDetails.add(o2);
        LessonDetails.lessonDetails.add(o3);
        LessonDetails.lessonDetails.add(o4);
        LessonDetails.lessonDetails.add(o5);
        LessonDetails.lessonDetails.add(o6);
        LessonDetails.lessonDetails.add(o7);
        LessonDetails.lessonDetails.add(o8);
        LessonDetails.lessonDetails.add(o9);
        LessonDetails.lessonDetails.add(o10);
        LessonDetails.lessonDetails.add(o11);
        LessonDetails.lessonDetails.add(o12);
        LessonDetails.lessonDetails.add(o13);
        LessonDetails.lessonDetails.add(o14);
        LessonDetails.lessonDetails.add(o15);
        LessonDetails.lessonDetails.add(o16);
        LessonDetails.lessonDetails.add(o17);
        LessonDetails.lessonDetails.add(o18);
        LessonDetails.lessonDetails.add(o19);
        LessonDetails.lessonDetails.add(o20);
        LessonDetails.lessonDetails.add(o21);
        LessonDetails.lessonDetails.add(o22);
        LessonDetails.lessonDetails.add(o23);
        LessonDetails.lessonDetails.add(o24);
        LessonDetails.lessonDetails.add(o25);
        LessonDetails.lessonDetails.add(o26);
        LessonDetails.lessonDetails.add(o27);
        LessonDetails.lessonDetails.add(o28);
        LessonDetails.lessonDetails.add(o29);
        LessonDetails.lessonDetails.add(o30);
        LessonDetails.lessonDetails.add(o31);
        LessonDetails.lessonDetails.add(o32);
        LessonDetails.lessonDetails.add(o33);
        LessonDetails.lessonDetails.add(o34);
        LessonDetails.lessonDetails.add(o35);
        LessonDetails.lessonDetails.add(o36);
        LessonDetails.lessonDetails.add(o37);
        LessonDetails.lessonDetails.add(o38);
        LessonDetails.lessonDetails.add(o39);
        LessonDetails.lessonDetails.add(o40);
        LessonDetails.lessonDetails.add(o41);
        LessonDetails.lessonDetails.add(o42);
        LessonDetails.lessonDetails.add(o43);
        LessonDetails.lessonDetails.add(o44);
                
        return lessonDetails;
    }

    
    
    //Day Timetable
    public static void dayTimetable(){
        System.out.print("Input Day : ");
        String day = Functions.takeInput.nextLine();
        
        if(day.equalsIgnoreCase("Monday")){
            day = "Mon";
        }else if(day.equalsIgnoreCase("Wednesday")){
            day = "Wed";
        }else if(day.equalsIgnoreCase("Friday")){
            day = "Fri";
        } if(day.equalsIgnoreCase("Saturday")){
            day = "Sat";
        }
       
        List<LessonDetails> lessonDetails = LessonDetails.getLessonDetails();
        List<CoachDetails> coachDetails = CoachDetails.getCoachDetails();
        
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-10s | %-30s | %-10s | %-10s | %-12s | %-20s | %-15s | %-15s | %-12s |\n",
                "Code", "Name", "Weekday", "Time", "Date", "Teach By", "No. of Hours", "Vacant Seat", "Grade Level");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        
        Set<String> uniqueClassCode = new HashSet<>(); 
        for (LessonDetails obj : lessonDetails) {
            if (!uniqueClassCode.contains(obj.getCode()) && obj.getWeekDay().equalsIgnoreCase(day)){
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
                        coachName, obj.getNoOfHours(), obj.getIsVacantSeat(), obj.getGradeLevel());
            }
        }
        if(UPDATE_LESSON == 0){
            Functions.takeLessonToBook();
        }else{
            Functions.takeLessonToUpdateBooking();
        }
    }
    
    //Coach Timetable
    public static void coachTimetable(){
       
        List<LessonDetails> lessonDetails = LessonDetails.getLessonDetails();
        List<CoachDetails> coachDetails = CoachDetails.getCoachDetails();
        Set<String> uniqueClassCode = new HashSet<>(); 
        Set<CoachDetails> uniqueCoachSet = new HashSet<>(); 

        uniqueCoachSet.clear();

        uniqueCoachSet.addAll(coachDetails);

        System.out.println("-------------------------------------------------------------------------------------");
        System.out.printf("| %-12s | %-20s | %-12s | %-15s | %-10s |\n", "Option No", "Username", "Gender", "Phone Number", "Age");
        System.out.println("-------------------------------------------------------------------------------------");

        int i = 1;        
        for (CoachDetails obj : uniqueCoachSet) {
            System.out.printf("| %-12s | %-20s | %-12s | %-15s | %-10d |\n", i, obj.getUsername(), obj.getGender(), obj.getPhoneNo(), obj.getAge());
            i++;
        }

        System.out.print("\nInput Coach Option : ");
        String coach = Functions.takeInput.nextLine();

        if (!BookingApp.isInputDigit(coach) || Integer.parseInt(coach) > (i-1)) {
            System.out.println("Enter Valid Option to Select Coach");
            return;
        }

        String coachUniqueNo = "";
        int selectedOption = Integer.parseInt(coach);
        if (selectedOption > 0 && selectedOption <= uniqueCoachSet.size()) {
            int index = 0;
            for (CoachDetails coachDetail : uniqueCoachSet) {
                if (index == selectedOption - 1) {
                    coachUniqueNo = coachDetail.getCoachUniqueNo();
                    break;
                }
                index++;
            }
        }
        
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-10s | %-30s | %-10s | %-10s | %-12s | %-20s | %-15s | %-15s | %-12s |\n",
                "Code", "Name", "Weekday", "Time", "Date", "Teach By", "No. of Hours", "Vacant Seat", "Grade Level");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        for (LessonDetails obj : lessonDetails) {
             if (!uniqueClassCode.contains(obj.getCode()) && obj.getTeachBy().equalsIgnoreCase(coachUniqueNo)){
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
                        coachName, obj.getNoOfHours(), obj.getIsVacantSeat(), obj.getGradeLevel());
            }
        }
        if(UPDATE_LESSON == 0){
            Functions.takeLessonToBook();
        }else{
            Functions.takeLessonToUpdateBooking();
        }

    }
        
    //Grade Level Timetable
    public static void gradeLevelTimetable(){
        System.out.print("\nInput Grade Level : ");
        String gradeNo = Functions.takeInput.nextLine();
        
        if(!BookingApp.isInputDigit(gradeNo) || Integer.parseInt(gradeNo) < 1 || Integer.parseInt(gradeNo) > 5){
            System.out.println("Enter Valid Grade Level between 1 to 5 ");
            return;
        }
        
        List<LessonDetails> lessonDetails = LessonDetails.getLessonDetails();
        List<CoachDetails> coachDetails = CoachDetails.getCoachDetails();
        
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-10s | %-30s | %-10s | %-10s | %-12s | %-20s | %-15s | %-15s | %-12s |\n",
                "Code", "Name", "Weekday", "Time", "Date", "Teach By", "No. of Hours", "Vacant Seat", "Grade Level");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        Set<String> uniqueClassCode = new HashSet<>(); 
        for (LessonDetails obj : lessonDetails) {
            if (!uniqueClassCode.contains(obj.getCode()) && obj.getGradeLevel() == Integer.parseInt(gradeNo)){
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
        if(UPDATE_LESSON == 0){
            Functions.takeLessonToBook();
        }else{
            Functions.takeLessonToUpdateBooking();
        }

    }
    
    
    
    //get Lesson Name
    public static String getLessonName(String code){
        String name = "";
        
        List<LessonDetails> lessonDetails = LessonDetails.getLessonDetails();
         for(LessonDetails obj : lessonDetails){
            if(obj.getCode().equalsIgnoreCase(code)){
                name = obj.getName();
                break;
            }
        }
        return name;
    }
        
    
    
    //Check Slot exists or not
    public static boolean isSlotExist(String code){
        boolean isExist = false;
        
        List<LessonDetails> lessonDetails = LessonDetails.getLessonDetails();
         for(LessonDetails obj : lessonDetails){
            if(obj.getCode().equalsIgnoreCase(code)){
                isExist = true;
                break;
            }
        }
        return isExist;
    }
        
    
    //Update Vacant seats 
    public static void increaseVacantSeatSpace(String code){        
        List<LessonDetails> lessonDetails = LessonDetails.getLessonDetails();
         for(LessonDetails obj : lessonDetails){
            if(obj.getCode().equalsIgnoreCase(code)){
                int vacantSpace = obj.getIsVacantSeat();
                obj.setIsVacantSeat(vacantSpace-1);
                break;
            }
        }
    }
    
    
        
    
    //Update Vacant seats 
    public static void decreaseVacantSeatSpace(String code){        
        List<LessonDetails> lessonDetails = LessonDetails.getLessonDetails();
         for(LessonDetails obj : lessonDetails){
            if(obj.getCode().equalsIgnoreCase(code)){
                int vacantSpace = obj.getIsVacantSeat();
                obj.setIsVacantSeat(vacantSpace-1);
                break;
            }
        }
    }
    
    
    
    //Update Vacant seats 
    public static int hasVacantSeats(String code){      
        int vacantSeat = 0;
        List<LessonDetails> lessonDetails = LessonDetails.getLessonDetails();
         for(LessonDetails obj : lessonDetails){
            if(obj.getCode().equalsIgnoreCase(code)){
                int vacantSpace = obj.getIsVacantSeat();
                vacantSeat = vacantSpace;
                break;
            }
        }
         return vacantSeat;
    }
    
    
     
    //Is Booking Higher / Lower Grade Level
    public static boolean isNotBookingCurrentGradeLevel(String lessonCode, String learnerUniqueNo){
        boolean isAllowed = false;
       
        //Get Learner Grade
        List<LearnerDetails> learnerDetails = LearnerDetails.getLearnerDetails();
        int currentGrade = 0;
        for(LearnerDetails obj : learnerDetails){
            if(obj.getLearnerUniqueNo().equalsIgnoreCase(learnerUniqueNo)){
                currentGrade = obj.getCurrentGradeLevel();
                break;
            }
        }
        
        //Get Class Grade
        List<LessonDetails> lessonDetails = LessonDetails.getLessonDetails();
        int lessonGrade = 0;
        for(LessonDetails obj : lessonDetails){
            if(obj.getCode().equalsIgnoreCase(lessonCode)){
                lessonGrade = obj.getGradeLevel();
                break;
            }
        }
        
        int learnerAllowedGrade = currentGrade +1;
        if(lessonGrade < currentGrade || lessonGrade > learnerAllowedGrade){
            return true;
        }
        
        return isAllowed;
    }
    
        
}
