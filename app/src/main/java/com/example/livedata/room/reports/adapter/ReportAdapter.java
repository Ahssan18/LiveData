package com.example.livedata.room.reports.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.livedata.R;
import com.example.livedata.databinding.CusReportBinding;
import com.example.livedata.room.workout.WorkOut;

import java.util.List;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.CustomReport> {

    private List<WorkOut> workOutList;
    private Context context;

    public ReportAdapter(List<WorkOut> workOutList, Context context) {
        this.workOutList = workOutList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomReport onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CusReportBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.cus_report, parent, false);
        return new CustomReport(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomReport holder, int position) {
        setData(holder, workOutList.get(position));
    }

    private void setData(CustomReport holder, WorkOut workOut) {
        holder.binding.setWorkout(workOut);
    }

    @Override
    public int getItemCount() {
        return workOutList.size();
    }

    public static class CustomReport extends RecyclerView.ViewHolder {


        CusReportBinding binding;

        public CustomReport(@NonNull CusReportBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
