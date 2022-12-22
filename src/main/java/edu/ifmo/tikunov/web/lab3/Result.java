package edu.ifmo.tikunov.web.lab3;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "result")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "result_id_generator")
    @SequenceGenerator(name = "result", sequenceName = "RESULT_ID", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    private double x;
    private double y;
    private double r;

    private boolean hit;

    private LocalDateTime date;
    private long executionTime;

    public long getId() { return id; }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getR() {
        return r;
    }

    public boolean isHit() {
        return hit;
    }

    public String getDateString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd.MM.yyyy");
        return date.format(formatter);
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public Result() {}

    public Result(double x, double y, double r, boolean hit, LocalDateTime date, long executionTime) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.hit = hit;
        this.date = date;
        this.executionTime = executionTime;
    }
}
