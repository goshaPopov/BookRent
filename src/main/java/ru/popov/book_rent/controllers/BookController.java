package ru.popov.book_rent.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.popov.book_rent.model.Book;
import ru.popov.book_rent.services.BookService;


@Api(value="/books", description="Book API", produces = "application/json", consumes = "application/json")
@RestController
@RequestMapping("/books")
public class BookController extends BaseController {

    @Autowired
    private BookService bookService;

    @ApiOperation(value = "Find all books for rent")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return page of books")
        }
    )
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<Book>> findBooks(Pageable pageable) {
        return new ResponseEntity<>(bookService.findAll(pageable), HttpStatus.OK);
    }



}
