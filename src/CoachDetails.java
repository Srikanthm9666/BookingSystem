
import java.util.ArrayList;
import java.util.List;


public class CoachDetails {
    
    private String coachUniqueNo;
    private String username;
    private String phoneNo;
    private int age;
    private String gender;
    
    public static ArrayList <CoachDetails> coachDetails = new ArrayList<>();

    public CoachDetails(String coachUniqueNo, String username, String phoneNo, int age, String gender) {
        this.coachUniqueNo = coachUniqueNo;
        this.username = username;
        this.phoneNo = phoneNo;
        this.age = age;
        this.gender = gender;
    }

    public String getCoachUniqueNo() {
        return coachUniqueNo;
    }

    public String getUsername() {
        return username;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public static ArrayList<CoachDetails> getCoachDetails() {
        coachDetails.removeAll(coachDetails);
        
        CoachDetails o1 = new CoachDetails("anth_201","Anthony","028 2827 8900",34,"Male");
        CoachDetails o2 = new CoachDetails("henr_202","Henry","01403 256890",35,"Male");
        CoachDetails o3 = new CoachDetails("rich_203","Richards","01283 542321",36,"Male");
        CoachDetails o4 = new CoachDetails("lewi_204","Lewis","01527 544999",37,"Male");
        CoachDetails o5 = new CoachDetails("adel_205","Adele","0151 709 7604",38,"Male");
        CoachDetails.coachDetails.add(o1);
        CoachDetails.coachDetails.add(o2);
        CoachDetails.coachDetails.add(o3);
        CoachDetails.coachDetails.add(o4);
        CoachDetails.coachDetails.add(o5);
        return coachDetails;
    }

    
    
    
    //get Coach Name
    public static String getCoachName(String code){
        String coachName = "";
        
        List<LessonDetails> lessonDetails = LessonDetails.getLessonDetails();
        List<CoachDetails> coachDetails = CoachDetails.getCoachDetails();
        
         for(LessonDetails obj : lessonDetails){
             if(obj.getCode().equalsIgnoreCase(code)){
                for(CoachDetails cobj : coachDetails){
                    if(obj.getTeachBy().equalsIgnoreCase(cobj.getCoachUniqueNo())){
                        coachName = cobj.getUsername();
                        break;
                    }
                }
             }
        }
        return coachName;
    }
        
   
}
