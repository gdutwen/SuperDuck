package sjkj.wen.superduck.activity;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.JsonObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import sjkj.wen.superduck.R;
import sjkj.wen.superduck.activity.global.SuperDuckApplication;
import sjkj.wen.superduck.activity.util.MultipartRequest;

public class IsharePostMsgActivity extends Activity implements OnClickListener,TextWatcher {
	
	private TextView ishare_post_title;
	private TextView bt_post_msg_know;
	private LinearLayout ll_ishare_add_pic;
	private ImageView iv_ishare_add_pic1;
	private ImageView iv_ishare_add_pic2;
	private ImageView iv_ishare_add_pic3;
	private ImageButton ib_ishare_post_message_back;
	private Button bt_post_messge;
	
	private EditText et_post_msg_title;
	private EditText et_post_msg_content;
	
	private static final int REQUEST_IMAGE = 2;
	private ArrayList<String> mSelectPath;
	private static String url = "http://115.28.244.91/sjkjsvn/index.php/Home/Post/createPost";
	private static String TAG = "psot";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ishare_post_message);
		Intent intent = getIntent();
		final String title=intent.getStringExtra("title");
		initview();
		ishare_post_title.setText(title);
		
	}

	private void initview() {
		bt_post_messge = (Button) findViewById(R.id.bt_post_messge);
		et_post_msg_title = (EditText) findViewById(R.id.et_post_msg_title);
		et_post_msg_content = (EditText) findViewById(R.id.et_post_msg_content);
		
		ishare_post_title = (TextView) findViewById(R.id.ishare_post_title);
		bt_post_msg_know = (TextView) findViewById(R.id.bt_post_msg_know);
		ll_ishare_add_pic = (LinearLayout) findViewById(R.id.ll_ishare_add_pic);
		iv_ishare_add_pic1 = (ImageView) findViewById(R.id.iv_ishare_add_pic1);
		iv_ishare_add_pic2 = (ImageView) findViewById(R.id.iv_ishare_add_pic2);
		iv_ishare_add_pic3 = (ImageView) findViewById(R.id.iv_ishare_add_pic3);
		ib_ishare_post_message_back = (ImageButton) findViewById(R.id.ib_ishare_post_message_back);
		
		bt_post_msg_know.setOnClickListener(this);
		ll_ishare_add_pic.setOnClickListener(this);
		bt_post_messge.setOnClickListener(this);
		et_post_msg_title.addTextChangedListener(this);
		et_post_msg_content.addTextChangedListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ib_ishare_post_message_back:
			finish();
			break;
		case R.id.bt_post_msg_know:
			Intent intent = new Intent(IsharePostMsgActivity.this, PostknowActivity.class);
			startActivity(intent);
			break;
		case R.id.ll_ishare_add_pic:
			Intent intent1 = new Intent(IsharePostMsgActivity.this, MultiImageSelectorActivity.class);
			if(mSelectPath != null && mSelectPath.size()>0){  
                intent1.putExtra(MultiImageSelectorActivity.EXTRA_DEFAULT_SELECTED_LIST, mSelectPath);  
            }  
			startActivityForResult(intent1, REQUEST_IMAGE);
			break;
		case R.id.bt_post_messge:	
			String path;
			File[] file =new File[mSelectPath.size()] ;
			List<File> files = new ArrayList<File>();
			String images = "images";
			for (int i = 0; i < mSelectPath.size(); i++) {
				path = mSelectPath.get(i);
				Log.e("path", path);
				file[i] =new File(path);
			    
				files.add(file[i]);
				System.out.println(files);
			}
			Map<String, String> hashMap = new HashMap<String, String>();
		    hashMap.put("p_category_id", "31");
		    hashMap.put("category_id", "36");
		    hashMap.put("is_address", "0");
		    hashMap.put("is_anonymous", "0");									    				    
            hashMap.put("title", et_post_msg_title.getText().toString());
            hashMap.put("content", et_post_msg_content.getText().toString());
            MultipartRequest(url, new ErrorListener() {

				@Override
				public void onErrorResponse(VolleyError error) {
					// TODO Auto-generated method stub
					
				}
			}, new Listener<String>() {

				@Override
				public void onResponse(String response) {
					Log.e("post", response);
					
				}
			}, images, files, hashMap);
			break;
		default:
			break;
		}		
	}
	public static void MultipartRequest(String url, Response.ErrorListener errorListener,
			Response.Listener<String> listener, String filePartName,
			List<File> files, Map<String, String> params) {
		
		MultipartRequest request = new MultipartRequest(url, errorListener, listener, filePartName, files, params){
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				HashMap<String, String> headers = new HashMap<String, String>();  
                headers.put("Token", "abfce2bb68b04de78cef86484995f47f");                   
            	return headers;
			}
		};
		SuperDuckApplication.getRequestQueue().add(request); 
      SuperDuckApplication.getRequestQueue().start();
    }
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE){
            if(resultCode == RESULT_OK){
                mSelectPath = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                StringBuilder sb = new StringBuilder();
                for(String p: mSelectPath){
                    sb.append(p);
                    sb.append("\n");
                }
                System.out.println("data="+mSelectPath);
                String path1 = mSelectPath.get(0);
                Bitmap bm1 = BitmapFactory.decodeFile(path1); 
                iv_ishare_add_pic1.setImageBitmap(bm1);
                String path2 = mSelectPath.get(1);
                Bitmap bm2 = BitmapFactory.decodeFile(path2); 
                iv_ishare_add_pic2.setImageBitmap(bm2);
                String path3 = mSelectPath.get(2);
                Bitmap bm3 = BitmapFactory.decodeFile(path3); 
                iv_ishare_add_pic3.setImageBitmap(bm3);
            }
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
		if (TextUtils.isEmpty(et_post_msg_title.getText().toString())  
			    && TextUtils.isEmpty(et_post_msg_content.getText().toString()))  
			{  
			   bt_post_messge.setEnabled(false);  
			}  
			else  
			{  
				bt_post_messge.setEnabled(true);  
			}  
		
	}
}
