package com.exmple.grademanage.Fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.exmple.grademanage.DatabaseHandler;
import com.exmple.grademanage.R;

import java.util.HashMap;

public class FragmentUpdate extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_update,container,false);
    }

    private EditText etUpdateId,etUpdateFirstName,etUpdateLastName,etUpdateMarks;
    private Button btnFind,btnUpdate;
    private TextView tvId;
    private TextView tvShowId,tvShowFirstName,tvShowLastName,tvShowCourse,tvShowCredits,tvShowMarks;
    private Spinner spUpdateCourse;
    private RadioGroup rgUpdate;
    private RadioButton rbUpdate;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etUpdateId = view.findViewById(R.id.etUpdateId);
        etUpdateFirstName = view.findViewById(R.id.etUpdateFirstName);
        etUpdateLastName = view.findViewById(R.id.etUpdateLastName);
        spUpdateCourse = view.findViewById(R.id.spUpdateCourse);
        rgUpdate = view.findViewById(R.id.radioGroupUpdate);
        etUpdateMarks = view.findViewById(R.id.etUpdateMarks);

        btnFind = view.findViewById(R.id.updateSearchButton);
        btnUpdate = view.findViewById(R.id.etUpdateButton);

        tvId = view.findViewById(R.id.tvUpdateId);

        tvShowId = view.findViewById(R.id.tvUpdateIdShow);
        tvShowFirstName = view.findViewById(R.id.tvUpdateFirstNameShow);
        tvShowLastName = view.findViewById(R.id.tvUpdateLastNameShow);
        tvShowCourse = view.findViewById(R.id.tvUpdateCourseShow);
        tvShowCredits = view.findViewById(R.id.tvUpdateCreditsShow);
        tvShowMarks = view.findViewById(R.id.tvUpdateMarksShow);

        String[] a = {"PROG 8480","PROG 8470","PROG 8460","PROG 8450"};

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item,a);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spUpdateCourse.setAdapter(arrayAdapter);

        final HashMap<String,Integer> map = new HashMap<>();
        map.put("PROG 8480",0);map.put("PROG 8470",1);map.put("PROG 8460",2);map.put("PROG 8450",3);

        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                makeInvisible();

                String id = etUpdateId.getText().toString().trim();

                if(id.isEmpty()) {
                    Toast.makeText(getActivity(),"Enter valid input ",Toast.LENGTH_SHORT).show();
                    return;
                }

                DatabaseHandler db = new DatabaseHandler(getActivity());
                Cursor cursor = db.getDataById(id);
                if(cursor.getCount() == 0) {
                    Toast.makeText(getActivity(),"No record Found",Toast.LENGTH_SHORT).show();
                } else {
                    makeVisible();
                    String set= "";

                    while (cursor.moveToNext()) {

                        tvId.setText(cursor.getString(0));
                        etUpdateFirstName.setText(cursor.getString(1));
                        etUpdateLastName.setText(cursor.getString(2));
                        //noinspection ConstantConditions
                        spUpdateCourse.setSelection(map.get(cursor.getString(3)));
                        int a = Integer.parseInt(cursor.getString(4));

                        if(a == 1) {
                            rgUpdate.check(R.id.radioOneUpdate);
                        } else if(a == 2) {
                            rgUpdate.check(R.id.radioTwoUpdate);
                        } else if(a == 3) {
                            rgUpdate.check(R.id.radioThreeUpdate);
                        } else  {
                            rgUpdate.check(R.id.radioFourUpdate);
                        }

                        etUpdateMarks.setText(cursor.getString(5));
                    }

                }
                etUpdateId.setText("");
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = tvId.getText().toString().trim();
                String firstName = etUpdateFirstName.getText().toString().trim();
                String lastName = etUpdateLastName.getText().toString().trim();
                String course = spUpdateCourse.getSelectedItem().toString();
                String credits = "0";

                switch (rgUpdate.getCheckedRadioButtonId()) {
                    case R.id.radioOneUpdate:
                        credits = "1";
                        break;

                    case R.id.radioTwoUpdate:
                        credits = "2";
                        break;

                    case R.id.radioThreeUpdate:
                        credits = "3";
                        break;

                    case R.id.radioFourUpdate:
                        credits = "4";
                        break;
                }

                String marks = etUpdateMarks.getText().toString().trim();

                if(firstName.isEmpty() || lastName.isEmpty() || course.isEmpty() || marks.isEmpty()) {
                    Toast.makeText(getActivity(),"All fields Are requierd ",Toast.LENGTH_SHORT).show();
                    return;
                }

                DatabaseHandler db = new DatabaseHandler(getActivity());
                try {
                    db.updateData(Integer.parseInt(id), firstName, lastName, course, Integer.parseInt(credits), Integer.parseInt(marks));
                    Toast.makeText(getActivity(), "Data Updated Successfully ", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }

                makeInvisible();
            }
        });
    }

    private void makeInvisible() {
        tvId.setVisibility(View.INVISIBLE);
        etUpdateFirstName.setVisibility(View.INVISIBLE);
        etUpdateLastName.setVisibility(View.INVISIBLE);
        spUpdateCourse.setVisibility(View.INVISIBLE);
        rgUpdate.setVisibility(View.INVISIBLE);
        etUpdateMarks.setVisibility(View.INVISIBLE);

        tvShowId.setVisibility(View.INVISIBLE);
        tvShowFirstName.setVisibility(View.INVISIBLE);
        tvShowLastName.setVisibility(View.INVISIBLE);
        tvShowCourse.setVisibility(View.INVISIBLE);
        tvShowCredits.setVisibility(View.INVISIBLE);
        tvShowMarks.setVisibility(View.INVISIBLE);

        btnUpdate.setVisibility(View.INVISIBLE);
    }

    private void makeVisible() {
        tvId.setVisibility(View.VISIBLE);
        etUpdateFirstName.setVisibility(View.VISIBLE);
        etUpdateLastName.setVisibility(View.VISIBLE);
        spUpdateCourse.setVisibility(View.VISIBLE);
        rgUpdate.setVisibility(View.VISIBLE);
        etUpdateMarks.setVisibility(View.VISIBLE);

        tvShowId.setVisibility(View.VISIBLE);
        tvShowFirstName.setVisibility(View.VISIBLE);
        tvShowLastName.setVisibility(View.VISIBLE);
        tvShowCourse.setVisibility(View.VISIBLE);
        tvShowCredits.setVisibility(View.VISIBLE);
        tvShowMarks.setVisibility(View.VISIBLE);

        btnUpdate.setVisibility(View.VISIBLE);
    }
}
