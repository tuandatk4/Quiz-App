package com.quizapp.example.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.quizapp.example.R;
import com.quizapp.example.model.HistoryModel;
import com.quizapp.example.ui.FinalResultActivity;
import com.quizapp.example.util.Constants;
import static com.quizapp.example.util.Utils.formatDate;

import java.util.List;

public class HistoryAdapter  extends RecyclerView.Adapter<HistoryAdapter.AttemptViewHolder> {
    private final Activity context;
    private final List<HistoryModel> attempts;

    public HistoryAdapter(List<HistoryModel> attempts, Activity context) {
        this.attempts = attempts;
        this.context=context;
    }

    @NonNull
    @Override
    public AttemptViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_history, parent, false);
        return new HistoryAdapter.AttemptViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AttemptViewHolder holder, int position) {

        HistoryModel item = attempts.get(position);

        holder.tvSubject.setText(String.valueOf(item.getSubject()));
        holder.tvEarned.setText(String.valueOf(item.getEarned()));
        holder.tvDate.setText(formatDate(item.getCreatedTime()));

        holder.cvParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, FinalResultActivity.class);
                intent.putExtra(Constants.TYPE,"history");
                intent.putExtra(Constants.CORRECT,item.getCorrect());
                intent.putExtra(Constants.INCORRECT,item.getIncorrect());
                intent.putExtra(Constants.SUBJECT,item.getSubject());
                context.startActivity(intent);
                context.finish();
            }
        });

    }

    @Override
    public int getItemCount() {
        return attempts.size();
    }

    public static class AttemptViewHolder extends RecyclerView.ViewHolder {

        public TextView tvSubject,tvEarned,tvDate;
        public CardView cvParent;

        public AttemptViewHolder(View v) {
            super(v);
            tvSubject = v.findViewById(R.id.textView23);
            tvEarned = v.findViewById(R.id.textView24);
            tvDate = v.findViewById(R.id.textView25);
            cvParent = v.findViewById(R.id.cvItemHistory);

        }
    }

}
