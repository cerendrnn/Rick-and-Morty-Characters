package com.ceren.rmcharacters;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

//This class is responsible for handling threads.
//Retrofit request are processed on the background of the threads.

public class AppExecutors {

    private static AppExecutors instance;//singleton pattern

    public static AppExecutors getInstance(){
        if(instance == null){
            instance = new AppExecutors();
        }
        return instance;
    }

    //ScheduledExecutorService adds extra functionality to AppExecutors.
    private final ScheduledExecutorService mNetworkIO = Executors.newScheduledThreadPool(3);

    public ScheduledExecutorService networkIO(){
        return mNetworkIO;
    }
}

