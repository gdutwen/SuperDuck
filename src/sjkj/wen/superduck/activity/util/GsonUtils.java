package sjkj.wen.superduck.activity.util;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import sjkj.wen.superduck.activity.bean.ClientMsg;

public class GsonUtils {
	
      public void paraseGsonVerifyCode(String gsondata){
    	  Gson gson = new Gson();
    	  List<ClientMsg> code = gson.fromJson(gsondata, 
    			  new TypeToken<List<ClientMsg>>() {}.getType());
		
    	  
      }
}
