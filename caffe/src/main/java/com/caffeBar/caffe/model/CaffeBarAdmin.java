package com.caffeBar.caffe.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class CaffeBarAdmin {
    @Id
    @GeneratedValue
    Long id;
    @Column
    String username;
    @Column
    String password;
}
