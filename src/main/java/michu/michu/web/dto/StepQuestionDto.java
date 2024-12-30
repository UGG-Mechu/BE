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
public class StepQuestionDto {
    private int step; // 단계 번호
    private String question; // 단계별 질문
    private List<String> options; // 선택지
    private String selectedOption; // 사용자가 선택한 옵션 추가(처음 질문 만들땐 null)
    private long evaluationId; // PurchaseEvaluation ID
}
