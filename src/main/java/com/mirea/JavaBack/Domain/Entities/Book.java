package com.mirea.JavaBack.Domain.Entities;

import com.mirea.JavaBack.Domain.Enums.TypeOfProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
