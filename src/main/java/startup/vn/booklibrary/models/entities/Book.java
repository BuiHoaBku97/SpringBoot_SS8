package startup.vn.booklibrary.models.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "book",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_book_title_author", columnNames = {"title", "author"})
        }
)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 100, nullable = false)
    private String author;

    @Column(nullable = false)
    private Integer stock;

    @Column(name = "cover_url", nullable = false)
    private String coverUrl;
}
