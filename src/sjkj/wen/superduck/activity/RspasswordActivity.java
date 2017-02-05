package sjkj.wen.superduck.activity;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import android.app.Activity;
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
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import sjkj.wen.superduck.R;
import sjkj.wen.superduck.activity.global.SuperDuckApplication;
import sjkj.wen.superduck.activity.util.CountTimerUtils;
import sjkj.wen.superduck.activity.util.StringUtil;

public class RspasswordActivity extends Activity implements OnClickListener,TextWatcher{
	
	private TextView tv_title;
	private Button bt_id_code;	
	private ImageButton bt_back;
	private Button bt_rp_finish;
	private EditText et_rp_phone;
	private EditText et_rp_verifycode;
	private EditText et_rp_password1;
	private EditText et_rp_password2;
	private CheckBox ck_rpeye;
	
	private CountTimerUtils time;
	
	private String phone1;
	private String phone2;
	private String verifycode;
	private String password1;
	private String password2;
	private String  clientcode;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.resetpassword);
		initview();
		tv_title.setText("重置密码");
		time = new CountTimerUtils(bt_id_code,60000,1000);
		
	}
	
	public void initview(){
		tv_title = (TextView) findViewById(R.id.title);
		bt_id_code = (Button) findViewById(R.id.bt_id_code);
		bt_back = (ImageButton) findViewById(R.id.bt_back);
		bt_rp_finish = (Button) findViewById(R.id.bt_rp_finish);
		et_rp_phone = (EditText) findViewById(R.id.et_rp_phone);
		et_rp_verifycode = (EditText) findViewById(R.id.et_rp_verifycode);
		et_rp_password1 = (EditText) findViewById(R.id.et_rp_password1);
		et_rp_password2 = (EditText) findViewById(R.id.et_rp_password2);
		ck_rpeye = (CheckBox) findViewById(R.id.ck_rpeye);
		
		bt_back.setOnClickListener(this);
		bt_id_code.setOnClickListener(this);
		bt_rp_finish.setOnClickListener(this);
		
		et_rp_phone.addTextChangedListener(this);
		et_rp_verifycode.addTextChangedListener(this);
		et_rp_password1.addTextChangedListener(this);
		et_rp_password2.addTextChangedListener(this);
		ck_rpeye.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){  
                    //设置为密文显示  
					et_rp_password1.setTransformationMethod(PasswordTransformationMethod.getInstance());
					et_rp_password2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }else{  
                    //设置为明文显示  
                	et_rp_password1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                	et_rp_password2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }  
                //光标在最后显示  
				et_rp_password1.setSelection(et_rp_password1.length()); 
				et_rp_password2.setSelection(et_rp_password2.length());
				
			}
		});
	}
	
	@Override
	public void onBackPressed() {
		finish();
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_back:
			finish();
			break;
		case R.id.bt_id_code:
			if (TextUtils.isEmpty(et_rp_phone.getText()))
            {
                Toast.makeText(RspasswordActivity.this,"请输入手机号",Toast.LENGTH_SHORT).show();
               
            }
            else if (!StringUtil.isPhoneNumberValid(et_rp_phone.getText().toString()))
            {
                Log.e("phoneNum.getText()",et_rp_phone.getText().toString().length()+"");
               
                Toast.makeText(RspasswordActivity.this,"输入有误，请重新检查号码是否正确",Toast.LENGTH_SHORT).show();
            }
            else
            {
            	Toast.makeText(RspasswordActivity.this,"获取验证码中。。。",Toast.LENGTH_SHORT).show();
                //进行验证            
                time.start();
                
                Thread codeThread = new Thread(codeRunnable);  
                codeThread.start(); 
            }
			break;
		case R.id.bt_rp_finish:
			if (isValid()) {  
	               Thread thread = new Thread(rpRunnable);  
	               thread.start();                        
	       }  
		default:
			break;
		}		
	}
	/** 
     * 自定义一个静态的具有弱引用的Handler，解决内存泄漏的问题,本handler用来获取验证码 
     */  
    private static class CodeHandler extends Handler {  
        // 持有对本外部类的弱引用  
        private final WeakReference<RspasswordActivity> mActivity;  
  
        public CodeHandler(RspasswordActivity activity) {  
            mActivity = new WeakReference<RspasswordActivity>(activity);  
        }   
        @Override  
        public void handleMessage(Message msg) {                
            // 获取上下文对象  
        	RspasswordActivity activity = mActivity.get();  
            if (activity != null) {  
                switch (msg.what) {  
                case 1:  
                	Toast.makeText(activity, "获取成功", Toast.LENGTH_SHORT).show();
                    break;  
                case -1:  
                    Toast.makeText(activity, (String)msg.obj, Toast.LENGTH_SHORT).show();  
                    break;  
                case 0:  
                    Toast.makeText(activity, "哎呀,出错啦..", Toast.LENGTH_SHORT).show();  
                    break;  
                default:  
                    break;  
                }  
            }  
        }  
    }        
    /**实例化自定义的handler*/  
    private final CodeHandler codeHandler = new CodeHandler(this);  
    private String backcode ;     
    /**定义获取验证码的子线程*/  
    private Runnable codeRunnable = new Runnable() {  
        @Override  
        public void run() {  
        	       	
        	final Gson gson = null;
        	String  code;
        	 final Message msg = new Message();  
        	 phone1 = et_rp_phone.getText().toString();
             String url = "http://192.168.0.108/sjkj/index.php/Home/Login/code"+"?mobile="+ et_rp_phone.getText().toString();
             Log.e("url", url);            
             StringRequest  request = new StringRequest(url, new Response.Listener<String>() {
            	 public void onResponse(String response) {
            		 Log.d("TAG", response);  
            		 try {
                         JSONObject jsonObject = new JSONObject(response);
                         Log.e("tag", "登陆的result=" + jsonObject);
                         String msg1 = jsonObject.optString("msg");
                         clientcode= jsonObject.optString("data");
                         String  code=jsonObject.optString("code");
                         Log.e("data", clientcode);
                         Log.e("msg", msg1);
                         Log.e("code", code);                         
                         try {                               
                             // 返回true则将消息的what值为1，为false则what为-1，异常为0  
                             if (code.equals("500")) {  
                                 msg.what = -1;  
                                 msg.obj = msg1;
                             } else {  
                                 msg.what = 1;  
                                 msg.obj = clientcode;  
                             }  
               
                         } catch (Exception e) {  
                             msg.what = 0;  
                             e.printStackTrace();  
                         }                          
                         codeHandler.sendMessage(msg); 
                     } catch (JSONException e) {
                         e.printStackTrace();
                     }           
                 
            	 }
             },new Response.ErrorListener(){
            		 public void onErrorResponse(VolleyError error) {
            			 
            		 }           	 
			});      
             SuperDuckApplication.getRequestQueue().add(request);
             SuperDuckApplication.getRequestQueue().start();
        }  
    };  
    private static class rpHandler extends Handler {  
        // 持有对本外部类的弱引用  
        private final WeakReference<RspasswordActivity> mActivity;  
  
        public rpHandler(RspasswordActivity activity) {  
            mActivity = new WeakReference<RspasswordActivity>(activity);  
        }  
  
        @Override  
        public void handleMessage(Message msg) {  
              
            // 获取上下文对象  
        	RspasswordActivity activity = mActivity.get();  
            if (activity != null) {  
                switch (msg.what) {  
                case 1:  
                    Toast.makeText(activity, "重置成功!", Toast.LENGTH_SHORT).show();  
                    activity.finish();  
                    break;  
                case -1:  
                    Toast.makeText(activity, (String)msg.obj, Toast.LENGTH_SHORT).show();  
                    break;                
                case 0:  
                    Toast.makeText(activity, "哎呀,出错啦..", Toast.LENGTH_SHORT).show();  
                    break;  
                default:  
                    break;  
                }  
            }  
        }  
    }  
      
    /**实例化自定义的handler*/  
    private final rpHandler mHandler = new rpHandler(this);  
      
    /**自定义子线程*/  
    private Runnable rpRunnable = new Runnable() {  
        @Override  
        public void run() {  
        	
       	 final Message msg = new Message();  
       	 String url = "http://192.168.0.108/sjkj/index.php/Home/Login/edit_password";
	        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
	            @Override
	            public void onResponse(String response) {   
	            	Log.d("TAG", response);  
           		 try {
                        JSONObject jsonObject = new JSONObject(response);
                        Log.e("tag", "登陆的result=" + jsonObject);
                        String msg1 = jsonObject.optString("msg");
                        String data = jsonObject.optString("data");
                        String  code=jsonObject.optString("code");
                        Log.e("data", data);
                        Log.e("msg", msg1);
                        Log.e("code", code);
                        
                        try {                             
                            // 返回true则将消息的what值为1，为false则what为-1，异常为0  
                            if (code.equals("500")) {  
                                msg.what = -1;  
                                msg.obj = msg1;
                            } else {  
                                msg.what = 1;  
                                msg.obj = data;  
                            }                
                        } catch (Exception e) {  
                            msg.what = 0;  
                            e.printStackTrace();  
                        }  
                        
                        codeHandler.sendMessage(msg); 
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }                           
	            }
	        }, new Response.ErrorListener() {
	            @Override
	            public void onErrorResponse(VolleyError volleyError) {
	            }
	        }){
	            @Override
	            protected Map<String, String> getParams() throws AuthFailureError {	
	            	Log.e("code", verifycode);
	                Map<String, String> hashMap = new HashMap<String, String>();
	                
	                hashMap.put("username", phone2);
	                hashMap.put("code", verifycode);
	                hashMap.put("password", password1);
	                hashMap.put("sub_password", password2);
	                return hashMap;
	            }
	        };
	        request.setTag("rpPost");	        
            SuperDuckApplication.getRequestQueue().add(request); 
            SuperDuckApplication.getRequestQueue().start();
       }  
   };  
    public boolean isValid() {  
 	           
        phone2 = et_rp_phone.getText().toString().trim();
        verifycode = et_rp_verifycode.getText().toString().trim();  
        password1 = et_rp_password1.getText().toString().trim(); 
        password2= et_rp_password2.getText().toString().trim();
  
        if (phone2.equals("")) {  
            Toast.makeText(this, "手机号码不能为空!", Toast.LENGTH_SHORT).show();  
            return false;  
        } else if(!phone2.equals(phone1)){
     	   Toast.makeText(this, "该号码还没获取验证码，请获取！", Toast.LENGTH_SHORT).show();
        }
  
        if (verifycode.equals("")) {  
            Toast.makeText(this, "验证码不能为空!", Toast.LENGTH_SHORT).show();  
            return false;  
        }          
        if(!clientcode.equals(verifycode))  
         {  
            Toast.makeText(this, "验证码错误", Toast.LENGTH_SHORT).show();  
            return false;  
        }  
  
        if (password1.equals("")) {  
            Toast.makeText(this, "密码不能为空!", Toast.LENGTH_SHORT).show();  
            return false;  
        } else if (password1.length() < 8) {  
            Toast.makeText(this, "密码至少8位!", Toast.LENGTH_SHORT).show();  
            return false;  
        }  
        if (password2.equals("")) {  
            Toast.makeText(this, "请再次输入密码!", Toast.LENGTH_SHORT).show();  
            return false;  
        } else if (!password2.equals(password1)) {  
            Toast.makeText(this, "密码不一致！", Toast.LENGTH_SHORT).show();  
            return false;  
        }  
        return true;  
    }

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
		if (TextUtils.isEmpty(et_rp_phone.getText().toString())  
			    && TextUtils.isEmpty(et_rp_verifycode.getText().toString())
			    && TextUtils.isEmpty(et_rp_password1.getText().toString())
			    && TextUtils.isEmpty(et_rp_password2.getText().toString()))  
			{  
			    bt_rp_finish.setEnabled(false);  
			}  
			{  
				bt_rp_finish.setEnabled(true);  
			}  
		
	}  
}
