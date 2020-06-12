package ua.ihor0k.heaven.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.ihor0k.heaven.entity.ParameterEntity;

import java.util.List;

@Repository
public interface ParameterRepository extends CrudRepository<ParameterEntity, Long> {
    List<ParameterEntity> findByOrderGreaterThan(Integer order);
    List<ParameterEntity> findByOrderBetween(Integer from, Integer to);
}
