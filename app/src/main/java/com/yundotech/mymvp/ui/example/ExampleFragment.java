package com.yundotech.mymvp.ui.example;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.yundotech.mymvp.R;
import com.yundotech.mymvp.data.ComUIType;
import com.yundotech.mymvp.data.ComWarningType;
import com.yundotech.mymvp.view.BaseFragmentView;

public class ExampleFragment extends BaseFragmentView<IUserMgrPresenter, ComUIType, ComWarningType> implements IUserMgrView<ComUIType, ComWarningType>{
    private UserListAdapter mListAdapter;
    private TextView mTotalNumberView;

    public ExampleFragment() {
        super(new UserMgrPresenter());
    }

    public static ExampleFragment newInstance() {
        return new ExampleFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListAdapter = new UserListAdapter(context, mPresenter);
    }

    @Override
    public void onDetach() {
        mListAdapter = null;
        super.onDetach();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.example_fragment, container, false);
        mTotalNumberView = v.findViewById(R.id.total_number_val);
        mTotalNumberView.setText(String.valueOf(mListAdapter.getCount()));

        ListView lv = v.findViewById(R.id.user_list);
        lv.setAdapter(mListAdapter);
        return v;
    }

    @Override
    public Object getUIContent(ComUIType uiType) {
        return null;
    }

    @Override
    public void notifyUpdateUI(ComUIType uiType) {
        if (uiType == ComUIType.UNIQUE_LIST) {
            if (mListAdapter != null) {
                mListAdapter.setDataList(mPresenter.getUserList());
                mTotalNumberView.setText(String.valueOf(mListAdapter.getCount()));
                mListAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void notifyWarning(final ComWarningType warnType) {
        Toast.makeText(getContext(), warnType.toString(), Toast.LENGTH_SHORT).show();
    }
}
