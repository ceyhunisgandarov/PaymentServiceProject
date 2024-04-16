package com.payment.service.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_field")
public class ReqField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    private String label;
    private String placeholder;
    private int maxLength;
    private String typeInput;
    @ManyToOne
    @JoinColumn(name = "operation_id")
    private Operation operation;

}
