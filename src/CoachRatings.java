
import java.util.ArrayList;


public class CoachRatings {
 
    
    private String coachUniqueNo;
    private String coachName;
    private String reviewBy;
    private String review;
    private int rating;
    
    public static ArrayList <CoachRatings> coachRatingDetails = new ArrayList<>();

    public CoachRatings(String coachUniqueNo, String coachName, String reviewBy, String review, int rating) {
        this.coachUniqueNo = coachUniqueNo;
        this.coachName = coachName;
        this.reviewBy = reviewBy;
        this.review = review;
        this.rating = rating;
    }

    public String getCoachUniqueNo() {
        return coachUniqueNo;
    }

    public String getCoachName() {
        return coachName;
    }

    public String getReviewBy() {
        return reviewBy;
    }

    public int getRating() {
        return rating;
    }

    public static ArrayList<CoachRatings> getCoachRatingDetails() {
        return coachRatingDetails;
    }
    
    
}
