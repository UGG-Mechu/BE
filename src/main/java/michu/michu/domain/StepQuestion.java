package michu.michu.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/** 쓰지마세요!! QnA 쓰세요
 *
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StepQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int step; // 단계 번호 (1~5)
    private String question; // 단계별 질문
    @ElementCollection
    private List<String> options; // 선택지
    private int score;
}

 */