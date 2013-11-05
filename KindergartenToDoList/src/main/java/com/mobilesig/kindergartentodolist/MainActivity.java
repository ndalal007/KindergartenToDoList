package com.mobilesig.kindergartentodolist;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    /* FIELDS */
    ViewModel vm = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(vm == null)
            vm = new ViewModel(getApplicationContext());
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
