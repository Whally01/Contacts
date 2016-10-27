package com.dinar.mycontactfragment.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Dr on 26-Oct-16.
 */

public class FavouriteFragment extends Fragment {
    public static final String NAME_KEY = "name";
    public static final String NUMBER_KEY = "number";
    public static final String PHOTO_KEY = "photo";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
