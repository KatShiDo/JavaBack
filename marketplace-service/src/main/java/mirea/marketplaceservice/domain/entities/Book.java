package mirea.marketplaceservice.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mirea.marketplaceservice.domain.enums.TypeOfProduct;

@Data
@Entity
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private double cost;

    private String sellerNumber;

    private String author;

    private int count;

    public void increaseCount() {
        count += 1;
    }

    public void decreaseCount() {
        if (count >= 1) {
            count -= 1;
        }
    }

    private final TypeOfProduct typeOfProduct = TypeOfProduct.Book;
}
