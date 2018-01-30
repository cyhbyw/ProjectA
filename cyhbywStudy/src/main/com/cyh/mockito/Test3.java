package com.cyh.mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

/**
 * Created by yanhuche on 3/10/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class Test3 {

    @Mock
    private List mockList;

    @Test
    public void shorthand() {
        mockList.add(1);
        verify(mockList).add(1);
    }

    @Test //10
    public void consecutive_calls() {
        Iterator iterator = mock(Iterator.class);
        // 预设当iterator调用next()时第一次返回hello，第n次都返回world
        when(iterator.next()).thenReturn("hello").thenReturn("world");
        String result = iterator.next() + " " + iterator.next() + " " + iterator.next();
        assertEquals("hello world world", result);
    }

    @Test //10
    public void consecutive_calls2() {
        Iterator iterator = mock(Iterator.class);
        when(iterator.next()).thenReturn("one", "two", "three");
        String result = iterator.next() + " " + iterator.next() + " " + iterator.next();
        assertEquals("one two three", result);
    }

    @Test(expected = RuntimeException.class) //10
    public void consecutive_calls3() {
        //模拟连续调用返回期望值，如果分开，则只有最后一个有效
        when(mockList.get(0)).thenReturn(0);
        when(mockList.get(0)).thenReturn(1);
        when(mockList.get(0)).thenReturn(2);
        assertEquals(2, mockList.get(0));
        assertEquals(2, mockList.get(0));

        when(mockList.get(1)).thenReturn(0).thenReturn(1).thenThrow(new RuntimeException());
        assertEquals(0, mockList.get(1));
        assertEquals(1, mockList.get(1));
        //第三次或更多调用都会抛出异常
        mockList.get(1);
    }

    @Test //11
    public void answer_with_callback() {
        //使用Answer来生成我们我们期望的返回
        when(mockList.get(anyInt())).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return "hello world:" + args[0];
            }
        });
        assertEquals("hello world:0", mockList.get(0));
        assertEquals("hello world:999", mockList.get(999));
    }

    @Test(expected = IndexOutOfBoundsException.class) //13
    public void spy_on_real_objects() {
        List list = new LinkedList();
        List spy = spy(list);
        //下面预设的spy.get(0)会报错，因为会调用真实对象的get(0)，所以会抛出越界异常
        //when(spy.get(0)).thenReturn(3);

        /**
         * 使用doReturn-when可以避免when-thenReturn调用真实对象api
         */
        doReturn(9999).when(spy).get(9999);
        //预设size()期望值
        when(spy.size()).thenReturn(100);
        //调用真实对象的api
        spy.add(1);
        spy.add(2);
        assertEquals(100, spy.size());
        assertEquals(1, spy.get(0));
        assertEquals(2, spy.get(1));
        verify(spy).add(1);
        verify(spy).add(2);
        assertEquals(9999, spy.get(9999));
        spy.get(2);
    }

    @Test
    public void unstubbed_invocations() {
        //mock对象使用Answer来对未预设的调用返回默认期望值
        List mock = mock(List.class, new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                return 999;
            }
        });
        //下面的get(1)没有预设，通常情况下会返回NULL，但是使用了Answer改变了默认期望值
        assertEquals(999, mock.get(1));
        //下面的size()没有预设，通常情况下会返回0，但是使用了Answer改变了默认期望值
        assertEquals(999, mock.size());

        doReturn(2).when(mock).get(2);
        assertEquals(2, mock.get(2));
    }

}
