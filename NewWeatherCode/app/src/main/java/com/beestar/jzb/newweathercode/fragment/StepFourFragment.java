package com.beestar.jzb.newweathercode.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.beestar.jzb.newweathercode.R;


public class StepFourFragment extends Fragment {


    // TODO: Rename and change types of parameters


    private OnFragmentNameListener mListener;
    private EditText mNameRegiter;

    public StepFourFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment StepFourFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StepFourFragment newInstance() {
        StepFourFragment fragment = new StepFourFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        mListener=(OnFragmentNameListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_step_four, container, false);
        initView(view);

        return view;
    }

    private void initView(View itemView) {
        mNameRegiter = (EditText) itemView.findViewById(R.id.regiter_name);
    }

    public String getDataName(){
        if (TextUtils.isEmpty(mNameRegiter.getText().toString().trim())){
            return null;
        }else {
            return mNameRegiter.getText().toString().trim();
        }
    }
    public interface OnFragmentNameListener {
        // TODO: Update argument type and name
        void onFragmentName(String name);
    }
}
