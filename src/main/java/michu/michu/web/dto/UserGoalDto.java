package michu.michu.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserGoalDto {
    private Long userId; // 사용자 ID
    private double dailyGoal; // 하루 목표 금액
}