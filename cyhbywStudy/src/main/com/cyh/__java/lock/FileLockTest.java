package com.cyh.__java.lock;



/**
 * Created by yanhuche on 9/10/2015.
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.Date;

/**
 * FileLocke是文件锁，进程锁，控制不同程序（JVM）对同一文件的并发访问
 * FileLock是java 1.4 版本后出现的一个类，它可以通过对一个可写文件(w)加锁，
 * 保证同时只有一个进程可以拿到文件的锁，这个进程从而可以对文件做访问；
 * 而其它拿不到锁的进程要么选择被挂起等待，要么选择去做一些其它的事情，
 * 这样的机制保证了众进程可以顺序访问该文件。
 * 也可以看出，能够利用文件锁的这种性质，在一些场景下，虽然我们不需要操作某个文件，
 * 但也可以通过 FileLock 来进行并发控制，保证进程的顺序执行，避免数据错误。
 * ---用于进程间并发
 * Locks are associated with files, not channels. Use locks to coordinate
 * with external processes, not between threads in the same JVM.
 * ---概念
 * 共享锁:共享读操作，但只能一个写
 * 排它锁:只有一个读或一个写
 * API中说：文件锁是独占或者共享的，共享锁防止其他正在运行的程序获得重复的独占锁，但是允许他们获得
 * 重复的共享锁，独占锁防止其他程序获得任何类型的锁。一旦锁释放，它就对其他程序可能要获得的锁没有了后续影响.
 * ---FileLock FileChannel.lock(long position, long size, boolean shared),文件锁
 * shared的含义:是否使用共享锁,一些不支持共享锁的操作系统,将自动将共享锁改成排它锁.
 * 可以通过调用isShared()方法来检测获得的是什么类型的锁
 * ---lock()和tryLock()的区别
 * lock()阻塞的方法，锁定范围可以随着文件的增大而增加
 * tryLock()非阻塞,当未获得锁时,返回null.
 * ---FileLock的生命周期
 * 在调用FileLock.release(),或者Channel.close(),或者JVM关闭
 * ---FileLock是线程安全的
 * ---boolean java.nio.channels.FileLock.overlaps(long position, long size)
 * true表示当前锁在区域内,false表示当前锁的区域与参数区域不重叠
 * 	注意事项：
 1.同一进程内，在文件锁没有被释放之前，不可以再次获取。
 即在release()方法调用前,只能lock()或者tryLock()一次。
 2.文件锁的效果是与操作系统相关的。一些系统中文件锁是强制性的（mandatory），
 就当Java的某进程获得文件锁后，操作系统将保证其它进程无法对文件做操作了。
 而另一些操作系统的文件锁是询问式的(advisory)，意思是说要想拥有进程互斥的效果，
 其它的进程也必须也按照API所规定的那样来申请或者检测文件锁，不然，将起不到进程互斥的功能。
 所以，文档里建议将所有系统都当做是询问式系统来处理，这样程序更加安全也更容易移植。
 *如何避免死锁
 在读写关键数据时加锁，操作完成后解锁；
 一次性申请所有需要的资源，并且在申请不成功的情况下放弃已申请到的资源;
 * @author shijin
 *
 */
public class FileLockTest {
    /**
     * 如代码所示，需要进行互斥的进程只要将自己的代码替换掉//互斥操作即可，
     * 每个进程在运行实际逻辑功能代码之前，会尝试获取锁文件锁，
     * 得到文件锁的进程可以继续执行后续的代码，而没有获得锁文件的进程将被操作系统挂起(suspend)，
     * 等到其它进程将文件锁释放后再重新开始尝试获取文件锁。
     * 这样子，进程就可以通过FileLock来实现间的互斥运行。
     * @param args
     */
    public static void main(String[] args){
        FileChannel channel = null;
        FileLock lock = null;
        try {
//			对于一个只读文件通过任意方式加锁时会报NonWritableChannelException异常
//			同样对写通道通过有参lock()方式加锁时也会报NonReadableChannelException异常
//			无参lock()默认为独占锁，不会报NonReadableChannelException异常，因为独占就是为了写
//			所谓的共享也只能读共享，写是独占的，共享锁控制的代码只能是读操作
//			channel = new FileOutputStream("logfile.txt",true).getChannel();

            RandomAccessFile raf = new RandomAccessFile("logfile.txt","rw");
            raf.seek(raf.length());//raf在文件末尾追加内容的处理
            channel = raf.getChannel();

//			获得锁方法一lock，阻塞的方法，当文件锁不可用时，当前进程会被挂起
//			lock = channel.lock(0L, Long.MAX_VALUE, true);//共享锁，有写操作会报异常
            lock = channel.lock();//独占锁

//			获得锁方法二trylock，非阻塞的方法，当文件锁不可用时，tryLock()会得到null值
//			do {
//				lock = channel.tryLock();
//			} while(null == lock);

//			互斥操作
            ByteBuffer sendBuffer=ByteBuffer.wrap((new Date()+" 写入\n").getBytes());
            channel.write(sendBuffer);
            Thread.sleep(5000);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(lock != null) {
                try {
                    lock.release();
                    lock = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(channel != null) {
                try {
                    channel.close();
                    channel = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
/**
 * 	间隔一秒钟两次运行本程序，程序会在文件锁的控制下对logfile.txt进行互斥操作
 logfile.txt内容：
 Thu Aug 16 15:39:02 CST 2012 写入
 Thu Aug 16 15:39:07 CST 2012 写入

 当采用第二种方法时，若还未获得文件锁就对文件进行操作，则会报以下异常：
 Exception in thread "main" java.io.IOException: 另一个程序已锁定文件的一部分，进程无法访问。
 at sun.nio.ch.FileDispatcher.write0(Native Method)
 at sun.nio.ch.FileDispatcher.write(Unknown Source)
 at sun.nio.ch.IOUtil.writeFromNativeBuffer(Unknown Source)
 at sun.nio.ch.IOUtil.write(Unknown Source)
 at sun.nio.ch.FileChannelImpl.write(Unknown Source)
 at FileLockTest.main(FileLockTest.java:19)
 */
