package com.beestar.jzb.newweathercode.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.beestar.jzb.newweathercode.R;


public class StepOneFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText resgite_tel;
    private OnFragemtGetData mOnFragemtGetData;

    public StepOneFragment() {
        // Required empty public constructor
    }


    public static StepOneFragment newInstance() {
        StepOneFragment fragment = new StepOneFragment();

        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        mOnFragemtGetData=(OnFragemtGetData)context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_step_one, container, false);

        resgite_tel = (EditText)view.findViewById(R.id.regiter_tel);
        return view;
    }
    public String getDataTel(){
        if (TextUtils.isEmpty(resgite_tel.getText().toString().trim())){
            return null;
        }else {
            return  resgite_tel.getText().toString();
        }

    }
     public interface OnFragemtGetData{
        void OnFragmentGetDataListener(String resgiteTel);
    }
}
