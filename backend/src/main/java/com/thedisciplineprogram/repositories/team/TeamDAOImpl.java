package com.thedisciplineprogram.repositories.team;

import com.thedisciplineprogram.models.db_entities.Team;
import com.thedisciplineprogram.utils.HibernateSessionFactoryUtil;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TeamDAOImpl implements TeamDAO {

    @Override
    public Team getTeamById(Long id) {
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
    public Team getTeamByName(String name) {
        Team result = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            try {
                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaQuery<Team> criteria = builder.createQuery(Team.class);
                Root<Team> from = criteria.from(Team.class);
                criteria.select(from);
                criteria.where(builder.equal(from.get("name"), name));
                TypedQuery<Team> typed = session.createQuery(criteria);
                result = typed.getSingleResult();
                log.info("Team is got from DB by name '{}'", name);
            } catch (NoResultException e) {
                log.error("Team with name '{}' is not found in DB", name);
                log.error(e.getMessage());
            }
        } catch (HibernateException e) {
            log.error(e.getMessage());
        }
        return result;
    }

    @Override
    public Boolean createTeam(Team team) {
        boolean isCreated = false;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Team existingTeam = getTeamByName(team.getName());
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
            } else {
                log.info("Team with id '{}' does not exist in DB", team.getId());
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
