package startup.vn.booklibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import startup.vn.booklibrary.models.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByTitleAndAuthor(String title, String author);
}
