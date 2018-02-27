package vn.uits.mockito;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * Copyright © 2017 BAP CO., LTD
 * Created by PHUQUY on 2/26/18.
 */

public class MockitoFoo {

    // instance
    @Mock
    private Foo mFoo;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setupFooMock() {
        mFoo = Mockito.mock(Foo.class);
        when(mFoo.greet()).thenReturn("Do Phu Quy");
    }

    @Test
    public void fooGreet() {
        Foo foo = mock(Foo.class);
        when(foo.greet()).thenReturn(Foo.HELLO_WORLD);
        System.out.println("Foo great " + foo.greet());
        assertEquals(foo.greet(), Foo.HELLO_WORLD);
    }

    @Test
    public void fooGreetMockito() {
        System.out.println("Android Great App " + mFoo.greet());
        assertEquals("Do Phu Quy", mFoo.greet());
    }

    @Test
    public void barGreets() {
        Bar bar = new Bar();
        assertEquals("Do Phu Quy", bar.greet(mFoo));
    }

    @Test
    public void testMockedList() {
        List<String> mockedList = Mockito.mock(List.class);
        mockedList.add("one");
        mockedList.add("two");
        mockedList.clear();

        Mockito.verify(mockedList).add("two");
        Mockito.verify(mockedList).clear();
    }

    @Test
    public void testSkyList() {
        List<String> mockedList = new ArrayList<>();
        mockedList.add("foo");
        List spy = Mockito.spy(mockedList);

// Impossible: real method is called so spy.get(0) throws IndexOutOfBoundsException (the list is yet empty)
        when(spy.get(0)).thenReturn("foo");
    }
}
