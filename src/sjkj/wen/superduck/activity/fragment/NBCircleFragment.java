package sjkj.wen.superduck.activity.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import sjkj.wen.superduck.R;
import sjkj.wen.superduck.activity.IShareMainActivity;
import sjkj.wen.superduck.activity.NBHelpAndYUnusedMainActivity;
import sjkj.wen.superduck.activity.PLiveMainActivity;
import sjkj.wen.superduck.activity.SmallClassActivity;
import sjkj.wen.superduck.activity.adapter.AdvAdapter;
import sjkj.wen.superduck.activity.adapter.NBcircleMainAdapter;
import sjkj.wen.superduck.activity.bean.BannerData;
import sjkj.wen.superduck.activity.bean.BannerMsg;
import sjkj.wen.superduck.activity.bean.NBCategoryIndex;
import sjkj.wen.superduck.activity.bean.NBCategoryIndexData;
import sjkj.wen.superduck.activity.global.SuperDuckApplication;
import sjkj.wen.superduck.activity.util.Advertisements;

public class NBCircleFragment extends Fragment {
	
	
	private ListView listview;
	private LinearLayout llAdvertiseBoard;
	private LayoutInflater inflater;
	
	private View view ;
	
	private ArrayList<Map<String,Object>> mInfos= new ArrayList<Map<String,Object>>();
	private NBcircleMainAdapter dataAdapter;
	private List<NBCategoryIndexData> data;
	private List<BannerData> picdata;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		view = inflater.inflate(R.layout.fragment_nbcircle, container, false);
		initview();
		
		return view ;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		inflater = LayoutInflater.from(getActivity());
		initadv();
		requestdate();
		
		
		
	}
    private void initview() {
		
		listview = (ListView) view.findViewById(R.id.nbcircle_main_list);
		llAdvertiseBoard = (LinearLayout) view.findViewById(R.id.llAdvertiseBoard);
		  		
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
			      llAdvertiseBoard.addView(new Advertisements(getActivity(), true, inflater, 3000).initView(advertiseArray));
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
	

	
    
   
    private void requestdate(){
    	String url = "http://115.28.244.91/sjkjsvn/index.php/Home/Post/categoryIndex";
    	StringRequest datarequest = new StringRequest(url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				Log.e("NBCategoryIndexData", response);
				Gson gson = new Gson();
				NBCategoryIndex message = gson.fromJson(response, NBCategoryIndex.class);
				System.out.println("message="+message.getMsg()); 
				data = message.getData();
				
				System.out.println(data.get(0).getTitle());
				int[] images = {R.drawable.icon_ishare,R.drawable.icon_plive,
						R.drawable.icon_nbhelp,R.drawable.icon_yunused,
						R.drawable.icon_smallclass};
				for(int i=0;i<data.size();i++){ 
		            Map<String,Object> item = new HashMap<String,Object>();  
		            item.put("name", data.get(i).getName()); 
		            item.put("title", data.get(i).getTitle());
		            item.put("pic", images[i]);
		            mInfos.add(item); 		            
			    }
				System.out.println(mInfos);
				dataAdapter=new NBcircleMainAdapter(getActivity().getApplicationContext(),mInfos);
				
				listview.setAdapter(dataAdapter);
				listview.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						Toast.makeText(getActivity(), "你单击了第" + position + "个", Toast.LENGTH_SHORT).show();
						switch (position) {
						case 0:
							Intent intent = new Intent(getActivity(), IShareMainActivity.class);
							intent.putExtra("type_id", data.get(0).getId());
							startActivity(intent);
							break;
						case 1:
							Intent intent1 = new Intent(getActivity(), PLiveMainActivity.class);
							intent1.putExtra("type_id", data.get(1).getId());
							startActivity(intent1);
							break;
						case 2:
							Intent intent2 = new Intent(getActivity(), NBHelpAndYUnusedMainActivity.class);
							intent2.putExtra("type_id", data.get(2).getId());
							startActivity(intent2);
							break;
						case 3:
							Intent intent3 = new Intent(getActivity(), NBHelpAndYUnusedMainActivity.class);
							intent3.putExtra("type_id", data.get(3).getId());
							startActivity(intent3);
							break;
						case 4:
							Intent intent4 = new Intent(getActivity(), SmallClassActivity.class);
							intent4.putExtra("type_id", data.get(4).getId());
							startActivity(intent4);
							break;
						default:
							break;
						}
						
					}
				});
				
		}}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				
			}
		}){
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
    
   
    
}
