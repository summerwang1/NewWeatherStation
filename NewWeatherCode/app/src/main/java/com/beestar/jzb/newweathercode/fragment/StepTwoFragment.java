package com.beestar.jzb.newweathercode.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.beestar.jzb.newweathercode.R;


public class StepTwoFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "step_two";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentSmsListener mListener;
    private TextView mStepTwoTel;
    private Button mSendSms;
    private EditText mSmsRegiter;

    public StepTwoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment StepTwoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StepTwoFragment newInstance() {
        StepTwoFragment fragment = new StepTwoFragment();
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
//        mListener=(OnFragmentSmsListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step_two, container, false);

        initView(view);

        return view;
    }

    private void initView( View itemView) {
        mStepTwoTel = (TextView) itemView.findViewById(R.id.stepTwoTel);
        mSendSms = (Button) itemView.findViewById(R.id.sendSms);
        mSendSms.setOnClickListener(this);
        mSmsRegiter = (EditText) itemView.findViewById(R.id.regiter_sms);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sendSms:
                // TODO 18/04/20

                break;
            default:
                break;
        }
    }
    public String  getDataSms(){
        if (TextUtils.isEmpty(mSmsRegiter.getText().toString().trim())){
            return null;
        }else {
            return  mSmsRegiter.getText().toString().trim();
        }
    }
    public void setTel(String tel){
        mStepTwoTel.setText(tel);
    }
    public interface OnFragmentSmsListener {
        void onFragmentSms(String pwd);
    }
}
