
import java.util.ArrayList;
import java.util.List;


public class LearnerDetails {
 
    private String learnerUniqueNo;
    private String username;
    private String gender;
    private int age;
    private String contactNo;
    private String address;
    private int currentGradeLevel;
    
    public static ArrayList <LearnerDetails> learnerDetails = new ArrayList<>();

    
    public LearnerDetails(String username, String gender, int age, String contactNo, String address, int currentGradeLevel) {
        this.username = username;
        this.gender = gender;
        this.age = age;
        this.contactNo = contactNo;
        this.address = address;
        this.currentGradeLevel = currentGradeLevel;
    }

    public LearnerDetails(String learnerUniqueNo, String username, String gender, int age, String contactNo, String address, int currentGradeLevel) {
        this.learnerUniqueNo = learnerUniqueNo;
        this.username = username;
        this.gender = gender;
        this.age = age;
        this.contactNo = contactNo;
        this.address = address;
        this.currentGradeLevel = currentGradeLevel;
    }

    
    public String getLearnerUniqueNo() {
        return learnerUniqueNo;
    }

    public String getUsername() {
        return username;
    }    

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getContactNo() {
        return contactNo;
    }

    public String getAddress() {
        return address;
    }

    public int getCurrentGradeLevel() {
        return currentGradeLevel;
    }

    public static ArrayList<LearnerDetails> getLearnerDetails() {
        LearnerDetails o1 = new LearnerDetails("Joh_758","Johnson","Male",5,"0845 975 8758","Paisley,Renfrewshire,United Kingdom",1);
        LearnerDetails o2 = new LearnerDetails("Jor_456","Jordan","Male",6,"01904 673456","York,North Yorkshire,United Kingdom",2);
        LearnerDetails o3 = new LearnerDetails("Pal_212","Palmer","Male",7,"028 9066 1212","Belfast,Northern Ireland,United Kingdom",3);
        LearnerDetails o4 = new LearnerDetails("Tay_504","Taylor","Male",8,"01392 274504","Exeter,Devon,United Kingdom",4);
        LearnerDetails o5 = new LearnerDetails("Joe_758","Joel ","Male",9,"0845 975 8758","Nantwich,Cheshire,United Kingdom",5);
       
        LearnerDetails o6 = new LearnerDetails("Kev_410","Kevin","Female",10,"01636 704410","Newark,Nottinghamshire,United Kingdom",1);
        LearnerDetails o7 = new LearnerDetails("Ash_942","Ashley","Male",11,"01782 712942","Newcastle,Staffordshire,United Kingdom",2);
        LearnerDetails o8 = new LearnerDetails("Ada_427","Adam","Male",4,"0113 249 4427","Leeds,West Yorkshire,United Kingdom",3);
        LearnerDetails o9 = new LearnerDetails("Rey_777","Reynolds","Male",7,"01883 712777","Oxted,Surrey,United Kingdom",4);
        LearnerDetails o10 = new LearnerDetails("Ste_766","Stephen","Male",8,"01245 261766","Chelmsford,Essex,United Kingdom",5);
        
        LearnerDetails.learnerDetails.add(o1);
        LearnerDetails.learnerDetails.add(o2);
        LearnerDetails.learnerDetails.add(o3);
        LearnerDetails.learnerDetails.add(o4);
        LearnerDetails.learnerDetails.add(o5);
        LearnerDetails.learnerDetails.add(o6);
        LearnerDetails.learnerDetails.add(o7);
        LearnerDetails.learnerDetails.add(o8);
        LearnerDetails.learnerDetails.add(o9);
        LearnerDetails.learnerDetails.add(o10);
        return learnerDetails;
    }

    public void setCurrentGradeLevel(int currentGradeLevel) {
        this.currentGradeLevel = currentGradeLevel;
    }

    public void setLearnerUniqueNo(String learnerUniqueNo) {
        this.learnerUniqueNo = learnerUniqueNo;
    }
    
    //Generate Learner Unique No
    public static String generateLearnerUniqueNo(String username,String contactNo){
        String uniqueNo = username.substring(0, 3) + "_" + contactNo.substring(0, 3);
        return uniqueNo;
    }
    
    //Update Learner Unique No
    public static void updateUniqueNo(String uniqueNo, String username){
        List<LearnerDetails> learnerDetails = LearnerDetails.getLearnerDetails();
        for(LearnerDetails obj : learnerDetails){
            if(obj.getUsername().equalsIgnoreCase(username)){
                obj.setLearnerUniqueNo(uniqueNo);
                break;
            }
        }
    }
    
      
     
    //Increase current grade
    public static void increaseGradeLevel(String learnerUniqueCode){
        List<LearnerDetails> learnerDetail = LearnerDetails.getLearnerDetails();
         for(LearnerDetails obj : learnerDetail){
            if(obj.getLearnerUniqueNo().equalsIgnoreCase(learnerUniqueCode)){
                int grade = obj.getCurrentGradeLevel();
                obj.setCurrentGradeLevel(grade+1);
                break;
            }
        }
    }

    
    //Check Learner exists or not
    public static boolean isLearnerExist(String learnerUniqueNo){
        boolean isExist = false;
        
        List<LearnerDetails> learnerDetails = LearnerDetails.getLearnerDetails();
         for(LearnerDetails obj : learnerDetails){
            if(obj.getLearnerUniqueNo().equalsIgnoreCase(learnerUniqueNo)){
                isExist = true;
                break;
            }
        }
        return isExist;
    }
    
     
    //Get current grade
    public static int getCurrentGradeLevelOfLearner(String learnerUniqueNo){
       
        //Get Learner Grade
        List<LearnerDetails> learnerDetails = LearnerDetails.getLearnerDetails();
        int currentGrade = 0;
        for(LearnerDetails obj : learnerDetails){
            if(obj.getLearnerUniqueNo().equalsIgnoreCase(learnerUniqueNo)){
                currentGrade = obj.getCurrentGradeLevel();
                break;
            }
        }
        return currentGrade;
    }
     
   
    
}
