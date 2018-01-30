package com.cyh.mockito;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.verify;

/**
 * Created by yanhuche on 3/10/2016.
 */
public class MockAtAnnotation {

    @Mock
    private List mockList;

    public MockAtAnnotation() {
        MockitoAnnotations.initMocks(this);
    }

    @Test //09
    public void shorthand() {
        mockList.add(1);
        verify(mockList).add(1);
    }
}
