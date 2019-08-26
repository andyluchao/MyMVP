package com.yundotech.mymvp.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.yundotech.mymvp.contract.IBaseContract;

abstract public class BaseFragmentView<IPresenter extends IBaseContract.IBasePresenter, UiType, WarningType>
        extends Fragment implements IBaseContract.IBaseView<UiType, WarningType> {

    protected IPresenter mPresenter;

    public BaseFragmentView(IPresenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mPresenter.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mPresenter.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        this.mPresenter.onStart(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        this.mPresenter.onResume();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        this.mPresenter.onSaveState(outState);
    }

    @Override
    public void onPause() {
        this.mPresenter.onPause();
        super.onPause();
    }

    @Override
    public void onStop() {
        this.mPresenter.onStop();
        super.onStop();
    }

    @Override
    public void onDestroy() {
        this.mPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        this.mPresenter.onDetach();
        super.onDetach();
    }
}
