// IMyAidlInterface.aidl
package com.whyalwaysmea.ipc.aidl;

// Declare any non-default types here with import statements
import com.whyalwaysmea.ipc.aidl.Book;
import com.whyalwaysmea.ipc.aidl.IOnNewBookArrivedListener;

interface IBookManager {
    List<Book> getBookList();
    void addBook(in Book book);
    void registerListener(IOnNewBookArrivedListener listener);
    void unregisterListener(IOnNewBookArrivedListener listener);
}
