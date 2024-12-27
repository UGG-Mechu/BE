package michu.michu.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @Column(nullable = false, length = 20)
    private String name;

    private Date date; // 구매 날짜

    @ColumnDefault("0")
    private int price;

    @Column(length = 1000)
    private String description; // 물건 설명

}
