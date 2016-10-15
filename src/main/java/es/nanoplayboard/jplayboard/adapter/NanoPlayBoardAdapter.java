package es.nanoplayboard.jplayboard.adapter;

import es.nanoplayboard.jplayboard.adapter.listener.PlayBoardEventListener;
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

    IODevice device;
    boolean initializated=false;

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


    public void setOnPlayBoardEventListener(PlayBoardEventListener listener){
        device.addEventListener(new IODeviceEventListener() {
            @Override
            public void onStart(IOEvent event) {

            }

            @Override
            public void onStop(IOEvent event) {

            }

            @Override
            public void onPinChange(IOEvent event) {


            }
        });
    }
}
