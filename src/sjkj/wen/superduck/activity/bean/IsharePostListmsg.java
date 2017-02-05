package sjkj.wen.superduck.activity.bean;


import java.util.List;

public class IsharePostListmsg {
	public String code;
	public String msg;
	public List<IsharePostListData> data;
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
	public List<IsharePostListData> getData() {
		return data;
	}
	public void setData(List<IsharePostListData> data) {
		this.data = data;
	}
        
}
