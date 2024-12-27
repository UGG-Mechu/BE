package michu.michu.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StepAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int step; // 단계 번호
    private String selectedOption; // 선택된 선택지

    @ManyToOne(fetch = FetchType.LAZY) // 다대일 관계 설정
    @JoinColumn(name = "evaluation_id", nullable = false) // 외래 키 설정
    private PurchaseEvaluation evaluation; // 연관된 PurchaseEvaluation 객체

}