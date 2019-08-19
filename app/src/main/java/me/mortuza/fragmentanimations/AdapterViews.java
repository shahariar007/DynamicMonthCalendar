package me.mortuza.fragmentanimations;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
    private long maxDate = 0;
    private long minDate = 0;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.US);
    NumberFormat formatter = new DecimalFormat("00");


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

    public void setMaxDate(long maxDate) {
        this.maxDate = maxDate;
    }

    public void setMinDate(long minDate) {
        this.minDate = minDate;
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
            views.textView.setText(list.get(i));
            //views.textView.setTag(1);
            if (!list.get(i).isEmpty()) {
                int now = Integer.valueOf(list.get(i));
                if (toDate == now) {
                    views.textView.setTextColor(Color.RED);
                    views.imageViews.setVisibility(View.VISIBLE);
                }
            }


        } else {
            views.textView.setText(list.get(i));
           // views.textView.setTag(1);
            views.imageViews.setVisibility(View.INVISIBLE);

        }

        if (!list.get(i).isEmpty()) {
            {
                boolean point = makeDate(list.get(i));
                if (point) {
                    views.textView.setTextColor(Color.GRAY);
                    views.textView.setTag(0);
                } else {
                    Log.d("AdapterViews", "onBindViewHolder: pointer null");
                   views.textView.setTextColor(Color.BLACK);
                    views.textView.setTag(1);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Views extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textView;
        private LinearLayout rootView_layout;
        private ImageView imageViews;

        private Views(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.dateText);
            imageViews = itemView.findViewById(R.id.imageViews);
            rootView_layout = itemView.findViewById(R.id.rootView_layout);
           // rootView_layout.setOnClickListener(this);
            textView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickBack != null && view.getId() == R.id.dateText && textView.getTag().equals(1)) {
                clickBack.backNow(givenMonth, givenYear, getAdapterPosition());
            } else {
                Toast.makeText(mContext, "Sorry target date not legal", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public interface ClickBack {
        void backNow(int month, int year, int pos);
    }

    private boolean makeDate(String date) {
        String givenDateString = formatter.format(Integer.valueOf(date));
        String givenMonthString = formatter.format((givenMonth + 1));
        date = givenDateString + "/" + givenMonthString + "/" + givenYear + " 00:00:00";
        Log.d("AdapterViews", "makeDate: " + date);

        boolean dec = true;
        try {
            Date givenDate = simpleDateFormat.parse(date);

            Log.d("AdapterViews", "makeDate: " + givenDate.getTime() + " maxDate=" + maxDate + " minDate=" + minDate);

            if (givenDate.getTime() < maxDate && givenDate.getTime() > minDate) {
                dec = false;
            }


        } catch (ParseException e) {
            e.printStackTrace();
            Log.d("AdapterViews", "makeDate: parse error");

        }
        return dec;
    }

}
