package es.nanoplayboard.jplayboard.adapter;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;




import com.fazecast.jSerialComm.SerialPort;
import com.google.gson.Gson;

import es.nanoplayboard.jplayboard.adapter.listener.StateChangeListener;
import es.nanoplayboard.jplayboard.common.Command;
import es.nanoplayboard.jplayboard.common.Init;
import es.nanoplayboard.jplayboard.exception.NotConnectedException;
import es.nanoplayboard.jplayboard.log.Logger;

/**
 * Main adapter for communicate with the NanoPlayBoard.
 * Created by victor on 15/10/16.
 * @version 0.0.2
 * @since 0.0.1
 */
public class NanoPlayBoardAdapter implements Adapter {

	/**
	 * State Change Listener. The listener that get all the sensors changes.
	 */
    private StateChangeListener listener;
    /**
     * Initialization Variable.
     */
    private boolean initializated=false;
    /**
     * Serial Port.
     */
    private SerialPort commPort;
    /**
     * OutputStream For Communication
     */
    private OutputStream ostream;
    /**
     * InputStream For Communication
     */
    private InputStream istream;
    private Thread thread;
    private Logger logger = es.nanoplayboard.jplayboard.log.Logger.getInstance(getClass());
    /**
     * Constructor.
     * @param port Port Name 
     * 
     */
    public NanoPlayBoardAdapter(String port){
        commPort=SerialPort.getCommPort(port);
    }


    @Override
    public void connect()throws IOException, InterruptedException {

        commPort.openPort();
        Thread.sleep(2500);
        if(!commPort.isOpen()){
            throw new NotConnectedException();
        }
        istream=commPort.getInputStream();
        ostream=commPort.getOutputStream();
        initializated=true;
        sendCommand(new Init());
        thread=new Thread(){
            public void run(){
                setName("StateThread");
              
                while(true){
                    try {
                            if(commPort.isOpen() && istream.available()>0) {

                               String json= readState();
                                System.out.println(json);
                                if(json.isEmpty()) {
                                    es.nanoplayboard.jplayboard.common.State state = es.nanoplayboard.jplayboard.common.State.generateState(json);
                                    if (listener != null)
                                        listener.onStateChange(state);
                                }
                            }
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();

    }

    private String readState()throws IOException{
        byte[] buffer= new byte[1024];
        istream.read(buffer);
        String json = new String(buffer);
        try {
            Thread.sleep(400);
        }catch (Exception e){

        }
        json = json.substring(0, json.lastIndexOf("\r\n"));
        return json;
    }
    @Override
    public void disconnect()throws IOException {
        if(!initializated)throw  new NotConnectedException();
        istream.close();
        ostream.close();
        commPort.closePort();
        initializated=false;
        thread.interrupt();

    }

    @Override
    public void setonStateChangeListener(StateChangeListener listener) {
        this.listener=listener;
    }

    @Override
    public void sendCommand(Command command) throws IOException,NotConnectedException {
        if(!initializated)throw new NotConnectedException();
       
        if(ostream!=null)
            ostream.write(command.getAT());
        try {
            Thread.sleep(600);
        }catch (Exception e){

        }
    }



}
