package sjkj.wen.superduck.activity;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import sjkj.wen.superduck.R;
import sjkj.wen.superduck.activity.fragment.ComunityFragment;
import sjkj.wen.superduck.activity.fragment.HUILifeFragment;
import sjkj.wen.superduck.activity.fragment.HomeFragment;
import sjkj.wen.superduck.activity.fragment.NBCircleFragment;

public class MainFragmentActivity extends FragmentActivity implements OnClickListener {
	private TextView tv_mainfragment_title;
	// �ĸ��ײ��˵�linearlayout
	private LinearLayout ll_home;
	private LinearLayout ll_community_estate;
	private LinearLayout ll_huilife;
	private LinearLayout ll_nbcircle;
	// �ĸ��ײ��˵�imageview
	private ImageView iv_home;
	private ImageView iv_community_estate;
	private ImageView iv_huilife;
	private ImageView iv_nbcircle;
	// �ĸ��ײ��˵�textview
	private TextView tv_home;
	private TextView tv_community_estate;
	private TextView tv_huilife;
	private TextView tv_nbcircle;
	// �ĸ�Fragment
	private HomeFragment homeFragment;
	private ComunityFragment comunityFragment;
	private HUILifeFragment huilifeFragment;
	private NBCircleFragment nbcircleFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		initView();

		initEvent();

		initFragment(1);
		// Intent intent = getIntent();
		// final String token=intent.getStringExtra("token");
		Log.e("token", "96a621b95d751cdddbe2b01948528fa4");
	}

	private void initFragment(int i) {
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		hideFragment(transaction);
		switch (i) {
		case 0:
			if (homeFragment == null) {
				tv_mainfragment_title.setText("首页");
				homeFragment = new HomeFragment();
				transaction.add(R.id.fl_content, homeFragment);
			} else {
				tv_mainfragment_title.setText("首页");
				transaction.show(homeFragment);
			}
			break;
		case 1:
			if (nbcircleFragment == null) {
				tv_mainfragment_title.setText("邻里圈");
				nbcircleFragment = new NBCircleFragment();
				transaction.add(R.id.fl_content, nbcircleFragment);
			} else {
				tv_mainfragment_title.setText("邻里圈");
				transaction.show(nbcircleFragment);
			}
			break;
		case 2:
			if (huilifeFragment == null) {
				tv_mainfragment_title.setText("惠生活");
				huilifeFragment = new HUILifeFragment();
				transaction.add(R.id.fl_content, huilifeFragment);
			} else {
				tv_mainfragment_title.setText("惠生活");
				transaction.show(huilifeFragment);
			}
			break;
		case 3:
			if (comunityFragment == null) {
				tv_mainfragment_title.setText("小区物业");
				comunityFragment = new ComunityFragment();
				transaction.add(R.id.fl_content, comunityFragment);
			} else {
				tv_mainfragment_title.setText("小区物业");
				transaction.show(comunityFragment);
			}
			break;
		default:
			break;
		}
		transaction.commit();
	}

	private void hideFragment(FragmentTransaction transaction) {
		if (homeFragment != null) {
			transaction.hide(homeFragment);
		}
		if (nbcircleFragment != null) {
			transaction.hide(nbcircleFragment);
		}
		if (huilifeFragment != null) {
			transaction.hide(huilifeFragment);
		}
		if (comunityFragment != null) {
			transaction.hide(comunityFragment);
		}

	}

	private void initEvent() {
		ll_home.setOnClickListener(this);
		ll_community_estate.setOnClickListener(this);
		ll_huilife.setOnClickListener(this);
		ll_nbcircle.setOnClickListener(this);
	}

	private void initView() {
		tv_mainfragment_title = (TextView) findViewById(R.id.tv_mainfragment_title);
		ll_home = (LinearLayout) findViewById(R.id.ll_home);
		ll_community_estate = (LinearLayout) findViewById(R.id.ll_community_estate);
		ll_huilife = (LinearLayout) findViewById(R.id.ll_huilife);
		ll_nbcircle = (LinearLayout) findViewById(R.id.ll_nbcircle);

		iv_home = (ImageView) findViewById(R.id.iv_home);
		iv_community_estate = (ImageView) findViewById(R.id.iv_community_estate);
		iv_huilife = (ImageView) findViewById(R.id.iv_huilife);
		iv_nbcircle = (ImageView) findViewById(R.id.iv_nbcircle);

		tv_home = (TextView) findViewById(R.id.tv_home);
		tv_community_estate = (TextView) findViewById(R.id.tv_community_estate);
		tv_huilife = (TextView) findViewById(R.id.tv_huilife);
		tv_nbcircle = (TextView) findViewById(R.id.tv_nbcircle);

	}

	@Override
	public void onClick(View v) {
		restartBotton();

		switch (v.getId()) {
		case R.id.ll_home:
			iv_home.setBackgroundResource(R.drawable.icon_home_selected);
			initFragment(0);
			break;
		case R.id.ll_nbcircle:
			iv_nbcircle.setBackgroundResource(R.drawable.icon_nbcircle_selected);
			initFragment(1);
			break;
		case R.id.ll_huilife:
			iv_huilife.setBackgroundResource(R.drawable.icon_huilife_selected);

			initFragment(2);
			break;
		case R.id.ll_community_estate:
			iv_community_estate.setBackgroundResource(R.drawable.icon_community_estate_selected);

			initFragment(3);
			break;
		default:
			break;
		}
	}

	private void restartBotton() {
		iv_home.setBackgroundResource(R.drawable.icon_home);
		iv_nbcircle.setBackgroundResource(R.drawable.icon_nbcircle);
		iv_huilife.setBackgroundResource(R.drawable.icon_huilife);
		iv_community_estate.setBackgroundResource(R.drawable.icon_community_estate);

	}
}
