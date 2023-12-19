package com.sukute1712.ketfilmstore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractAuditable;

import java.io.File;
import java.util.List;
import java.util.UUID;


@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Table(name = "images")
public class Image extends AbstractAuditable<User, UUID> {
    private String source;
    private String publicId;
    @ManyToMany(mappedBy = "images")
    private List<Product> products;

}
