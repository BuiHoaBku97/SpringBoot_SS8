package startup.vn.booklibrary.mappers;

import org.springframework.stereotype.Component;
import startup.vn.booklibrary.models.dtos.requests.BookCreateDTO;
import startup.vn.booklibrary.models.dtos.responses.BookCreatedDTO;
import startup.vn.booklibrary.models.entities.Book;

@Component
public class BookMapper {
    public Book toEntity(BookCreateDTO bookCreateDTO, String coverUrl) {
        return Book.builder()
                .title(bookCreateDTO.getTitle())
                .author(bookCreateDTO.getAuthor())
                .stock(bookCreateDTO.getStock())
                .coverUrl(coverUrl)
                .build();
    }

    public BookCreatedDTO toResponse(Book book) {
        return BookCreatedDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .stock(book.getStock())
                .coverUrl(book.getCoverUrl())
                .build();
    }
}
