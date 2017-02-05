package sjkj.wen.superduck.activity.adapter;

import com.android.volley.toolbox.ImageLoader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import sjkj.wen.superduck.R;
import sjkj.wen.superduck.activity.global.SuperDuckApplication;

public class GirdViewAdapter extends BaseAdapter {
	class ViewHodler{
		ImageView iv;
	}
	private LayoutInflater inflater;
	private Context context;
	private String[] images;
	ImageLoader imageLoader;
	public GirdViewAdapter(Context context,String[] images){
		this.context = context;
		this.images = images;
		imageLoader = ((SuperDuckApplication) context.getApplicationContext()).getImageLoader();
	}

	@Override
	public int getCount() {
		
		return images.length;
	}

	@Override
	public Object getItem(int position) {
		
		return images[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHodler hodler = null;
		if (convertView == null) {
			hodler = new ViewHodler();
			inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.post_detail_showphoto, null);
			hodler.iv = (ImageView) convertView.findViewById(R.id.ig_detail_showphoto);
			convertView.setTag(hodler);
		}else{
			hodler = (ViewHodler) convertView.getTag();
		}
		imageLoader.get(images[position], ImageLoader.getImageListener(hodler.iv,
                R.drawable.default_photo, R.drawable.default_photo));	
		return convertView;
	}
	

}
