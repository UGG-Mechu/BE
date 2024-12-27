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
    private Long id; // 상품 ID

    @ManyToOne
    private User user; // 상품 소유하고 있는 User

    @Column(nullable = false, length = 20)
    private String name; // 상품 이름

    private Date date; // 상품 구매 날짜

    @ColumnDefault("0")
    private int price; // 상품 가격

    @Column(length = 1000)
    private String description; // 상품에 대한 설명

}
