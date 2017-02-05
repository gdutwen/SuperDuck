package sjkj.wen.superduck.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import sjkj.wen.superduck.R;

public class PostknowActivity extends Activity {
	private ImageButton bt_post_know_cancel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.post_messge_know);
		bt_post_know_cancel = (ImageButton) findViewById(R.id.bt_post_know_cancel);
		bt_post_know_cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
	}
}
