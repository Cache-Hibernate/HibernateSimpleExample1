package DAO.Impl;

import DAO.StudentDAO;
import logic.Student;
import org.hibernate.Session;
import util.HibernateUtil;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * {@link http://javaxblog.ru/article/java-hibernate-2/}
 * *****************************************************
 * Как обычно все транзакции/запросы к базе данных деляться на 2-типа:
 * 1) те которые делают выборку данных (ничего не изменяя внутри базы данных):
 *    - Hibernate предлагает 3-вида запросов:
 *    1) Criteria - создается критерий запроса на основе класса...
 *    2) SQL - это универсальный язык позволяет выражать запросы на родном для вашей базы данных диалекте SQL...
 *    3) HQL - (The Hibernate Query Language — Язык запросов Hibernate относительно сложен, но зато богат и дает очень много возможностей...
 * 2) те которые изменяют/обновляют данные в базе данных:
 *    - применяются функции "beginTransaction()" и "getTransaction().commit()" между которыми строится сама транзакция (save(..),update(..),delete(..));
 * *****************************************************
 */
public class StudentDAOImpl implements StudentDAO {
    
    public void addStudent(Student stud) throws SQLException {
            Session session = null;
            try {
                session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                session.save(stud);
                session.getTransaction().commit();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
            } finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }
      }

      public void updateStudent(Student stud) throws SQLException {
            Session session = null;
            try {
                session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                session.update(stud);
                session.getTransaction().commit();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
            } finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }
      }

      public Student getStudentById(Long id) throws SQLException {
            Session session = null;
            Student stud = null;
            try {
                session = HibernateUtil.getSessionFactory().openSession();
//                stud = (Student) session.get(Student.class, id);
                stud = (Student) session.load(Student.class, id); // вернется неинициализированный прокси, обращения к базе вообще не будет
                stud.getName();
//                if( stud.getId() != id ){
//                    System.out.println("Not Found Student #" + id);
//                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
            } finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }
            return stud;
      }

      public List<Student> getAllStudents() throws SQLException {
            Session session = null;
            List<Student> studs = new ArrayList<Student>();
            try {
                session = HibernateUtil.getSessionFactory().openSession();
                studs = session.createCriteria(Student.class).list();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
            } finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }
            return studs;
      }

      public void deleteStudent(Student stud) throws SQLException {
            Session session = null;
            try {
                session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                session.delete(stud);
                session.getTransaction().commit();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
            } finally {
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }
      }  
}