package com.example.islamiapp.fragments.tasbeh;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.islamiapp.R;

public class TasbehFragment extends Fragment {


    public TasbehFragment() {
        // Required empty public constructor
    }
    int counter = 0;
    int totalCounter = 0;
    TextView count;
    TextView totalCount;
    Spinner tasbe7_spinner;
    ImageView sebhaImage;
    Button reset;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =inflater.inflate(R.layout.fragment_tasbeh, container, false);
        count = view.findViewById(R.id.single);
        totalCount = view.findViewById(R.id.total);
        tasbe7_spinner = view.findViewById(R.id.tasbe7_spinner);
        sebhaImage = view.findViewById(R.id.sebhaimage);
        reset =  view.findViewById(R.id.recet);
        Log.e("hiiii", "" + tasbe7_spinner);
        tasbe7_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                counter = 0;
                count.setText(counter + "");
                totalCount.setText(totalCounter + "");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sebhaImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                totalCounter++;
                if (count != null)
                    count.setText(counter + "");
                if (totalCount != null)

                    totalCount.setText(totalCounter + "");
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter = 0;
                totalCounter = 0;
                if (count != null)
                    count.setText(counter + "");
                if (totalCount != null)
                    totalCount.setText(totalCounter + "");
            }
        });
        return view;

    }


}