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
	            //���tag==5���ʾ�û���5s��û���κβ���,��������ɾ����ť
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
	     * ʵʱ����EditText������
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
	     * ����ɾ����ť
	     * @param isFocus:�Ƿ��ȡ����
	     */
	    private void setDrawable(boolean isFocus) {

	        //EditText���н��������ݲ�Ϊ��ʱ��ʾһ��ɾ��ͼƬ
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
	     * ģ���ʱ��
	     */
	    private void startTimer(){
	        //ͨ��Handle���м�ʱ
	        //ÿ���û�����ǰ��ʱ������
	        tag = 0;
	        //ɾ��runnable����,ʹ�߳�ֹͣ
	        if(runnable!=null) {
	            handler.removeCallbacks(runnable);
	        }
	        //�ӳ�1s��ʼִ��
	        handler.postDelayed(runnable, 1000);
	    }

	    /**
	     * ����ɾ����ť�ĵ���¼�
	     *  getTotalPaddingRight():��ȡɾ��ͼ�����Ե���ؼ��ұ�Ե�ľ���
	     *  getPaddingRight():��ȡɾ��ͼ���ұ�Ե���ؼ��ұ�Ե�ľ���
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
	                //��ǰEditTextһ��ɾ��ͼ����ʧ��,�ٴε���ж��Ƿ���ʾһ��ɾ����ť
	                if(isFocusable()){
	                    setDrawable(isFocusable());
	                }
	                break;
	        }
	        return super.onTouchEvent(event);
	    }

	    /**
	     * �������� �Ƿ��ȡ
	     */
	    @Override
	    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
	        super.onFocusChanged(focused, direction, previouslyFocusedRect);
	        setDrawable(focused);
	    }
}
