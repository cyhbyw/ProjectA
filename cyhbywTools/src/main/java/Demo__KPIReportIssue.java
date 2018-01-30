import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * Created by yanhuche on 11/18/2016.
 */
public class Demo__KPIReportIssue {


    public static void main(String[] args) {
        Demo__KPIReportIssue main = new Demo__KPIReportIssue();
        main.run(1459033200000L);
        main.run(1477778400000L);
    }

    private static final int interval = 15;
    private static final String SP = "    ";
    private static final int TOTAL_MINUTE = 60 * 6;
    private static final int ADD_STEP = 15;

    private void run(long startMilli) {
        for (int addMinute = 0; addMinute < TOTAL_MINUTE; addMinute += ADD_STEP) {
            long milli = startMilli + TimeUnit.MINUTES.toMillis(addMinute);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(milli);

            calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) - interval);
            long timeInMillis = calendar.getTimeInMillis();

            long minuteDiff = TimeUnit.MILLISECONDS.toMinutes(milli - timeInMillis);
            if (minuteDiff != interval) {
                Calendar calendar2 = Calendar.getInstance();
                calendar2.setTimeInMillis(milli);

                System.out.println("diff: " + SP + calendar2.getTime() + SP + calendar.getTime() + SP + minuteDiff);
            }
        }
    }


}
