package com.example.shoplist;

import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;


public class ListFragment extends Fragment {

    private View rootView;

    private TableLayout tableLayout;
    private String getName;
    private TextView tvRemove;
    private FrameLayout fr;

    public static List<String> nameList = new LinkedList<>();
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public ListFragment() {
    }

    public static ListFragment newInstance(String param1, String param2) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        rootView =  inflater.inflate(R.layout.fragment_list, container, false);
        initView();
        return rootView;
    }

    private void initView()
    {
        fr = rootView.findViewById(R.id.fr);
        tableLayout = rootView.findViewById(R.id.tableLayout);
        tvRemove = rootView.findViewById(R.id.tvRemove);

        Bundle bundle = this.getArguments();
        if (bundle != null)
        {
            getName = bundle.getString("name");
            nameList.add(getName);
            fr.removeView(tvRemove);
            addTableRow();
        }
    }

    private void addTableRow()
    {
        tableLayout.setBackground(getResources().getDrawable(R.drawable.table_corners));
        for (int i = 0; i < nameList.size(); i++)
        {
            TableRow row = new TableRow(requireActivity());
            row.setLayoutParams(new TableRow.LayoutParams(-2, 100));
            row.setBackground(getResources().getDrawable(R.drawable.textview_corners));

            TextView tvName = new TextView(requireActivity());
            tvName.setLayoutParams(new TableRow.LayoutParams(-2, 100));

            tvName.setText(nameList.get(i));
            tvName.setTextColor(Color.BLACK);
            tvName.setTextSize(19);
            tvName.setGravity(Gravity.CENTER);
            tvName.setBackground(getResources().getDrawable(R.drawable.textview_corners));

            CheckBox cb = new CheckBox(requireActivity());
            TableRow.LayoutParams cbParams = new TableRow.LayoutParams(-2, 100);
            cbParams.gravity = Gravity.CENTER;
            cb.setLayoutParams(cbParams);

            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked)
                    {
                        tvName.setTextColor(ContextCompat.getColor(requireActivity(), R.color.blue));
                    }
                    else
                    {
                        tvName.setTextColor(ContextCompat.getColor(requireActivity(), R.color.black));
                    }
                }
            });

            row.addView(cb);
            row.addView(tvName);
            tableLayout.addView(row);
        }
    }
}