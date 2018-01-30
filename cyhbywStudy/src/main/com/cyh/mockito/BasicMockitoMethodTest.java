package com.cyh.mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.InOrder;
import org.mockito.exceptions.verification.NoInteractionsWanted;

/**
 * Created by yanhuche on 3/10/2016.
 */
public class BasicMockitoMethodTest {

    @Test
    //01
    public void verify_behaviour() {
        List mock = mock(List.class);
        mock.add(1);
        mock.clear();
        verify(mock).add(1);
        verify(mock).clear();
    }

    @Test(expected = RuntimeException.class)
    //02
    public void when_thenThrow() {
        List mock = mock(List.class);
        when(mock.get(0)).thenReturn("the first element");
        when(mock.get(1)).thenThrow(new RuntimeException());

        assertEquals("the first element", mock.get(0));
        assertEquals(null, mock.get(999));

        mock.get(1);
    }

    @Test(expected = RuntimeException.class)
    //05
    public void doThrow_when() {
        List list = mock(List.class);
        doThrow(new RuntimeException()).when(list).add(1);
        list.add(1);
    }

    @Test(expected = IOException.class)
    //05
    public void doThrow_when_2() throws IOException {
        OutputStream outputStream = mock(OutputStream.class);
        doThrow(new IOException()).when(outputStream).close(); //void method
        outputStream.close();
    }

    @Test
    public void with_arguments() {
        Comparable comparable = mock(Comparable.class);
        when(comparable.compareTo("Test")).thenReturn(1);
        when(comparable.compareTo("Omg")).thenReturn(2);
        assertEquals(1, comparable.compareTo("Test"));
        assertEquals(2, comparable.compareTo("Omg"));
        // 对于没有预设的情况会返回默认值
        assertEquals(0, comparable.compareTo("Not stub"));
    }

    @Test
    //03
    public void with_unspecified_arguments() {
        List list = mock(List.class);
        when(list.get(anyInt())).thenReturn(1);
        when(list.contains(argThat(new CYHArgumentMatcher()))).thenReturn(true);
        assertEquals(1, list.get(1));
        assertEquals(1, list.get(999));
        assertTrue(list.contains(1));
        assertTrue(!list.contains(3));
    }

    private class CYHArgumentMatcher extends ArgumentMatcher<List> {
        @Override
        public boolean matches(Object o) {
            return (Integer) o == 1 || (Integer) o == 2;
        }

    }

    @Test
    public void all_arguments_provided_by_matchers() {
        Comparator comparator = mock(Comparator.class);
        comparator.compare("nihao", "hello");
        // 如果你使用了参数匹配，那么所有的参数都必须通过matchers来匹配
        verify(comparator).compare(anyString(), eq("hello"));
        // 下面的为无效的参数匹配使用
        // verify(comparator).compare(anyString(),"hello");
    }

    @Test
    //04
    public void verifying_number_of_invocations() {
        List list = mock(List.class);
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(3);
        list.add(3);
        list.add(3);
        // 验证是否被调用一次，等效于下面的times(1)
        verify(list).add(1);
        verify(list, times(1)).add(1);

        verify(list, times(2)).add(2);

        verify(list, times(3)).add(3);

        verify(list, never()).add(4);

        verify(list, atLeastOnce()).add(1);

        verify(list, atLeast(2)).add(2);

        verify(list, atMost(3)).add(3);
    }

    @Test
    //06
    public void verification_in_order() {
        List list = mock(List.class);
        List list2 = mock(List.class);
        list.add(1);
        list2.add("hello");
        list.add(2);
        list2.add("world");
        // 将需要排序的mock对象放入InOrder
        InOrder inOrder = inOrder(list, list2);

        inOrder.verify(list).add(1);
        inOrder.verify(list2).add("hello");
        inOrder.verify(list).add(2);
        inOrder.verify(list2).add("world");
    }

    @Test
    //07
    public void verify_interaction() {
        List list = mock(List.class);
        List list2 = mock(List.class);
        List list3 = mock(List.class);
        list.add(1);
        verify(list).add(1);
        verify(list, never()).add(2);
        // 验证零互动行为
        verifyZeroInteractions(list2, list3);
    }

    @Test(expected = NoInteractionsWanted.class)
    //08
    public void find_redundant_interaction() {
        List list = mock(List.class);
        list.add(1);
        list.add(2);
        verify(list, times(2)).add(anyInt());
        // 检查是否有未被验证的互动行为，因为add(1)和add(2)都会被上面的anyInt()验证到，所以下面的代码会通过
        verifyNoMoreInteractions(list);

        List list2 = mock(List.class);
        list2.add(1);
        list2.add(2);
        verify(list2).add(1);
        // 检查是否有未被验证的互动行为，因为add(2)没有被验证，所以下面的代码会失败抛出异常
        verifyNoMoreInteractions(list2);
    }

}
