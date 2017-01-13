package com.whyalwaysmea.ipc.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Long
 * on 2017/1/3.
 */

public class Book  implements Parcelable{
    public int bookId;
    public String bookName;


    protected Book(Parcel in) {
        bookId = in.readInt();
        bookName = in.readString();
    }

    public Book(int bookId, String bookName) {
        this.bookId = bookId;
        this.bookName = bookName;
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(bookId);
        parcel.writeString(bookName);
    }
}
