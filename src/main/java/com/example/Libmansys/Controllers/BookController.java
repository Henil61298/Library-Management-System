package com.example.Libmansys.Controllers;

import com.example.Libmansys.Entities.Book;
import com.example.Libmansys.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public ResponseEntity addBook(@RequestBody Book book){
        try {
            String result = bookService.addBook(book);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/associate-book-author")
    public ResponseEntity associateBookWithAuthor(@RequestParam Integer bookId, Integer authorId){
        try {
            String result = bookService.associateBookWithAuthor(bookId, authorId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/find-book-by-author")
    public ResponseEntity findBooksByAuthor(@RequestParam Integer authorId){
        return new ResponseEntity<>(bookService.findAllBooksByAuthor(authorId), HttpStatus.OK);
    }
}
