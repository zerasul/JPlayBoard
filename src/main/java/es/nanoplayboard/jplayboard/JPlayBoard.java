package es.nanoplayboard.jplayboard;


import es.nanoplayboard.jplayboard.adapter.Adapter;
import es.nanoplayboard.jplayboard.adapter.NanoPlayBoardAdapter;
import org.firmata4j.IODeviceEventListener;

import java.io.IOException;

/**
 * Created by victor on 15/10/16.
 */
public class JPlayBoard {

    Adapter playBoardAdapter;

    public JPlayBoard(String port){
        playBoardAdapter= new NanoPlayBoardAdapter(port);
    }

    public JPlayBoard(Adapter adapter){
        playBoardAdapter=adapter;
    }

    public void setListenerAdapter(IODeviceEventListener listener){
        playBoardAdapter.
    }

    public void disconnect()throws IOException{
        playBoardAdapter.disconnect();
    }

}
