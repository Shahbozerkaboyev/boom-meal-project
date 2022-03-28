package uz.elmurodov.meal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.elmurodov.meal.domain.Order;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
