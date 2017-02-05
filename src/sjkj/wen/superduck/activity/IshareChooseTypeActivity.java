package sjkj.wen.superduck.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import sjkj.wen.superduck.R;

public class IshareChooseTypeActivity extends Activity implements OnClickListener{
	
	private Button travel;
	private Button food;
	private Button scene;
	private Button photo;
	private Button other;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ishare_choose_type);
		initview();
	}

	private void initview() {
		travel = (Button) this.findViewById(R.id.ishare_choose_travel);
		food = (Button) this.findViewById(R.id.ishare_choose_food);
		scene = (Button) this.findViewById(R.id.ishare_choose_scene);
		photo = (Button) this.findViewById(R.id.ishare_choose_photo);
		other = (Button) this.findViewById(R.id.ishare_choose_other);
		
		travel.setOnClickListener(this);
		food.setOnClickListener(this);
		scene.setOnClickListener(this);
		photo.setOnClickListener(this);
		other.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ishare_choose_travel:
			Intent intent1 = new Intent(IshareChooseTypeActivity.this, IsharePostMsgActivity.class);
			intent1.putExtra("title", "享出游");
			startActivity(intent1);
			break;
		case R.id.ishare_choose_food:
			Intent intent2 = new Intent(IshareChooseTypeActivity.this, IsharePostMsgActivity.class);
			intent2.putExtra("title", "享美食");
			startActivity(intent2);
			break;
		case R.id.ishare_choose_scene:
			Intent intent3 = new Intent(IshareChooseTypeActivity.this, IsharePostMsgActivity.class);
			intent3.putExtra("title", "爆现场");
			startActivity(intent3);
			break;
		case R.id.ishare_choose_photo:
			Intent intent4 = new Intent(IshareChooseTypeActivity.this, IsharePostMsgActivity.class);
			intent4.putExtra("title", "享美照");
			startActivity(intent4);
			break;
		case R.id.ishare_choose_other:
			Intent intent5 = new Intent(IshareChooseTypeActivity.this, IsharePostMsgActivity.class);
			intent5.putExtra("title", "随心享");
			startActivity(intent5);
			break;
		default:
			break;
		}
		
	}

}
