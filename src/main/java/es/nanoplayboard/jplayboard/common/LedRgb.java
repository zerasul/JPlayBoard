package es.nanoplayboard.jplayboard.common;

import com.google.gson.JsonObject;

/**
 * Created by victor on 26/10/16.
 */
public class LedRgb extends Command {

    private int red;
    private int green;
    private int blue;

    public LedRgb() {
        super(CommandTypes.LEDRGB.ordinal());
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
        this();
        this.red=red;
        this.green=green;
        this.blue=blue;

    }

	@Override
	public byte[] getAT() {
		StringBuffer strbuffer = new StringBuffer("AT");
		strbuffer.append(Integer.toString(getType()));
		String strval= Integer.toString(this.red)+"\t"+Integer.toString(this.green)+"\t"+Integer.toString(this.blue);
		strbuffer.append(strval.length());
		strbuffer.append(strval);
		String checksum=generatechecksum(strval);
		strbuffer.append(checksum);
		strbuffer.append("\r\n");
		return strbuffer.toString().getBytes();
	}
   
}
