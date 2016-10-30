package es.nanoplayboard.jplayboard.common;

import com.google.gson.JsonObject;

/**
 * Created by victor on 30/10/16.
 */
public class StopBuzzer extends Command{

    public StopBuzzer(){
        super("stopbuzzer");
    }
    @Override
    public String getJson() {
        JsonObject json= new JsonObject();
        json.addProperty("type",super.getType());


        return json.toString();
    }
}
