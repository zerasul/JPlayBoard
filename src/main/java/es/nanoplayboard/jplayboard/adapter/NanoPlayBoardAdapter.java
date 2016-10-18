package es.nanoplayboard.jplayboard.adapter;


import es.nanoplayboard.jplayboard.adapter.listener.PlayBoardPinEvent;
import es.nanoplayboard.jplayboard.adapter.listener.PlayBoardPinEventListener;
import es.nanoplayboard.jplayboard.exception.NotConnectedException;
import org.firmata4j.IODevice;
import org.firmata4j.IODeviceEventListener;
import org.firmata4j.IOEvent;
import org.firmata4j.firmata.FirmataDevice;

import java.io.IOException;

/**
 * Created by victor on 15/10/16.
 */
public class NanoPlayBoardAdapter implements Adapter {

    private IODevice device;
    private PlayBoardPinEventListener listener;
    private boolean initializated=false;

    public NanoPlayBoardAdapter(String port){
        device = new FirmataDevice(port);
    }


    @Override
    public void connect()throws IOException, InterruptedException {
        device.start();
        device.ensureInitializationIsDone();
        initializated=true;

    }

    @Override
    public void disconnect()throws IOException {
        if(!initializated)throw  new NotConnectedException();
        device.stop();
    }

    @Override
    public void setOnPlayBoardEventListener(final PlayBoardPinEventListener listener){
        this.listener= listener;
        device.addEventListener(new IODeviceEventListener() {
            @Override
            public void onStart(IOEvent event) {

            }

            @Override
            public void onStop(IOEvent event) {

            }

            @Override
            public void onPinChange(IOEvent event) {
                PlayBoardPinEvent playevent = new PlayBoardPinEvent();
                playevent.setPin(event.getPin().getIndex());
                playevent.setValue(Long.valueOf(event.getPin().getValue()).intValue());
                NanoPlayBoardAdapter.this.listener.onEvent(playevent);

            }
        });
    }
}
