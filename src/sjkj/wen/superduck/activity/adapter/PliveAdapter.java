package sjkj.wen.superduck.activity.adapter;

import java.util.List;

import com.android.volley.toolbox.ImageLoader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import sjkj.wen.superduck.R;

import sjkj.wen.superduck.activity.bean.IsharePostListData;
import sjkj.wen.superduck.activity.global.SuperDuckApplication;
import sjkj.wen.superduck.activity.view.CircleImageView;

public class PliveAdapter extends BaseAdapter {
	private Context context;
	private List<IsharePostListData> postdata;
	private ImageLoader imageLoader;
	private LayoutInflater listContainer;
    public PliveAdapter(Context context,List<IsharePostListData> postdata) {
		this.context = context;
		this.postdata = postdata;
		imageLoader=((SuperDuckApplication) context.getApplicationContext()).getImageLoader();
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
		if (convertView == null) {
			viewHolder = new ViewHolder1();
			viewHolder2 = new ViewHolder2();
			if (postdata.get(position).getId().equals("44")) {
				listContainer = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = listContainer.inflate(R.layout.plive_list_item_other, null);
				viewHolder2.portrait2 = (CircleImageView) convertView.findViewById(R.id.plive_head_other_photo);
				viewHolder2.name2 = (TextView) convertView.findViewById(R.id.plive_tv_other_nickname);
				viewHolder2.time2 = (TextView) convertView.findViewById(R.id.plive_tv_other_time);
				viewHolder2.content2 = (TextView) convertView.findViewById(R.id.plive_tv_other_title);
				viewHolder2.tv = (TextView) convertView.findViewById(R.id.plive_tv_other_content);
				convertView.setTag(viewHolder2);
			}else{
				listContainer = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = listContainer.inflate(R.layout.plive_list_item_common, null);
				viewHolder.portrait = (CircleImageView) convertView.findViewById(R.id.plive_head_photo);
				viewHolder.name = (TextView) convertView.findViewById(R.id.plive_tv_nickname);
				viewHolder.time = (TextView) convertView.findViewById(R.id.plive_tv_time);
				viewHolder.content = (TextView) convertView.findViewById(R.id.plive_tv_content);
				viewHolder.tv1 = (TextView) convertView.findViewById(R.id.plive_tv_content1);
				viewHolder.tv2 = (TextView) convertView.findViewById(R.id.plive_tv_content2);
				viewHolder.tv3 = (TextView) convertView.findViewById(R.id.plive_tv_content3);
				convertView.setTag(viewHolder);
			}	
		}else{
			if (postdata.get(position).getId().equals("44")) {
				viewHolder2 = (ViewHolder2) convertView.getTag();
			}else{
				viewHolder = (ViewHolder1) convertView.getTag();
			}
		}
		if (postdata.get(position).getId().equals("44")) {
			imageLoader.get(postdata.get(position).getPortrait(), ImageLoader.getImageListener(viewHolder2.portrait2,
	                R.drawable.default_photo, R.drawable.default_photo));
			viewHolder2.name2.setText(postdata.get(position).getNickname());
			String date2 = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(postdata.get(position).getCreate_time()*1000));
			viewHolder2.time2.setText(date2);
			viewHolder2.content2.setText(postdata.get(position).getTitle());
			viewHolder2.tv.setText(postdata.get(position).getContent());
			
		}else{
			imageLoader.get(postdata.get(position).getPortrait(), ImageLoader.getImageListener(viewHolder.portrait,
	                R.drawable.default_photo, R.drawable.default_photo));
			viewHolder.name.setText(postdata.get(position).getNickname());
			String date = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(postdata.get(position).getCreate_time()*1000));
			viewHolder.time.setText(date);
			viewHolder.content.setText(postdata.get(position).getTitle());
			viewHolder.tv1.setText(postdata.get(position).getCar_travel_time() + postdata.get(position).getTravel_time());
			viewHolder.tv2.setText(postdata.get(position).getStart_travel_route());
			viewHolder.tv3.setText(postdata.get(position).getEnd_travel_route());
			
		}
		
		return convertView;
	}
   public class ViewHolder1{
	   private CircleImageView portrait;
	   private TextView time;
	   private TextView name;
	   private TextView content;
	   private TextView tv1;
	   private TextView tv2;
	   private TextView tv3;
   }
   private class ViewHolder2{
	   private CircleImageView portrait2;
	   private TextView time2;
	   private TextView name2;
	   private TextView content2;
	   private TextView tv;
   }
}
