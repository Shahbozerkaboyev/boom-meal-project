package uz.elmurodov.meal.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;
import uz.elmurodov.meal.domain.Meal;
import uz.elmurodov.meal.dto.meal.MealCreateDto;
import uz.elmurodov.meal.dto.meal.MealDto;
import uz.elmurodov.meal.dto.meal.MealUpdateDto;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface MealMapper extends BaseMapper<
        Meal,
        MealDto,
        MealCreateDto,
        MealUpdateDto> {

    @Override
    MealDto toDto(Meal meal);

    @Override
    List<MealDto> toDto(List<Meal> e);

    @Override
    Meal fromCreateDto(MealCreateDto mealCreateDto);

    @Override
    Meal fromUpdateDto(MealUpdateDto d);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Meal fromUpdateDto(MealUpdateDto d, @MappingTarget Meal meal);

}
