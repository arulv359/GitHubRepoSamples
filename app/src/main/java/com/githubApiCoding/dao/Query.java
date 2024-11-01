package com.githubApiCoding.dao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;

/**
 * DB Entity for user search query from UI
 */
@SuppressWarnings("WeakerAccess")
@Entity
public class Query {

    @Id private Long id;

    @Index(unique = true) private String query;
    private Long execDate;

    @ToMany
    @JoinEntity(
            entity = Query2User.class,
            sourceProperty = "queryId",
            targetProperty = "userId"
    )
    private List<User> users;
    //

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 223113186)
    private transient QueryDao myDao;

    @Generated(hash = 1889251744)
    public Query(Long id, String query, Long execDate) {
        this.id = id;
        this.query = query;
        this.execDate = execDate;
    }

    @Generated(hash = 1837957505)
    public Query() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuery() {
        return this.query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 408780530)
    public List<User> getUsers() {
        if (users == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            UserDao targetDao = daoSession.getUserDao();
            List<User> usersNew = targetDao._queryQuery_Users(id);
            synchronized (this) {
                if (users == null) {
                    users = usersNew;
                }
            }
        }
        return users;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1027274768)
    public synchronized void resetUsers() {
        users = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    public Long getExecDate() {
        return this.execDate;
    }

    public void setExecDate(Long execDate) {
        this.execDate = execDate;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 533042382)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getQueryDao() : null;
    }

}
