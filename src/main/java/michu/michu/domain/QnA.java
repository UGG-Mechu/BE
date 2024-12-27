package michu.michu.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Map;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QnA { //StepQuestion 대체

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int step; // 문항 번호 (1~5)

    private String question; // 단계별 질문

    @ElementCollection
    private List<String> answers; // 선택지 (점수는 리스트 인덱스로)

}
