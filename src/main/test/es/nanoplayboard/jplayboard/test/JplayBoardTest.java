package es.nanoplayboard.jplayboard.test;

import es.nanoplayboard.jplayboard.JPlayBoard;
import es.nanoplayboard.jplayboard.adapter.listener.StateChangeListener;
import es.nanoplayboard.jplayboard.common.State;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by victor on 3/11/16.
 */


public class JplayBoardTest{

    private MockAdapter adapter;
    private JPlayBoard jplayboard;

    @Before
    public void initPlayBoard(){
        adapter=new MockAdapter();
        jplayboard=new JPlayBoard(adapter);
    }

    @Test
    public void testConnection(){
        StateChangeListener listener = new StateChangeListener() {
            @Override
            public void onStateChange(State state) {
                Assert.assertEquals(255,state.getLdr().intValue());
            }
        };
        jplayboard.setOnStateChangeListener(listener);
    }
    @Test
    public void testsetRGB(){
        jplayboard.setRgbColor(255,0,0);
        jplayboard.setRgbColor(0,255,0);
    }
    @Test
    public void testsetPlayTone(){
        jplayboard.playTone(128);

    }

    @Test
    public void testStopTone(){
        jplayboard.stopTone();
    }
    @Test
    public void testSetMatrix(){
        jplayboard.setMatrix('A');
    }
    @After
    public void dinitPlayBoard(){
        try {
            jplayboard.disconnect();
        }catch (IOException e){
            Assert.fail(e.getMessage());
        }
    }
}
