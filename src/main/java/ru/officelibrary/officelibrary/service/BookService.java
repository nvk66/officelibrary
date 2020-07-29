package ru.officelibrary.officelibrary.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.officelibrary.officelibrary.entity.Author;
import ru.officelibrary.officelibrary.entity.Book;
import ru.officelibrary.officelibrary.entity.Genre;
import ru.officelibrary.officelibrary.repository.BookRepository;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class BookService {

    private final  BookRepository bookRepository;
    private final  AuthorService authorService;

    public BookService(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    public Book addBook(Book book){
        return bookRepository.save(book);
    }

    public void deleteBook(long id){
        bookRepository.delete(get(id));
    }

    public Book get(long id){
        return bookRepository.findById(id).get();
    }

    public List<Book> getAll(){
        return (List<Book>) bookRepository.findAll();
    }

    public List<Book> search(long id) {
        Set<Author> authors = new HashSet<>();
        authors.add(authorService.get(id));
        return bookRepository.search(authors);
    }

    public Book findBookById(String ids){
        return bookRepository.findBookById(ids);
    }


    public List<Book> findBookByAuthor(Author author){
        HashSet<Author> authors = new HashSet<>();
        authors.add(author);
        return bookRepository.findBookByAuthorsIn(authors);
    }

    public List<Book> findBookByGenre(Genre genre){
        return bookRepository.findBookByGenresIn(Collections.singleton(genre));
    }

    public boolean isItPossibleToBookABook(long id) throws Exception {
        if (get(id).getStats().equals("Free"))
            return true;
        else throw new Exception("Someone has already taken it");
    }
}
