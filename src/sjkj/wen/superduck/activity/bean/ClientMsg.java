package sjkj.wen.superduck.activity.bean;

import java.util.List;

public class ClientMsg {
     private String code;
     private String msg;
     private Data data;
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "code=" + code + ", msg=" + msg + ", data=" + data ;
	}
	
     
}
