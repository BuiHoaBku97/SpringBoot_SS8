package startup.vn.booklibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import startup.vn.booklibrary.models.entities.Borrow;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {
}
