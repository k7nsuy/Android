package com.example.a21_04_23_viewpager01;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PagerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PagerFragment extends Fragment {

    // 1. page number
    private int mPagerNumber;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PagerFragment() {
        // Required empty public constructor
    }

    // 2. page fragment
    public static PagerFragment create(int mPagerNumber) {
        PagerFragment fragment = new PagerFragment();
        Bundle args = new Bundle();
        args.putInt("page",mPagerNumber);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PagerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PagerFragment newInstance(String param1, String param2) {
        PagerFragment fragment = new PagerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    // 3. fragment 에 저장 된 args 를 가져온다.
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPagerNumber = getArguments().getInt("page");
        }
    }

    // 4. view 에 args 를 뿌린다.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_pager,container,false);
        TextView tvNum = rootView.findViewById(R.id.tvNum);
        ImageView ivPoster = rootView.findViewById(R.id.ivPoster);
        tvNum.setText("페이지 번호 : " + mPagerNumber);
        ivPoster.setImageResource(R.drawable.mov01+mPagerNumber);

        return rootView;
    }
}