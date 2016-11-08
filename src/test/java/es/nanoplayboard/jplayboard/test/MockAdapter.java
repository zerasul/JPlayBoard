package es.nanoplayboard.jplayboard.test;

import com.google.gson.Gson;
import es.nanoplayboard.jplayboard.adapter.Adapter;
import es.nanoplayboard.jplayboard.adapter.listener.StateChangeListener;
import es.nanoplayboard.jplayboard.common.Command;
import es.nanoplayboard.jplayboard.common.Init;
import es.nanoplayboard.jplayboard.common.State;
import es.nanoplayboard.jplayboard.exception.NotConnectedException;
import org.junit.Assert;

import java.io.IOException;

/**
 * Created by victor on 3/11/16.
 */
public class MockAdapter implements Adapter {

    private Thread stateThread;
    private boolean initiated=false;
    private StateChangeListener listener;
    @Override
    public void connect() throws IOException, InterruptedException {
        initiated=true;
        sendCommand(new Init());
        stateThread=new Thread(new Runnable() {
            @Override
            public void run() {

                while(initiated){
                    if(listener!=null) {
                       String str= "255\t512\ttrue\tfalse\ttrue\tfalse\t48.0\t5.00";
                        State state = State.generateState(str);
                        listener.onStateChange(state);
                    }
                    try {
                        Thread.sleep(1500);
                    }catch (InterruptedException e){

                    }

                }
            }
        });
    }

    @Override
    public void disconnect() throws IOException {
        initiated=false;
    }

    @Override
    public void setonStateChangeListener(StateChangeListener listener) {
            this.listener=listener;
    }

    @Override
    public void sendCommand(Command command) throws IOException, NotConnectedException {
            Gson gson = new Gson();
        try {
            gson.fromJson(command.getJson(),Object.class);
        }catch (Exception e){
            Assert.fail(e.getMessage());
        }
    }
}
