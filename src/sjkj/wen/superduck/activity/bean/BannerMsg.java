package sjkj.wen.superduck.activity.bean;

import java.util.List;

public class BannerMsg {
     private String code;
     private String msg;
     private List<BannerData> data;
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
	public List<BannerData> getData() {
		return data;
	}
	public void setData(List<BannerData> data) {
		this.data = data;
	}
}
