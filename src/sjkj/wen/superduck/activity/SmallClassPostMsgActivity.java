package sjkj.wen.superduck.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import sjkj.wen.superduck.R;

public class SmallClassPostMsgActivity extends Activity implements OnClickListener{
	private String ID;
	private ImageButton ib_smallclass_post_message_back;
	private TextView bt_post_msg_know;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.small_class_post_msg);
		Intent intent = getIntent();
		ID = intent.getStringExtra("id");
		Log.e("id", ID);
		initview();
	
	}

	private void initview() {
		bt_post_msg_know = (TextView) findViewById(R.id.bt_post_msg_know);
		ib_smallclass_post_message_back = (ImageButton) findViewById(R.id.ib_smallclass_post_message_back);
		ib_smallclass_post_message_back.setOnClickListener(this);
		bt_post_msg_know.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ib_smallclass_post_message_back:
			finish();
			break;
		case R.id.bt_post_msg_know:
			Intent intent = new Intent(SmallClassPostMsgActivity.this, PostknowActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
		
	}
}
