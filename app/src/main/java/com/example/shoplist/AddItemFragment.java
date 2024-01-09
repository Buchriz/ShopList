package com.example.shoplist;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddItemFragment extends Fragment {

    private  View rootView;
    private EditText etName;
    private String name;
    private Button btnAdd;

    private MyDatabaseHelper databaseHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_add_item, container, false);
        databaseHelper = new MyDatabaseHelper(requireActivity());

        etName = rootView.findViewById(R.id.etName);
        btnAdd = rootView.findViewById(R.id.btnAddToList);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                name = etName.getText().toString().trim();
                etName.setText("");

                if (name.length() == 0)
                {
                    Toast.makeText(requireActivity(), "כתוב מוצר", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    ListFragment listFragment = new ListFragment();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_fragment, listFragment).commit();
                    databaseHelper.addProduct(name);
                }
            }
        });


        return rootView;
    }

}