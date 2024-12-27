package michu.michu.converter;

import michu.michu.domain.StepAnswer;
import michu.michu.web.dto.StepAnswerDto;

public class StepAnswerConverter {

    public static StepAnswerDto toDTO(StepAnswer stepAnswer) {
        return StepAnswerDto.builder()
                .step(stepAnswer.getStep())
                .selectedOption(stepAnswer.getSelectedOption())
                .build();
    }

    public static StepAnswer toEntity(StepAnswerDto stepAnswerDTO) {
        return StepAnswer.builder()
                .step(stepAnswerDTO.getStep())
                .selectedOption(stepAnswerDTO.getSelectedOption())
                .build();
    }
}