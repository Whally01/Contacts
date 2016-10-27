package com.dinar.mycontactfragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dinar.mycontactfragment.Fragments.ContactsFragment;
import com.dinar.mycontactfragment.Model.Contact;
import com.dinar.mycontactfragment.RecyclerView.ContactsAdapter;
import com.dinar.mycontactfragment.RecyclerView.ContactsProvider;

import java.util.ArrayList;

public class ContactsActivity extends AppCompatActivity implements ContactsAdapter.OnItemClickListener {
    RecyclerView mContactsList;
    ContactsAdapter mContactsAdapter;
    boolean isBigMode;
    private ArrayList<Contact> list;
   // ArrayList<Contact> favouriteList;
   // FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_list);
        mContactsList = (RecyclerView) findViewById(R.id.rv);
        mContactsList.setLayoutManager(new LinearLayoutManager(this));
        mContactsAdapter = new ContactsAdapter();
        mContactsList.setAdapter(mContactsAdapter);

        mContactsAdapter.setContacts(ContactsActivity.this, list, false);
        mContactsAdapter.setContactItemClickListener(this);

        isBigMode = isBigMode();

    }

    private boolean isBigMode() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            return true;
        else
            return false;
    }

    @Override
    public void onSelectItemClick(String contact) {
        if (isBigMode) {
            Fragment fragment = new ContactsFragment();
            Bundle bundle = new Bundle();
            bundle.putString(ContactsFragment.NAME_KEY, contact);
            bundle.putString(ContactsFragment.PHOTO_KEY, ContactsProvider.getInstance().getContactPhoto(contact));
            fragment.setArguments(bundle);
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment, ContactsFragment.class.getName()).commit();
        }
    }

    @Override
    public void onDeleteItemClick(String contact) {
       //
    }
}






