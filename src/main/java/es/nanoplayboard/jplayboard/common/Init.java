package es.nanoplayboard.jplayboard.common;


/**
 * Created by victor on 26/10/16.
 */
public class Init extends  Command {

    public Init(){
        super(CommandTypes.INIT.ordinal());
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
