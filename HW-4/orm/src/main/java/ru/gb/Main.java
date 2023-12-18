package ru.gb;

import models.Courses;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.*;


public class Main {
    /*
        Создайте базу данных (например, SchoolDB).
    В этой базе данных создайте таблицу Courses с полями id (ключ), title, и duration.
    Настройте Hibernate для работы с вашей базой данных.
    Создайте Java-класс Course, соответствующий таблице Courses, с необходимыми аннотациями Hibernate.
    Используя Hibernate, напишите код для вставки, чтения, обновления и удаления данных в таблице Courses.
    Убедитесь, что каждая операция выполняется в отдельной транзакции.
     */
    public static void main(String[] args) {

        // Создадим нужную таблицу

        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "password";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {

            String useDatabaseSQL = "USE CoursesDB;";

            try (PreparedStatement statement = connection.prepareStatement(useDatabaseSQL)) {
                statement.execute();
            }
            String droptabDatabaseSQL = "DROP TABLE IF EXISTS courses;";

            try (PreparedStatement statement = connection.prepareStatement(droptabDatabaseSQL)) {
                statement.execute();
            }
            String ctabDatabaseSQL = "CREATE TABLE courses(" +
                    "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(45) NULL, " +
                    "duration INT NULL" +
                    ");";

            try (PreparedStatement statement = connection.prepareStatement(ctabDatabaseSQL)) {
                statement.execute();
                System.out.println("Создали таблицу courses.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Работаем с таблицей с помощью ORM

        try (SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Courses.class)
                .buildSessionFactory()) {

            // Создание сессии
            Session session = sessionFactory.getCurrentSession();

            // Начало транзакции
            session.beginTransaction();

            // Создание объекта
            Courses course = Courses.create();
            System.out.println("Создали:");
            System.out.println("Курс: " + course);
            session.save(course);
            System.out.println("Сохранили!");

            // Чтение объекта из базы данных
            Courses courseR = session.get(Courses.class, course.getId());
            System.out.println("Прочитали:");
            System.out.println("Курс: " + courseR);

            // Обновление объекта
            courseR.updateName();
            courseR.updateDuration();
            session.update(courseR);
            System.out.println("Оновили!");
            Courses courseU = session.get(Courses.class, courseR.getId());
            System.out.println("Перечитали:");
            System.out.println("Курс: " + courseU);

            // Удаление объекта
            session.delete(courseU);
            System.out.println("Удалили!");
            Courses courseD = session.get(Courses.class, courseU.getId());
            System.out.println("Перечитали:");
            System.out.println("Курс: " + courseD);

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}