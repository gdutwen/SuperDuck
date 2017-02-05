package sjkj.wen.superduck.activity.adapter;

import java.util.List;

import com.android.volley.toolbox.ImageLoader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import me.nereo.multi_image_selector.adapter.ImageGridAdapter;
import sjkj.wen.superduck.R;
import sjkj.wen.superduck.activity.bean.IsharePostListData;
import sjkj.wen.superduck.activity.global.SuperDuckApplication;
import sjkj.wen.superduck.activity.view.CircleImageView;

public class SmallClassAdapter extends BaseAdapter {
	private Context context;
	private List<IsharePostListData> postdata;
	private ImageLoader imageLoader;
	private LayoutInflater listContainer;
	public SmallClassAdapter(Context context,List<IsharePostListData> postdata) {
		this.context = context;
		this.postdata = postdata;
		imageLoader=((SuperDuckApplication) context.getApplicationContext()).getImageLoader();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return postdata.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return postdata.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewholder = null;
		if (convertView == null) {
			viewholder = new ViewHolder();
			listContainer = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = listContainer.inflate(R.layout.small_class_list_item, null);
			viewholder.iv = (ImageView) convertView.findViewById(R.id.small_class_pic);
			viewholder.title = (TextView) convertView.findViewById(R.id.small_class_title);
			viewholder.activity_time = (TextView) convertView.findViewById(R.id.small_class_time);
			viewholder.address = (TextView) convertView.findViewById(R.id.small_class_address);
			viewholder.portrait = (CircleImageView) convertView.findViewById(R.id.small_class_list_head_photo);
			viewholder.nickname = (TextView) convertView.findViewById(R.id.small_class_nickname);
			viewholder.time = (TextView) convertView.findViewById(R.id.small_class_createtime);
			convertView.setTag(viewholder);
		}else{
			viewholder = (ViewHolder) convertView.getTag();
		}
		imageLoader.get(postdata.get(position).getImages()[0], ImageLoader.getImageListener(viewholder.iv,
                R.drawable.default_photo, R.drawable.default_photo));
		viewholder.title.setText("主题 :" + postdata.get(position).getActivity_project());
		viewholder.activity_time.setText("时间 :" + postdata.get(position).getTravel_time());
		viewholder.address.setText("地点 :" + postdata.get(position).getDestination());
		imageLoader.get(postdata.get(position).getPortrait(), ImageLoader.getImageListener(viewholder.portrait,
				R.drawable.default_photo, R.drawable.default_photo));
		String date = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(postdata.get(position).getCreate_time()*1000));
		viewholder.time.setText(date);
		return convertView;
	}
	public class ViewHolder{
		private ImageView iv;
		private TextView title;
		private TextView activity_time;
		private TextView address;
		private CircleImageView portrait;
		private TextView nickname;
		private TextView time;
	}

}
