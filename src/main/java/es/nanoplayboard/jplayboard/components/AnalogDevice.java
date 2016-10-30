package es.nanoplayboard.jplayboard.components;

import es.nanoplayboard.jplayboard.adapter.Adapter;
import es.nanoplayboard.jplayboard.adapter.listener.AnalogListener;


/**
 * Created by victor on 19/10/16.
 */
public class AnalogDevice {

    private AnalogListener listener;
    private Adapter adapter;
    private int pin;


    public AnalogDevice(Adapter adapter){
        this.pin=pin;
        this.adapter=adapter;
        this.listener=new AnalogListener() {
            @Override
            public void onChangeValue(long value) {
                //DO Nothing
            }
        };
    }

    public void setonPotentiometerChangeListener(AnalogListener listener){
        this.listener= listener;



    }
}
