
public class BookingClient {
    
    private LessonCommand lessonCommand;

    public void setLessonCommand(LessonCommand lessonCommand) {
        this.lessonCommand = lessonCommand;
    }

    public void processBookingChangeOrCancel(String choice) {
        if (choice.equalsIgnoreCase("1")) {
            setLessonCommand(new UpdateLessonCommand());
        } else if (choice.equalsIgnoreCase("2")) {
            setLessonCommand(new CancelLessonCommand());
        }

        if (lessonCommand != null) {
            lessonCommand.execute();
        }
    }
}
