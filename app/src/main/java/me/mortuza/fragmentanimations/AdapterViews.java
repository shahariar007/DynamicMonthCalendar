package me.mortuza.fragmentanimations;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AdapterViews extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<String> list = new ArrayList<>();
    private Calendar calendar;
    private int toMonth;
    private int toYear;
    private int toDate;

    private int givenMonth;
    private int givenYear;

    private boolean zx = false;
    private ClickBack clickBack;


    public void setList(List<String> list, int month, int year) {
        this.list = list;
        this.givenMonth = month;
        this.givenYear = year;

        if (toMonth == givenMonth && givenYear == toYear) {
            zx = true;
        } else {
            zx = false;
        }
    }

    public void setClickBack(ClickBack clickBack) {
        this.clickBack = clickBack;
    }

    public AdapterViews(Context mContext) {
        this.mContext = mContext;
        calendar = Calendar.getInstance();
        toMonth = calendar.get(Calendar.MONTH);
        toDate = calendar.get(Calendar.DATE);
        toYear = calendar.get(Calendar.YEAR);

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return new Views(LayoutInflater.from(mContext).inflate(R.layout.row_view, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Views views = (Views) viewHolder;
        if (zx) {
            if (!list.get(i).isEmpty()) {
                int now = Integer.valueOf(list.get(i));
                if (toDate == now)
                    views.textView.setTextColor(Color.RED);
            }
            views.textView.setText(list.get(i));

        } else {
            views.textView.setText(list.get(i));
        }

        if (i % 2 == 0) {
            views.rootView_layout.setBackgroundColor(Color.parseColor("#D5CDE0"));
        } else {
            views.rootView_layout.setBackgroundColor(Color.parseColor("#CDE0D1"));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Views extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textView;
        private LinearLayout rootView_layout;

        private Views(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.dateText);
            rootView_layout = itemView.findViewById(R.id.rootView_layout);
            rootView_layout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickBack != null) {
                clickBack.backNow(givenMonth, givenYear, getAdapterPosition());
            }
        }
    }

    public interface ClickBack {
        public void backNow(int month, int year, int pos);
    }
}
