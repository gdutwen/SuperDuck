package sjkj.wen.superduck.activity.fragment;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import sjkj.wen.superduck.R;
import sjkj.wen.superduck.activity.IsharePostDetailActivity;
import sjkj.wen.superduck.activity.adapter.IshareAdapter;
import sjkj.wen.superduck.activity.bean.IsharePostListData;
import sjkj.wen.superduck.activity.bean.IsharePostListmsg;
import sjkj.wen.superduck.activity.global.SuperDuckApplication;

public class IshareChildFragment extends Fragment {
	private View view;
	private ListView ishare_listview;
	private IshareAdapter adapter;
	
	private List<IsharePostListData> postdata;
	  
	@Override  
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){  
        


        ViewGroup p = (ViewGroup) view.getParent();
        if (p != null) {
            p.removeAllViewsInLayout();
        }
        ishare_listview = (ListView) view.findViewById(R.id.ishare_listview); 
        return view;      
    } 	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.fragemnt_ishare_item, (ViewGroup) getActivity().findViewById(R.id.post_pager), false);
	}
	@Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            
            qryDataFromServer();
        } else {
            
        }
    }

    private void qryDataFromServer() {
    	Bundle mBundle = getArguments();
		String category_id = mBundle.getString("argID");
		String id = mBundle.getString("id");
		
		Log.e("id", "id=" + id +",category_id="+ category_id);
				
		String url = "http://115.28.244.91/sjkjsvn/index.php/Home/Post/itemsPost"+"?id="+ id +"?category_id" +category_id ;
		StringRequest postdatarequest = new StringRequest(url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				Log.e("post", response);
				Gson gson = new Gson();
				IsharePostListmsg post = gson.fromJson(response, IsharePostListmsg.class);
				postdata =post.getData();
				int[] imageacount = new int[postdata.size()] ;
				for(int i = 0; i<postdata.size();i++){
					if (postdata.get(i).getImages().length == 1) {
						imageacount[i] =1;
					}else if(postdata.get(i).getImages().length == 2){
						imageacount[i] =2;
					}else{
						imageacount[i] =3;
					}
					
				}
				for (int j = 0; j < imageacount.length; j++) {
					System.out.println(imageacount[j]);
				}
				//System.out.println(postdata.get(0).getDestination());
				adapter = new IshareAdapter(getActivity().getApplicationContext(), postdata,imageacount);
			       ishare_listview.setAdapter(adapter);
			       ishare_listview.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						Toast.makeText(getActivity(), "你单击了"+position, Toast.LENGTH_SHORT).show();
						Intent intent  = new Intent(getActivity(), IsharePostDetailActivity.class);
						intent.putExtra("postID", postdata.get(position).getId());
						startActivity(intent);
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
}
