package com.example.vinit.criminalintent;

import android.app.Fragment;

import java.util.UUID;

/**
 * Created by Vinit on 3/21/2016.
 */
public class CrimeListActivity  extends SingleFragmentActivity {
    private static final String EXTRA_CRIME_ID =
            "com.bignerdranch.android.criminalintent.crime_id";

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }

}
