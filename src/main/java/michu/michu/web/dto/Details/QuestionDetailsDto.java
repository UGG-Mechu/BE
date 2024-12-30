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
public class QuestionDetailsDto {
    private int step;                  // 질문 단계
    private String question;           // 질문 내용
    private List<String> options;      // 선택지 목록
    private String selectedOption;     // 선택한 옵션
    private int score;                 // 선택한 답변의 점수
}