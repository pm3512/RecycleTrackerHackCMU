package com.example.recycletracker;

import android.app.Activity;
import android.graphics.DashPathEffect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.androidplot.ui.DynamicTableModel;
import com.androidplot.ui.TableOrder;
import com.androidplot.util.PixelUtils;
import com.androidplot.xy.BoundaryMode;
import com.androidplot.xy.CatmullRomInterpolator;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYGraphWidget;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.Arrays;


public class StatsPersonalFragment extends Fragment {

    private XYPlot plot;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // initialize our XYPlot reference:
        View view = inflater.inflate(R.layout.fragment_stats_personal, container, false);
        plot = (XYPlot) view.findViewById(R.id.plot);

        // create a couple arrays of y-values to plot:
        final String[] domainLabels = {"Sep 11", "Sep 18", "Sep 25", "Oct 2"};
        Number[] series1Numbers = {15, 14, 20, 17};
        Number[] series2Numbers = {9, 17, 16, 15};
        Number[] series3Numbers = {3, 5, 4, 4};

        // turn the above arrays into XYSeries':
        // (Y_VALS_ONLY means use the element index as the x value)
        XYSeries series1 = new SimpleXYSeries(
                Arrays.asList(series1Numbers), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Plastic");
        XYSeries series2 = new SimpleXYSeries(
                Arrays.asList(series2Numbers), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Aluminum");
        XYSeries series3 = new SimpleXYSeries(
                Arrays.asList(series3Numbers), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Paper");

        // create formatters to use for drawing a series using LineAndPointRenderer
        // and configure them from xml:
        LineAndPointFormatter series1Format =
                new LineAndPointFormatter(getContext(), R.xml.line_point_formater_with_labels);

        LineAndPointFormatter series2Format =
                new LineAndPointFormatter(getContext(), R.xml.line_point_formater_with_labels2);

        LineAndPointFormatter series3Format =
                new LineAndPointFormatter(getContext(), R.xml.line_point_formater_with_labels3);

        // (optional) add some smoothing to the lines:
        // see: http://androidplot.com/smooth-curves-and-androidplot/
        series1Format.setInterpolationParams(
                new CatmullRomInterpolator.Params(10, CatmullRomInterpolator.Type.Centripetal));

        series2Format.setInterpolationParams(
                new CatmullRomInterpolator.Params(10, CatmullRomInterpolator.Type.Centripetal));

        series3Format.setInterpolationParams(
                new CatmullRomInterpolator.Params(10, CatmullRomInterpolator.Type.Centripetal));

        // add a new series' to the xyplot:
        plot.addSeries(series1, series1Format);
        plot.addSeries(series2, series2Format);
        plot.addSeries(series3, series3Format);
        plot.getLegend().setWidth(0.85f);
        plot.getGraph().getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).setFormat(new Format() {
            @Override
            public StringBuffer format(Object obj, @NonNull StringBuffer toAppendTo, @NonNull FieldPosition pos) {
                float i = ((Number) obj).floatValue();
                System.out.println(((Number) obj).floatValue());
                if (Math.abs(i - Math.round(i)) < 0.0001) {
                    return toAppendTo.append(domainLabels[Math.round(i)]);
                }
                return toAppendTo;
            }
            @Override
            public Object parseObject(String source, @NonNull ParsePosition pos) {
                return null;
            }
        });

        Button button = (Button) view.findViewById(R.id.switch_to_global);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                swapFragment();
            }
        });

        return view;
    }
    private void swapFragment(){
        GlobalFragment newEntryFragment = new GlobalFragment();
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, newEntryFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}