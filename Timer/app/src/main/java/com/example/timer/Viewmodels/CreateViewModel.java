package com.example.timer.Viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class CreateViewModel extends ViewModel {


    private MutableLiveData<Integer> Prepare = new MutableLiveData<>(0);
    private MutableLiveData<Integer> Work = new MutableLiveData<>(0);
    private MutableLiveData<Integer> Rest = new MutableLiveData<>(0);
    private MutableLiveData<Integer> Sets = new MutableLiveData<>(0);
    private MutableLiveData<Integer> Cycle = new MutableLiveData<>(0);

    public MutableLiveData<Integer> getPrepare() {
        if (Prepare == null) {
            Prepare = new MutableLiveData<>(0);
        }
        return Prepare;
    }

    public MutableLiveData<Integer> getWork() {
        if (Work == null) {
            Work = new MutableLiveData<>(0);
        }
        return Work;
    }

    public MutableLiveData<Integer> getRest() {
        if (Rest == null) {
            Rest = new MutableLiveData<>(0);
        }
        return Rest;
    }

    public MutableLiveData<Integer> getSets() {
        if (Sets == null) {
            Sets = new MutableLiveData<>(0);
        }
        return Sets;
    }

    public MutableLiveData<Integer> getCycle() {
        if (Cycle == null) {
            Cycle = new MutableLiveData<>(0);
        }
        return Cycle;
    }

    public void setPrepare(int prepare) {
        if (Prepare == null) {
            Prepare = new MutableLiveData<>(0);
        }
        Prepare.setValue(prepare);
    }

    public void setWork(int work) {
        if (Work == null) {
            Work = new MutableLiveData<>(0);
        }
        Work.setValue(work);
    }

    public void setRest(int rest) {
        if (Rest == null) {
            Rest = new MutableLiveData<>(0);
        }
        Rest.setValue(rest);
    }

    public void setSets(int sets) {
        if (Sets == null) {
            Sets = new MutableLiveData<>(0);
        }
        Sets.setValue(sets);
    }

    public void setCycle(int cycle) {
        if (Cycle == null) {
            Cycle = new MutableLiveData<>(0);
        }
        Cycle.setValue(cycle);
    }

    public void PrepareInc() {
        if (Prepare == null) {
            Prepare = new MutableLiveData<>(0);
        }
        Prepare.setValue(Prepare.getValue() + 1);
    }

    public void PrepareDec() {
        if (Prepare == null) {
            Prepare = new MutableLiveData<>(0);
        }
        Prepare.setValue(Prepare.getValue() - 1);
    }

    public void WorkInc() {
        if (Work == null) {
            Work = new MutableLiveData<>(0);
        }
        Work.setValue(Work.getValue() + 1);
    }

    public void WorkDec() {
        if (Work == null) {
            Work = new MutableLiveData<>(0);
        }
        Work.setValue(Work.getValue() - 1);
    }

    public void RestInc() {
        if (Rest == null) {
            Rest = new MutableLiveData<>(0);
        }
        Rest.setValue(Rest.getValue() + 1);
    }

    public void RestDec() {
        if (Rest == null) {
            Rest = new MutableLiveData<>(0);
        }
        Rest.setValue(Rest.getValue() - 1);
    }

    public void SetsInc() {
        if (Sets == null) {
            Sets = new MutableLiveData<>(0);
        }
        Sets.setValue(Sets.getValue() + 1);
    }

    public void SetsDec() {
        if (Sets == null) {
            Sets = new MutableLiveData<>(0);
        }
        Sets.setValue(Sets.getValue() - 1);
    }

    public void CycleInc() {
        if (Cycle == null) {
            Cycle = new MutableLiveData<>(0);
        }
        Cycle.setValue(Cycle.getValue() + 1);
    }

    public void CycleDec() {
        if (Cycle == null) {
            Cycle = new MutableLiveData<>(0);
        }
        Cycle.setValue(Cycle.getValue() - 1);
    }
}
