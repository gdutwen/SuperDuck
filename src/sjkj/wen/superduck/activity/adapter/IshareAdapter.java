package sjkj.wen.superduck.activity.adapter;



import java.util.List;

import com.android.volley.toolbox.ImageLoader;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import sjkj.wen.superduck.R;
import sjkj.wen.superduck.activity.bean.IsharePostListData;
import sjkj.wen.superduck.activity.global.SuperDuckApplication;
import sjkj.wen.superduck.activity.view.CircleImageView;

public class IshareAdapter extends BaseAdapter {
	final int TYPE_1 = 0;
    final int TYPE_2 = 1;
    final int TYPE_3 = 2;
    class ViewHolder1{
    	CircleImageView portrait;
	    TextView nickname;
	    TextView time;
	    TextView title;
	    TextView content;
	    ImageView iv;
	    TextView address;
	    TextView like_num;
	    TextView comment_num;
    }
	class ViewHolder2{
		CircleImageView portrait2;
	    TextView nickname2;
	    TextView time2;
	    TextView title2;
	    TextView content2;
	    ImageView ivone;
	    ImageView ivtwo;
	    TextView address2;
	    TextView like_num2;
	    TextView comment_num2;
	}
	class ViewHolder3{
		   
		    CircleImageView portrait3;
		    TextView nickname3;
		    TextView time3;
		    TextView title3;
		    TextView content3;
		    ImageView iv1;
		    ImageView iv2;
		    ImageView iv3;
		    TextView address3;
		    TextView like_num3;
		    TextView comment_num3;
	   }
	
	private Context context;  
    private LayoutInflater listContainer;
    
    private List<IsharePostListData> postdata;
    private ImageLoader imageLoader;
    private int[] imageacount;
    
    public IshareAdapter(Context context,List<IsharePostListData> postdata,int[] imageacount) { 
        this.context = context;  
        
        this.postdata = postdata;
        this.imageacount = imageacount;
        imageLoader=((SuperDuckApplication) context.getApplicationContext()).getImageLoader();
        for (int j = 0; j < imageacount.length; j++) {
			System.out.println(imageacount[j]);
		}
        }  
    @Override
    public int getItemViewType(int position) {
    	if(imageacount[position] == 3){
    		return TYPE_3 ;
    	}else if(imageacount[position] == 2){
    		return TYPE_2;
    	}else{
    		return TYPE_1;
    	}
    }
    @Override
    public int getViewTypeCount() {
    	
    	return 3;
    }
	@Override
	public int getCount() {
		
		return postdata.size();
		
	}

	@Override
	public Object getItem(int position) {
		
		return postdata.get(position);
	}

	@Override
	public long getItemId(int position) {
		
		return position;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder1 viewHolder = null;	
		ViewHolder2 viewHolder2 = null;
		ViewHolder3 viewHolder3 = null;
		int type = getItemViewType(position);
		if (convertView == null) {
			viewHolder = new ViewHolder1();
			viewHolder2 = new ViewHolder2();
			viewHolder3 = new ViewHolder3();
			System.out.println(position);
			
			switch (type) {
			case 0:
				listContainer = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = listContainer.inflate(R.layout.ishare_list_item1, null);
				viewHolder.iv = (ImageView) convertView.findViewById(R.id.ishare_list_pic);
				viewHolder.portrait = (CircleImageView) convertView.findViewById(R.id.ishare_head_photo);
				viewHolder.nickname = (TextView) convertView.findViewById(R.id.ishare_user_name);
				viewHolder.time = (TextView) convertView.findViewById(R.id.ishare_messge_time);
				viewHolder.title = (TextView) convertView.findViewById(R.id.ishare_message_title);
				viewHolder.content = (TextView) convertView.findViewById(R.id.ishare_message_content);		
				viewHolder.address = (TextView) convertView.findViewById(R.id.ishare_post_address);
				viewHolder.like_num = (TextView) convertView.findViewById(R.id.ishare_praise_acount);
				viewHolder.comment_num = (TextView) convertView.findViewById(R.id.ishare_comment_acount);
				convertView.setTag(viewHolder);
				break;
			case 1:
				listContainer = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = listContainer.inflate(R.layout.ishare_list_item2, null);
				viewHolder2.ivone = (ImageView) convertView.findViewById(R.id.ishare_list_picone);
				viewHolder2.ivtwo = (ImageView) convertView.findViewById(R.id.ishare_list_pictwo);
				viewHolder2.portrait2 = (CircleImageView) convertView.findViewById(R.id.ishare_head_photo2);
				viewHolder2.nickname2 = (TextView) convertView.findViewById(R.id.ishare_user_name2);
				viewHolder2.time2 = (TextView) convertView.findViewById(R.id.ishare_messge_time2);
				viewHolder2.title2 = (TextView) convertView.findViewById(R.id.ishare_message_title2);
				viewHolder2.content2 = (TextView) convertView.findViewById(R.id.ishare_message_content2);		
				viewHolder2.address2 = (TextView) convertView.findViewById(R.id.ishare_post_address2);
				viewHolder2.like_num2 = (TextView) convertView.findViewById(R.id.ishare_praise_acount2);
				viewHolder2.comment_num2 = (TextView) convertView.findViewById(R.id.ishare_comment_acount2);
				convertView.setTag(viewHolder2);
				break;
			case 2:
				listContainer = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = listContainer.inflate(R.layout.ishare_list_item3, null);
				viewHolder3.iv1 = (ImageView) convertView.findViewById(R.id.ishare_list_pic1);
				viewHolder3.iv2 = (ImageView) convertView.findViewById(R.id.ishare_list_pic2);
				viewHolder3.iv3 = (ImageView) convertView.findViewById(R.id.ishare_list_pic3);
				viewHolder3.portrait3 = (CircleImageView) convertView.findViewById(R.id.ishare_head_photo3);
				viewHolder3.nickname3 = (TextView) convertView.findViewById(R.id.ishare_user_name3);
				viewHolder3.time3 = (TextView) convertView.findViewById(R.id.ishare_messge_time3);
				viewHolder3.title3 = (TextView) convertView.findViewById(R.id.ishare_message_title3);
				viewHolder3.content3 = (TextView) convertView.findViewById(R.id.ishare_message_content3);		
				viewHolder3.address3 = (TextView) convertView.findViewById(R.id.ishare_post_address3);
				viewHolder3.like_num3 = (TextView) convertView.findViewById(R.id.ishare_praise_acount3);
				viewHolder3.comment_num3 = (TextView) convertView.findViewById(R.id.ishare_comment_acount3);
				convertView.setTag(viewHolder3);
				break;
			
			}
						
			
			
			
			
		}else {
			switch (type) {
			case 0:
				viewHolder = (ViewHolder1) convertView.getTag();
				break;
			case 1:
				viewHolder2 = (ViewHolder2) convertView.getTag();
				break;
			case 2:
				viewHolder3 = (ViewHolder3) convertView.getTag();
				break;
			default:
				break;
			}
			
		}
		
		
		switch (type) {
		case 0:
			imageLoader.get(postdata.get(position).getPortrait(), ImageLoader.getImageListener(viewHolder.portrait,
	                R.drawable.default_photo, R.drawable.default_photo));	


		 
		viewHolder.nickname.setText(postdata.get(    
                position).getNickname());		

		String date = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(postdata.get(position).getCreate_time()*1000));
		viewHolder.time.setText(date);
		
		viewHolder.title.setText(postdata.get(    
                position).getTitle());
		viewHolder.content.setText(postdata.get(    
                position).getContent());
		viewHolder.address.setText(postdata.get(    
                position).getAddress());
		viewHolder.like_num.setText(postdata.get(    
                position).getLike_num());
		viewHolder.comment_num.setText(postdata.get(    
                position).getComment_num());
				
		
		imageLoader.get(postdata.get(position).getImages()[0], ImageLoader.getImageListener(viewHolder.iv,
                R.drawable.default_photo, R.drawable.default_photo));
			break;
		case 1:
			imageLoader.get(postdata.get(position).getImages()[1], ImageLoader.getImageListener(viewHolder2.ivtwo,
	                R.drawable.default_photo, R.drawable.default_photo));
			imageLoader.get(postdata.get(position).getPortrait(), ImageLoader.getImageListener(viewHolder2.portrait2,
	                R.drawable.default_photo, R.drawable.default_photo));	


		 
		viewHolder2.nickname2.setText(postdata.get(    
                position).getNickname());		

		String date2 = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(postdata.get(position).getCreate_time()*1000));
		viewHolder2.time2.setText(date2);
		
		viewHolder2.title2.setText(postdata.get(    
                position).getTitle());
		viewHolder2.content2.setText(postdata.get(    
                position).getContent());
		viewHolder2.address2.setText(postdata.get(    
                position).getAddress());
		viewHolder2.like_num2.setText(postdata.get(    
                position).getLike_num());
		viewHolder2.comment_num2.setText(postdata.get(    
                position).getComment_num());
				
		
		imageLoader.get(postdata.get(position).getImages()[0], ImageLoader.getImageListener(viewHolder2.ivone,
                R.drawable.default_photo, R.drawable.default_photo));
			break;
		case 2:
			imageLoader.get(postdata.get(position).getImages()[1], ImageLoader.getImageListener(viewHolder3.iv2,
	                R.drawable.default_photo, R.drawable.default_photo));
			imageLoader.get(postdata.get(position).getImages()[2], ImageLoader.getImageListener(viewHolder3.iv3,
	                R.drawable.default_photo, R.drawable.default_photo));
			imageLoader.get(postdata.get(position).getPortrait(), ImageLoader.getImageListener(viewHolder3.portrait3,
	                R.drawable.default_photo, R.drawable.default_photo));	


		 
		viewHolder3.nickname3.setText(postdata.get(    
                position).getNickname());		

		String date3 = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(postdata.get(position).getCreate_time()*1000));
		viewHolder3.time3.setText(date3);
		
		viewHolder3.title3.setText(postdata.get(    
                position).getTitle());
		viewHolder3.content3.setText(postdata.get(    
                position).getContent());
		viewHolder3.address3.setText(postdata.get(    
                position).getAddress());
		viewHolder3.like_num3.setText(postdata.get(    
                position).getLike_num());
		viewHolder3.comment_num3.setText(postdata.get(    
                position).getComment_num());
				
		
		imageLoader.get(postdata.get(position).getImages()[0], ImageLoader.getImageListener(viewHolder3.iv1,
                R.drawable.default_photo, R.drawable.default_photo));
			break;
		default:
			break;
		}
			
		
		return convertView;
	}
    
}
    
