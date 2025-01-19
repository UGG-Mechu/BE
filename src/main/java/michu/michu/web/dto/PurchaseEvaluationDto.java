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
public class PurchaseEvaluationDto {
    private String itemName;
    private String evaluationResult;
    private List<StepAnswerDto> stepAnswers; //질문답변들
    private int totalScore; //총 점수
    private Long userId; // 연계된 사용자 ID
}