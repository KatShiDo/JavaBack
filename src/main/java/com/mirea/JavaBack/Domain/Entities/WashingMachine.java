package com.mirea.JavaBack.Domain.Entities;

import com.mirea.JavaBack.Domain.Enums.TypeOfProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "washing_machines")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WashingMachine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String manufacturer;

    private int volume;

    private String sellerNumber;

    private int count;

    private final TypeOfProduct typeOfProduct = TypeOfProduct.WashingMachine;

    private double cost;
}
