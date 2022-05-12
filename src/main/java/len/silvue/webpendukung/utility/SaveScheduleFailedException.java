package len.silvue.webpendukung.utility;

public class SaveScheduleFailedException extends Exception {
    public SaveScheduleFailedException(String msg) {
        super(msg);
    }

    public SaveScheduleFailedException(String msg, Throwable e) {
        super(msg, e);
    }
}
