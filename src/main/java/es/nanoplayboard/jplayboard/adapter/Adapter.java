package es.nanoplayboard.jplayboard.adapter;




import java.io.IOException;

import es.nanoplayboard.jplayboard.adapter.listener.StateChangeListener;
import es.nanoplayboard.jplayboard.common.Command;
import es.nanoplayboard.jplayboard.exception.NotConnectedException;

/**
 * this is the aadapter interface for communicate with NanoPlayBoard.
 * Created by victor on 15/10/16.
 * @version 0.0.1
 * @since 0.0.1
 */
public interface Adapter {

	/**
	 * Connect with NanoPlayBoard.
	 * @throws IOException Throws This exception if there is a problem with Communication
	 * @throws InterruptedException Throws this exception if a problem ocurred with BackGround Thread.
	 */
    void connect() throws IOException, InterruptedException;
    /**
     * Disconnect the communication with NanoPlayBoard
     * @throws IOException Throws this Exception if there is a problem with communication
     */
    void disconnect()throws IOException;
    /**
     * Sets the StateChangeListener for get the data from the sensors of the NanoPlayBoard.
     * @param listener StateChangeListener that gets the data from sensors of the NanoPlayBoard.
     */
    void setonStateChangeListener(StateChangeListener listener);
    /**
     * Send a command to NanoPlayBoard.
     * @param command command to Send
     * @throws IOException Throws this exception if there is a problem with communication.
     * @throws NotConnectedException Throws this exception if the board is not connected.
     */
    void sendCommand(Command command) throws IOException, NotConnectedException;
}
