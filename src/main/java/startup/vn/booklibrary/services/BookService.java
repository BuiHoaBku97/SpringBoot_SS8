package startup.vn.booklibrary.services;

import startup.vn.booklibrary.models.dtos.requests.BookCreateDTO;
import startup.vn.booklibrary.models.dtos.requests.BookUpdateStockDTO;
import startup.vn.booklibrary.models.dtos.responses.BookCreatedDTO;

public interface BookService {
    BookCreatedDTO createBook(BookCreateDTO bookCreateDTO);

    BookCreatedDTO updateBook(Long id, BookUpdateStockDTO bookUpdateStockDTO);

    BookCreatedDTO getBookById(Long id);
}
