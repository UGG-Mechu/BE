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
     * 모든 질문 단계 조회
     *
     * @return List<StepQuestionDTO>
     */
    public List<StepQuestionDto> getAllQuestions() {
        List<StepQuestion> questions = stepQuestionRepository.findAll();
        return questions.stream().map(question -> StepQuestionDto.builder()
                .step(question.getStep())
                .question(question.getQuestion())
                .options(question.getOptions())
                .build()
        ).collect(Collectors.toList());
    }
}