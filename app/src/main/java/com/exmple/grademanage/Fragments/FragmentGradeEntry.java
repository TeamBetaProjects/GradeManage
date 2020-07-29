package com.exmple.grademanage.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.exmple.grademanage.DatabaseHandler;
import com.exmple.grademanage.R;

public class FragmentGradeEntry extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_grade_entry,container,false);
    }

    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private EditText etFName,etLName,etMarks;
    private String set;
    private int credits;
    private Button btnSubmit;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Spinner spinner = view.findViewById(R.id.couresList);
        btnSubmit = view.findViewById(R.id.submit);
        etFName = view.findViewById(R.id.firstName);
        etLName = view.findViewById(R.id.lastName);
        etMarks = view.findViewById(R.id.marks);

        String[] a = {"PROG 8480","PROG 8470","PROG 8460","PROG 8450"};

        set = "PROG 8480";

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item,a);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                set = (String)item;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        radioGroup = view.findViewById(R.id.radioGroup);

        credits = 1;
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.radioOne:
                        credits = 1;
                        break;

                    case R.id.radioTwo:
                        credits = 2;
                        break;

                    case R.id.radioThree:
                        credits = 3;
                        break;

                    case R.id.radioFour:
                        credits = 4;
                        break;
                }
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fname =  etFName.getText().toString().trim();
                String lname = etLName.getText().toString().trim();
                String marks = etMarks.getText().toString().trim();

                if(fname.isEmpty() || lname.isEmpty() || marks.isEmpty()) {
                    Toast.makeText(view.getContext(),"All field are required",Toast.LENGTH_SHORT).show();
                } else {
                    int marksValue = Integer.parseInt(marks);
                    if (marksValue < 0) {
                        Toast.makeText(view.getContext(), "Marks cannot be negative", Toast.LENGTH_SHORT).show();
                    } else {

                        try {

                            DatabaseHandler db = new DatabaseHandler(view.getContext());
                            boolean flag = db.insertData(fname, lname, set, credits, marksValue);

                            if (flag) {
                                Toast.makeText(view.getContext(), "Successful", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(view.getContext(), "Something Went Wrong!", Toast.LENGTH_SHORT).show();
                            }

                            etMarks.setText("");
                            etLName.setText("");
                            etFName.setText("");

                        } catch (Exception e) {
                            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }

    public void checkButton (View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = v.findViewById(radioId);
    }


}
