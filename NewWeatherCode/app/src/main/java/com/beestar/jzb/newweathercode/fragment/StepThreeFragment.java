package com.beestar.jzb.newweathercode.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.beestar.jzb.newweathercode.R;


public class StepThreeFragment extends Fragment {

    private static final String ARG_PARAM1 = "stepThreePwd";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentPwdListener mListener;
    private TextView mStepThreePwd;
    private EditText mPwdRegiter;

    public StepThreeFragment() {
        // Required empty public constructor
    }


    public static StepThreeFragment newInstance(String stepThreePwd) {
        StepThreeFragment fragment = new StepThreeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, stepThreePwd);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        mListener = (OnFragmentPwdListener)context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_step_three, container, false);

        initView(view);

        return view;
    }

    private void initView( View itemView) {
        mStepThreePwd = (TextView) itemView.findViewById(R.id.stepThreePwd);
        mPwdRegiter = (EditText) itemView.findViewById(R.id.regiter_pwd);
    }
    public String getDataPwd(){
        if (TextUtils.isEmpty(mPwdRegiter.getText().toString().trim())){
            return null;
        }else {
            return mPwdRegiter.getText().toString().trim();
        }
    }

    public interface OnFragmentPwdListener {
        // TODO: Update argument type and name
        void onFragmentPwd(String pwd);
    }
}
