package michu.michu.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StepAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 답변 아이디

    @ManyToOne
    private Item item; // 답변의 대상인 물건

    private int step; // 질문 문항번호

    private String selectedOption; // 질문에서 선택된 선택지

}
