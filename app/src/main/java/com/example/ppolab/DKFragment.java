package com.example.ppolab;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;


public class DKFragment extends Fragment {

    public void copy1() {
        ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(getString(R.string.randomm), InputModel.getSelectedDataInput().getValue());
        clipboard.setPrimaryClip(clip);

    }
    public void copy2() {
        ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(getString(R.string.randomm), InputModel.getSelectedDataOutput().getValue());
        clipboard.setPrimaryClip(clip);

    }
    private void clear_text () {
        InputModel.selectDataInput("0");
        InputModel.selectDataOutput("0");
    }

    private void change_text (String StringValue){
        String value = InputModel.getSelectedDataInput().getValue();
        if (value.equals("0")) {
            value = StringValue;
        } else {
            value += StringValue;
        }
        InputModel.selectDataInput(value);
    }
    private void change_text_c () {
        String value = InputModel.getSelectedDataInput().getValue();

        value += ".";

        InputModel.selectDataInput(value);
    }

    private void change_text_b () {
        String value = InputModel.getSelectedDataInput().getValue();
        if (!value.equals("0")) {
            if (value.length() == 1) {
                value = "0";
            } else {
                value = value.substring(0, value.length() - 1);
            }
        }
        InputModel.selectDataInput(value);
    }
 
    Button b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, bd, bc,bdel,bcopy1,bcopy2,bconv;
    DataViewModel InputModel;
    


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =
                inflater.inflate(R.layout.main_buttons, container, false);

        InputModel = ViewModelProviders.of(requireActivity()).get(DataViewModel.class);

        if (v.findViewById(R.id.buttonswap) != null){
            bcopy1 = (Button) v.findViewById(R.id.copy1);
        bcopy2 = (Button) v.findViewById(R.id.copy2);
    }
        b0 = (Button) v.findViewById(R.id.button0);
        b1 = (Button) v.findViewById(R.id.button1);
        b2 = (Button) v.findViewById(R.id.button2);
        b3 = (Button) v.findViewById(R.id.button3);
        b4 = (Button) v.findViewById(R.id.button4);
        b5 = (Button) v.findViewById(R.id.button5);
        b6 = (Button) v.findViewById(R.id.button6);
        b7 = (Button) v.findViewById(R.id.button7);
        b8 = (Button) v.findViewById(R.id.button8);
        b9 = (Button) v.findViewById(R.id.button9);
        bc = (Button) v.findViewById(R.id.buttonc);
        bd = (Button) v.findViewById(R.id.buttonb);
        bdel = (Button) v.findViewById(R.id.delete);
        bconv =(Button) v.findViewById(R.id.convert);

        View.OnClickListener btcopy1 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copy1();
            }
        };
        View.OnClickListener btcopy2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copy2();
            }
        };
        View.OnClickListener btdel = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_text();
            }
        };
        View.OnClickListener btc = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change_text_c ();
            }
        };
        View.OnClickListener btd = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change_text_b ();
            }
        };
        View.OnClickListener bt0 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change_text("0");
            }
        };
        View.OnClickListener bt1 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change_text("1");
            }
        };
        View.OnClickListener bt2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change_text("2");
            }
        };
        View.OnClickListener bt3 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change_text("3");
            }
        };
        View.OnClickListener bt4 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change_text("4");
            }
        };
        View.OnClickListener bt5 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change_text("5");
            }
        };
        View.OnClickListener bt6 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change_text("6");
            }
        };
        View.OnClickListener bt7 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change_text("7");
            }
        };
        View.OnClickListener bt8 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change_text("8");
            }
        };
        View.OnClickListener bt9 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change_text("9");
            }
        };
        View.OnClickListener oclButtonConvert = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                InputModel.convert_func();
            }
        };

        if (v.findViewById(R.id.buttonswap) != null){
            bcopy1.setOnClickListener(btcopy1);
            bcopy2.setOnClickListener(btcopy2);}

        bc.setOnClickListener( btc);
        bd.setOnClickListener( btd);
        b0.setOnClickListener(bt0);
        b1.setOnClickListener( bt1);
        b2.setOnClickListener( bt2);
        b3.setOnClickListener( bt3);
        b4.setOnClickListener(bt4);
        b5.setOnClickListener(bt5);
        b6.setOnClickListener( bt6);
        b7.setOnClickListener(bt7);
        b8.setOnClickListener( bt8);
        b9.setOnClickListener(bt9);
        bdel.setOnClickListener(btdel);
        bconv.setOnClickListener(oclButtonConvert);
        return v;
    }




    }
