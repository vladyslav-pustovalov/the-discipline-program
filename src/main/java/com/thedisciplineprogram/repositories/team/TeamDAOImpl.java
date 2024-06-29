package com.thedisciplineprogram.repositories.team;

import com.thedisciplineprogram.models.db_entities.Team;
import com.thedisciplineprogram.utils.HibernateSessionFactoryUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TeamDAOImpl implements TeamDAO {

    @Override
    public Team findTeamById(Long id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Team result = session.get(Team.class, id);
            log.info("Team is got from DB");
            return result;
        } catch (HibernateException e) {
            log.error(e.getMessage());
        }
        return new Team();
    }

    @Override
    public Boolean createTeam(Team team) {
        boolean isCreated = false;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Team existingTeam = session.get(Team.class, team.getId());
            if (existingTeam == null) {
                session.getTransaction().begin();
                session.persist(team);
                session.getTransaction().commit();
                log.info("Team created in DB");
                isCreated = true;
            }
        } catch (HibernateException e) {
            log.error(e.getMessage());
        }
        return isCreated;
    }

    @Override
    public Boolean updateTeam(Team team) {
        boolean isUpdated = false;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Team existingTeam = session.get(Team.class, team.getId());
            if (existingTeam != null) {
                session.getTransaction().begin();
                session.merge(team);
                session.getTransaction().commit();
                log.info("Team updated in DB");
                isUpdated = true;
            }
        } catch (HibernateException e) {
            log.error(e.getMessage());
        }
        return isUpdated;
    }

    @Override
    public Boolean deleteTeamById(Long id) {
        boolean isDeleted = false;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Team existingTeam = session.get(Team.class, id);
            if (existingTeam != null) {
                session.getTransaction().begin();
                session.remove(existingTeam);
                session.getTransaction().commit();
                log.info("Team deleted in DB");
                isDeleted = true;
            }
        } catch (HibernateException e) {
            log.error(e.getMessage());
        }
        return isDeleted;
    }
}
