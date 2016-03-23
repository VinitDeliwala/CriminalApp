package com.example.vinit.criminalintent;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Vinit on 3/15/2016.
 */
public class Crime {

    public UUID mid;
    public String mTitle;
    public boolean mSolved;
    public Date mDate;

    public Crime(){
        mid= UUID.randomUUID();
       mDate = new Date();
    }

    public boolean ismSolved() {
        return mSolved;
    }

    public void setmSolved(boolean mSolved) {
        this.mSolved = mSolved;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public UUID getMid() {
        return mid;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }
}
