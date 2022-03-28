package uz.elmurodov.meal.dto.menu;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.elmurodov.meal.dto.GenericDto;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuUpdateDto extends GenericDto {
    private String date;
    private Long mealId;
}
