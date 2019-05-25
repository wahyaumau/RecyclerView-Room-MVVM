package com.example.recyclerviewhardwareroom;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.support.annotation.NonNull;

import java.util.List;

@Dao
public interface HardwareDao {
    @Insert
    void insertHardware(Hardware hardware);

    @Delete
    void deleteHardware(Hardware hardware);

    @Query("DELETE FROM Hardware")
    void deleteAllHardware();

    @Query("SELECT * from Hardware")
    LiveData<List<Hardware>> getAllHardware();

    @Query("Select * from Hardware where id=:id")
    Hardware getHardware(@NonNull int id);

}
