package com.backend.fitta.entity.image;

import com.backend.fitta.entity.member.Member;
import com.backend.fitta.entity.utils.Auditing;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image extends Auditing {
    @Column(name = "image_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String originalName;
    private String storeName;
    @OneToOne(mappedBy = "image")
    private Member member;


    public Image(String originalName, String storeName, Member member) {
        this.originalName = originalName;
        this.storeName = storeName;
        this.member = member;
    }
}
