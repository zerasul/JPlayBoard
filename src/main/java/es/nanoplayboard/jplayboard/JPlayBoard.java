package es.nanoplayboard.jplayboard;

import java.io.IOException;

import es.nanoplayboard.jplayboard.adapter.Adapter;
import es.nanoplayboard.jplayboard.adapter.NanoPlayBoardAdapter;
import es.nanoplayboard.jplayboard.adapter.listener.StateChangeListener;
import es.nanoplayboard.jplayboard.common.Buzzer;
import es.nanoplayboard.jplayboard.common.Command;
import es.nanoplayboard.jplayboard.common.LedRgb;
import es.nanoplayboard.jplayboard.common.Matrix;
import es.nanoplayboard.jplayboard.common.StopBuzzer;
import es.nanoplayboard.jplayboard.exception.NotConnectedException;
import es.nanoplayboard.jplayboard.log.Logger;

/**
 * JPLayBoard: main class that provides all the main functionality to connect
 * and interact with NanoPlayBoard.<br/>
 * This code is under GNU 3.0 license.<br/>
 * 
 * @version 0.0.2
 * @since 0.0.1
 * @author Victor Suarez
 */
public class JPlayBoard {

	private Adapter playBoardAdapter;

	private Logger logger = es.nanoplayboard.jplayboard.log.Logger
			.getInstance(getClass());

	/**
	 * Constructor. This is the main constructor that use the default adapter:
	 * {@link NanoPlayBoardAdapter}. Also Open the communication with
	 * NanoPlayBoard.<br/>
	 * <b>NOTE</b> See your operating System documentation for get the Name of
	 * the port of your nanoplayboard.
	 * 
	 * @param port
	 *            the Serial Port to use with the communication.
	 */
	public JPlayBoard(String port) {
		this(new NanoPlayBoardAdapter(port));

	}

	/**
	 * Constructor.<br/>
	 * This constructor use a custom adapter for the communication with
	 * NanoPlayBoard. Also Open the communication with NanoPlayBoard.<br/>
	 * <b>NOTE</b> See your operating System documentation for get the Name of
	 * the port of your nanoplayboard.
	 * 
	 * @param adapter
	 *            custom Adapter. see {@link Adapter} for more info.
	 */
	public JPlayBoard(Adapter adapter) {
		playBoardAdapter = adapter;
		try {
			this.playBoardAdapter.connect();
		} catch (IOException e) {
			logger.error("Error: " + e.getMessage());
			e.printStackTrace();
		} catch (InterruptedException e1) {
			logger.error("Error: " + e1.getMessage());
			e1.printStackTrace();
		}

	}

	/**
	 * Close the communication
	 * 
	 * @throws IOException
	 *             throws this exception if have some problems with the
	 *             communication.
	 */
	public void disconnect() throws IOException {
		playBoardAdapter.disconnect();
	}

	/**
	 * 
	 * @param listener
	 */
	public void setOnStateChangeListener(StateChangeListener listener) {
		this.playBoardAdapter.setonStateChangeListener(listener);
	}

	public void setRgbColor(int r, int g, int b) {
		Command c = new LedRgb(r, g, b);
		try {
			playBoardAdapter.sendCommand(c);
		} catch (NotConnectedException e) {
			logger.error("Error: " + e.getMessage());
		} catch (IOException e1) {
			logger.error("Error: " + e1.getMessage());
		}
	}

	public void playTone(int val) {
		Buzzer buzzer = new Buzzer(val);
		try {
			playBoardAdapter.sendCommand(buzzer);
		} catch (NotConnectedException e) {
			logger.error("Error: " + e.getMessage());
		} catch (IOException e1) {
			logger.error("Error: " + e1.getMessage());
		}
	}

	public void stopTone() {
		StopBuzzer sbuzzer = new StopBuzzer();
		{
			try {
				playBoardAdapter.sendCommand(sbuzzer);
			} catch (NotConnectedException e) {
				logger.error("Error: " + e.getMessage());
			} catch (IOException e1) {
				logger.error("Error: " + e1.getMessage());
			}
		}
	}

	public void setMatrix(Character value) {
		Matrix matrix = new Matrix(value);
		try {
			playBoardAdapter.sendCommand(matrix);
		} catch (NotConnectedException e) {
			logger.error("Error: " + e.getMessage());
		} catch (IOException e1) {
			logger.error("Error: " + e1.getMessage());
		}
	}
}
