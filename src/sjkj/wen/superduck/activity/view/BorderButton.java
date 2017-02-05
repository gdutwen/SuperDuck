package sjkj.wen.superduck.activity.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.Button;
import sjkj.wen.superduck.R;

@SuppressLint("WrongCall")
public class BorderButton extends Button {

	/**
	 * �����Ƿ���б߿�true:���ܴ��б߿򡿡�false:���ܲ����߿�
	 */
	boolean borders = false;
	/**
	 * ����Ƿ���б߿�true:�����б߿򡿡�false:��಻���߿�
	 */
	boolean borderLeft = false;
	/**
	 * �����Ƿ���б߿�true:�������б߿򡿡�false:�ײ������߿�
	 */
	boolean borderTop = false;
	/**
	 * �Ҳ��Ƿ���б߿�true:�Ҳ���б߿򡿡�false:�Ҳ಻���߿�
	 */
	boolean borderRight = false;
	/**
	 * �ײ��Ƿ���б߿�true:�ײ����б߿򡿡�false:�ײ������߿�
	 */
	boolean borderBottom = false;
	/**
	 * �߿���ɫ
	 */
	String textColor = "#ff000000";

	public BorderButton(Context context) {
		this(context, null);
	}

	public BorderButton(Context context, AttributeSet attrs) {
		this(context, attrs, android.R.attr.textViewStyle);
	}

	public BorderButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// ��ȡ�Զ������Լ�
		TypedArray typedArray = context.obtainStyledAttributes(attrs,
				R.styleable.BorderButton);
		// �Ƿ�����ȫ���߿�Ĭ��Ϊfalse
		borders = typedArray.getBoolean(
				R.styleable.BorderButton_layout_borders, false);
		// �Ƿ��������߿�Ĭ��Ϊfalse
		borderLeft = typedArray.getBoolean(
				R.styleable.BorderButton_layout_borderLeft, false);
		// �Ƿ����ö����߿�Ĭ��Ϊfalse
		borderTop = typedArray.getBoolean(
				R.styleable.BorderButton_layout_borderTop, false);
		// �Ƿ������Ҳ�߿�Ĭ��Ϊfalse
		borderRight = typedArray.getBoolean(
				R.styleable.BorderButton_layout_borderRight, false);
		// �Ƿ����õײ��߿�Ĭ��Ϊfalse
		borderBottom = typedArray.getBoolean(
				R.styleable.BorderButton_layout_borderBottom, false);
		// ��ȡ�ı���ɫֵ���������߿�ģ����ں��ı���ɫƥ��
		textColor = attrs.getAttributeValue(
				"http://schemas.android.com/apk/res/android", "textColor");
		typedArray.recycle();
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);

		super.onDraw(canvas);

		super.draw(canvas);
		// ��������
		Paint paint = new Paint();
		// ��ȡ�û�����ɫ
		int color = paint.getColor();
		// ���û�����ɫ
		paint.setColor(Color.parseColor(textColor));
		// ���bordersΪtrue����ʾ�������¶��б߿�
		if (borders) {
			canvas.drawLine(0, 0, 0, this.getHeight() - 1, paint);
			canvas.drawLine(0, 0, this.getWidth() - 1, 0, paint);
			canvas.drawLine(this.getWidth() - 1, 0, this.getWidth() - 1,
					this.getHeight() - 1, paint);
			canvas.drawLine(0, this.getHeight() - 1, this.getWidth() - 1,
					this.getHeight() - 1, paint);
		} else {
			if (borderLeft) {
				// ����߿���
				canvas.drawLine(0, 0, 0, this.getHeight() - 1, paint);
			}
			if (borderTop) {
				// �������߿���
				canvas.drawLine(0, 0, this.getWidth() - 1, 0, paint);
			}
			if (borderRight) {
				// ���Ҳ�߿���
				canvas.drawLine(this.getWidth() - 1, 0, this.getWidth() - 1,
						this.getHeight() - 1, paint);
			}
			if (borderBottom) {
				// ���ײ��߿���
				canvas.drawLine(0, this.getHeight() - 1, this.getWidth() - 1,
						this.getHeight() - 1, paint);
			}
		}
		// ���û�����ɫ��λ
		paint.setColor(color);
	}

}
