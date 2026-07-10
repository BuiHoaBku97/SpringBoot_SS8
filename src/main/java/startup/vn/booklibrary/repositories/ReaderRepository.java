package startup.vn.booklibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import startup.vn.booklibrary.models.entities.Reader;

public interface ReaderRepository extends JpaRepository<Reader, Long> {
    boolean existsByEmail(String email);
}
