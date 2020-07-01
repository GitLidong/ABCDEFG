package com.lidong.abcdefg.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.lidong.abcdefg.R;
import com.lidong.abcdefg.recyclerview.listener.PowerGroupListener;


public class BehaviorRecordActivity extends AppCompatActivity {

    private PineRecyclerView mRecyclerView;
    private BehaviorAdapter mAdapter;
    private BaseDecoration mDecoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behavior_record);

        mRecyclerView = findViewById(R.id.behavior_recycler_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new BehaviorAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        mDecoration = PowerfulStickyDecoration.Builder
                .init(new PowerGroupListener() {
                    @Override
                    public View getGroupView(int position) {
                        return null;
                    }

                    @Override
                    public String getGroupName(int position) {
                        if (position == 0) {
                            return "组1";
                        } else if (position == 10) {
                            return "组2";
                        }
                        return null;
                    }
                })
                .build();
        mRecyclerView.addItemDecoration(mDecoration);

    }
}
