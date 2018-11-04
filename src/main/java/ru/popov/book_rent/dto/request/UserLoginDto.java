package ru.popov.book_rent.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDto implements Serializable {

    private static final long serialVersionUID = 2718390031199436930L;

    private String login;

    private String password;

}
