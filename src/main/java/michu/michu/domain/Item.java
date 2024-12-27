package michu.michu.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String itemName;

    @Column(name="price", nullable = false)
    private int price;

    @Lob
    @Column(nullable = false)
    private String itemDetail; //상품 상세 설명

    @ManyToOne // 여러 아이템이 하나의 사용자에 속할 수 있음을 나타냄
    @JoinColumn(name = "user_id") // Item 테이블에 외래 키 컬럼을 생성
    private User user; // 관계를 설정하기 위한 필드 추가

}
