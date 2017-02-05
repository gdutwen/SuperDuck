package sjkj.wen.superduck.activity.view;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.Toast;
import sjkj.wen.superduck.R;

public class ClearEditTextUtils extends EditText {
	 private Drawable drawable;
	    private float getX;
	    private int tag=0;
	    Handler handler=new Handler();
	    Runnable runnable=new Runnable() {
	        @Override
	        public void run() {
	            tag++;
	            handler.postDelayed(this, 1000);
	            //如果tag==5则表示用户在5s内没有任何操作,所以隐藏删除按钮
	            if(tag==5){
	                setDrawable(false);
	                handler.removeCallbacks(runnable);
	               
	            }
	        }
	    };


	    public ClearEditTextUtils(Context context) {
	        super(context);
	        init();
	    }

	    public ClearEditTextUtils(Context context, AttributeSet attrs) {
	        super(context, attrs);
	        init();
	    }

	    /**
	     * 实时监听EditText的输入
	     */
	    private void init() {
	        drawable=getResources().getDrawable(R.drawable.icon_clear);
	        addTextChangedListener(new TextWatcher() {
	            @Override
	            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

	            }

	            @Override
	            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
	                setDrawable(true);
	            }

	            @Override
	            public void afterTextChanged(Editable editable) {


	            }
	        });
	    }

	    /**
	     * 设置删除按钮
	     * @param isFocus:是否获取焦点
	     */
	    private void setDrawable(boolean isFocus) {

	        //EditText有有焦点且内容不为空时显示一键删除图片
	        if(getText().length()>0 && isFocus) {
	            drawable.setBounds(0,0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
	            setCompoundDrawables(null, null, drawable, null);
	            startTimer();
	        }else{
	            setCompoundDrawables(null, null, null, null);
	            if(runnable!=null) {
	                handler.removeCallbacks(runnable);
	            }
	        }
	    }

	    /**
	     * 模拟计时器
	     */
	    private void startTimer(){
	        //通过Handle进行计时
	        //每次用户操作前计时都清零
	        tag = 0;
	        //删除runnable对象,使线程停止
	        if(runnable!=null) {
	            handler.removeCallbacks(runnable);
	        }
	        //延迟1s后开始执行
	        handler.postDelayed(runnable, 1000);
	    }

	    /**
	     * 监听删除按钮的点击事件
	     *  getTotalPaddingRight():获取删除图标左边缘到控件右边缘的距离
	     *  getPaddingRight():获取删除图标右边缘到控件右边缘的距离
	     */
	    @Override
	    public boolean onTouchEvent(MotionEvent event) {
	        switch (event.getAction()){
	            case MotionEvent.ACTION_DOWN:
	                getX=event.getX();
	                boolean touchable = getX > (getWidth() - getTotalPaddingRight()) && (getX < ((getWidth() - getPaddingRight())));
	                if(touchable){
	                    setText("");
	                }
	                //当前EditText一键删除图标消失后,再次点击判断是否显示一键删除按钮
	                if(isFocusable()){
	                    setDrawable(isFocusable());
	                }
	                break;
	        }
	        return super.onTouchEvent(event);
	    }

	    /**
	     * 监听焦点 是否获取
	     */
	    @Override
	    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
	        super.onFocusChanged(focused, direction, previouslyFocusedRect);
	        setDrawable(focused);
	    }
}
