package michu.michu.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestionsDto {
    private List<QnADto> questions;

    @Override
    public String toString() {
        return "QuestionsDto{" +
                "questions=" + questions +
                '}';
    }
}
