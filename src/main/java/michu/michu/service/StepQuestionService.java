package michu.michu.service;

import michu.michu.domain.StepQuestion;
import michu.michu.repository.StepQuestionRepository;
import michu.michu.web.dto.StepQuestionDto;
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
                .score(dto.getScore())
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
                .score(question.getScore())
                .build();
    }


    /**
     * 모든 질문 단계 조회
     *
     * @return List<StepQuestionDTO>
     */
    public List<StepQuestionDto> getAllQuestions() {
        List<StepQuestion> questions = stepQuestionRepository.findAll();
        return questions.stream()
                .map(this::convertToDto) // convertToDto 메서드 사용
                .collect(Collectors.toList());
    }

    /**
     * 질문 등록
     *
     * @param dto StepQuestionDto
     * @return 등록된 질문의 ID
     */
    public Long createQuestion(StepQuestionDto dto) {
        StepQuestion question = convertToEntity(dto);
        StepQuestion savedQuestion = stepQuestionRepository.save(question);
        return savedQuestion.getId();
    }
}