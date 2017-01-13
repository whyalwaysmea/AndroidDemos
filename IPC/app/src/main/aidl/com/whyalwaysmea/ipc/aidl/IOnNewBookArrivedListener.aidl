// IOnNewBookArrivedListener.aidl
package com.whyalwaysmea.ipc.aidl;

// Declare any non-default types here with import statements
import com.whyalwaysmea.ipc.aidl.Book;

interface IOnNewBookArrivedListener {
   void onNewBookArrived(in Book newBook);
}
