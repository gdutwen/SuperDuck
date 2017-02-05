package sjkj.wen.superduck.activity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import sjkj.wen.superduck.R;
import sjkj.wen.superduck.activity.adapter.GirdViewAdapter;
import sjkj.wen.superduck.activity.bean.IsharePostCommentData;
import sjkj.wen.superduck.activity.bean.IsharePostDetailData;
import sjkj.wen.superduck.activity.bean.IsharePostDetailMsg;
import sjkj.wen.superduck.activity.bean.PostReplyData;
import sjkj.wen.superduck.activity.global.SuperDuckApplication;
import sjkj.wen.superduck.activity.view.CircleImageView;

public class IsharePostDetailActivity extends Activity implements OnClickListener {

	private CircleImageView ishare_detail_head_photo;
	private TextView ishare_detail_user_name;
	private TextView ishare_detail_messge_time;
	private TextView ishare_detail_message_title;
	private TextView ishare_detail_message_content;
	private GridView ishare_detail_showpic;

	private LinearLayout ll_answerpost;
	private Context mContext = null;
	private String postID;
	private IsharePostDetailData detaildata;
	private List<IsharePostCommentData> commentdata;
	private List<PostReplyData> replydata;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.ishare_detail);

		mContext = this;

		Intent intent = getIntent();
		postID = intent.getStringExtra("postID");
		Log.e("postid", postID);
		initview();
		initdata();
	}

	private void initview() {
		ishare_detail_head_photo = (CircleImageView) findViewById(R.id.ishare_detail_head_photo);
		ishare_detail_user_name = (TextView) findViewById(R.id.ishare_detail_user_name);
		ishare_detail_messge_time = (TextView) findViewById(R.id.ishare_detail_messge_time);
		ishare_detail_message_title = (TextView) findViewById(R.id.ishare_detail_message_title);
		ishare_detail_message_content = (TextView) findViewById(R.id.ishare_detail_message_content);
		ishare_detail_showpic = (GridView) findViewById(R.id.ishare_detail_showpic);

		ll_answerpost = (LinearLayout) findViewById(R.id.ll_answerpost);

		ll_answerpost.setOnClickListener(this);

	}

	private void initdata() {
		String url = "http://115.28.244.91/sjkjsvn/index.php/Home/Post/itemPost" + "?id=" + postID;
		StringRequest datarequest = new StringRequest(url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				Log.e("data", response);
				Gson gson = new Gson();
				IsharePostDetailMsg detailmsg = gson.fromJson(response, IsharePostDetailMsg.class);
				detaildata = detailmsg.getDetaildata();
				System.out.println(detaildata.getTitle());
				ishare_detail_user_name.setText(detaildata.getNickname());
				String date = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(new java.util.Date(detaildata.getCreate_time() * 1000));
				ishare_detail_messge_time.setText(date);
				ishare_detail_message_title.setText(detaildata.getTitle());
				ishare_detail_message_content.setText(detaildata.getContent());
				ishare_detail_showpic.setAdapter(new GirdViewAdapter(getApplicationContext(), detaildata.getImages()));
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub

			}
		}) {
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				// TODO Auto-generated method stub
				HashMap<String, String> headers = new HashMap<String, String>();
				headers.put("Token", "abfce2bb68b04de78cef86484995f47f");
				return headers;
			}
		};
		SuperDuckApplication.getRequestQueue().add(datarequest);
		SuperDuckApplication.getRequestQueue().start();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_answerpost:

			showPopupWindow();
			break;

		default:
			break;
		}

	}

	private void showPopupWindow() {

		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.pop_answer_post, null);
		PopupWindow window = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,
				WindowManager.LayoutParams.WRAP_CONTENT);
		window.setFocusable(true);
		ColorDrawable dw = new ColorDrawable(0xb0000000);
		window.setBackgroundDrawable(dw);

		window.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);

		window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
		window.showAtLocation(((Activity) mContext).findViewById(R.id.ll_answerpost), Gravity.BOTTOM, 0, 0);
		Button first = (Button) view.findViewById(R.id.answerpost);
		first.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				System.out.println("第一个按钮被点击了");
			}
		});
		// popWindow消失监听方法
		window.setOnDismissListener(new OnDismissListener() {

			@Override
			public void onDismiss() {
				System.out.println("popWindow消失");
			}
		});
	}

}
