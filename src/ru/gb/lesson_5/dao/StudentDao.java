package ru.gb.lesson_5.dao;

import ru.gb.lesson_5.entity.StudentEntity;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class StudentDao {

    private final SessionFactory sessionFactory;

    public void add(StudentEntity student) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(student);
        session.getTransaction().commit();
    }

    public void deleteById(Long id) {

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        StudentEntity student = session.get(StudentEntity.class, id);
        session.remove(student);
        session.getTransaction().commit();

    }

    public void delete(StudentEntity student) {

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        StudentEntity removeStudent = session.get(StudentEntity.class, student.getId());
        session.remove(removeStudent);
        session.getTransaction().commit();

    }

    public void update(StudentEntity student) {

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        StudentEntity updatedStudent = session.get(StudentEntity.class, student.getId());
        updatedStudent.setName(student.getName());
        updatedStudent.setMark(student.getMark());
        session.getTransaction().commit();

    }

    public List<StudentEntity> findAll() {

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<StudentEntity> students = Collections.unmodifiableList(sessionFactory.getCurrentSession().createQuery("from Student s").list());
        session.getTransaction().commit();
        return students;

    }

    public StudentEntity findById(Long id) {

        Session session = sessionFactory.getCurrentSession();
        StudentEntity student = session.get(StudentEntity.class, id);
        return student;

    }

    public StudentEntity findByName(String fname) {

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query<StudentEntity> query = session.createQuery("SELECT s FROM Student s WHERE s.name =:fname", StudentEntity.class);
        query.setParameter("fname", fname);
        query.setMaxResults(1);
        StudentEntity student = query.getSingleResult();
        session.getTransaction().commit();
        return student;

    }
}