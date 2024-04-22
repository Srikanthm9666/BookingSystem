
public class CancelLessonCommand implements LessonCommand{
    @Override
    public void execute() {
        Functions.cancelLesson();
    }
}
