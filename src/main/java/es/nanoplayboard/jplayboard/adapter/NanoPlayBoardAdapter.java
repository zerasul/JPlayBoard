package es.nanoplayboard.jplayboard.adapter;


import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.security.jgss.GSSUtil;
import es.nanoplayboard.jplayboard.adapter.listener.PlayBoardPinEvent;
import es.nanoplayboard.jplayboard.adapter.listener.PlayBoardPinEventListener;
import es.nanoplayboard.jplayboard.adapter.listener.StateChangeListener;
import es.nanoplayboard.jplayboard.common.Command;
import es.nanoplayboard.jplayboard.common.Init;
import es.nanoplayboard.jplayboard.common.State;
import es.nanoplayboard.jplayboard.exception.NotConnectedException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.util.ExecutorServices;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/**
 * Created by victor on 15/10/16.
 */
public class NanoPlayBoardAdapter implements Adapter {


    private StateChangeListener listener;
    private boolean initializated=false;
    private SerialPort commPort;
    private OutputStream ostream;
    private InputStream istream;
    private Thread thread;
    private Logger logger = es.nanoplayboard.jplayboard.log.Logger.getLogger(getClass());
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
                Gson gson = new Gson();
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
        String json = command.getJson()+"\r\n";
        if(ostream!=null)
            ostream.write(json.getBytes());
        try {
            Thread.sleep(600);
        }catch (Exception e){

        }
    }



}
