package ru.popov.book_rent.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.popov.book_rent.model.User;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse implements Serializable {

    private static final long serialVersionUID = -8556863057340597813L;

    private Long id;

    private String login;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    private BigDecimal balance;

    private String token;

    public UserResponse(User user, String token) {
        this(user.getId(), user.getLogin(), user.getFirstName(), user.getLastName(), user.getBalance(), "Bearer " + token);
    }
}
