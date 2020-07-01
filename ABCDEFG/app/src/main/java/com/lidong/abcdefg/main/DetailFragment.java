package com.lidong.abcdefg.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lidong.abcdefg.R;

import java.util.ArrayList;
import java.util.List;

public class DetailFragment extends Fragment {

    private RecyclerView mDetailFunctionList;
    private static List<Function> mFunctions;

    static {
        mFunctions = new ArrayList<Function>();
        mFunctions.add(new Function("属性动画", "abcdefg.demo.valueanimator"));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_layout, container, false);
        mDetailFunctionList = view.findViewById(R.id.detail_function_list);
        return mDetailFunctionList;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FunctionAdapter functionAdapter = new FunctionAdapter(getActivity());
        mDetailFunctionList.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mDetailFunctionList.setAdapter(functionAdapter);
    }

    private static class FunctionAdapter extends RecyclerView.Adapter<FunctionAdapter.ViewHolder> {

        private Context mContext;

        public FunctionAdapter(Context context) {
            this.mContext = context;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_base_layout, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
            holder.mBtn.setText(mFunctions.get(position).name);
            holder.mBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mFunctions.get(position).action);
                    mContext.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mFunctions.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {

            Button mBtn;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                mBtn = itemView.findViewById(R.id.item_base_btn);
            }
        }

    }

    private static class Function {
        String name;
        String action;

        public Function(String name, String action) {
            this.name = name;
            this.action = action;
        }
    }
}
