package com.ratethis.productservice.model.product.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "product_book")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@DynamicUpdate(true)
public class ProductBook {

    @Id
    @Column(name = "book_id")
    private long bookId;

    @Basic
    @Column(name = "book_story_mark")
    private int storyMark;
    @Basic
    @Column(name = "book_art_mark")
    private int artMark;
    @Basic
    @Column(name = "book_info_mark")
    private int infoMark;
    @Basic
    @Column(name = "book_size")
    private int bookSize;

}
