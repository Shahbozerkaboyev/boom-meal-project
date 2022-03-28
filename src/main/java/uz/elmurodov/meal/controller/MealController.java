package uz.elmurodov.meal.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.elmurodov.meal.criteria.MealCriteria;
import uz.elmurodov.meal.dto.meal.MealCreateDto;
import uz.elmurodov.meal.dto.meal.MealDto;
import uz.elmurodov.meal.dto.meal.MealUpdateDto;
import uz.elmurodov.meal.dto.response.DataDto;
import uz.elmurodov.meal.service.MealService;

import javax.validation.Valid;
import java.util.List;


@RestController
public class MealController extends AbstractController<MealService> {
    public MealController(MealService service) {
        super(service);
    }


    /*@RequestMapping(value = PATH + "/meal/create", method = RequestMethod.POST)
    public ResponseEntity<DataDto<Long>> create(@Valid @RequestBody MealCreateDto dto) {
        return service.create(dto);
    }*/


    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = PATH + "/meal", method = RequestMethod.GET)
    public ResponseEntity<DataDto<List<MealDto>>> getAll(@Valid MealCriteria criteria) {
        return service.getAll(criteria);
    }

    @RequestMapping(value = PATH + "/meal/{id}", method = RequestMethod.GET)
    public ResponseEntity<DataDto<MealDto>> get(@PathVariable Long id) {
        return service.get(id);
    }

    @RequestMapping(value = PATH + "/meal/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<DataDto<Boolean>> delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @RequestMapping(value = PATH + "/meal/", method = RequestMethod.PUT)
    public ResponseEntity<DataDto<Boolean>> update(@Valid @RequestBody MealUpdateDto dto) {
        return service.update(dto);
    }


}
