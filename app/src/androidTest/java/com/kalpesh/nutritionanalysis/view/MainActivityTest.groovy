package com.kalpesh.nutritionanalysis.view

import android.widget.Button
import android.widget.EditText
import androidx.test.rule.ActivityTestRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    public MainActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = mActivityTestRule.getActivity();
    }

    @Test
    public void testLogin(){
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                EditText name = activity.findViewById(R.id.ed_name)
                EditText quantity = activity.findViewById(R.id.ed_quantity)
                EditText unit = activity.findViewById(R.id.ed_unit);
                name.setText("rice")
                quantity.setText("20")
                unit.setText("10")
                Button analyse = activity.findViewById(R.id.btn_analyse)
                analyse.performClick();
                assertTrue(activity.isCurUserLoggedIn());
            }
        });
    }

    @After
    public void tearDown() throws Exception {
        activity = null;
    }

}
