package startup.vn.booklibrary.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import startup.vn.booklibrary.validators.BookIdValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BookIdValidator.class)
public @interface ExistingBookId {
    String message() default "Sách không tồn tại trong hệ thống";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
