package com.cyh.test;

import java.math.BigInteger;

/**
 * Created by yanhuche on 1/27/2016.
 */
public class IntLongBigIntegerEfficiencyTest {

    private int MOD = 1<<30;

    private BigInteger BigMOD = new BigInteger(String.valueOf(MOD));

    private final int maxLoopValue = 10000000;

    private final BigInteger maxLoopValueForBigInt = new BigInteger(String.valueOf(maxLoopValue));

    private final BigInteger addStep = new BigInteger(String.valueOf("1"));


    public static void main(String[] args) {
        IntLongBigIntegerEfficiencyTest cyh = new IntLongBigIntegerEfficiencyTest();
        cyh.testInt();
        cyh.testLong();
        cyh.testBigInteger();
    }

    private void testInt() {
        long st = System.currentTimeMillis();
        int sum = 0;
        for (int e = 1; e <= maxLoopValue; e++) {
            sum = (sum + e) % MOD;
        }
        System.out.println("sum=" + sum);

        long en = System.currentTimeMillis();
        System.out.println("(int)cost=" + (en - st) + "\n");
    }

    private void testLong() {
        long st = System.currentTimeMillis();
        long sum = 0;
        for (long e = 1; e <= maxLoopValue; e++) {
            sum = (sum + e) % MOD;
        }
        System.out.println("sum=" + sum);

        long en = System.currentTimeMillis();
        System.out.println("(long)cost=" + (en - st) + "\n");
    }

    private void testBigInteger() {
        long st = System.currentTimeMillis();
        BigInteger sum = new BigInteger("0");
        for (BigInteger e = new BigInteger("1"); e.compareTo(maxLoopValueForBigInt) <= 0; e = e.add(addStep)) {
            sum = sum.add(e).mod(BigMOD);
        }

        System.out.println("sum=" + sum.toString());
        long en = System.currentTimeMillis();
        System.out.println("(BigInteger)cost=" + (en - st) + "\n");
    }


}


/**
 sum=143223616
 (int)cost=83

 sum=143223616
 (long)cost=146

 sum=143223616
 (BigInteger)cost=1985


 Process finished with exit code 0


 sum=143223616
 (int)cost=83

 sum=143223616
 (long)cost=148

 sum=143223616
 (BigInteger)cost=1950


 Process finished with exit code 0


 sum=143223616
 (int)cost=89

 sum=143223616
 (long)cost=156

 sum=143223616
 (BigInteger)cost=1976


 Process finished with exit code 0

 */
