package michu.michu.service;

import michu.michu.domain.Evaluation;
import michu.michu.repository.EvaluationRepository;
import michu.michu.repository.UserRepository;
import michu.michu.web.dto.EvaluationResponseDto;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EvaluationService {

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Autowired
    private UserRepository userRepository;

    private static final String[] QUESTIONS = {
            "유사한 옷이 이미 있는지?",
            "유행에 따라가는 건지?",
            "소득 대비 적절한지?",
            "지금 할인 중인지?",
            "사용자와 정말 어울리는지?"
    };

    public EvaluationResponseDto getQuestions(Long userId) {
        return EvaluationResponseDto.builder()
                .userId(userId)
                .responses(
                        List.of(QUESTIONS).stream()
                                .map(question -> EvaluationResponseDto.QuestionResponse.builder()
                                        .question(question)
                                        .score(0) // 초기값 0
                                        .build())
                                .collect(Collectors.toList())
                )
                .build();
    }

    public void saveEvaluation(EvaluationResponseDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + dto.getUserId()));

        Evaluation evaluation = Evaluation.builder()
                .user((michu.michu.domain.User) user)
                .answers(
                        dto.getResponses().stream()
                                .map(response -> Evaluation.QuestionAnswer.builder()
                                        .question(response.getQuestion())
                                        .score(response.getScore())
                                        .build())
                                .collect(Collectors.toList())
                )
                .build();

        evaluationRepository.save(evaluation);
    }
}