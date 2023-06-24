package com.backend.fitta.entity.image;

import com.backend.fitta.entity.gym.Gym;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gym_id")//
    private Gym gym;

    public Image(String originalName, String storeName, Gym gym) {
        this.originalName = originalName;
        this.storeName = storeName;
        if (gym != null) {
            changeGym(gym);
        }
    }
    public void changeGym(Gym gym){
        this.gym=gym;
        gym.getImage().add(this);
    }
}
