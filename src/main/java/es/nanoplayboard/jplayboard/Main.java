package es.nanoplayboard.jplayboard;

import es.nanoplayboard.jplayboard.adapter.listener.StateChangeListener;
import es.nanoplayboard.jplayboard.common.State;

/**
 * Created by victor on 19/10/16.
 */
public class Main {

    public static void main(String[] args){
        JPlayBoard jplayboard=new JPlayBoard("/dev/cu.wchusbserial1420");


        jplayboard.setOnStateChangeListener(new StateChangeListener() {
            @Override
            public void onStateChange(State state) {
                System.out.println("ldr: "+state.getLdr());
            }
        });


        try {
            jplayboard.setRgbColor(255,0,0);
            Thread.sleep(1500);
            jplayboard.setMatrix('A');
            Thread.sleep(1500);
            jplayboard.setRgbColor(0,0,255);
            Thread.sleep(1500);




            //jplayboard.disconnect();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
