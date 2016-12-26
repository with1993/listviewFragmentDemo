package com.example.listviewfragmentdemo;

import com.example.listviewfragmentdemo.adapter.MyAdapter;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * 
 * @author qdwang
 *
 */
public class MainActivity extends FragmentActivity implements
		OnItemClickListener {

	private String[] strs = { "常用分类", "服饰内衣", "鞋靴", "手机", "家用电器", "数码", "电脑办公",
			"个护化妆", "图书" };
	private ListView listView;
	private MyAdapter adapter;
	private MyFragment myFragment;
	public static int mPosition;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();
	}

	/**
	 * 初始化view
	 */
	private void initView() {
		// TODO Auto-generated method stub
		listView = (ListView) findViewById(R.id.listview);

		adapter = new MyAdapter(this, strs);
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(this);

		//创建MyFragment对象
		myFragment = new MyFragment();
		FragmentTransaction fragmentTransaction = getSupportFragmentManager()
				.beginTransaction();
		fragmentTransaction.replace(R.id.fragment_container, myFragment);
		//通过bundle传值给MyFragment
		Bundle bundle = new Bundle();
		bundle.putString(MyFragment.TAG, strs[mPosition]);
		myFragment.setArguments(bundle);
		fragmentTransaction.commit();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		//拿到当前位置
		mPosition = position;
		//即使刷新adapter
		adapter.notifyDataSetChanged();
		for (int i = 0; i < strs.length; i++) {
			myFragment = new MyFragment();
			FragmentTransaction fragmentTransaction = getSupportFragmentManager()
					.beginTransaction();
			fragmentTransaction.replace(R.id.fragment_container, myFragment);
			Bundle bundle = new Bundle();
			bundle.putString(MyFragment.TAG, strs[position]);
			myFragment.setArguments(bundle);
			fragmentTransaction.commit();
		}
	}
}
