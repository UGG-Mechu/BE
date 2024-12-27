package michu.michu.web.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StepAnswerDto {

    private int step; // 질문 단계 번호 (1~5)
    private String selectedOption; // 사용자가 선택한 옵션
    private long evaluationId; // PurchaseEvaluation ID
}