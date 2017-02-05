package sjkj.wen.superduck.activity.adapter;

import java.util.List;

import org.json.JSONArray;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;
import sjkj.wen.superduck.R;
import sjkj.wen.superduck.activity.util.ImageLoaderUtil;

public class AdvertisementAdapter extends PagerAdapter {

	private Context context;
	   private List<View> views;
	   JSONArray advertiseArray;
	   
	   public AdvertisementAdapter() {
	      super();
	      // TODO Auto-generated constructor stub
	   }

	   public AdvertisementAdapter(Context context, List<View> views, JSONArray advertiseArray) {
	      this.context = context;
	      this.views = views;
	      this.advertiseArray = advertiseArray;
	   }

	   @Override
	   public int getCount() {
	      // TODO Auto-generated method stub
	      return views.size();
	   }

	   @Override
	   public boolean isViewFromObject(View arg0, Object arg1) {
	      return (arg0 == arg1);
	   }
	   
	   @Override
	   public void destroyItem(View container, int position, Object object) {
	      ((ViewPager) container).removeView(views.get(position));
	   }

	   @Override
	   public Object instantiateItem(View container, int position) {
	      ((ViewPager) container).addView(views.get(position), 0);
	      final int POSITION = position + 1;
	      View view = views.get(position);
	      try {
	         String head_img = advertiseArray.optJSONObject(position).optString("head_img");
	         ImageView ivAdvertise = (ImageView) view.findViewById(R.id.ivAdvertise);
	         ImageLoaderUtil.getImage(context, ivAdvertise, head_img, R.drawable.default_photo, R.drawable.default_photo, 0, 0);
	         ivAdvertise.setOnClickListener(new OnClickListener() {
	            @Override
	            public void onClick(View v) {
	               Toast.makeText(context, "第"+POSITION+"个广告图片被点击", Toast.LENGTH_LONG).show();
	            }
	         });
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      return view;
	   }
	   

}
