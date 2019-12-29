package com.example.myapplication;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class Address_book extends Fragment {
    View v;
    private RecyclerView myRecyclerView;
    private ArrayList<ContactItem> listContact;

    // Constructor
    public Address_book() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_address_book, container, false);
        myRecyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);
        MyAdapter recyclerAdapter = new MyAdapter(getContext(), listContact);

        // Add RecyclerView divider
//        DividerItemDecoration myDividerItemDecoration = new DividerItemDecoration(myRecyclerView.getContext(), 0);
        myRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), R.drawable.divider));
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(recyclerAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // getContactList 함수는 주소록에 접근하여 ArrayList<ContactItem> 형식의 데이터를 제공
        listContact = getContactList();
//        listContact = new ArrayList<ContactItem>();
//        listContact.add(new ContactItem("010-1598-4568", "cat", R.drawable.cat));
//        listContact.add(new ContactItem("010-2485-9901", "pengsu", R.drawable.pengsu));
//        listContact.add(new ContactItem("010-7505-3241", "doraemon", R.drawable.doraemon));
    }

    public ArrayList<ContactItem> getContactList() {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projection = new String[]{
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.Contacts.PHOTO_ID,
                ContactsContract.Contacts._ID
        };
        String[] selectionArgs = null;
        String sortOrder = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " COLLATE LOCALIZED ASC";
        Cursor cursor = getContext().getContentResolver().query(uri, projection, null, selectionArgs, sortOrder);
        LinkedHashSet<ContactItem> hashlist = new LinkedHashSet<>();
        if (cursor.moveToFirst()) {
            do {
                int photo_id = cursor.getInt(2);
                int person_id = cursor.getInt(3);
                ContactItem contactItem = new ContactItem("", "", 0);

                // Set up the phone number
                String s = cursor.getString(0);
                if (s.length() == 11) {
                    contactItem.setPhone_number(s.substring(0, 3) + "-" + s.substring(3, 7) + "-" + s.substring(7, 11));
                } else contactItem.setPhone_number(s);

                contactItem.setName(cursor.getString(1));

                // Set up the photo
                if (photo_id == 0) {
                    contactItem.setPhoto_id(R.drawable.profile_photo);
                } else contactItem.setPhoto_id(photo_id);

                contactItem.setPerson_id(person_id);

                if (contactItem.getPhone_number().startsWith("010")) {
                    hashlist.add(contactItem);
                    Log.d("<<CONTACT>>", "name=" + contactItem.getName() + ", phone=" + contactItem.getPhone_number());
                }
            } while (cursor.moveToNext());
        }
        ArrayList<ContactItem> contactItems = new ArrayList<>(hashlist);
        for (int i = 0; i < contactItems.size(); i++) {
            contactItems.get(i).setId(i);
        }
        if (cursor != null) cursor.close();
        System.out.println(contactItems.size());
        return contactItems;
    }
}