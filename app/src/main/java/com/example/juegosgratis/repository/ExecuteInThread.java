package com.example.juegosgratis.repository;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ExecuteInThread<T> {

    public T getData(Ipetition<T> petition) throws ExecutionException, InterruptedException {
        ExecutorService executor = Thread.obtenerEjecutorHilo();
        Future<T> result = executor.submit(new Callable<T>() {
            @Override
            public T call() throws Exception {
                return petition.getData();
            }
        });
        return result.get();
    }

}

interface Ipetition <T>{
    public T getData();
}
