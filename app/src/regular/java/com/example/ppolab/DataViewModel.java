package com.example.ppolab;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DataViewModel extends ViewModel {
   private MutableLiveData<String> dataInput = new MutableLiveData<String>("0");
    private MutableLiveData<String> dataOutput = new MutableLiveData<String>("0");
    private MutableLiveData<Integer>  spinner1data= new MutableLiveData<Integer>(0);
    private MutableLiveData<Integer> spinner2data = new MutableLiveData<Integer>(0);
    private MutableLiveData<Integer> spinner3data = new MutableLiveData<Integer>(0);

    public void selectDataInput(String item) {
        dataInput.setValue(item);

    }


        public LiveData<String> getSelectedDataInput() {
        return dataInput;
    }

    public void selectDataOutput(String item) {
        dataOutput.setValue(item);

    }


    public LiveData<String> getSelectedDataOutput() {
        return dataOutput;
    }

    public void selectDataSpin1(Integer item) {
        spinner1data.setValue(item);

    }


    public LiveData<Integer> getSelectedDataSpin1() {
        return spinner1data;
    }
    public void selectDataSpin2(Integer item) {
        spinner2data.setValue(item);

    }


    public LiveData<Integer> getSelectedDataSpin2() {
        return spinner2data;
    }
    public void selectDataSpin3(Integer item) {
        spinner3data.setValue(item);

    }


    public LiveData<Integer> getSelectedDataSpin3() {
        return spinner3data;
    }

    public void convert_func(){
       String temp = getSelectedDataInput().getValue();
        assert temp != null;
        if (temp.equals("0")){
            selectDataOutput("0");
        }

        else {
            int Main_category = getSelectedDataSpin1().getValue();
            int type1 = getSelectedDataSpin2().getValue();
            int type2 = getSelectedDataSpin3().getValue();
            if (Main_category==0){
                double convert =Double.parseDouble(getSelectedDataInput().getValue());
                if(type1 == 1){ convert *=0.3048; }
                else if (type1== 2){convert = convert * 0.0254;}
                switch (type2){
                    case (0): selectDataOutput(String.valueOf(convert));
                    break;
                    case (1): selectDataOutput(String.valueOf(convert * 3.28084));
                    break;
                    case (2): selectDataOutput(String.valueOf(convert * 39.3701));
                    break;
                }

            }
                else if (Main_category==1){
                double convert =Double.parseDouble(getSelectedDataInput().getValue());
                if(type1 == 1){ convert = convert * 60; }
                else if (type1== 2){convert = convert *3600.00288;}
                switch (type2){
                    case (0): selectDataOutput(String.valueOf(convert));
                        break;
                    case (1): selectDataOutput(String.valueOf(convert * 0.000277778));
                        break;
                    case (2): selectDataOutput(String.valueOf(convert * 0.0166667));
                        break;
                }
                          }
            else if (Main_category==2){
                double convert =Double.parseDouble(getSelectedDataInput().getValue());
                if(type1 == 1){ convert = convert * 1000; }
                else if (type1== 2){convert = convert *0.453592;}
                switch (type2){
                    case (0): selectDataOutput(String.valueOf(convert));
                        break;
                    case (1): selectDataOutput(String.valueOf(convert * 0.001));
                        break;
                    case (2): selectDataOutput(String.valueOf(convert * 0.001));
                        break;
                }
            }
            else if (type1 == type2){
                selectDataOutput(getSelectedDataInput().getValue());
            }

        }



    }


}
