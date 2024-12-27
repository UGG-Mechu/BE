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
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user; // 사용자 정보

    @OneToMany(cascade = CascadeType.ALL)
    private List<QuestionAnswer> answers; // 질문별 답변 리스트

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Entity
    public static class QuestionAnswer {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String question; // 질문 텍스트
        private int score;       // 선택한 점수 (1~5)
        private String comment;  // 선택에 대한 추가 의견
    }
}