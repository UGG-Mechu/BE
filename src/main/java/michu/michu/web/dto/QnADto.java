package michu.michu.web.dto;

import lombok.*;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class QnADto {
    String question;
    List<Map<String, String>> answers;

    @Override
    public String toString() {
        return "QnADto [question=" + question + ", answers=" + answers.toString() + "]";
    }
}
