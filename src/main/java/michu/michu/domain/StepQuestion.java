package michu.michu.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class StepQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 질문 ID

    private int step; // 질문 문항 (1~5)

    private String question; // 질문 내용

    @ElementCollection
    private List<String> options; // 질문에 대한 선택지 (5개?)
}
