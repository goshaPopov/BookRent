package ru.popov.book_rent.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.popov.book_rent.dto.BookRentResponse;
import ru.popov.book_rent.model.BookRent;
import ru.popov.book_rent.model.User;
import ru.popov.book_rent.services.BookRentService;
import ru.popov.book_rent.services.UserService;


@Api(value="/users", description="User API. Provide login", produces ="application/json", consumes = "application/json")
@RestController
@RequestMapping("/users")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookRentService bookRentService;

    @ApiOperation(value = "Find all books rented by user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return page of books and time to end rent"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "Return 404 if user doesn't exist")
    }
    )
    @PreAuthorize("hasPermission(#userId, 'isThisUser')")
    @RequestMapping(value = "/{userId}/books",method = RequestMethod.GET)
    public ResponseEntity<Page<BookRentResponse>> findBooksOfUser(@PathVariable("userId") final Long userId, Pageable pageable) {

        return userService.findById(userId).map(user1 -> {
                    Page<BookRent> bookRent = bookRentService.findBookRentByUser(user1, pageable);
                    return new ResponseEntity<>(bookRent.map(BookRentResponse::new), HttpStatus.OK);
                })
                .orElseGet(
                        () ->  new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @ApiOperation(value = "Find all books rented by user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return page of books and time to end rent"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "Return 404 if user doesn't exist")
    }
    )
    @PreAuthorize("hasPermission(#userId, 'isThisUser')")
    @RequestMapping(value = "/{userId}",method = RequestMethod.GET)
    public ResponseEntity<User> findMyInfo(@PathVariable("userId") final Long userId) {

        return userService.findById(userId).map(user1 -> new ResponseEntity<>(user1, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

}
