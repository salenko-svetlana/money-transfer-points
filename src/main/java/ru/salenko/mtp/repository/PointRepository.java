package ru.salenko.mtp.repository;

import org.springframework.data.repository.CrudRepository;
import ru.salenko.mtp.entity.Point;

public interface PointRepository extends CrudRepository<Point, Long> {
}
