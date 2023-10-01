package com.mirea.JavaBack.Domain.Entities;

import com.mirea.JavaBack.Domain.Enums.TypeOfProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "telephones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Telephone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String manufacturer;

    private int batteryCapacity;

    private String sellerNumber;

    private final TypeOfProduct typeOfProduct = TypeOfProduct.Telephone;

    private double cost;
}
