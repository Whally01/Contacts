package com.dinar.mycontactfragment.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.dinar.mycontactfragment.Model.Contact;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

/**
 * Created by Dr on 15.10.2016.
 */

public class ContactsProvider {
    public static final String CONTACT_PREFERENCES = "contacts_preferences_fragmania";

    private final String[] contactsNames = new String[]{"Dinar", "Dinar", "Dinar",  "Dinar",  "Dinar",  "Dinar"  };

    @NonNull
    private Hashtable<String, String> nameToPhotoMapper = new Hashtable<>();

    private static ContactsProvider sInstance;

    private ContactsProvider() {
    }

    public static ContactsProvider getInstance() {
        if (sInstance == null) {
            sInstance = new ContactsProvider();
        }
        return sInstance;
    }

    public boolean restoreData(@NonNull Context context) {
        SharedPreferences preferences = context.getSharedPreferences(CONTACT_PREFERENCES, Context.MODE_PRIVATE);
        boolean isExists = false;
        for (String contactName : contactsNames) {
            if (preferences.contains(contactName)) {
                isExists = true;
                if (!nameToPhotoMapper.contains(contactName)) {
                    String contactsPhotoUrl = preferences.getString(contactName, "");
                    nameToPhotoMapper.put(contactName, contactsPhotoUrl);
                }
            }
        }
        return isExists;
    }

    public Contact provideRandomContact() {
        int randomContactId = new Random().nextInt(contactsNames.length);
        return new Contact(contactsNames[randomContactId],"");
    }

    public List<Contact> provideContacts(int count) {
        return generateRandomData(count);
    }

    @NonNull
    public String getContactPhoto(@NonNull String contactName) {
        if (nameToPhotoMapper.containsKey(contactName)) {
            return nameToPhotoMapper.get(contactName);
        }
        return "";
    }

    private List<Contact> generateRandomData(int count) {
        List<Contact> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Contact item = new Contact();
            int randomNameId = new Random().nextInt(contactsNames.length);
            item.setName(contactsNames[randomNameId]);
            result.add(item);
        }
        return result;
    }

    public boolean savePhoto(@NonNull Context context,
                                   @NonNull String contactName,
                                   @NonNull String contactPhoto) {
        nameToPhotoMapper.put(contactName, contactPhoto);
        SharedPreferences prefs = context.getSharedPreferences(CONTACT_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = prefs.edit();

        prefsEditor.putString(contactName, contactPhoto);

        return prefsEditor.commit();
    }

}
