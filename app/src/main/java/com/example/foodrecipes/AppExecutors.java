package com.example.foodrecipes;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

// AppExecutors is the class for executing tasks. It is a singleton.
public class AppExecutors {

    // Singleton implementation.
    private static AppExecutors instance;

    // Singleton implementation.
    public static AppExecutors getInstance() {
        if (instance == null) {
            instance = new AppExecutors();
        }
        return instance;
    }

    // This ScheduledExecutorService service can run commands after a delay. Executors are responsible to run runnable tasks. They
    // can do it on whatever thread you need and you can pass a number of threads you want to work with. In our case it is three.
    // This method is convenient to implement network timeout. We will get a reference to this class from our API client and use
    // this method to pass runnables.
    private final ScheduledExecutorService networkInputOutput = Executors.newScheduledThreadPool(3);

    // Singleton implementation constructor.
    public ScheduledExecutorService getNetworkInputOutput() {
        return networkInputOutput;
    }
}
