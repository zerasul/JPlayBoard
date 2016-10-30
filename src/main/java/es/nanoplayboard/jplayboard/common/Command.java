package es.nanoplayboard.jplayboard.common;

/**
 * Created by victor on 26/10/16.
 */
public abstract class Command {

    private String type;

    public Command(String type){
        this.type=type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public abstract String getJson();


}
