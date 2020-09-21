package com.example.LifestyleApp;

import android.os.Build;
import android.widget.Button;
import android.widget.TextView;

import com.example.LifestyleApp.UserInfo.UserInfo3;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(maxSdk = Build.VERSION_CODES.P, minSdk = Build.VERSION_CODES.P) // Value of Build.VERSION_CODES.P is 28
public class UserInfo3Tests {

    private UserInfo3 userInfo3;

    @Before
    public void setup() {

        userInfo3 = Robolectric.setupActivity(UserInfo3.class);

    }

    @Test
    public void loginShouldNotBeNull() throws Exception
    {
        assertNotNull( userInfo3 );
    }

    @Test
    public void createButtonCorrectText() {

        Button createButton = (Button) userInfo3.findViewById(R.id.createButton);

        assertTrue("Create button contains incorrect text",
                "Create".equals(createButton.getText().toString()));

    }

    @Test
    public void snapSelfieTextviewCorrectText() {

        TextView snapSelfie = (TextView) userInfo3.findViewById(R.id.snapSelfie);

        assertTrue("Snap selfie text view contains incorrect text",
                "Snap a Selfie!".equals(snapSelfie.getText().toString()));

    }

}