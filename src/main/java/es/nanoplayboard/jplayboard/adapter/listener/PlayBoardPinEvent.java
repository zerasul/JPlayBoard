package es.nanoplayboard.jplayboard.adapter.listener;

/**
 * Created by victor on 15/10/16.
 */
public class PlayBoardPinEvent {
    private int pin;
    private int value;
    private String message;

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
