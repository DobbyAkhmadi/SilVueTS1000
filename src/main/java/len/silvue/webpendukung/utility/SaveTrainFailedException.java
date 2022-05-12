package len.silvue.webpendukung.utility;

public class SaveTrainFailedException extends Exception{

    public SaveTrainFailedException(String msg) {
        super(msg);
    }

    public SaveTrainFailedException(String msg, Throwable e) {
        super(msg, e);
    }
}
