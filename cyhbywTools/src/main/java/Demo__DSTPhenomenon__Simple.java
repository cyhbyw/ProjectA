import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * Created by yanhuche on 12/6/2016.
 */
public class Demo__DSTPhenomenon__Simple {


    public static void main(String[] args) throws ParseException {
        Demo__DSTPhenomenon__Simple main = new Demo__DSTPhenomenon__Simple();
        main.run(1459033200000L);
        main.run(1477778400000L);
    }

    private static final String SP = "    ";
    private static final int TOTAL_MINUTE = 60 * 6;
    private static final int ADD_STEP = 15;

    private void run(long startMilli) throws ParseException {
        for (int addMinute = 0; addMinute < TOTAL_MINUTE; addMinute += ADD_STEP) {
            long milli = startMilli + TimeUnit.MINUTES.toMillis(addMinute);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(milli);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");
            String minuteFolder = simpleDateFormat.format(calendar.getTime());

            System.out.println(calendar.getTime() + SP + minuteFolder);
        }
    }

}
