package ru.salenko.mtp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.salenko.mtp.entity.Point;
import ru.salenko.mtp.repository.PointRepository;

@RestController
@RequestMapping("/api/point")
public class PointController {

    private final PointRepository pointRepository;
    public PointController(PointRepository pointRepository) { this.pointRepository = pointRepository; }


    @GetMapping("/list")
    public Iterable<Point> getPoint() {
        return pointRepository.findAll();
    }
}
