package startup.vn.booklibrary.models.dtos.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookUpdateStockDTO {
    @NotNull(message = "Stock is require")
    @Min(value = 0, message = "Stock always equal or greater 0")
    private Integer stock;
}
