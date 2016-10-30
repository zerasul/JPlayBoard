package es.nanoplayboard.jplayboard.common;

/**
 * Created by victor on 24/10/16.
 */
public class State {

    private Integer ldr;
    private Integer potentiometer;

    public Integer getLdr() {
        return ldr;
    }

    public void setLdr(Integer ldr) {
        this.ldr = ldr;
    }

    public Integer getPotentiometer() {
        return potentiometer;
    }

    public void setPotentiometer(Integer potentiometer) {
        this.potentiometer = potentiometer;
    }

    public static State generateState(String data){
        String[] sdata= data.split("\t");
        State state = new State();
        state.setLdr(Integer.parseInt(sdata[0]));
        return state;
    }
}
