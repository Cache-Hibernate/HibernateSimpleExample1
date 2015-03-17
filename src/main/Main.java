package main;

import DAO.Factory;
import logic.Student;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Aleksandr Konstantinovitch
 * @version 1.0
 * @date 25/01/2015
 ** {@link http://javaxblog.ru/article/java-hibernate-1/}
 * {@link http://www.developer.am/j2eetutorial/hibernate/?page=load_hibernate_cfg_xml_from_different_directory}
 * {@link http://perrohunter.com/org-hibernate-hibernateexception-hibernate-cfg-xml-not-found-in-intellij-project/}
 *********************************************************************
 * SQL> CREATE TABLE bookstore.Student
 * (
 *   id INT NOT NULL,
 *   name VARCHAR(255),
 *   age INT NOT NULL,
 *   PRIMARY KEY ( id )
 * );
 *
 * *******************************************************************
 * 'Hibernate' — библиотека для языка программирования Java, предназначенная для решения задач объектно-реляционного отображения (object-relational mapping — ORM).
 * 'ORM' — это отображение объектов какого-либо объектно-ориентированного языка в структуры реляционных баз данных (именно объектов, таких, какие они есть, со всеми полями, значениями, отношениями и так далее).
 * *******************************************************************
 * Первое что нужно сделать - это создать объект-сущности для таблицы в базе.
 * Далее для выполнения транзакций нам будет нужен слой-DAO (он состоит из интерфейса и класса который реализует его).
 * Собственно любая транзакция к базе выполняется после подключения к ней и создания сессии - контекста внутри которого будут выполняться все транзакции (за это отвечает Hibernate 'SessionFactory', который вытягивает параметры из конфигурационного файла 'hibernate.ctf.xml').
 * Также особенностью DAO-слоя является правильное закрытие коннектов (пулов) после их использования (за это будет отвечать наш синглтон 'Factory').
 */
public class Main {
    
    public static void main(String[] args) throws SQLException {
        // Создадим двух студентов
        Student student1 = new Student();
        Student student2 = new Student();
        
        // Проинициализируем их
        student1.setName("Ivanov Ivan");
        student1.setAge(21l);
        student2.setName("Petrova Alisa");
        student2.setAge(24l);
        // Сохраним их в бд, id будут сгенерированы автоматически
        Factory.getInstance().getStudentDAO().addStudent(student1);
        Factory.getInstance().getStudentDAO().addStudent(student2);
        
        // Выведем всех студентов из базы данных
        System.out.println("\nВыведем всех студентов из базы данных");
        List<Student> students = Factory.getInstance().getStudentDAO().getAllStudents();
        System.out.println("+--------+---------+-------------------+");
        System.out.println("|   ID   |   AGE   |        NAME       |");
        System.out.println("+--------+---------+-------------------+");
        for(int i = 0; i < students.size(); ++i) {
            String strOut = "|   " + students.get(i).getId() + "      ";
            strOut = strOut.substring(0,9);
            strOut += strOut = "|   " + students.get(i).getAge() + "      ";
            strOut = strOut.substring(0,19);
            strOut += strOut = "|   " + students.get(i).getName() + "      ";
            strOut = strOut.substring(0,36) + "   |";
            System.out.println( strOut );
            System.out.println("+--------+---------+-------------------+");
        }

        // Выведем только одного студента из базы данных
        System.out.println("\nВыведем только одного студента из базы данных");
        Student student3 = Factory.getInstance().getStudentDAO().getStudentById(3l);
        System.out.println("+--------+---------+-------------------+");
        System.out.println("|   ID   |   AGE   |        NAME       |");
        System.out.println("+--------+---------+-------------------+");
        String strOut = "|   " + student3.getId() + "      ";
        strOut = strOut.substring(0,9);
        strOut += strOut = "|   " + student3.getAge() + "      ";
        strOut = strOut.substring(0,19);
        strOut += strOut = "|   " + student3.getName() + "      ";
        strOut = strOut.substring(0,36) + "   |";
        System.out.println( strOut );
        System.out.println("+--------+---------+-------------------+");

        // Обновим только одного студента в базе данных
        System.out.println("\nОбновим только одного студента в базе данных");
        student1.setId(3l);
        student1.setName("Aaa Aaaaa");
        student1.setAge(121l);
        // Обновим их в бд
        Factory.getInstance().getStudentDAO().updateStudent(student1);
        Student updateStudent3 = Factory.getInstance().getStudentDAO().getStudentById(3l);
        System.out.println("+--------+---------+-------------------+");
        System.out.println("|   ID   |   AGE   |        NAME       |");
        System.out.println("+--------+---------+-------------------+");
        strOut = "|   " + updateStudent3.getId() + "      ";
        strOut = strOut.substring(0,9);
        strOut += strOut = "|   " + updateStudent3.getAge() + "      ";
        strOut = strOut.substring(0,19);
        strOut += strOut = "|   " + updateStudent3.getName() + "      ";
        strOut = strOut.substring(0,36) + "   |";
        System.out.println( strOut );
        System.out.println("+--------+---------+-------------------+");

        // Удалим только одного студента из базы данных
        System.out.println("\nУдалим только одного студента из базы данных");
        Student deleteStudent1 = Factory.getInstance().getStudentDAO().getStudentById(10l);
        Factory.getInstance().getStudentDAO().deleteStudent(deleteStudent1);
        students = Factory.getInstance().getStudentDAO().getAllStudents();
        System.out.println("+--------+---------+-------------------+");
        System.out.println("|   ID   |   AGE   |        NAME       |");
        System.out.println("+--------+---------+-------------------+");
        for(int i = 0; i < students.size(); ++i) {
            strOut = "|   " + students.get(i).getId() + "      ";
            strOut = strOut.substring(0,9);
            strOut += strOut = "|   " + students.get(i).getAge() + "      ";
            strOut = strOut.substring(0,19);
            strOut += strOut = "|   " + students.get(i).getName() + "      ";
            strOut = strOut.substring(0,36) + "   |";
            System.out.println( strOut );
            System.out.println("+--------+---------+-------------------+");
        }
    }
}