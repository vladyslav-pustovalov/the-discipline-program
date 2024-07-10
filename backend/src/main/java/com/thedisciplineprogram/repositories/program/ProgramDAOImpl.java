package com.thedisciplineprogram.repositories.program;

import com.thedisciplineprogram.models.db_entities.Program;
import com.thedisciplineprogram.utils.HibernateSessionFactoryUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProgramDAOImpl implements ProgramDAO {

    @Override
    public Program getProgramById(Long id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Program result = session.get(Program.class, id);
            log.info("Training Program is got from DB");
            return result;
        } catch (HibernateException e) {
            log.error(e.getMessage());
        }
        return new Program();
    }

    @Override
    public Boolean createProgram(Program program) {
        boolean isCreated = false;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
//            TrainingProgram existingProgram = session.get(TrainingProgram.class, program.getId());
//            if (existingProgram == null) {
            //TODO: add checking program for dates
                session.getTransaction().begin();
                session.persist(program);
                session.getTransaction().commit();
                log.info("Training Program created in DB");
                isCreated = true;
//            }
        } catch (HibernateException e) {
            log.error(e.getMessage());
        }
        return isCreated;
    }

    @Override
    public Boolean updateProgram(Program program) {
        boolean isUpdated = false;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Program existingProgram = session.get(Program.class, program.getId());
            if (existingProgram != null) {
                session.getTransaction().begin();
                session.merge(program);
                session.getTransaction().commit();
                log.info("Training Program updated in DB");
                isUpdated = true;
            }
        } catch (HibernateException e) {
            log.error(e.getMessage());
        }
        return isUpdated;
    }

    @Override
    public boolean deleteProgramById(Long id) {
        boolean isDeleted = false;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Program existingProgram = session.get(Program.class, id);
            if (existingProgram != null) {
                session.getTransaction().begin();
                session.remove(existingProgram);
                session.getTransaction().commit();
                log.info("Training Program deleted in DB");
                isDeleted = true;
            }
        } catch (HibernateException e) {
            log.error(e.getMessage());
        }
        return isDeleted;
    }
}
