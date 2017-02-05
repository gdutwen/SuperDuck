package sjkj.wen.superduck.activity;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.baidu.mapapi.SDKInitializer;
import com.google.gson.Gson;

import android.app.Activity;
import android.database.CursorJoiner.Result;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import sjkj.wen.superduck.R;
import sjkj.wen.superduck.activity.bean.ClientMsg;
import sjkj.wen.superduck.activity.global.SuperDuckApplication;
import sjkj.wen.superduck.activity.util.CountTimerUtils;
import sjkj.wen.superduck.activity.util.GsonUtils;
import sjkj.wen.superduck.activity.util.StringUtil;


public class RegisterActivity extends Activity implements OnClickListener,TextWatcher {
	//控件
	private Button bt_id_code;	
	private ImageButton bt_back;
	private EditText et_rg_nickname;
	private EditText et_phone;
	private EditText et_code;
	private EditText et_rg_password1;
	private EditText et_rg_password2;
	private CheckBox ck_rgeye;
	private EditText et_address;
	private Button bt_register;
	private Button bt_user_know;
	
	
	private LocationClient mLocationClient;
	
	 // 变量 
    private String nickName;  
    private String passWord1; 
    private String phone1;
    private String phone2;
    private String passWord2;
    private String address;
    //验证码
    private String verifycode;
    //服务器返回码
    private String clientmsg;
    //经纬度
	private double Latitude;
	private double Longitude;
	
	private CountTimerUtils time;
      
    public boolean isChange = false;  
    private boolean tag = true;  
    private int i = 60;  

	boolean flag = false; 
	/**客户端输入的验证码*/  
    private String valicationCode;  
      
    /**服务器端获取的返回信息*/  
    private static String verifyserverValicationmsg; 
    private String clientcode;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		SDKInitializer.initialize(getApplicationContext());
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.register);		
		mLocationClient = new LocationClient(this.getApplicationContext());
		mLocationClient.registerLocationListener(new MyLocationListener());
		initview();
		time = new CountTimerUtils(bt_id_code,60000,1000);		
	}	
	public void initview(){
		bt_id_code = (Button) findViewById(R.id.bt_id_code);	
		bt_back = (ImageButton) findViewById(R.id.bt_back);
		et_rg_password1 = (EditText) findViewById(R.id.et_rg_password1);
		et_rg_password2 = (EditText) findViewById(R.id.et_rg_password2);
		ck_rgeye = (CheckBox) findViewById(R.id.ck_rgeye);
		et_address = (EditText) findViewById(R.id.et_address);
		et_rg_nickname = (EditText) findViewById(R.id.et_rg_nickname);
		et_phone = (EditText) findViewById(R.id.et_rg_phone);
		et_code = (EditText) findViewById(R.id.et_rg_code);
		bt_register = (Button) findViewById(R.id.bt_register);
		bt_user_know = (Button) findViewById(R.id.bt_user_know);
		
		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(LocationMode.Hight_Accuracy);		
		int span=1000;
		option.setScanSpan(span);
		option.setIsNeedAddress(true);
		mLocationClient.setLocOption(option);
		mLocationClient.start();
		
		bt_id_code.setOnClickListener(this);
		bt_back.setOnClickListener(this);		
		bt_register.setOnClickListener(this);
		bt_user_know.setOnClickListener(this);
		
		et_rg_password1.addTextChangedListener(this);
		et_rg_password2.addTextChangedListener(this);
		et_rg_nickname.addTextChangedListener(this);
		et_phone.addTextChangedListener(this);
		et_code.addTextChangedListener(this);
		
		ck_rgeye.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){  
                    //设置为密文显示  
					et_rg_password1.setTransformationMethod(PasswordTransformationMethod.getInstance());
					et_rg_password2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }else{  
                    //设置为明文显示  
                	et_rg_password1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                	et_rg_password2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }  
                //光标在最后显示  
				et_rg_password1.setSelection(et_rg_password1.length()); 
				et_rg_password2.setSelection(et_rg_password2.length()); 
            }  
				
			
		});
	}
	public class MyLocationListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			Latitude = location.getLatitude();
			Longitude = location.getLongitude();
			et_address.setText(location.getAddrStr());
		}
	} 	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_id_code:
			if (TextUtils.isEmpty(et_phone.getText()))
            {
                Toast.makeText(RegisterActivity.this,"请输入手机号",Toast.LENGTH_SHORT).show();
               
            }
            else if (!StringUtil.isPhoneNumberValid(et_phone.getText().toString()))
            {
                Log.e("phoneNum.getText()",et_phone.getText().toString().length()+"");
                et_phone.getText().clear();
                Toast.makeText(RegisterActivity.this,"输入有误，请重新输入",Toast.LENGTH_SHORT).show();
            }
            else
            {
            	Toast.makeText(RegisterActivity.this,"获取验证码中。。。",Toast.LENGTH_SHORT).show();
                //进行验证            
                time.start();
                
                Thread codeThread = new Thread(codeRunnable);  
                codeThread.start(); 
            }
			break;
		case R.id.bt_back:
			finish();
			break;
		
		case R.id.bt_register:
			register();
			break;
		case R.id.bt_user_know:
			Toast.makeText(RegisterActivity.this, "用户须知",Toast.LENGTH_LONG).show();
			break;
		default:
			break;
		}		
	}
	@Override
	public void onBackPressed() {
		finish();
	}
	@Override
	protected void onStop() {
		mLocationClient.stop();
		super.onStop();
	}
	/** 
     * 自定义一个静态的具有弱引用的Handler，解决内存泄漏的问题,本handler用来获取验证码 
     */  
    private static class CodeHandler extends Handler {  
        // 持有对本外部类的弱引用  
        private final WeakReference<RegisterActivity> mActivity;  
  
        public CodeHandler(RegisterActivity activity) {  
            mActivity = new WeakReference<RegisterActivity>(activity);  
        }   
        @Override  
        public void handleMessage(Message msg) {                
            // 获取上下文对象  
            RegisterActivity activity = mActivity.get();  
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
        	
        	String clientcode = null;
        	final Gson gson = null;
        	String  code;
        	 final Message msg = new Message();  
        	 phone1 = et_phone.getText().toString();
             String url = "http://192.168.0.108/sjkj/index.php/Home/Login/code"+"?mobile="+ et_phone.getText().toString();
             Log.e("url", url);            
             StringRequest  request = new StringRequest(url, new Response.Listener<String>() {
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
             },new Response.ErrorListener(){
            		 public void onErrorResponse(VolleyError error) {
            			 
            		 }           	 
			});      
             SuperDuckApplication.getRequestQueue().add(request);
             SuperDuckApplication.getRequestQueue().start();
        }  
    };  
    /** 
     * 自定义一个静态的具有弱引用的Handler，解决内存泄漏的问题，注册使用 
     */  
    private static class registerHandler extends Handler {  
        // 持有对本外部类的弱引用  
        private final WeakReference<RegisterActivity> mActivity;  
  
        public registerHandler(RegisterActivity activity) {  
            mActivity = new WeakReference<RegisterActivity>(activity);  
        }  
  
        @Override  
        public void handleMessage(Message msg) {  
               
            // 获取上下文对象  
            RegisterActivity activity = mActivity.get();  
            if (activity != null) {  
                switch (msg.what) {  
                case 1:  
                    Toast.makeText(activity, "注册成功!", Toast.LENGTH_SHORT).show();  
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
    private final registerHandler mHandler = new registerHandler(this);  
      
    /**自定义子线程*/  
    private Runnable registerRunnable = new Runnable() {  
        @Override  
        public void run() {  
        	String  code;
       	 final Message msg = new Message();  
       	 String url = "http://192.168.0.108/sjkj/index.php/Home/Login/register";
			// 创建StringRequest，定义字符串请求的请求方式为POST，
	        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
	            // 请求成功后执行的函数
	            @Override
	            public void onResponse(String response) {   
	            	Log.d("TAG", response);  
           		 try {
                        JSONObject jsonObject = new JSONObject(response);
                        Log.e("tag", "登陆的result=" + jsonObject);
                        String msg1 = jsonObject.optString("msg");
                        clientcode = jsonObject.optString("data");
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
	        }, new Response.ErrorListener() {
	            // 请求失败时执行的函数
	            @Override
	            public void onErrorResponse(VolleyError volleyError) {
	            }
	        }){
	            // 定义请求数据
	            @Override
	            protected Map<String, String> getParams() throws AuthFailureError {	            	
	                Map<String, String> hashMap = new HashMap<String, String>();
	                hashMap.put("nickname", nickName);
	                hashMap.put("username", phone2);
	                hashMap.put("code", verifycode);
	                hashMap.put("password", passWord1);
	                hashMap.put("sub_password", passWord2);
	                hashMap.put("address", et_address.getText().toString());
	                hashMap.put("lat", Double.toString(Latitude));
	                hashMap.put("lng", Double.toString(Longitude));
	                return hashMap;
	            }
	        };
	        // 设置该请求的标签
	        request.setTag("registerPost");
	        // 将请求添加到队列中	        
            SuperDuckApplication.getRequestQueue().add(request); 
            SuperDuckApplication.getRequestQueue().start();
       }  
   };  
   public boolean isValid() {  
	   
       nickName = et_rg_nickname.getText().toString().trim();
       phone2 = et_phone.getText().toString().trim();
       verifycode = et_code.getText().toString().trim();  
       passWord1 = et_rg_password1.getText().toString().trim(); 
       passWord2 = et_rg_password2.getText().toString().trim();
 
         
         
       if (nickName.equals("")) {  
           Toast.makeText(this, "昵称不能为空!", Toast.LENGTH_SHORT).show();  
           return false;  
       }  
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
 
       if (passWord1.equals("")) {  
           Toast.makeText(this, "密码不能为空!", Toast.LENGTH_SHORT).show();  
           return false;  
       } else if (passWord1.length() < 8) {  
           Toast.makeText(this, "密码至少8位!", Toast.LENGTH_SHORT).show();  
           return false;  
       }  
       if (passWord2.equals("")) {  
           Toast.makeText(this, "请再次输入密码!", Toast.LENGTH_SHORT).show();  
           return false;  
       } else if (!passWord2.equals(passWord1)) {  
           Toast.makeText(this, "密码不一致！", Toast.LENGTH_SHORT).show();  
           return false;  
       }  
       return true;  
   }  
   public void register() {  
       // 1.首先判断输入的值是否有效   
       // 2.最后注册  
       if (isValid()) {  
               Thread thread = new Thread(registerRunnable);  
               thread.start();                        
       }  
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
		if (TextUtils.isEmpty(et_rg_nickname.getText().toString())  
			    && TextUtils.isEmpty(et_phone.getText().toString())
			    && TextUtils.isEmpty(et_code.getText().toString())
			    && TextUtils.isEmpty(et_rg_password1.getText().toString())
			    && TextUtils.isEmpty(et_rg_password2.getText().toString()))  
			{  
			    bt_register.setEnabled(false);  
			}  
			{  
				bt_register.setEnabled(true);  
			}  
		
	}
}  


