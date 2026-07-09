package startup.vn.booklibrary.mappers;

import org.springframework.stereotype.Component;
import startup.vn.booklibrary.enums.BorrowStatus;
import startup.vn.booklibrary.models.dtos.requests.BorrowCreateDTO;
import startup.vn.booklibrary.models.dtos.responses.BorrowCreatedDTO;
import startup.vn.booklibrary.models.entities.Borrow;

@Component
public class BorrowMapper {
    public Borrow toEntity(BorrowCreateDTO borrowCreateDTO) {
        return Borrow.builder()
                .username(borrowCreateDTO.getUsername())
                .bookId(borrowCreateDTO.getBookId())
                .status(BorrowStatus.BORROWING)
                .build();
    }

    public BorrowCreatedDTO toResponse(Borrow borrow) {
        return BorrowCreatedDTO.builder()
                .id(borrow.getId())
                .username(borrow.getUsername())
                .bookId(borrow.getBookId())
                .status(borrow.getStatus())
                .returnDate(borrow.getReturnDate())
                .build();
    }
}
