package len.silvue.webpendukung.utility;

public class DataNotFoundException extends Exception{
    public DataNotFoundException(String msg) {
        super(msg);
    }

    public DataNotFoundException(String msg, Throwable e) {
        super(msg, e);
    }

    public DataNotFoundException() {
        super("Data tidak ditemukan");
    }
}
