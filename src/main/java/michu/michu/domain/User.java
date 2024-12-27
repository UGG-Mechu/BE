package michu.michu.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 사용자 ID

    @Column(nullable = false, length = 20)
    private String name; // 사용자 이름

    @ColumnDefault("0")
    private int money; // 목표 금액

}
