package DatabaseObject;

public class dtoEnvironment {
	public String tradedate;
	public String environment;
	public String scinario;
	public String result;
	

	public void setTradedate(String arg_strTradedate) {
		this.tradedate = arg_strTradedate;
	}
	public String getTradedate() {
		if(tradedate == null) {
			tradedate = "";
		}
		return tradedate;
	}
	
	public void setEnvironment(String arg_strEnvironment) {
		this.environment = arg_strEnvironment;
	}
	public String getEnvironment() {
		if(environment == null) {
			environment = "";
		}
		return environment;
	}
	
	public void setScinario(String arg_strScinario) {
		this.scinario = arg_strScinario;
	}
	public String getScinario() {
		if(scinario == null) {
			scinario = "";
		}
		return scinario;
	}
	
	public void setResult(String arg_strResult) {
		this.result = arg_strResult;
	}
	public String getResult() {
		if(result == null) {
			result = "";
		}
		return result;
	}

}
