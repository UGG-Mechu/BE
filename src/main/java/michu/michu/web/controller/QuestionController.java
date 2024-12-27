package michu.michu.web.controller;

import michu.michu.web.dto.QnADto;
import michu.michu.web.dto.QuestionsDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class QuestionController { // AI 파트에서 질문 답변 받아오기

    @PostMapping("/question")
    public void getQuestion(@RequestBody QuestionsDto question) {
        System.out.println(question);
    }
}

