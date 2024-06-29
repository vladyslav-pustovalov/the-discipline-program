package com.thedisciplineprogram.repositories.training_program;

import com.thedisciplineprogram.models.db_entities.TrainingProgram;
import com.thedisciplineprogram.utils.HibernateSessionFactoryUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TrainingProgramDAOImpl implements TrainingProgramDAO {

    @Override
    public TrainingProgram findProgramById(Long id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            TrainingProgram result = session.get(TrainingProgram.class, id);
            log.info("Training Program is got from DB");
            return result;
        } catch (HibernateException e) {
            log.error(e.getMessage());
        }
        return new TrainingProgram();
    }

    @Override
    public Boolean createProgram(TrainingProgram program) {
        boolean isCreated = false;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
//            TrainingProgram existingProgram = session.get(TrainingProgram.class, program.getId());
//            if (existingProgram == null) {
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
    public Boolean updateProgram(TrainingProgram program) {
        boolean isUpdated = false;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            TrainingProgram existingProgram = session.get(TrainingProgram.class, program.getId());
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
            TrainingProgram existingProgram = session.get(TrainingProgram.class, id);
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
