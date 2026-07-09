package startup.vn.booklibrary.models.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import startup.vn.booklibrary.validations.ExistingBookId;

@Getter
@Setter
public class BorrowCreateDTO {
    @NotBlank(message = "Username is require")
    private String username;

    @NotNull(message = "Book id is require")
    @ExistingBookId
    private Long bookId;
}
