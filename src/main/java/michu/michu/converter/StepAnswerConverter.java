package michu.michu.converter;

import michu.michu.domain.StepAnswer;
import michu.michu.web.dto.StepAnswerDto;

public class StepAnswerConverter {

    public static StepAnswerDto toDTO(StepAnswer stepAnswer) {
        return StepAnswerDto.builder()
                .step(stepAnswer.getStep())
                .selectedOption(stepAnswer.getSelectedOption())
                .evaluationId(stepAnswer.getEvaluation().getId())
                .score(stepAnswer.getScore())
                .build();
    }

}