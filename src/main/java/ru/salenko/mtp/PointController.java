package ru.salenko.mtp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
