package es.nanoplayboard.jplayboard.common;

import com.google.gson.JsonObject;

/**
 * Created by victor on 26/10/16.
 */
public class Init extends  Command {

    public Init(){
        super("init");
    }
    @Override
    public String getJson() {
        JsonObject json= new JsonObject();
        json.addProperty("type",super.getType());
        return json.toString();
    }

}
