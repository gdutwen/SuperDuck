package sjkj.wen.superduck.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import sjkj.wen.superduck.R;

public class PLiveChooseTypeActivity extends Activity implements OnClickListener{
	private Button car;
	private Button travel;
	private Button food;
	private Button sports;
	private Button other;
	private String ID;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.plive_choose_type);
		initview();
		Intent intent = getIntent();
		ID = intent.getStringExtra("id");
	}

	private void initview() {
		car = (Button) this.findViewById(R.id.plive_choose_car);
		travel = (Button) this.findViewById(R.id.plive_choose_travel);
		food = (Button) this.findViewById(R.id.plive_choose_food);
		sports = (Button) this.findViewById(R.id.plive_choose_sports);
		other = (Button) this.findViewById(R.id.plive_choose_other);
		
		car.setOnClickListener(this);
		food.setOnClickListener(this);
		travel.setOnClickListener(this);
		sports.setOnClickListener(this);
		other.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.plive_choose_car:
			Intent intent1 = new Intent(PLiveChooseTypeActivity.this, IsharePostMsgActivity.class);
			intent1.putExtra("id", "40");
			intent1.putExtra("ID", ID);
			intent1.putExtra("title", "拼车");
			startActivity(intent1);
			break;
		case R.id.plive_choose_travel:
			Intent intent2 = new Intent(PLiveChooseTypeActivity.this, IsharePostMsgActivity.class);
			intent2.putExtra("title", "拼出游");
			intent2.putExtra("id", "41");
			intent2.putExtra("ID", ID);
			startActivity(intent2);
			break;
		case R.id.plive_choose_food:
			Intent intent3 = new Intent(PLiveChooseTypeActivity.this, IsharePostMsgActivity.class);
			intent3.putExtra("title", "拼美食");
			intent3.putExtra("id", "42");
			intent3.putExtra("ID", ID);
			startActivity(intent3);
			break;
		case R.id.plive_choose_sports:
			Intent intent4 = new Intent(PLiveChooseTypeActivity.this, IsharePostMsgActivity.class);
			intent4.putExtra("title", "拼运动");
			intent4.putExtra("id", "43");
			intent4.putExtra("ID", ID);
			startActivity(intent4);
			break;
		case R.id.plive_choose_other:
			Intent intent5 = new Intent(PLiveChooseTypeActivity.this, IsharePostMsgActivity.class);
			intent5.putExtra("title", "拼一拼(其他)");
			intent5.putExtra("id", "44");
			intent5.putExtra("ID", ID);
			startActivity(intent5);
			break;
		default:
			break;
		}
		
	}
}
