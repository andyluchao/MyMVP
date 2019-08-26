package com.yundotech.mymvp.ui.example;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yundotech.mymvp.R;

import java.util.ArrayList;

public class UserListAdapter extends BaseAdapter {
    private ArrayList<UserInfo> mList;
    private Context mContext;
    private IUserMgrPresenter mPresenter;

    UserListAdapter(Context context, IUserMgrPresenter presenter) {
        this.mContext = context;
        this.mPresenter = presenter;
        this.mList = presenter.getUserList();
    }

    void setDataList(ArrayList<UserInfo> list) {
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v;
        if (view != null) {
            v = view;
        } else {
            v = LayoutInflater.from(mContext).inflate(R.layout.user_item, viewGroup, false);
        }
        UserInfo info = mList.get(i);
        TextView tv = v.findViewById(R.id.user_id_value);
        tv.setText(info.getUserId());

        tv = v.findViewById(R.id.user_name_value);
        tv.setText(info.getName());

        tv = v.findViewById(R.id.user_phone_value);
        tv.setText(info.getPhone());

        tv = v.findViewById(R.id.user_department_value);
        tv.setText(mPresenter.getDepartment(info.getDepartId()));
        return v;
    }
}
