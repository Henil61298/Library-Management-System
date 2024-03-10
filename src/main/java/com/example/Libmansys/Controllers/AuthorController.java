package com.example.Libmansys.Controllers;

import com.example.Libmansys.Entities.Author;
import com.example.Libmansys.Services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @PostMapping("/add")
    public ResponseEntity addAuthor(@RequestBody Author author){
        return new ResponseEntity<>(authorService.addAuthor(author), HttpStatus.OK);
    }

    @GetMapping("/get-author-with-max-books")
    public ResponseEntity authorWithMaxBooks(){
        return new ResponseEntity<>(authorService.getAuthorWithMaxBooks(), HttpStatus.OK);
    }
}
