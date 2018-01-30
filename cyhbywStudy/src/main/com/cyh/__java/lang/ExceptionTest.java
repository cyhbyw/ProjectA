package com.cyh.__java.lang;

import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

/**
 * Created by yanhuche on 3/28/2016.
 */
public class ExceptionTest {


    public static void main(String[] args) {
        ExceptionTest cyh = new ExceptionTest();
        cyh.run();
    }

    private void run() {
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(1);
                funA();
            } catch (MyException e) {
                System.out.println(e);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void funA() throws MyException {
        try {
            if (Math.random() >= 0.5) {
                System.out.println("throw new FileNotFoundException()");
                throw new FileNotFoundException("aa");
            } else {
                System.out.println("throw new InterruptedException()");
                throw new InterruptedException("bb");
            }
        } catch (Exception e) {
            throw new MyException("test.", e);
        }

    }

    class MyException extends Exception {

        public MyException(Throwable cause) {
            super(cause);
        }

        public MyException(String message, Exception e) {
            super(message, e);
        }
    }


}
