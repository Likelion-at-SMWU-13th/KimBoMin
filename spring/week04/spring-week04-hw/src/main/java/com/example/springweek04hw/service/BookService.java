package com.example.springweek04hw.service;

import com.example.springweek04hw.exception.BookNotFoundException;
import com.example.springweek04hw.exception.DuplicateBookException;
import com.example.springweek04hw.model.Book;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BookService {
    private final Map<Long, Book> store = new LinkedHashMap<>();
    private final AtomicLong seq = new AtomicLong(0);

    public List<Book> findAll() {
        return new ArrayList<>(store.values());
    }

    public Book findById(Long id) {
        Book b = store.get(id);
        if (b == null) throw new BookNotFoundException(id);
        return b;
    }

    public Book add(Book b) {
        boolean duplicate = store.values().stream()
                .anyMatch(x -> Objects.equals(x.getTitle(), b.getTitle())
                        && Objects.equals(x.getAuthor(), b.getAuthor()));
        if (duplicate) throw new DuplicateBookException(b.getTitle(), b.getAuthor());

        long id = seq.incrementAndGet();
        b.setId(id);
        store.put(id, b);
        return b;
    }

    public void delete(Long id) {
        if (!store.containsKey(id)) throw new BookNotFoundException(id);
        store.remove(id);
    }

    public void initDummyIfEmpty() {
        if (store.isEmpty()) {
            add(new Book(null, "Clean Code", "Robert C. Martin"));
            add(new Book(null, "The Pragmatic Programmer", "Andrew Hunt"));
        }
    }
}
