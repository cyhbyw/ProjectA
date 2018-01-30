package com.cyh.mockitoOverload;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(LicenseUtil.class)
public class Test1 {

	
	@Test
	public void testA() {
		PowerMockito.mockStatic(LicenseUtil.class);
//		Mockito.when(LicenseUtil.getLicense(Mockito.anyLong(), Mockito.anyString())).thenReturn(300);
//		PowerMock.replayAll( );
		
		System.out.println(LicenseUtil.getLicense(235222L, "cyhbywsdag"));
		
		System.out.println(LicenseUtil.getLicense(2352L));
		
		LicenseUtil.print();
		
		new Test2().fun();
		
	}
	
}
