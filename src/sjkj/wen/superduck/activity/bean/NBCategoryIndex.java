package sjkj.wen.superduck.activity.bean;

import java.util.ArrayList;
import java.util.List;

public class NBCategoryIndex {
	private String code;
	private String msg;
	private List<NBCategoryIndexData> data = new ArrayList<NBCategoryIndexData>();
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
	public List<NBCategoryIndexData> getData() {
		return data;
	}
	public void setData(List<NBCategoryIndexData> data) {
		this.data = data;
	}
    
}
