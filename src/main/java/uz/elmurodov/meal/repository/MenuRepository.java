package uz.elmurodov.meal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.elmurodov.meal.domain.Menu;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface MenuRepository extends JpaRepository<Menu, Long> {

    List<Menu> findAllByDeletedFalseAndMenuDate(LocalDate localDate);

    Optional<Menu> findByIdAndDeletedFalse(Long id);

//    List<Menu> findAllByDeletedFalse();

}
