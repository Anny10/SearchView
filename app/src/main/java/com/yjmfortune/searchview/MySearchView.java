package com.yjmfortune.searchview;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by lixian on 2016/2/29.
 */
public class MySearchView extends LinearLayout {

    Context ct;

    private EditText search_et_input;
    private ImageView search_iv_delete;
    private Button search_btn_back;
    private ListView search_lv_tips;

    public MySearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ct = context;
        LayoutInflater.from(ct).inflate(R.layout.search_layout, this);

        search_et_input = (EditText) findViewById(R.id.search_et_input);
        search_iv_delete = (ImageView) findViewById(R.id.search_iv_delete);
        search_btn_back = (Button) findViewById(R.id.search_btn_back);
        search_lv_tips = (ListView) findViewById(R.id.search_lv_tips);
        search_lv_tips.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String SelectItem = search_lv_tips.getAdapter().getItem(position).toString();
                search_et_input.setText(SelectItem);
                HintLis.setSelectText(SelectItem);
                search_lv_tips.setVisibility(View.GONE);
                //隐藏软键盘
                InputMethodManager imm = (InputMethodManager) ct.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });
        search_et_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s != null && s.toString().isEmpty()) {
                    search_lv_tips.setVisibility(View.GONE);
                } else {
                    HintLis.UpdateHintAdapter(s.toString());
                    search_lv_tips.setVisibility(View.VISIBLE);
                }
                Log.e("lx,on", s + "");
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        search_et_input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    //隐藏软键盘
                    InputMethodManager imm = (InputMethodManager) ct.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                }
                return true;
            }
        });


    }


    public MySearchView(Context context) {
        super(context);
    }


    ArrayAdapter<String> hintAdapter;

    public void setHintAdapter(ArrayAdapter<String> hintAdapter, SearchHintListener listener) {
        this.hintAdapter = hintAdapter;
        HintLis = listener;
        if (search_lv_tips.getAdapter() == null) {
            search_lv_tips.setAdapter(hintAdapter);
        }
    }

    public SearchHintListener HintLis;

    public interface SearchHintListener {

        void UpdateHintAdapter(String s);

        void setSelectText(String selectItem);
    }


}
