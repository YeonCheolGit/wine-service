package com.example.winesearchservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "wine")
@Builder
@AllArgsConstructor @NoArgsConstructor
public class Wine {

    @Id
    private long id;
    private String windName;
    private int price;
    private String type;

}
