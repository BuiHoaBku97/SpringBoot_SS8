package startup.vn.booklibrary.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import startup.vn.booklibrary.repositories.BookRepository;
import startup.vn.booklibrary.validations.ExistingBookId;

public class BookIdValidator implements ConstraintValidator<ExistingBookId, Long> {
    private final BookRepository bookRepository;

    public BookIdValidator(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public boolean isValid(Long bookId, ConstraintValidatorContext context) {
        if (bookId == null) {
            return true;
        }

        return bookRepository.existsById(bookId);
    }
}
