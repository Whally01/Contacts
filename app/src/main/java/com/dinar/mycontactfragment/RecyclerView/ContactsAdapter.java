package com.dinar.mycontactfragment.RecyclerView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dinar.mycontactfragment.Model.Contact;
import com.dinar.mycontactfragment.R;

import java.util.ArrayList;

/**
 * Created by Dr on 15.10.2016.
 */

public class ContactsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Contact> contacts;
    private Context context;
    private OnItemClickListener listener;
    public boolean isFavourite;

    public void setContacts(Context context, ArrayList<Contact> contacts, boolean isFavourite) {
        this.contacts = contacts;
        this.isFavourite = isFavourite;
        this.context = context;
        notifyDataSetChanged();
    }

    public void setContactItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ContactViewHolder) {
            if (!isFavourite) {
                ((ContactViewHolder) holder).bind(contacts.get(position), listener);
            }
        }

    }

    @Override
    public int getItemCount() {
        return contacts != null ? contacts.size() : 0;
    }


    // ********** Интерфейс слушателя элемента *******//

    public interface OnItemClickListener {
        void onSelectItemClick(String contact);

        void onDeleteItemClick(String contact);
    }


    // *************View Holder ************** ///
    public class ContactViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;
        TextView number;
        View view;

        public ContactViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            name = (TextView) itemView.findViewById(R.id.name);
            // imageView = (ImageView) itemView.findViewById(R.id.photo);
            number = (TextView) itemView.findViewById(R.id.number);

        }

        public void bind(@NonNull final Contact contact, @Nullable final OnItemClickListener listener) {
            if (contact != null) {
                number.setText(contact.getNumber());
                name.setText(contact.getName());
                if (listener != null) {
                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            listener.onSelectItemClick(contact.getName());
                        }
                    });
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            listener.onDeleteItemClick(contact.getName());
                        }
                    });
                }


            }
        }

    }
}
