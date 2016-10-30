package es.nanoplayboard.jplayboard.common;

import com.google.gson.JsonObject;

/**
 * Created by victor on 26/10/16.
 */
public class LedRgb extends Command {

    private int red;
    private int green;
    private int blue;

    public LedRgb(String type) {
        super(type);
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public LedRgb(int red, int green, int blue){
        super("rgb");
        this.red=red;
        this.green=green;
        this.blue=blue;

    }
    @Override
    public String getJson() {
        JsonObject json= new JsonObject();
        json.addProperty("type",super.getType());
        JsonObject params = new JsonObject();
        params.addProperty("r",getRed());
        params.addProperty("g",getGreen());
        params.addProperty("b",getBlue());
        json.add("params",params);
        return json.toString();
    }
}
