package com.example.recyclerviewhardwareroom;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class HardwareViewModel extends AndroidViewModel {
    private HardwareRepository mRepository;
    private LiveData<List<Hardware>> mAllHardware;

    public HardwareViewModel(@NonNull Application application) {
        super(application);
        mRepository = new HardwareRepository(application);
        mAllHardware = mRepository.getmAllHardware();
    }
    //insert
    public void insert(Hardware hardware) { mRepository.insert(hardware); }
    //delete
    public void delete(Hardware hardware) { mRepository.delete(hardware); }

    LiveData<List<Hardware>> getmAllHardware() { return mAllHardware; }
}

