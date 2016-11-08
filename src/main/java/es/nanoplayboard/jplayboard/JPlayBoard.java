package es.nanoplayboard.jplayboard;


import es.nanoplayboard.jplayboard.adapter.Adapter;
import es.nanoplayboard.jplayboard.adapter.NanoPlayBoardAdapter;
import es.nanoplayboard.jplayboard.adapter.listener.PlayBoardPinEventListener;
import es.nanoplayboard.jplayboard.adapter.listener.StateChangeListener;
import es.nanoplayboard.jplayboard.common.*;
import es.nanoplayboard.jplayboard.components.AnalogDevice;
import es.nanoplayboard.jplayboard.exception.NotConnectedException;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InterruptedIOException;

/**
 * Created by victor on 15/10/16.
 */
public class JPlayBoard {

    private Adapter playBoardAdapter;
    private AnalogDevice pontentiometer;
    private AnalogDevice ldr;

    private Logger logger = es.nanoplayboard.jplayboard.log.Logger.getLogger(getClass());

    public JPlayBoard(String port){
        this(new NanoPlayBoardAdapter(port));


    }

    public JPlayBoard(Adapter adapter){
        playBoardAdapter=adapter;
        try {
            this.playBoardAdapter.connect();
        }catch (IOException e){
            logger.error("Error: "+ e.getMessage());
            e.printStackTrace();
        }catch (InterruptedException e1){
            logger.error("Error: "+ e1.getMessage());
            e1.printStackTrace();
        }
        this.pontentiometer=new AnalogDevice(playBoardAdapter);
        this.ldr= new AnalogDevice(playBoardAdapter);
    }



    public void disconnect()throws IOException{
        playBoardAdapter.disconnect();
    }


    public void setOnStateChangeListener(StateChangeListener listener){
        this.playBoardAdapter.setonStateChangeListener(listener);
    }
    public void setRgbColor(int r,int g,int b){
        Command c = new LedRgb(r,g,b);
        try {
            playBoardAdapter.sendCommand(c);
        }catch (NotConnectedException e) {
            logger.error("Error: "+ e.getMessage());
        }catch(IOException e1){
            logger.error("Error: "+ e1.getMessage());
        }
    }

    public void playTone(int val){
        Buzzer buzzer = new Buzzer(val);
        try {
            playBoardAdapter.sendCommand(buzzer);
        }catch (NotConnectedException e){
            logger.error("Error: "+ e.getMessage());
        }catch (IOException e1){
            logger.error("Error: "+ e1.getMessage());
        }
    }

    public void stopTone(){
        StopBuzzer sbuzzer = new StopBuzzer();{
            try{
                playBoardAdapter.sendCommand(sbuzzer);
            }catch(NotConnectedException e){
                logger.error("Error: "+ e.getMessage());
            }catch (IOException e1){
                logger.error("Error: "+ e1.getMessage());
            }
        }
    }

    public void setMatrix(Character value){
        Matrix matrix = new Matrix(value);
        try{
            playBoardAdapter.sendCommand(matrix);
        }catch (NotConnectedException e){
            logger.error("Error: "+ e.getMessage());
        }catch (IOException e1){
            logger.error("Error: "+ e1.getMessage());
        }
    }
}
