package com.example.fmrapidev.services;

import com.example.fmrapidev.models.Book;

import java.util.List;

public interface BookService {
    List<Book> getAll();
    Book getById(int bookId);
    Book create(Book book);
    Book update(int bookId, Book book);
    void deleteById(int bookId);
}
