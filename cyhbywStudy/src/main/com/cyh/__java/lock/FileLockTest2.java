package com.cyh.__java.lock;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.concurrent.TimeUnit;

public class FileLockTest2 {

	public static void main(String[] args) {
		File f = new File("D:/work/OMAgentLog/oss_trace0_1.log");
		if(!f.exists()) {
			System.err.println("file not exists..........");
			return;
		}
		while(true) {
			RandomAccessFile raf = null;
			FileChannel channel = null;
			FileLock lock = null;
			try {
				TimeUnit.SECONDS.sleep((int)(Math.random()*10));
				raf = new RandomAccessFile(f, "rw");
				channel = raf.getChannel();
				while(true) {
					lock = channel.tryLock();
					if(lock != null) {
						deal("got");
						break;
					} else {
						System.out.println("not got...");
						TimeUnit.SECONDS.sleep(1);
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				try {
					if(lock != null) {
						lock.release();
						lock = null;
					}
					if(channel != null) {
						channel.close();
						channel = null;
					}
					if(raf != null) {
						raf.close();
						raf = null;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}


	private static void deal(String str) {
		for(int e=1; e <= 10; e++) {
			System.out.println(str + "..." + System.currentTimeMillis());
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}

}
