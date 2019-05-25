package com.example.recyclerviewhardwareroom;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

@Database(entities = {Hardware.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class HardwareRoomDatabase extends RoomDatabase {

    public abstract HardwareDao hardwareDao();

    private static volatile HardwareRoomDatabase INSTANCE;

    static HardwareRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (HardwareRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            HardwareRoomDatabase.class, "hardware_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}