package com.example.littleshelf.HomePage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.littleshelf.Main.Databases.GroceriesDataBaseHelper;
import com.example.littleshelf.Main.GroceryItem.GroceryItem;
import com.example.littleshelf.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.w3c.dom.Text;

import java.time.LocalDate;
import java.util.ArrayList;

public class HomePage extends AppCompatActivity {

    private GroceriesDataBaseHelper groceriesDataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hp_activity_home_page);

        // Creating database for current groceries
        groceriesDataBaseHelper = new GroceriesDataBaseHelper(this);

        int freshAmount = 0;
        int expiredAmount = 0;

        for (GroceryItem groceryItem : groceriesDataBaseHelper.getAllItems()) {
            if (groceryItem.isFresh()) {
                ++freshAmount;
            }
            else {
                ++expiredAmount;
            }
        }

        LinearLayout weekCalendar = findViewById(R.id.linearLayoutWeekCalendar);
        LocalDate date = LocalDate.of(2023, 9, 29);

        for (int i = 0; i < weekCalendar.getChildCount(); i++) {
            LinearLayout linearLayout = ((LinearLayout)weekCalendar.getChildAt(i));

            ((TextView)linearLayout.getChildAt(0)).setText(date.getDayOfWeek().toString().substring(0, 1));
            ((TextView)linearLayout.getChildAt(2)).setText(String.valueOf(date.getDayOfMonth()));
            date = date.plusDays(1);
        }

        createPieChart(freshAmount, expiredAmount);

        ((TextView)findViewById(R.id.textViewFresh)).setText(String.valueOf(freshAmount));
        ((TextView)findViewById(R.id.textViewExpired)).setText(String.valueOf(expiredAmount));

    }

    private void createPieChart(int freshAmount, int expiredAmount) {
        ArrayList<PieEntry> items = new ArrayList<>();
        items.add(new PieEntry(freshAmount));
        items.add(new PieEntry(expiredAmount));

        PieDataSet pieDataSet = new PieDataSet(items, "");
        pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        PieData pieData = new PieData(pieDataSet);

        PieChart pieChart = findViewById(R.id.pieChart);

        pieChart.setData(pieData);
        pieChart.setCenterText(String.valueOf(freshAmount + expiredAmount));
        Description description = new Description();
        description.setText("");
        pieChart.setDescription(description);

        // Disable rotation
        pieChart.setRotationEnabled(false);

        // Disable slice selection (highlighting when touched)
        pieChart.setHighlightPerTapEnabled(false);

        Legend legend = pieChart.getLegend();
        legend.setEnabled(false);
        pieChart.invalidate();

        pieChart.highlightValues(new Highlight[]
                {new Highlight(0, 0, 0),
                        new Highlight(1, 0, 0)});

        pieChart.setHoleRadius(80f);
        pieChart.setHoleColor(android.R.color.transparent);
        pieChart.setTransparentCircleRadius(0f);
    }

}