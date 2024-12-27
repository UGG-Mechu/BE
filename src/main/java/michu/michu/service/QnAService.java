package michu.michu.service;

import michu.michu.domain.QnA;
import michu.michu.repository.QnARepository;
import michu.michu.web.dto.QnADto;
import michu.michu.web.dto.QuestionsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class QnAService {

    @Autowired
    private QnARepository qnaRepository;

    /**
     * answers와 answersDto 타입 변환
     */
    private List<String> changeTypeAnswer(List<Map<String, String>> answers) {
        List<String> result = new ArrayList<>();
        for (Map<String, String> answer : answers) {
            answer.values().stream().toList().forEach(v -> result.add(v));
        }
        return result;
    }

    private List<Map<String, String>> changeTypeAnswerDto(List<String> answers) {
        List<Map<String, String>> result = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        for (int i = 1; i < answers.size() + 1; i++) {
            map.put(String.valueOf(i), answers.get(i));
            result.add(map);
        }
        return result;
    }

    /**
     * QnADto를 QnA으로 변환
     *
     * @param dto QnADto
     * @return QnA
     */

    private QnA convertToEntity(QnADto dto) {
        return QnA.builder()
                .question(dto.getQuestion())
                .answers(changeTypeAnswer(dto.getAnswers()))
                .step(1)
                .build();
    }

    /**
     * QnA을 QnADto로 변환
     *
     * @param question QnA
     * @return QnADto
     */

    private QnADto convertToDto(QnA question) {
        return QnADto.builder()
                .question(question.getQuestion())
                .answers(changeTypeAnswerDto(question.getAnswers()))
                .build();
    }


    /**
 * 모든 질문 단계 조회
 *
 * @return List<StepQuestionDTO>
 */

    public List<QnADto> getAllQuestions() {
        List<QnA> questions = qnaRepository.findAll();
        return questions.stream()
                .map(this::convertToDto) // convertToDto 메서드 사용
                .collect(Collectors.toList());
    }
}
