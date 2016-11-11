package com.whyalwaysmea.stickview.binder;

import com.whyalwaysmea.stickview.binder.Book;

interface IBookManager {
     List<Book> getBookList();
     void addBook(in Book book);
}