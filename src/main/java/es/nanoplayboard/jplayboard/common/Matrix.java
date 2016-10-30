package es.nanoplayboard.jplayboard.common;

import com.google.gson.JsonObject;

/**
 * Created by victor on 30/10/16.
 */
public class Matrix extends Command {
    private Character value;

    public Matrix(Character value){
        super("matrix");
        this.value=value;
    }
    @Override
    public String getJson() {
        JsonObject json= new JsonObject();
        json.addProperty("type",super.getType());
        JsonObject params = new JsonObject();
        params.addProperty("val", this.value);
        json.add("params",params);
        return json.toString();
    }
}
