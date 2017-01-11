package es.nanoplayboard.jplayboard.common;

import com.google.gson.JsonObject;

/**
 * Created by victor on 30/10/16.
 */
public class StopBuzzer extends Command{

    public StopBuzzer(){
        super(CommandTypes.STOPBUZZER.ordinal());
    }

	@Override
	public byte[] getAT() {
		StringBuffer strbuffer = new StringBuffer("AT");
		strbuffer.append(Integer.toString(getType()));
		strbuffer.append("0");//no parameters
		strbuffer.append(generatechecksum("0"));
		strbuffer.append("\r\n");
		return strbuffer.toString().getBytes();
	}
   
}
