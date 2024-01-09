package com.example.shoplist;

import android.annotation.SuppressLint;
import android.database.Cursor;
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
    private static MyDatabaseHelper databaseHelper;
    private Cursor cursor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_list, container, false);

        tableLayout = rootView.findViewById(R.id.tableLayout);
        databaseHelper = new MyDatabaseHelper(requireActivity());
        cursor = databaseHelper.readAllData();

        int n = cursor.getCount();
        cursor.moveToFirst();

        for (int i = 0; i < n; i++) {
            String name = cursor.getString(1);
            int done = cursor.getInt(2);
            addTableRow(name, done, Integer.parseInt(cursor.getString(0)));
            cursor.moveToNext();
        }

        return rootView;
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    private void addTableRow(String name, int done, int id)
    {
        tableLayout.setBackground(getResources().getDrawable(R.drawable.table_corners));


        TableRow row = new TableRow(requireActivity());
        TableRow.LayoutParams rowp = new TableRow.LayoutParams(-2, -2);
        row.setLayoutParams(rowp);
        row.setBackground(getResources().getDrawable(R.drawable.table_corners));

        TextView tvName = new TextView(requireActivity());
        TableRow.LayoutParams ll = new TableRow.LayoutParams(-2, -2);
        ll.setMargins(0, 1, 0, 0);
        tvName.setLayoutParams(ll);

        if (name.length() > 10) {
            tvName.setText(DownRow(name).trim());
        } else {
            tvName.setText(name.trim());
        }
        tvName.setTextSize(26);
        tvName.setGravity(Gravity.CENTER);
        if (done == 1)
        {
            tvName.setTextColor(Color.BLUE);
        }
        else {
            tvName.setTextColor(Color.BLACK);
        }
        tvName.setBackground(getResources().getDrawable(R.drawable.table_corners));

        CheckBox cb = new CheckBox(requireActivity());
        TableRow.LayoutParams cbParams = new TableRow.LayoutParams(-2, 100);
        cbParams.gravity = Gravity.CENTER;
        cb.setTextColor(Color.BLACK);
        cb.setLayoutParams(cbParams);
        cb.setChecked(done == 1);



        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked)
                {
                    tvName.setTextColor(Color.BLUE);
                    databaseHelper.updateCheckBox(1, String.valueOf(id));
                }
                else
                {
                    tvName.setTextColor(Color.BLACK);
                    databaseHelper.updateCheckBox(0, String.valueOf(id));
                }
            }
        });


        row.addView(cb);
        row.addView(tvName);
        tableLayout.addView(row);


    }

    public String DownRow(String name) {
        int index = 0;
        String str = "";

        for (int i = 0; i < name.length(); i++) {
            index++;
            str += name.charAt(i);
            if (index >= 10) {
                str += "\n";
                index = 0;
            }
        }
        return str;
    }

}