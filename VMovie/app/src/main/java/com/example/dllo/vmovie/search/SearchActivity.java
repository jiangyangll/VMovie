package com.example.dllo.vmovie.search;

import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dllo.vmovie.R;
import com.example.dllo.vmovie.base.BaseActivity;
import com.example.dllo.vmovie.netutil.NetUtil;
import com.example.dllo.vmovie.okhttptool.NetTool;
import com.example.dllo.vmovie.okhttptool.OnHttpCallBack;

/**
 * Created by dllo on 16/8/30.
 */
public class SearchActivity extends BaseActivity {

    //搜索
    //搜索结果列表view
    private ListView lvResults;

    //搜索结果列表adapter
    private SearchAdapter resultAdapter;

    private EditText etInput;
    private TextView searchTv;

    @Override
    public int setLayout() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        lvResults = (ListView) findViewById(R.id.lv_search_results);
        etInput = (EditText) findViewById(R.id.search_et_input);
        searchTv = (TextView) findViewById(R.id.search_tv_search);
    }

    @Override
    protected void initData() {

        searchTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchContent = etInput.getText().toString().trim();
                getResultData(searchContent);
            }
        });
    }

    private void getResultData(String text) {

        NetTool.getInstance().startRequest(NetUtil.SEARCH + text, SearchBean.class, new OnHttpCallBack<SearchBean>() {
            @Override
            public void onSuccess(final SearchBean response) {
                resultAdapter = new SearchAdapter(getApplicationContext(), response, R.layout.item_search_list);
                lvResults.setAdapter(resultAdapter);

                lvResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent intent = new Intent(SearchActivity.this, SearchDetailActivity.class);
                        intent.putExtra("requestUrl", response.getData().get(position).getRequest_url());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onError(Throwable e) {
            }
        });
    }
}
