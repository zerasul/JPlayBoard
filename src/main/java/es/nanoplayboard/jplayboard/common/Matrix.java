package es.nanoplayboard.jplayboard.common;


/**
 * Created by victor on 30/10/16.
 */
public class Matrix extends Command {
    private Character value;

    public Matrix(Character value){
        super(CommandTypes.MATRIX.ordinal());
        this.value=value;
    }

	@Override
	public byte[] getAT() {
		StringBuffer strbuffer = new StringBuffer("AT");
		strbuffer.append(Integer.toString(getType()));
		String strval= Character.toString(value);
		strbuffer.append(strval.length());
		strbuffer.append(strval);
		String checksum=generatechecksum(strval);
		strbuffer.append(checksum);
		strbuffer.append("\r\n");
		return strbuffer.toString().getBytes();
	}
   
}
