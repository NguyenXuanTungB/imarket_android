package com.example.framgia.imarketandroid.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.framgia.imarketandroid.R;
import com.example.framgia.imarketandroid.data.FakeContainer;
import com.example.framgia.imarketandroid.data.model.Floor;
import com.example.framgia.imarketandroid.data.model.Point;
import com.example.framgia.imarketandroid.data.model.SavedPointItem;
import com.example.framgia.imarketandroid.ui.adapter.CartProductAdapter;
import com.example.framgia.imarketandroid.ui.adapter.SavePointAdapter;
import com.example.framgia.imarketandroid.ui.widget.LinearItemDecoration;
import com.example.framgia.imarketandroid.util.Constants;
import com.example.framgia.imarketandroid.util.SystemUtil;

import java.util.ArrayList;
import java.util.List;

public class SavePointActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEdtSavePoint;
    private ImageButton mImageButtonSavePoint;
    private RecyclerView mRvPointSaved;
    private RecyclerView.Adapter mPointSavedAdapter;
    private RecyclerView.LayoutManager mPointSavedLayoutManager;
    public static ArrayList<SavedPointItem> mListPoint = new ArrayList<>();
    private Button mDoneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_point);
        mEdtSavePoint = (EditText) findViewById(R.id.edt_save_point);
        mImageButtonSavePoint = (ImageButton) findViewById(R.id.img_save_point);
        mImageButtonSavePoint.setOnClickListener(this);
        mDoneButton = (Button) findViewById(R.id.btn_done_save_point);
        mDoneButton.setOnClickListener(this);
        mRvPointSaved = (RecyclerView) findViewById(R.id.rv_list_point_saved);
        mRvPointSaved.addItemDecoration(new LinearItemDecoration(this));
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRvPointSaved.setHasFixedSize(true);
        // use a linear layout manager
        mPointSavedLayoutManager = new LinearLayoutManager(this);
        mRvPointSaved.setLayoutManager(mPointSavedLayoutManager);
        mPointSavedAdapter = new SavePointAdapter(this, mListPoint);
        mRvPointSaved.setAdapter(mPointSavedAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_save_point:
                String tempNote = mEdtSavePoint.getText().toString();
                Point point = FloorActivity.sCurrentLocation;
                int i = 0;
                if (point != null) {
                    if (mListPoint.size() == 0) {
                        SavedPointItem item = new SavedPointItem(point.getId(), Constants
                            .LIST_AVATAR_STORE[point
                            .getType()], Constants.LIST_NAME_STORE[point.getType()], tempNote,
                            false, R
                            .drawable.delete);
                        mListPoint.add(item);
                        mPointSavedAdapter.notifyDataSetChanged();
                    } else {
                        for (i = 0; i < mListPoint.size(); i++) {
                            if (point.getId() == mListPoint.get(i).getmId())
                                break;
                        }
                        if (i == mListPoint.size()) {
                            SavedPointItem item = new SavedPointItem(point.getId(), Constants
                                .LIST_AVATAR_STORE[point
                                .getType()], Constants.LIST_NAME_STORE[point.getType()], tempNote,
                                false, R
                                .drawable.delete);
                            mListPoint.add(item);
                            mPointSavedAdapter.notifyDataSetChanged();
                        } else
                            Toast.makeText(SavePointActivity.this, "Địa điểm này đã được lưu", Toast
                                .LENGTH_LONG).show();
                    }
                }
                mEdtSavePoint.setText("");
                break;
            case R.id.btn_done_save_point:
                finish();
        }
    }
}
