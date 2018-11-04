package ru.popov.book_rent.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRentRequest implements Serializable {

    @NotNull
    @JsonProperty("book_id")
    private Long bookId;

    @NotNull
    @JsonProperty("duration")
    private Integer duration;

}
