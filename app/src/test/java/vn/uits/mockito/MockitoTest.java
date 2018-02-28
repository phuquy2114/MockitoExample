package vn.uits.mockito;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Copyright © 2017 BAP CO., LTD
 * Created by PHUQUY on 2/26/18.
 */

@RunWith(MockitoJUnitRunner.class)
public class MockitoTest {
    private static final String FAKE_STRING = "HELLO WORLD";

    @Mock
    Context mMockContext;

    @Test
    public void readStringFromContext_LocalizedString() {
        // Given a mocked Context injected into the object under test...
        Mockito.when(mMockContext.getString(R.string.app_name))
                .thenReturn(FAKE_STRING);
    }
}
