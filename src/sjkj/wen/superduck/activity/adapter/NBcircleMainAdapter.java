package sjkj.wen.superduck.activity.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import sjkj.wen.superduck.R;
import sjkj.wen.superduck.activity.bean.NBCategoryIndex;
import sjkj.wen.superduck.activity.bean.NBCategoryIndexData;

public class NBcircleMainAdapter extends BaseAdapter {
	
	 private Context context;
	    private ArrayList<Map<String, Object>> mInfos;
	    private LayoutInflater layoutInflater;
	    public NBcircleMainAdapter(Context context,ArrayList<Map<String, Object>> mInfos){
	        this.context=context;
	        this.mInfos=mInfos;
	        System.out.println(mInfos);
	    }

	@Override
	public int getCount() {
		
		return mInfos.size();
	}

	@Override
	public Object getItem(int position) {
		
		return mInfos.get(position);
	}

	@Override
	public long getItemId(int position) {
		
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHodler viewhodler = null;
	   System.out.println(position);
		if (convertView == null) {
			viewhodler = new ViewHodler();
			layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = layoutInflater.inflate(R.layout.fragment_nbcircle_list_item, null);
			
			viewhodler.title = (TextView) convertView.findViewById(R.id.tv_nbcircle_main_title);
			viewhodler.content = (TextView) convertView.findViewById(R.id.tv_nbcircle_main_content);
			viewhodler.image = (ImageView) convertView.findViewById(R.id.iv_nbcircle_main_pic);
			convertView.setTag(viewhodler);
		   	
		}else {
			viewhodler = (ViewHodler) convertView.getTag();
		}
		viewhodler.title.setText((String) mInfos.get(    
                position).get("name"));
		viewhodler.content.setText((String) mInfos.get(    
                position).get("title"));
		viewhodler.image.setImageResource((Integer) mInfos.get(    
                position).get("pic"));
		return convertView;
	}
	
	public class ViewHodler{
		private TextView title;
		private TextView content;
		private ImageView image;
	}

}
