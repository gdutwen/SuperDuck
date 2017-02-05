package sjkj.wen.superduck.activity.util;

import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;
import sjkj.wen.superduck.R;

public class CountTimerUtils extends CountDownTimer {
	  private Button mbutton;
	  
	  
	  public CountTimerUtils(Button button, long millisInFuture, long countDownInterval) { 
	    super(millisInFuture, countDownInterval); 
	    this.mbutton = button; 
	  }
	 
	  
	  @Override
	  public void onTick(long millisUntilFinished) { 
		  mbutton.setClickable(false); 
		  mbutton.setText(millisUntilFinished / 1000 + "s"); 
		  mbutton.setBackgroundResource(R.drawable.shape_verify_btn_press);   	    
	  }

	  @Override
	  public void onFinish() { 
		  mbutton.setText("重新获取验证码"); 
		  mbutton.setClickable(true);
		  mbutton.setBackgroundResource(R.drawable.shape_verify_btn_normal);
	  } 
	} 

