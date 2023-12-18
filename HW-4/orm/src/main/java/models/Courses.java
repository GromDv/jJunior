package models;

import jdk.jfr.Name;

import javax.persistence.*;
import java.util.Random;

@Entity
@Table(name = "courses")
public class Courses {
    private static final String[] names = new String[]{"Java ЯП", "С# ЯП", "C++ ЯП", "Python ЯП", "Ruby ЯП", "Go ЯП", "JS ЯП", "C ЯП", "PHP ЯП", "Pascal ЯП"};
    private static final Random random = new Random();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;
    @Column(name = "duration")
    private int duration;

    public Courses() {
    }

    public Courses(int id, String name, int duration) {
        this.id = id;
        this.name = name;
        this.duration = duration;
    }

    public Courses(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

    public static Courses create() {
        return new Courses(names[random.nextInt(names.length)], random.nextInt(10, 100));
    }


    public void updateDuration() {
        duration = random.nextInt(10, 100);
    }

    public void updateName() {
        name = names[random.nextInt(names.length)];
    }

    public int getId() {
        return id;
    }



    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                '}';
    }
}
