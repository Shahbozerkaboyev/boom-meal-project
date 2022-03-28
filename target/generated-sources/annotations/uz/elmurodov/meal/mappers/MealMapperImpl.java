package uz.elmurodov.meal.mappers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import uz.elmurodov.meal.domain.Meal;
import uz.elmurodov.meal.dto.meal.MealCreateDto;
import uz.elmurodov.meal.dto.meal.MealDto;
import uz.elmurodov.meal.dto.meal.MealDto.MealDtoBuilder;
import uz.elmurodov.meal.dto.meal.MealUpdateDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-28T22:16:05+0500",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_322 (Amazon.com Inc.)"
)
@Component
public class MealMapperImpl implements MealMapper {

    @Override
    public MealDto toDto(Meal meal) {
        if ( meal == null ) {
            return null;
        }

        MealDtoBuilder mealDto = MealDto.childBuilder();

        mealDto.id( meal.getId() );
        mealDto.name( meal.getName() );

        return mealDto.build();
    }

    @Override
    public List<MealDto> toDto(List<Meal> e) {
        if ( e == null ) {
            return null;
        }

        List<MealDto> list = new ArrayList<MealDto>( e.size() );
        for ( Meal meal : e ) {
            list.add( toDto( meal ) );
        }

        return list;
    }

    @Override
    public Meal fromCreateDto(MealCreateDto mealCreateDto) {
        if ( mealCreateDto == null ) {
            return null;
        }

        Meal meal = new Meal();

        meal.setName( mealCreateDto.getName() );

        return meal;
    }

    @Override
    public Meal fromUpdateDto(MealUpdateDto d) {
        if ( d == null ) {
            return null;
        }

        Meal meal = new Meal();

        meal.setId( d.getId() );
        meal.setName( d.getName() );

        return meal;
    }

    @Override
    public Meal fromUpdateDto(MealUpdateDto d, Meal meal) {
        if ( d == null ) {
            return null;
        }

        if ( d.getId() != null ) {
            meal.setId( d.getId() );
        }
        if ( d.getName() != null ) {
            meal.setName( d.getName() );
        }

        return meal;
    }
}
