package michu.michu.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvaluationResponseDto {
    private Long userId; // 사용자 ID
    private List<QuestionResponse> responses; // 질문별 응답 리스트

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class QuestionResponse {
        private String question; // 질문 텍스트
        private int score;       // 선택한 점수 (1~5)
        private String comment;  // 선택에 대한 추가 의견
    }
}