package es.nanoplayboard.jplayboard.common;

import com.google.gson.JsonObject;

/**
 * Created by victor on 28/10/16.
 */
public class Buzzer extends Command {
    int val;
    public Buzzer(int val){
        super("buzzer");
        this.val=val;
    }
    @Override
    public String getJson() {
        JsonObject json= new JsonObject();
        json.addProperty("type",super.getType());
        JsonObject params = new JsonObject();
        params.addProperty("val",this.val);
        json.add("params",params);
        return json.toString();
    }
}
