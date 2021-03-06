package com.example.vinit.criminalintent;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Vinit on 3/21/2016.
 */
public class CrimeListFragment extends Fragment {
    private RecyclerView recyclerView;
    private CrimeAdapter mAdapter;
    private static final int REQUEST_CRIME = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.crime_recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();

        if (mAdapter == null) {
            mAdapter = new CrimeAdapter(crimes);
            recyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }

//        mAdapter = new CrimeAdapter(crimes);
        recyclerView.setAdapter(mAdapter);
    }

    private class CrimeHolder extends RecyclerView.ViewHolder {
//        public TextView mTitleTextView;
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private CheckBox mSolvedCheckBox;
        private Crime mCrime;

        public CrimeHolder(View itemView) {
            super(itemView);
        //    mTitleTextView = (TextView) itemView;
            mTitleTextView = (TextView)
                    itemView.findViewById(R.id.list_item_crime_title_text_view);
            mDateTextView = (TextView)
                    itemView.findViewById(R.id.list_item_crime_date_text_view);
            mSolvedCheckBox = (CheckBox)
                    itemView.findViewById(R.id.list_item_crime_solved_checkbox);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent i = new Intent(getActivity(),CrimeActivity.class);
//                    startActivity(i);

                    Intent intent = CrimeActivity.newIntent(getActivity(), mCrime.getMid());
                    startActivityForResult(intent, REQUEST_CRIME);
                }
            });
        }
        public void returnResult() {
            getActivity().setResult(Activity.RESULT_OK, null);
        }
        public void bindCrime(Crime crime) {
            mCrime = crime;
            mTitleTextView.setText(mCrime.getmTitle());
            mDateTextView.setText(mCrime.getmDate().toString());
            mSolvedCheckBox.setChecked(mCrime.ismSolved());
        }
    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {

    private List<Crime> mCrimes;
    public CrimeAdapter(List<Crime> crimes) {
        mCrimes = crimes;
    }

        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_crime,parent,false);
            return new CrimeHolder(view);
        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            Crime crime = mCrimes.get(position);
            holder.bindCrime(crime);
//            holder.mTitleTextView.setText(crime.getmTitle());

        }

        @Override
        public int getItemCount() {
            return mCrimes.size();

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }
}
