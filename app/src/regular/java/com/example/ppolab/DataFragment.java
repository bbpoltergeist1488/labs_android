package com.example.ppolab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class DataFragment extends Fragment {



    DataViewModel InputModel;
    TextView DataInput,DataOutput;
    Spinner Spinner_category,spinner1,spinner2;
    String[] category ={"Длина","Время","Масса"};
    String[] typeLength ={"Метр","Футы","Дюймы"};
    String[] typeTime ={"Секунда","Минута","Час"};
    String[] typeMass ={"Килограмм","Тонна","Фунт"};
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.input, container, false);
        DataInput = (TextView)v.findViewById(R.id.textview1);
        DataOutput= (TextView)v.findViewById(R.id.textview2);


        InputModel = ViewModelProviders.of(requireActivity()).get(DataViewModel.class);

        InputModel.getSelectedDataInput().observe(getViewLifecycleOwner(), new Observer<String>()
         {

            @Override
            public void onChanged(@Nullable String s) {
                DataInput.setText(s);
            }
        });
        InputModel.getSelectedDataOutput().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                DataOutput.setText(s);
            }
        });

        Spinner_category = (Spinner) v.findViewById(R.id.main_spin);
        spinner1 = (Spinner) v.findViewById(R.id.spinner1);
        spinner2 = (Spinner) v.findViewById(R.id.spinner2);

        InputModel.getSelectedDataSpin1().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer s) {
                Spinner_category.setSelection(s);
            }
        });
        InputModel.getSelectedDataSpin2().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer s) {
                spinner1.setSelection(s);
            }
        });
        InputModel.getSelectedDataSpin3().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer s) {
                spinner2.setSelection(s);
            }
        });

       final  ArrayAdapter<String> Main_adapter= new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, category);
        Main_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         final ArrayAdapter<String> Dist_adapter = new ArrayAdapter<String>(v.getContext(), android.R.layout.simple_spinner_item, typeLength);
        Dist_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final ArrayAdapter<String> Time_adapter = new ArrayAdapter<String>(v.getContext(), android.R.layout.simple_spinner_item, typeTime);
        Time_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

       final ArrayAdapter<String> Weight_adapter = new ArrayAdapter<String>(v.getContext(), android.R.layout.simple_spinner_item, typeMass);
        Weight_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner_category.setAdapter(Main_adapter);

        Spinner_category.setSelection(InputModel.getSelectedDataSpin1().getValue());
        AdapterView.OnItemSelectedListener Data_main = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {



                InputModel.selectDataSpin1(pos);

                if (pos== 0){
                    spinner1.setAdapter(Dist_adapter);
                    spinner2.setAdapter(Dist_adapter);
                }
                else if (pos == 1){
                    spinner1.setAdapter(Time_adapter);
                    spinner2.setAdapter(Time_adapter);
                }
                else if (pos== 2){
                    spinner1.setAdapter(Weight_adapter);
                    spinner2.setAdapter(Weight_adapter);
                }
                spinner1.setSelection(InputModel.getSelectedDataSpin2().getValue());
                spinner2.setSelection(InputModel.getSelectedDataSpin3().getValue());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };


        AdapterView.OnItemSelectedListener Data_from = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                InputModel.selectDataSpin2(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };

        AdapterView.OnItemSelectedListener Data_to = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                InputModel.selectDataSpin3(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinner1.setOnItemSelectedListener(Data_from);
        spinner2.setOnItemSelectedListener(Data_to);
        Spinner_category.setOnItemSelectedListener(Data_main);
        return v;}






}
