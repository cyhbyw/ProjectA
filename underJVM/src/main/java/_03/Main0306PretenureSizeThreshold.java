package _03;

/**
 * Created by yanhuche on 3/21/2017.
 */
public class Main0306PretenureSizeThreshold {


    private static final int _1MB = 1024 * 1024;

    /**
     * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     * -XX:PretenureSizeThreshold=3145728
     */
    public static void testPretenureSizeThreshold() {
        byte[] allocation;
        allocation = new byte[4 * _1MB]; //直接分配在老年代中
    }



    public static void main(String[] args) {

    }


}
