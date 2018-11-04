package ru.popov.book_rent.controllers;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.popov.book_rent.exceptions.NotEnoughMoneyException;
import ru.popov.book_rent.dto.request.BookRentRequest;
import ru.popov.book_rent.model.Book;
import ru.popov.book_rent.model.BookRent;
import ru.popov.book_rent.model.User;
import ru.popov.book_rent.services.BookRentService;
import ru.popov.book_rent.services.BookService;
import ru.popov.book_rent.services.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@Api(value="/rents", description="Provide API for managing books rent", produces = "application/json", consumes = "application/json")
@RestController
@RequestMapping("/rent")
public class BookRentController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookRentService bookRentService;

    @Autowired
    private BookService bookService;

    @ApiOperation(value = "Rent a book")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return page of books and time to end rent"),
            @ApiResponse(code = 403, message = "FORBIDDEN"),
            @ApiResponse(code = 404, message = "Return 404 if user doesn't exist")
        }
    )
    @PreAuthorize("#oauth2.isUser()")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createBookRent(@Valid @RequestBody final BookRentRequest bookRentRequest, final Principal principal) {

        Long bookId = bookRentRequest.getBookId();

        Optional<User> user = userService.findByLogin(principal.getName());
        Optional<Book> book = bookService.findById(bookId);

        if (!user.isPresent() || !book.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (bookRentService.isThisBookRentedNow(user.get(), book.get())) {
            return new ResponseEntity<>(getJsonMessage("Already rented book #" + bookId),HttpStatus.BAD_REQUEST);
        }

        BookRent bookRent;
        try {
            // We can't use lambda, because #rentTheBook throw Exception (construction with try catch looks ugly )
            bookRent = bookRentService.rentTheBook(user.get(), book.get(), bookRentRequest.getDuration());
        } catch (NotEnoughMoneyException exc) {
            return new ResponseEntity<>(getJsonMessage(exc.getMessage()),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(bookRent, HttpStatus.OK);
    }

}
