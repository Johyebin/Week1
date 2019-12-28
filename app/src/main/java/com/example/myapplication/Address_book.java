package com.example.myapplication;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

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
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(recyclerAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // getContactList 함수는 주소록에 접근하여 ArrayList<ContactItem> 형식의 데이터를 제공
//        listContact = ((MainActivity)MainActivity.mContext).getContactList();
        // 다른 함수들을 테스트하기 위해서 일단 수동으로 값을 입력해야 할 듯.
        listContact = new ArrayList<ContactItem>();
        listContact.add(new ContactItem("010-1598-4568", "cat", R.drawable.cat));
        listContact.add(new ContactItem("010-2485-9901", "pengsu", R.drawable.pengsu));
        listContact.add(new ContactItem("010-7505-3241", "doraemon", R.drawable.doraemon));
        listContact.add(new ContactItem("010-1598-4568", "cat", R.drawable.cat));
        listContact.add(new ContactItem("010-2485-9901", "pengsu", R.drawable.pengsu));
        listContact.add(new ContactItem("010-7505-3241", "doraemon", R.drawable.doraemon));
        listContact.add(new ContactItem("010-1598-4568", "cat", R.drawable.cat));
        listContact.add(new ContactItem("010-2485-9901", "pengsu", R.drawable.pengsu));
        listContact.add(new ContactItem("010-7505-3241", "doraemon", R.drawable.doraemon));
        listContact.add(new ContactItem("010-1598-4568", "cat", R.drawable.cat));
        listContact.add(new ContactItem("010-2485-9901", "pengsu", R.drawable.pengsu));
        listContact.add(new ContactItem("010-7505-3241", "doraemon", R.drawable.doraemon));
    }
}