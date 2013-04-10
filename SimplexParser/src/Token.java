
public class Token {

	public enum tokenClass {COEFFICIENT,VARIABLE,OPERAND,NUMBER,EQIN}
	private String tokenValue;
	private tokenClass tClass;
	
	
	public Token(tokenClass tClass, String tokenValue){
		this.tClass = tClass;
		this.tokenValue = tokenValue;
	}
}
