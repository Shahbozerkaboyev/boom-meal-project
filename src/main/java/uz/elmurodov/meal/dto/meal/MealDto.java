package uz.elmurodov.meal.dto.meal;

import uz.elmurodov.meal.dto.GenericDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class MealDto extends GenericDto {
    private String name;
    private String image;


    @Builder(builderMethodName = "childBuilder")
    public MealDto(Long id, String name, String image) {
        super(id);
        this.name = name;
        this.image = image;
    }
}
