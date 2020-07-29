package com.exmple.grademanage.Fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class FragmentViewGrades extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_view_grades,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DatabaseHandler db = new DatabaseHandler(getActivity());
        Cursor cursor = db.getData();

        ArrayList<StudentInformation> arrayList = new ArrayList<>();

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);


        if(cursor.getCount() == 0) {
            Toast.makeText(getActivity(),"No Record Found",Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                arrayList.add(new StudentInformation(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3),
                        Integer.parseInt(cursor.getString(4)), Integer.parseInt(cursor.getString(5))));
            }
        }

        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(arrayList,getActivity());
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
