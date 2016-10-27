package com.dinar.mycontactfragment.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dinar.mycontactfragment.R;
import com.dinar.mycontactfragment.RecyclerView.ContactsProvider;

/**
 * Created by Dr on 15.10.2016.
 */

public class ContactsFragment extends Fragment {
    public static final String TAG = "fragment";
    public static final String NAME_KEY = "name";
    public static final String NUMBER_KEY = "number";
    public static final String PHOTO_KEY = "photo";

    TextView contactName;
    ImageView contactPhoto;
    EditText contactPhotoInput;
    Button contactPhotoInputConfirm;
    TextView contactNumber;

    public ContactsFragment newInstance(String contactName, String contactPhotoUrl) {
        Bundle bundle = new Bundle();
        bundle.putString(NAME_KEY, contactName);
        bundle.putString(PHOTO_KEY, contactPhotoUrl);
        ContactsFragment fragment = new ContactsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.contact_item, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        contactName = (TextView) view.findViewById(R.id.name);
        //contactPhoto = (ImageView) view.findViewById(R.id.photo);
        contactPhotoInput = (EditText) view.findViewById(R.id.photo_input);
        contactPhotoInputConfirm = (Button) view.findViewById(R.id.photo_input_confirm);
        contactNumber = (TextView) view.findViewById(R.id.number);

        if (getArguments() != null) {
            String name = getArguments().getString(NAME_KEY, "");
            String number = getArguments().getString(NUMBER_KEY, "");
            contactName.setText(name);
            contactNumber.setText(number);
            String photoUrl = getArguments().getString(PHOTO_KEY, "");
            if (photoUrl.length() > 0) {
                Glide.with(getActivity())
                        .load(photoUrl)
                        .fitCenter()
                        .into(contactPhoto);
            }
        }
        contactPhotoInputConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactsProvider.getInstance().savePhoto(getActivity(),
                        contactName.getText().toString(), contactPhotoInput.getText().toString());
                Glide.with(getActivity())
                        .load(contactPhotoInput.getText().toString())
                        .fitCenter()
                        .into(contactPhoto);
                contactPhotoInput.setText("");
            }
        });
    }

}
