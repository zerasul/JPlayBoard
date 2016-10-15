package es.nanoplayboard.jplayboard;

import es.nanoplayboard.jplayboard.serial.ArduinoSerialAdapter;
import es.nanoplayboard.jplayboard.serial.SerialAdapter;

/**
 * Created by victor on 15/10/16.
 */
public class JPlayBoard {

    SerialAdapter sadapter;

    public JPlayBoard(String port){
        sadapter=new ArduinoSerialAdapter(port);
        sadapter.connect();
    }


}
