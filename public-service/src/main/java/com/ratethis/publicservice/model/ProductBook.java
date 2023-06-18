package com.ratethis.publicservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product_book")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductBook {

    @Id
    private long id;
    @MapsId
    @OneToOne
    @JoinColumn(name="book_id")
    private Product product;

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
    private int size;

}
