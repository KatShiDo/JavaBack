package com.mirea.JavaBack.Domain.Entities;

import com.mirea.JavaBack.Domain.Enums.TypeOfProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    private final TypeOfProduct typeOfProduct = TypeOfProduct.Book;
}