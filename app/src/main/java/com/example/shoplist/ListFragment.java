package com.example.shoplist;

import android.annotation.SuppressLint;
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
    public static List<String> nameList = new LinkedList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        tableLayout = rootView.findViewById(R.id.tableLayout);

        Bundle bundle = this.getArguments();
        if (bundle != null)
        {
            getName = bundle.getString("name");
            nameList.add(getName);
        }
        addTableRow();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void addTableRow()
    {
        tableLayout.setBackground(getResources().getDrawable(R.drawable.table_corners));
        for (int i = 0; i < nameList.size(); i++)
        {
            TableRow row = new TableRow(requireActivity());
            TableRow.LayoutParams rowp = new TableRow.LayoutParams(-2, -2);
            row.setLayoutParams(rowp);
            row.setBackground(getResources().getDrawable(R.drawable.table_corners));

            TextView tvName = new TextView(requireActivity());
            TableRow.LayoutParams ll = new TableRow.LayoutParams(-2, -2);
            ll.setMargins(0,1,0,0);
            tvName.setLayoutParams(ll);

            if (nameList.get(i).length() > 10)
            {
                tvName.setText(DownRow(nameList.get(i)));
            }
            else
            {
                tvName.setText(nameList.get(i));
            }
            tvName.setTextColor(Color.BLACK);
            tvName.setTextSize(26);
            tvName.setGravity(Gravity.CENTER);
            tvName.setBackground(getResources().getDrawable(R.drawable.table_corners));

            CheckBox cb = new CheckBox(requireActivity());
            TableRow.LayoutParams cbParams = new TableRow.LayoutParams(-2, 100);
            cbParams.gravity = Gravity.CENTER;
            cb.setLayoutParams(cbParams);

            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
                {
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

    public String DownRow(String name)
    {
        int index = 0;
        String str = "";

        for (int i = 0; i < name.length(); i++)
        {
            index++;
            str += name.charAt(i);
            if (index >= 10)
            {
                str += "\n";
                index = 0;
            }
        }
        return str;
    }
}