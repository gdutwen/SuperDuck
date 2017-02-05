package sjkj.wen.superduck.activity.bean;

import java.util.ArrayList;
import java.util.List;

public class NBNextCategoryIndex {
	private String code;
	private String msg;
	private List<NBNextCategoryIndexData> data = new ArrayList<NBNextCategoryIndexData>();
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
	public List<NBNextCategoryIndexData> getData() {
		return data;
	}
	public void setData(List<NBNextCategoryIndexData> data) {
		this.data = data;
	}

}
