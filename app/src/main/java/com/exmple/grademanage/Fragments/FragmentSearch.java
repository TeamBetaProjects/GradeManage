package com.exmple.grademanage.Fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.exmple.grademanage.Adapter.RecyclerViewAdapter;
import com.exmple.grademanage.DatabaseHandler;
import com.exmple.grademanage.R;
import com.exmple.grademanage.StudentInformation;

import java.util.ArrayList;
import java.util.Objects;

public class FragmentSearch extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private EditText etQuery;
    private Button submitSearch;
    private ListView listView;
    private RecyclerView recyclerView, listViewId;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        radioGroup = view.findViewById(R.id.radioGroupSearch);
        radioButton = view.findViewById(R.id.idSearch);

        etQuery = view.findViewById(R.id.etQuery);
        submitSearch = view.findViewById(R.id.searchButton);
        listView = view.findViewById(R.id.listViewSearch);
        recyclerView = view.findViewById(R.id.recyclerViewSearch);
        listViewId = view.findViewById(R.id.listViewId);


        submitSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = etQuery.getText().toString().trim();

                if (id.isEmpty()) {
                    Toast.makeText(getActivity(), "Enter valid input ", Toast.LENGTH_SHORT).show();
                    listViewId.setAdapter(null);
                    return;
                }

                DatabaseHandler db = new DatabaseHandler(getActivity());
                Cursor cursor = db.getDataById(id);
                if (cursor.getCount() == 0) {
                    Toast.makeText(getActivity(), "No record Found", Toast.LENGTH_SHORT).show();
                    listViewId.setAdapter(null);
                } else {

                    ArrayList<StudentInformation> arrayList = new ArrayList<>();
                    while (cursor.moveToNext()) {
                        arrayList.add(new StudentInformation(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3),
                                Integer.parseInt(cursor.getString(4)), Integer.parseInt(cursor.getString(5))));
                    }
                    listViewId.setAdapter(new RecyclerViewAdapter(arrayList, getActivity()));
                    listViewId.setLayoutManager(new LinearLayoutManager(getActivity()));
                }
                etQuery.setText("");
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.idSearch:
                        etQuery.setVisibility(View.VISIBLE);
                        submitSearch.setVisibility(View.VISIBLE);
                        listView.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.GONE);
                        listViewId.setVisibility(View.VISIBLE);

                        break;
                    case R.id.codeSearch:
                        etQuery.setVisibility(View.GONE);
                        submitSearch.setVisibility(View.GONE);
                        listViewId.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        listView.setVisibility(View.VISIBLE);

                        final ArrayList<String> programList = new ArrayList<>();
                        programList.add("PROG 8480");
                        programList.add("PROG 8470");
                        programList.add("PROG 8460");
                        programList.add("PROG 8450");

                        listView.setAdapter(new ArrayAdapter<String>(Objects.requireNonNull(getActivity()), android.R.layout.simple_list_item_1, programList));

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                String s = programList.get(i);

                                DatabaseHandler db = new DatabaseHandler(getActivity());
                                Cursor cursor = db.getDataByProgrammeId(s);

                                if (cursor.getCount() == 0) {
                                    Toast.makeText(getActivity(), "No record Found", Toast.LENGTH_SHORT).show();
                                    recyclerView.setAdapter(null);
                                } else {
                                    ArrayList<StudentInformation> arrayList = new ArrayList<>();
                                    while (cursor.moveToNext()) {
                                        arrayList.add(new StudentInformation(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3),
                                                Integer.parseInt(cursor.getString(4)), Integer.parseInt(cursor.getString(5))));
                                    }
                                    RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(arrayList, getActivity());
                                    recyclerView.setAdapter(recyclerViewAdapter);
                                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                                }

                            }
                        });
                        break;
                }
            }
        });


    }
}
