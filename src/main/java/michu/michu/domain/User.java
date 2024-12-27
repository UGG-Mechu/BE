package michu.michu.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @ColumnDefault("0")
    private double dailyGoal; // 하루 목표 금액

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Item> itemList = new ArrayList<>();
}
