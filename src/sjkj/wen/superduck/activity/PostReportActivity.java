package sjkj.wen.superduck.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import sjkj.wen.superduck.R;

public class PostReportActivity extends Activity implements OnClickListener {

	private RelativeLayout rl_report_item1;
	private RelativeLayout rl_report_item2;
	private RelativeLayout rl_report_item3;
	private RelativeLayout rl_report_item4;
	private RelativeLayout rl_report_item5;
	private RelativeLayout rl_report_item6;

	private ImageView iv_report_item1;
	private ImageView iv_report_item2;
	private ImageView iv_report_item3;
	private ImageView iv_report_item4;
	private ImageView iv_report_item5;
	private ImageView iv_report_item6;

	private EditText et_post_report;
	private Button bt_post_report;

	Boolean iv1 = false;
	Boolean iv2 = false;
	Boolean iv3 = false;
	Boolean iv4 = false;
	Boolean iv5 = false;
	Boolean iv6 = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.post_report);
		initview();

	}

	private void initview() {
		rl_report_item1 = (RelativeLayout) findViewById(R.id.rl_report_item1);
		rl_report_item2 = (RelativeLayout) findViewById(R.id.rl_report_item2);
		rl_report_item3 = (RelativeLayout) findViewById(R.id.rl_report_item3);
		rl_report_item4 = (RelativeLayout) findViewById(R.id.rl_report_item4);
		rl_report_item5 = (RelativeLayout) findViewById(R.id.rl_report_item5);
		rl_report_item6 = (RelativeLayout) findViewById(R.id.rl_report_item6);

		iv_report_item1 = (ImageView) findViewById(R.id.iv_report_item1);
		iv_report_item2 = (ImageView) findViewById(R.id.iv_report_item2);
		iv_report_item3 = (ImageView) findViewById(R.id.iv_report_item3);
		iv_report_item4 = (ImageView) findViewById(R.id.iv_report_item4);
		iv_report_item5 = (ImageView) findViewById(R.id.iv_report_item5);
		iv_report_item6 = (ImageView) findViewById(R.id.iv_report_item6);

		et_post_report = (EditText) findViewById(R.id.et_post_report);
		bt_post_report = (Button) findViewById(R.id.bt_post_report);

		rl_report_item1.setOnClickListener(this);
		rl_report_item2.setOnClickListener(this);
		rl_report_item3.setOnClickListener(this);
		rl_report_item4.setOnClickListener(this);
		rl_report_item5.setOnClickListener(this);
		rl_report_item6.setOnClickListener(this);
		bt_post_report.setOnClickListener(this);

		et_post_report.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				if (!s.toString().equals("")) {

					bt_post_report.setEnabled(true);
				} else {

					bt_post_report.setEnabled(false);
				}

			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rl_report_item1:
			if (!iv1) {
				iv_report_item1.setVisibility(View.VISIBLE);
				Log.e("1", "虚假信息");
				iv1 = true;
			} else {
				iv_report_item1.setVisibility(View.INVISIBLE);
				Log.e("1", "1");
				iv1 = false;
			}
			break;
		case R.id.rl_report_item2:
			if (!iv2) {
				iv_report_item2.setVisibility(View.VISIBLE);
				Log.e("2", "欺诈");
				iv2 = true;
			} else {
				iv_report_item2.setVisibility(View.INVISIBLE);
				Log.e("2", "2");
				iv2 = false;
			}
			break;
		case R.id.rl_report_item3:
			if (!iv3) {
				iv_report_item3.setVisibility(View.VISIBLE);
				Log.e("3", "色情暴力");
				iv3 = true;
			} else {
				iv_report_item3.setVisibility(View.INVISIBLE);
				Log.e("3", "3");
				iv3 = false;
			}
			break;
		case R.id.rl_report_item4:
			if (!iv4) {
				iv_report_item4.setVisibility(View.VISIBLE);
				Log.e("4", "政治谣言");
				iv4 = true;
			} else {
				iv_report_item4.setVisibility(View.INVISIBLE);
				Log.e("4", "");
				iv4 = false;
			}
			break;
		case R.id.rl_report_item5:
			if (!iv5) {
				iv_report_item5.setVisibility(View.VISIBLE);
				Log.e("5", "隐私收集");
				iv5 = true;
			} else {
				iv_report_item5.setVisibility(View.INVISIBLE);
				Log.e("5", "");
				iv5 = false;
			}
			break;
		case R.id.rl_report_item6:
			if (!iv6) {
				iv_report_item6.setVisibility(View.VISIBLE);
				Log.e("6", "恶意营销");
				iv6 = true;
			} else {
				iv_report_item6.setVisibility(View.INVISIBLE);
				Log.e("6", "");
				iv6 = false;
			}
			break;

		default:
			break;
		}

	}

}
