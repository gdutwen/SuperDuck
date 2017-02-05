package sjkj.wen.superduck.activity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import sjkj.wen.superduck.R;
import sjkj.wen.superduck.activity.adapter.SmallClassAdapter;
import sjkj.wen.superduck.activity.bean.BannerData;
import sjkj.wen.superduck.activity.bean.BannerMsg;
import sjkj.wen.superduck.activity.bean.IsharePostListData;
import sjkj.wen.superduck.activity.bean.IsharePostListmsg;
import sjkj.wen.superduck.activity.global.SuperDuckApplication;
import sjkj.wen.superduck.activity.util.Advertisements;

public class SmallClassActivity extends Activity implements OnClickListener{
	private LinearLayout llAdvertiseBoard;
	private String ID;
	private List<BannerData> picdata;
	private LayoutInflater inflater;
	private ListView listview;
	private List<IsharePostListData> postdata;
	private ImageButton smallclass_add_tpye;
	private ImageButton ib_smallclass_back;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.smallclass);
		Intent intent = getIntent();
        ID= intent.getStringExtra("type_id");
        Log.e("id", ID);
        findById();
        inflater = LayoutInflater.from(this);
        initadv();
        initdata();
        
	}
	private void findById() {
		llAdvertiseBoard = (LinearLayout) findViewById(R.id.llAdvertiseBoard);
		listview = (ListView) findViewById(R.id.smallclass_listview);
		smallclass_add_tpye = (ImageButton) findViewById(R.id.smallclass_add_tpye);
		ib_smallclass_back = (ImageButton) findViewById(R.id.ib_smallclass_back);
		
		smallclass_add_tpye.setOnClickListener(this);
		ib_smallclass_back.setOnClickListener(this);
	}


	private void initdata() {
		
		
		String url = "http://115.28.244.91/sjkjsvn/index.php/Home/Post/itemsPost"+"?id="+ ID +"?category_id" +ID ;
		StringRequest postdatarequest = new StringRequest(url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				Log.e("post", response);
				Gson gson = new Gson();
				IsharePostListmsg post = gson.fromJson(response, IsharePostListmsg.class);
				postdata =post.getData();				
				SmallClassAdapter adapter = new SmallClassAdapter(SmallClassActivity.this, postdata);
				listview.setAdapter(adapter);
				listview.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						Toast.makeText(SmallClassActivity.this, "你单击了"+position, Toast.LENGTH_SHORT).show();
//						Intent intent  = new Intent(getActivity(), IsharePostDetailActivity.class);
//						intent.putExtra("postID", postdata.get(position).getId());
//						startActivity(intent);
					}
				});
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				
			}
		}){
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				// TODO Auto-generated method stub
				HashMap<String, String> headers = new HashMap<String, String>();  
                headers.put("Token", "abfce2bb68b04de78cef86484995f47f");                   
            	return headers;
			}
		};
		SuperDuckApplication.getRequestQueue().add(postdatarequest); 
        SuperDuckApplication.getRequestQueue().start();
    }
	


	
	private void initadv() {
		String url1 = "http://115.28.244.91/sjkjsvn/index.php/Home/Banner/itemsBanner?object_type=NHC";
        StringRequest picrequest = new StringRequest(url1, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				Log.e("pic", response);
				Gson gson = new Gson();
				BannerMsg bannermsg = gson.fromJson(response, BannerMsg.class);
				System.out.println("message="+ bannermsg.getMsg()); 
			    picdata = bannermsg.getData();			    
			    JSONArray advertiseArray = new JSONArray();
			      try {
			         JSONObject head_img0 = new JSONObject();
			         head_img0.put("head_img",picdata.get(0).getImage());
			         JSONObject head_img1 = new JSONObject();
			         head_img1.put("head_img",picdata.get(1).getImage());
			         JSONObject head_img2 = new JSONObject();
			         head_img2.put("head_img",picdata.get(2).getImage());
			         JSONObject head_img3 = new JSONObject();
			         head_img3.put("head_img",picdata.get(3).getImage());
			         advertiseArray.put(head_img0);
			         advertiseArray.put(head_img1);
			         advertiseArray.put(head_img2);
			         advertiseArray.put(head_img3);
			      } catch (JSONException e) {
			         // TODO Auto-generated catch block
			         e.printStackTrace();
			      }
			      llAdvertiseBoard.addView(new Advertisements(SmallClassActivity.this, true, inflater, 3000).initView(advertiseArray));
			}
    	},new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				
			}
		} ){
        	@Override
        	public Map<String, String> getHeaders() throws AuthFailureError {
        		HashMap<String, String> headers = new HashMap<String, String>();  
                headers.put("Token", "abfce2bb68b04de78cef86484995f47f");                   
            	return headers;
        	}
        };
        SuperDuckApplication.getRequestQueue().add(picrequest); 
        SuperDuckApplication.getRequestQueue().start();
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.smallclass_add_tpye:
			Intent intent = new Intent(SmallClassActivity.this, SmallClassPostMsgActivity.class);
			intent.putExtra("id", ID);
			startActivity(intent);
			break;
		case R.id.ib_smallclass_back:
			finish();
			break;
		default:
			break;
		}
		
	}
}
