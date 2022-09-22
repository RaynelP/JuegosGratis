package com.example.juegosgratis.repository;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Thread {

    public static ExecutorService obtenerEjecutorHilo(){
        if(ejecutor == null){
            ejecutor = Executors.newFixedThreadPool(CANTIDAD_DE_HILOS);
        }
        return ejecutor;
    }

    private static ExecutorService ejecutor;
    private static final byte CANTIDAD_DE_HILOS = 1;
}
