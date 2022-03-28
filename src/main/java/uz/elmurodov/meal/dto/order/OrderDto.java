package uz.elmurodov.meal.dto.order;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uz.elmurodov.meal.dto.GenericDto;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Getter
@Setter
public class OrderDto extends GenericDto {
    private Long userId;
    private Long mealId;
    private LocalDate date;
    private boolean receive;

    @Builder(builderMethodName = "childBuilder")
    public OrderDto(@NotNull(message = "Id Can not be null") Long id, Long userId, Long mealId, LocalDate date, boolean receive) {
        super(id);
        this.userId = userId;
        this.mealId = mealId;
        this.date = date;
        this.receive = receive;
    }
}
