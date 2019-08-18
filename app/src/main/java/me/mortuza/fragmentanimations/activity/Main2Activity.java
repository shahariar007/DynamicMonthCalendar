package me.mortuza.fragmentanimations.activity;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormatSymbols;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import me.mortuza.fragmentanimations.AdapterViews;
import me.mortuza.fragmentanimations.ModelQ;
import me.mortuza.fragmentanimations.ModelX.ModelN;
import me.mortuza.fragmentanimations.MultipleTouch;
import me.mortuza.fragmentanimations.R;

public class Main2Activity extends AppCompatActivity {

    private static final int SELECTEDMONTH = 6;
    int OFFSET = 0;
    int MONTHDAYS = 0;
    RecyclerView calView;
    ImageView previous, next;
    List<String> strings = new ArrayList<>();
    private Calendar calendar;
    TextView monthText;
    String[] weekdays = new DateFormatSymbols().getWeekdays();
    String[] month = new DateFormatSymbols().getMonths();
    private AdapterViews adapterViews;
    private int MONTH_CAP = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        calView = findViewById(R.id.calView);
        previous = findViewById(R.id.previous);
        next = findViewById(R.id.next);
        monthText = findViewById(R.id.month);
        calendar = Calendar.getInstance();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 7);
        calView.setLayoutManager(gridLayoutManager);
        adapterViews = new AdapterViews(this);
        adapterViews.setList(strings, calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR));
        calView.setAdapter(adapterViews);
        setDate();

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (calendar.get(Calendar.MONTH) == 0) {
                    calendar.roll(Calendar.MONTH, false);
                    calendar.add(Calendar.YEAR, -1);
                } else {
                    calendar.roll(Calendar.MONTH, false);
                }

                Log.d("Main2Activity", "onClick: " + calendar.get(Calendar.MONTH));
                setDate();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (calendar.get(Calendar.MONTH) == 11) {
                    calendar.roll(Calendar.MONTH, true);
                    calendar.add(Calendar.YEAR, 1);
                } else {
                    calendar.roll(Calendar.MONTH, true);
                }
                Log.d("Main2Activity", "onClick: " + calendar.get(Calendar.MONTH));
                setDate();
            }
        });


        adapterViews.setClickBack(new AdapterViews.ClickBack() {
            @Override
            public void backNow(int month, int year, int pos) {
                if (!strings.get(pos).isEmpty()) {
                    Toast.makeText(Main2Activity.this, "Date= " + strings.get(pos) + "/" + (month + 1) + "/" + year, Toast.LENGTH_SHORT).show();
                }
            }
        });
        //calView.addOnItemTouchListener(new MultipleTouch());


        ModelQ modelQ = new ModelQ();//. parent

        List<ModelN> modelNS = new ArrayList<>(); // parent list

        ModelN d = new ModelN("A", "B");
        modelNS.add(d); // parent list child
        modelQ.setS("XX");
        modelQ.setX(modelNS); // parent list addred on parent


        ModelN x = new ModelN("D", "E");
        modelNS.add(x);
        Log.d("Main2Activity", "onCreate: " + modelQ.getX().size());
        Log.d("Main2Activity", "onCreate: " + modelQ.getX().get(0).getC());
        Log.d("Main2Activity", "onCreate: " + modelQ.getX().get(1).getC());


    }

    //TODO MAIN DATE SET
    @SuppressLint("DefaultLocale")
    public void setDates() {
        strings.clear();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        OFFSET = calendar.get(Calendar.DAY_OF_WEEK);
        MONTHDAYS = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        monthText.setText(String.format("%s %d", month[calendar.get(Calendar.MONTH)], calendar.get(Calendar.YEAR)));
        for (int i = 1; i < (MONTHDAYS + OFFSET); i++) {
            Log.d("Main2Activity", "onCreate: " + i);
            if (i >= OFFSET && (MONTHDAYS + OFFSET) > i) {
                strings.add("" + (i + 1 - (OFFSET)));
            } else {
                strings.add("");
            }
        }
        adapterViews.setList(strings, calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR));
        adapterViews.notifyDataSetChanged();
    }

    @SuppressLint("DefaultLocale")
    public void setDate() {
        strings.clear();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        OFFSET = calendar.get(Calendar.DAY_OF_WEEK);
        MONTHDAYS = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        monthText.setText(String.format("%s %d", month[calendar.get(Calendar.MONTH)], calendar.get(Calendar.YEAR)));
        for (int i = 1; i < (MONTHDAYS + OFFSET); i++) {
            Log.d("Main2Activity", "onCreate: " + i);
            if (i >= OFFSET && (MONTHDAYS + OFFSET) > i) {
                strings.add("" + (i + 1 - (OFFSET)));
            } else {
                strings.add("");
            }
        }
        adapterViews.setList(strings, calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR));
        adapterViews.notifyDataSetChanged();
    }


//Log.d("Main2Activity", "getMinimalDaysInFirstWeek: " + calendar.getMinimalDaysInFirstWeek());
//        Log.d("Main2Activity", "getFirstDayOfWeek: " + calendar.getFirstDayOfWeek());
//        Log.d("Main2Activity", "getFirstDayOfWeek: " + Arrays.toString(weekdays));
//        Log.d("Main2Activity", "getFirstDayOfWeek: " + Arrays.toString(month));
//        Log.d("Main2Activity", "getMaximum: " + calendar.getMaximum(Calendar.DAY_OF_MONTH));
//        Log.d("Main2Activity", "getActualMaximum: " + calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
//    String weekDay = "";
//    int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
//        if (Calendar.MONDAY == dayOfWeek) weekDay = "monday=" + dayOfWeek;
//        else if (Calendar.TUESDAY == dayOfWeek) weekDay = "tuesday=" + dayOfWeek;
//        else if (Calendar.WEDNESDAY == dayOfWeek) weekDay = "wednesday=" + dayOfWeek;
//        else if (Calendar.THURSDAY == dayOfWeek) weekDay = "thursday=" + dayOfWeek;
//        else if (Calendar.FRIDAY == dayOfWeek) weekDay = "friday=" + dayOfWeek;
//        else if (Calendar.SATURDAY == dayOfWeek) weekDay = "saturday=" + dayOfWeek;
//        else if (Calendar.SUNDAY == dayOfWeek) weekDay = "sunday=" + dayOfWeek;
//        Log.d("Main2Activity", "weekDay: " + weekDay);

    // monthText.setText(month[calendar.get(Calendar.MONTH)]);
//    int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
//    String weekDay = "";
//        if (Calendar.MONDAY == dayOfWeek) weekDay = "monday=" + dayOfWeek;
//        else if (Calendar.TUESDAY == dayOfWeek) weekDay = "tuesday=" + dayOfWeek;
//        else if (Calendar.WEDNESDAY == dayOfWeek) weekDay = "wednesday=" + dayOfWeek;
//        else if (Calendar.THURSDAY == dayOfWeek) weekDay = "thursday=" + dayOfWeek;
//        else if (Calendar.FRIDAY == dayOfWeek) weekDay = "friday=" + dayOfWeek;
//        else if (Calendar.SATURDAY == dayOfWeek) weekDay = "saturday=" + dayOfWeek;
//        else if (Calendar.SUNDAY == dayOfWeek) weekDay = "sunday=" + dayOfWeek;
//        Log.d("Main2Activity", "weekDay: " + weekDay);
//
//        Log.d("Main2Activity", "onCreate: " + strings.toString());
//        Log.d("Main2Activity", "onCreate: " + (MONTHDAYS + OFFSET));
}
