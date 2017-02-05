package sjkj.wen.superduck.activity;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.content.Intent;
import android.database.DatabaseErrorHandler;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import sjkj.wen.superduck.R;
import sjkj.wen.superduck.activity.bean.ClientMsg;
import sjkj.wen.superduck.activity.bean.Data;
import sjkj.wen.superduck.activity.global.SuperDuckApplication;

public class LoginActivity extends Activity implements View.OnClickListener, TextWatcher {

	private Button bt_register;
	private Button bt_forgetpassword;
	private Button bt_login;
	private EditText et_username;
	private EditText et_password;
	private CheckBox ck_leye;

	private String Token;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		intveiw();
	}

	public void intveiw() {
		bt_register = (Button) findViewById(R.id.bt_register);
		bt_forgetpassword = (Button) findViewById(R.id.bt_forgetpassword);
		bt_login = (Button) findViewById(R.id.bt_login);
		et_username = (EditText) findViewById(R.id.et_username);
		et_password = (EditText) findViewById(R.id.et_password);
		ck_leye = (CheckBox) findViewById(R.id.ck_leye);

		bt_register.setOnClickListener(this);
		bt_forgetpassword.setOnClickListener(this);
		bt_login.setOnClickListener(this);

		et_username.addTextChangedListener(this);
		et_password.addTextChangedListener(this);

		ck_leye.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					// ����Ϊ������ʾ
					et_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
				} else {
					// ����Ϊ������ʾ
					et_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
				}
				// ����������ʾ
				et_password.setSelection(et_password.length());
			}

		});

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_register:
			Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
			startActivity(i);
			break;
		case R.id.bt_forgetpassword:
			Intent i1 = new Intent(LoginActivity.this, RspasswordActivity.class);
			startActivity(i1);
			break;
		case R.id.bt_login:
			if (TextUtils.isEmpty(et_username.getText())) {
				Toast.makeText(LoginActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();

			}
			if (TextUtils.isEmpty(et_password.getText())) {
				Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();

			} else {
				Thread loginThread = new Thread(loginRunnable);
				loginThread.start();
				// Intent intent = new
				// Intent(LoginActivity.this,MainFragmentActivity.class);
				// intent.putExtra("token", Token);
				//
				// startActivity(intent);
			}
			break;
		default:
			break;
		}
	}

	/**
	 * ��¼��֤
	 */
	private static class loginHandler extends Handler {
		// ���жԱ��ⲿ���������
		private final WeakReference<LoginActivity> mActivity;

		public loginHandler(LoginActivity activity) {
			mActivity = new WeakReference<LoginActivity>(activity);
		}

		@Override
		public void handleMessage(Message msg) {

			// ��ȡ�����Ķ���
			LoginActivity activity = mActivity.get();
			if (activity != null) {
				switch (msg.what) {
				case 1:
					String serverValicationmsg = (String) msg.obj;
					Toast.makeText(activity, "登陆成功", Toast.LENGTH_SHORT).show();

					break;
				case -1:
					Toast.makeText(activity, (String) msg.obj, Toast.LENGTH_SHORT).show();
					break;
				case 0:
					Toast.makeText(activity, "哎呀，出错了！", Toast.LENGTH_SHORT).show();
					break;
				default:
					break;
				}
			}
		}
	}

	/** ʵ�����Զ����handler */
	private final loginHandler loginHandler = new loginHandler(this);
	private String backcode;
	/** �����ȡ��֤������߳� */
	private Runnable loginRunnable = new Runnable() {
		@Override
		public void run() {
			String code;
			final Message msg = new Message();
			String url = "http://115.28.244.91/sjkjsvn/index.php/Home/Login/login";
			StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
				@Override
				public void onResponse(String s) {
					// if (s != null) {
					// s = s.trim();
					// if (s.startsWith("ï»¿")) {
					// s = s.substring(3);
					// }
					// }
					Log.e("request", s);
					System.out.println("message=" + s);
					try {
						Gson gson = new Gson();
						ClientMsg message = gson.fromJson(s, ClientMsg.class);
						System.out.println("message=" + message.getMsg());
						Data data = message.getData();
						System.out.println("data=" + data);
						// Token = data.getAccess_tocken();
						// System.out.println("data="+Token);
						try {

							if (message.getCode().equals("500")) {
								msg.what = -1;
								msg.obj = message.getMsg();
							} else {
								msg.what = 1;
								msg.obj = message.getMsg();
							}
						} catch (Exception e) {
							msg.what = 0;
							e.printStackTrace();
						}

						loginHandler.sendMessage(msg);
					} catch (Exception e) {

						e.printStackTrace();
					}
				}
			}, new Response.ErrorListener() {
				// ����ʧ��ʱִ�еĺ���
				@Override
				public void onErrorResponse(VolleyError volleyError) {
				}
			}) {
				// ������������
				@Override
				protected Map<String, String> getParams() throws AuthFailureError {

					Map<String, String> hashMap = new HashMap<String, String>();
					hashMap.put("username", et_username.getText().toString());
					hashMap.put("password", et_password.getText().toString());
					return hashMap;
				}
			};
			// ���ø�����ı�ǩ
			request.setTag("loginPost");
			// ��������ӵ�������
			SuperDuckApplication.getRequestQueue().add(request);
			SuperDuckApplication.getRequestQueue().start();
		}
	};

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterTextChanged(Editable s) {
		if (TextUtils.isEmpty(et_username.getText().toString())
				&& TextUtils.isEmpty(et_password.getText().toString())) {
			bt_login.setEnabled(false);
		} else {
			bt_login.setEnabled(true);
		}

	}
}
