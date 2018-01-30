import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * Created by yanhuche on 12/6/2016.
 */
public class Demo__DSTPhenomenon__WithOneHourAgo {


    public static void main(String[] args) throws ParseException {
        Demo__DSTPhenomenon__WithOneHourAgo main = new Demo__DSTPhenomenon__WithOneHourAgo();
        main.run(1459033200000L);
        main.run(1477778400000L);
    }

    private static final String SP = "    ";
    private static final int TOTAL_MINUTE = 60 * 6;
    private static final int ADD_STEP = 15;

    private void run(long startMilli) throws ParseException {
        for (int addMinute = 0; addMinute < TOTAL_MINUTE; addMinute += ADD_STEP) {
            long milli = startMilli + TimeUnit.MINUTES.toMillis(addMinute);

            String result = formatToMinuteFolder(milli, 0);
            result += SP + "one hour ago" + SP;
            result += formatToMinuteFolder(milli, -1);
            System.out.println(result);
        }
    }

    private String formatToMinuteFolder(long milli, int hourAgo) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milli);
        calendar.add(Calendar.HOUR_OF_DAY, hourAgo);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");
        String minuteFolder = simpleDateFormat.format(calendar.getTime());

        return calendar.getTime() + SP + minuteFolder;
    }

}
