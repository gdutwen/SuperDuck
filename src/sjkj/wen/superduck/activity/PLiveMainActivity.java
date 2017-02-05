package sjkj.wen.superduck.activity;

import java.util.ArrayList;
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
import com.viewpagerindicator.TabPageIndicator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import sjkj.wen.superduck.R;
import sjkj.wen.superduck.activity.bean.BannerData;
import sjkj.wen.superduck.activity.bean.BannerMsg;
import sjkj.wen.superduck.activity.bean.NBNextCategoryIndex;
import sjkj.wen.superduck.activity.bean.NBNextCategoryIndexData;
import sjkj.wen.superduck.activity.fragment.IshareChildFragment;
import sjkj.wen.superduck.activity.fragment.PLiveChildFragment;
import sjkj.wen.superduck.activity.global.SuperDuckApplication;
import sjkj.wen.superduck.activity.util.Advertisements;

public class PLiveMainActivity extends FragmentActivity {
	private ImageButton ib_ishare_main_back;

    private TextView nbcircle_title;
    private ImageButton nbcircle_add_tpye;

    private ViewPager pager;
    private TabPageIndicator indicator ;
    
    private LinearLayout llAdvertiseBoard;
	private LayoutInflater inflater;
	
	private List<BannerData> picdata;
	private List<NBNextCategoryIndexData> data = new ArrayList<NBNextCategoryIndexData>();
	private String ID;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.plive_main);
		Intent intent = getIntent();
        ID= intent.getStringExtra("type_id");
        Log.e("id", ID);
        findById();
        inflater = LayoutInflater.from(this);
        nbcircle_title.setText("拼生活");
		initadv();

        init(); 
        
        inittitle();
	}
	private void findById() {  
		nbcircle_title = (TextView) findViewById(R.id.nbcircle_title);
    	llAdvertiseBoard = (LinearLayout) findViewById(R.id.llAdvertiseBoard);
    	
    	ib_ishare_main_back = (ImageButton) this.findViewById(R.id.ib_ishare_main_back);
    	
        nbcircle_add_tpye = (ImageButton) this.findViewById(R.id.nbcircle_add_tpye);
        
        pager = (ViewPager)findViewById(R.id.post_pager);
        indicator = (TabPageIndicator)findViewById(R.id.indicator);
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
			      llAdvertiseBoard.addView(new Advertisements(PLiveMainActivity.this, true, inflater, 3000).initView(advertiseArray));
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
    private void inittitle() {
    
    	String url1 = "http://115.28.244.91/sjkjsvn/index.php/Home/Post/nextCategoryIndex"+ "?id=" + ID;
        StringRequest datarequest = new StringRequest(url1, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				Log.e("pic", response);
				Gson gson = new Gson();
				NBNextCategoryIndex message = gson.fromJson(response, NBNextCategoryIndex.class);
				System.out.println("message="+ message.getMsg()); 
				data = message.getData();
				final String[] TITLE = new String[data.size()];
			    for(int i = 0;i<data.size();i++){
			    	
			    	TITLE[i] = data.get(i).getName();
			    	
			    	System.out.println(TITLE[i]);
			    }	
			    /**
				 * ViewPager适配器
				 *
				 */
			    class TabPageIndicatorAdapter extends FragmentPagerAdapter {
			        public TabPageIndicatorAdapter(FragmentManager fm) {
			            super(fm);
			        }

			        @Override
			        public Fragment getItem(int position) {
			        	Fragment fragment = null;
			        	Bundle args = null;
			        	fragment = new PLiveChildFragment();
			        	args = new Bundle();  
			            args.putString("argID", data.get(position).getId()); 					            
			            args.putString("id", ID);
			            fragment.setArguments(args);
			            return fragment;
			            
			        }

			        @Override
			        public CharSequence getPageTitle(int position) {
			            return TITLE[position % TITLE.length];
			        }

			        @Override
			        public int getCount() {
			            return TITLE.length;
			        }
			    }  
			    FragmentPagerAdapter adapter = new TabPageIndicatorAdapter(getSupportFragmentManager());
			    
		        pager.setAdapter(adapter);
		        //实例化TabPageIndicator然后设置ViewPager与之关联
		        indicator.setVisibility(View.VISIBLE);
		        indicator.setViewPager(pager);
		        
		        //如果我们要对ViewPager设置监听，用indicator设置就行了
		        indicator.setOnPageChangeListener(new OnPageChangeListener() {
					
					@Override
					public void onPageSelected(int arg0) {
						Toast.makeText(getApplicationContext(), TITLE[arg0], Toast.LENGTH_SHORT).show();
					}
					
					@Override
					public void onPageScrolled(int arg0, float arg1, int arg2) {
						
					}
					
					@Override
					public void onPageScrollStateChanged(int arg0) {
						
					}
				});
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
       
        SuperDuckApplication.getRequestQueue().add(datarequest); 
        SuperDuckApplication.getRequestQueue().start();
		
	}
    
	private void init() {  	        
        
        
        nbcircle_add_tpye.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(PLiveMainActivity.this, PLiveChooseTypeActivity.class);
				intent.putExtra("id", ID);
				startActivity(intent);
				
			}
		});
        ib_ishare_main_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
   
	}
}
