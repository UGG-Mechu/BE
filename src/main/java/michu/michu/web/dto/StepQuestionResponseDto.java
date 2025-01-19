package michu.michu.web.dto;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StepQuestionResponseDto {
    private int step; // 단계 번호
    private String question; // 단계별 질문
    private List<String> options; // 선택지
    private String selectedOption; // 사용자가 선택한 옵션 추가(처음 질문 만들땐 null)
    private Long evaluationId; // PurchaseEvaluation ID
}
