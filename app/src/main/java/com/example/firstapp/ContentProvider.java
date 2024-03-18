package com.example.firstapp;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.ArrayList;

public class ContentProvider {
    private Context mContext;

    public ContentProvider(Context mContext) {
        this.mContext = mContext;
    }

    public ArrayList<Contact> getAllContect(){
        ArrayList<Contact> listContact = new ArrayList<>();
        String[] projection = new String[]{
            ContactsContract.CommonDataKinds.Phone._ID,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER
        };

        Cursor cursor = mContext.getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                projection, null, null, null);

        if(cursor.moveToFirst()){
            do{
                Contact c = new Contact(cursor.getInt(0),
                        cursor.getString(1), cursor.getString(2));
                listContact.add(c);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return listContact;
    }
}
