package michu.michu.service;

import michu.michu.domain.StepQuestion;
import michu.michu.repository.PurchaseEvaluationRepository;
import michu.michu.repository.StepQuestionRepository;
import michu.michu.web.dto.StepQuestionDto;
import michu.michu.web.dto.StepQuestionResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StepQuestionService {

    @Autowired
    private StepQuestionRepository stepQuestionRepository;

    /**
     * StepQuestionDto를 StepQuestion으로 변환
     *
     * @param dto StepQuestionDto
     * @return StepQuestion
     */
    private StepQuestion convertToEntity(StepQuestionDto dto) {
        return StepQuestion.builder()
                .step(dto.getStep())
                .question(dto.getQuestion())
                .options(dto.getOptions())
                .build();
    }

    /**
     * StepQuestion을 StepQuestionDto로 변환
     *
     * @param question StepQuestion
     * @return StepQuestionDto
     */
    private StepQuestionDto convertToDto(StepQuestion question) {
        return StepQuestionDto.builder()
                .step(question.getStep())
                .question(question.getQuestion())
                .options(question.getOptions())
                .build();
    }



    /**
     * 질문 등록
     *
     * @param dto StepQuestionDto
     * @return 등록된 질문의 ID
     */
    public StepQuestionResponseDto createQuestion(StepQuestionDto dto,Long evaluationId) {
        StepQuestion question = convertToEntity(dto);
        StepQuestion savedQuestion = stepQuestionRepository.save(question);

        return StepQuestionResponseDto.builder()
                .step(savedQuestion.getStep())
                .evaluationId(evaluationId)
                .selectedOption(null) //처음 질문 만들땐 null
                .options(savedQuestion.getOptions())
                .question(savedQuestion.getQuestion())
                .build();
    }
}