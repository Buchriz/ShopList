package com.example.shoplist;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddItemFragment extends Fragment implements View.OnClickListener {

    private  View rootView;
    private EditText etName;
    private String name;
    private Button btnAdd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_add_item, container, false);
        initView();
        return rootView;
    }

    private void initView()
    {
        etName = rootView.findViewById(R.id.etName);

        btnAdd = rootView.findViewById(R.id.btnAddToList);
        btnAdd.setOnClickListener(this);
    }


    @Override
    public void onClick(View view)
    {
        if (view == btnAdd)
        {
            name = etName.getText().toString();
            etName.setText("");
            
            if (name.length() == 0)
            {
                Toast.makeText(requireActivity(), "כתוב מוצר", Toast.LENGTH_SHORT).show();
            }
            else 
            {
                Bundle bundle = new Bundle();

                bundle.putString("name", name);
                ListFragment listFragment = new ListFragment();
                listFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_fragment, listFragment).commit();
            }
        }
    }
    
}