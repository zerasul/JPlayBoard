package es.nanoplayboard.jplayboard.adapter;

import es.nanoplayboard.jplayboard.adapter.listener.PlayBoardPinEventListener;

import java.io.IOException;

/**
 * Created by victor on 15/10/16.
 */
public interface Adapter {

    void connect() throws IOException, InterruptedException;
    void disconnect()throws IOException;
    void setOnPlayBoardEventListener(final PlayBoardPinEventListener listener);
}
