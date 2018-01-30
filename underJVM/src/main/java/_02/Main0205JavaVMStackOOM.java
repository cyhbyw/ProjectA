package _02;

/**
 * VM Args：-Xss2M （这时候不妨设大些）
 * @author zzm
 */
public class Main0205JavaVMStackOOM {

    private void dontStop() {
        while (true) {
        }
    }

    public void stackLeakByThread() {
        while (true) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) throws Throwable {
        Main0205JavaVMStackOOM oom = new Main0205JavaVMStackOOM();
        oom.stackLeakByThread();
    }
}
