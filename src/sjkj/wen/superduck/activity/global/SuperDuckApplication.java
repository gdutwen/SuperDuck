package sjkj.wen.superduck.activity.global;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import android.app.Application;
import sjkj.wen.superduck.activity.util.LruBitmapCache;

public class SuperDuckApplication extends Application {
	private  ImageLoader imageLoader;
	public static RequestQueue volleyQueue;
    @Override
    public void onCreate() {
        super.onCreate();
        volleyQueue = Volley.newRequestQueue(getApplicationContext());
        imageLoader = new ImageLoader(volleyQueue, new LruBitmapCache());
    }
    public  ImageLoader getImageLoader(){
		return imageLoader;
    	
    }
    public static  RequestQueue getRequestQueue() {
        return volleyQueue;
    }
}
