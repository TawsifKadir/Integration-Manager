package com.kit.integrationmanager.event.bus;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class SimpleEventBus {
    private final PublishSubject<Object> mBusSubject;
    private static SimpleEventBus myInst = null;
    private static Object syncObject = new Object();

    private SimpleEventBus() {
        mBusSubject = PublishSubject.create();
    }

    public static SimpleEventBus getInstance(){
        synchronized (syncObject) {
            if (myInst == null) {
                myInst = new SimpleEventBus();
            }
        }
        return myInst;
    }

    public synchronized  void post(Object event) {
        mBusSubject.onNext(event);
    }

    public synchronized Observable<Object> observable() {
        return mBusSubject;
    }

    public synchronized  <T> Observable<T> filteredObservable(final Class<T> eventClass) {
        return mBusSubject.filter(new Predicate<Object>() {
            @Override
            public boolean test(Object event) throws Throwable {
                return eventClass.isInstance(event);
            }
        }).map(new Function<Object, T>() {

            @Override
            public T apply(Object event) throws Throwable {
                 return (T) event;
            }
        });
    }
}