package len.silvue.webpendukung.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataFormat {
    private final static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

    public static Date changeStrTimeToDate(String dateStr) throws Exception {
        try {
            return simpleDateFormat.parse(dateStr);
        } catch(Exception e) {
            throw new Exception("Gagal menrubah time string ke date", e);
        }
    }

    public static String changeDateToStrTime(Date date) throws Exception {
        try {
            return simpleDateFormat.format(date);
        } catch(Exception e) {
            throw new Exception("Gagal merubah date to str time", e);
        }
    }
}
