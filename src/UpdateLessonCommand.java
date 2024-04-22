
public class UpdateLessonCommand implements LessonCommand{
    
    @Override
    public void execute() {
        Functions.updateLesson();
    }
    
}
