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
public class DailyScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 데일리 스코어 ID

    @ManyToOne
    private User user;

    @ColumnDefault("0")
    private int dailyScore; // 일일 점수

}
