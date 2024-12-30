package michu.michu.web.dto.Details;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvaluationDetailsDto {
    private Long evaluationId;                // 평가 ID
    private String itemName;                  // 평가 항목 이름
    private String evaluationResult;          // 평가 결과
    private int totalScore;                   // 총 점수
    private List<QuestionDetailsDto> questions; // 질문과 답변 정보 리스트
}