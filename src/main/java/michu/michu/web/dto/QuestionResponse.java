package michu.michu.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionResponse {
    private String question; // 질문 텍스트
    private int score;       // 선택한 점수 (1~5)
    private String comment;  // 선택에 대한 추가 의견
}