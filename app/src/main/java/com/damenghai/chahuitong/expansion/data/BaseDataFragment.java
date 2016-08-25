package com.damenghai.chahuitong.expansion.data;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.bijection.BeamFragment;
import com.damenghai.chahuitong.bijection.Presenter;

/**
 *
 */
public class BaseDataFragment<PresenterType extends BaseDataFragmentPresenter, M> extends BeamFragment<PresenterType> {

    public void setData(M m) {}

    public void onError(Throwable throwable) {}

}
