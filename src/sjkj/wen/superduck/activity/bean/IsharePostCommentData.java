package sjkj.wen.superduck.activity.bean;

import java.util.List;

public class IsharePostCommentData {
	private String nickname;
	private String portrait;
	private String sex;
	private String id;
	private String user_id;
	private String is_anonymous;
	private String content;
	private String create_time;
	private List<PostReplyData> repley;
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPortrait() {
		return portrait;
	}
	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getIs_anonymous() {
		return is_anonymous;
	}
	public void setIs_anonymous(String is_anonymous) {
		this.is_anonymous = is_anonymous;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public List<PostReplyData> getRepleydata() {
		return repley;
	}
	public void setRepleydata(List<PostReplyData> repley) {
		this.repley = repley;
	}
	

}
