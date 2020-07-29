package com.exmple.grademanage.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.exmple.grademanage.R;
import com.exmple.grademanage.StudentInformation;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<StudentInformation> info = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(ArrayList<StudentInformation> info, Context mContext) {
        this.info = info;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvId.setText("Id : " + info.get(position).getId());
        holder.tvFirstName.setText("First Name : " + info.get(position).getFirstName());
        holder.tvLastName.setText("Last Name : " + info.get(position).getLastName());
        holder.tvCourse.setText("Course : " + info.get(position).getCourse());
        holder.tvCredits.setText("Credits : " + info.get(position).getCredits());
        holder.tvMarks.setText("Marks : " + info.get(position).getMarks());
    }

    @Override
    public int getItemCount() {
        return info.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvId,tvFirstName,tvLastName,tvCourse,tvCredits,tvMarks;
        LinearLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            tvId = itemView.findViewById(R.id.idDb);
            tvFirstName = itemView.findViewById(R.id.firstNameDb);
            tvLastName = itemView.findViewById(R.id.lastNameDb);
            tvCourse = itemView.findViewById(R.id.couresDb);
            tvCredits = itemView.findViewById(R.id.creditsDb);
            tvMarks = itemView.findViewById(R.id.marksDb);
        }
    }
}
