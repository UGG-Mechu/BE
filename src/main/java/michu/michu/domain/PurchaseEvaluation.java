package michu.michu.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseEvaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName; // 구매하려는 물건의 이름
    private String evaluationResult; // 평가 결과

    private int totalScore; //총 점수

    @OneToMany(mappedBy = "evaluation", cascade = CascadeType.ALL)
    private List<StepAnswer> stepAnswers; // 연계된 질문 답변들

    @ManyToOne(fetch = FetchType.LAZY)
    private User user; // 해당 구매 평가와 연계된 사용자
}
