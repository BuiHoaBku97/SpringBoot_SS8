package startup.vn.booklibrary.models.dtos.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class BookCreateDTO {
    @NotBlank(message = "Title is require")
    private String title;

    @NotBlank(message = "Author is require")
    private String author;

    @NotNull(message = "Stock is require")
    @Min(value = 0, message = "Stock always equal or greater 0")
    private Integer stock;

    @NotNull(message = "coverImage is require")
    private MultipartFile coverImage;
}
