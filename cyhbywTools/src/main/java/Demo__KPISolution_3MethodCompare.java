import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by yanhuche on 10/21/2016.
 */
public class Demo__KPISolution_3MethodCompare {


    public static void main(String[] args) throws ParseException {
        new Demo__KPISolution_3MethodCompare().run(args);
    }

    public Demo__KPISolution_3MethodCompare() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = simpleDateFormat.parse("2015-01-01T00:00:00");
        startMilli = date.getTime();
    }

    private final int interval = 15;
    private final int addMinute = 60 * 24 * 366 * 2;
    private final long startMilli;
    private final String SP = "    ";

    private void run(String[] args) throws ParseException {
        if (args.length != 1) {
            System.err.println("Need 1 argument: 1(set)/2(add)/3(milli)");
            return;
        }

        doRun(Integer.valueOf(args[0]));
    }

    private void doRun(int type) throws ParseException {
        for (int addIndex = 0; addIndex < addMinute; addIndex++) {
            long milli = startMilli + TimeUnit.MINUTES.toMillis(addIndex);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(milli);

            minusMinute(type, calendar);
            long timeInMillis = calendar.getTimeInMillis();

            long minuteDiff = TimeUnit.MILLISECONDS.toMinutes(milli - timeInMillis);
            if (minuteDiff != interval) {
                Calendar calendar2 = Calendar.getInstance();
                calendar2.setTimeInMillis(milli);

                System.out.println("diff: " + SP + calendar2.getTime() + SP + calendar.getTime() + SP + minuteDiff);
            }
        }
    }

    private void minusMinute(int type, Calendar calendar) {
        if (type == 1) {
            calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) - interval);
        } else if (type == 2) {
            calendar.add(Calendar.MINUTE, -interval);
        } else if (type == 3) {
            calendar.setTimeInMillis(calendar.getTimeInMillis() - TimeUnit.MINUTES.toMillis(interval));
        } else {
            System.err.println("type muse equals 1, 2 or 3.");
            System.exit(-1);
        }
    }

}
