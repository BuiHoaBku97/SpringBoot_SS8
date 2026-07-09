package startup.vn.booklibrary.services;

import startup.vn.booklibrary.models.dtos.requests.BorrowCreateDTO;
import startup.vn.booklibrary.models.dtos.responses.BorrowCreatedDTO;

public interface BorrowService {
    BorrowCreatedDTO createBorrow(BorrowCreateDTO borrowCreateDTO);

    BorrowCreatedDTO returnBook(Long ticketId);
}
