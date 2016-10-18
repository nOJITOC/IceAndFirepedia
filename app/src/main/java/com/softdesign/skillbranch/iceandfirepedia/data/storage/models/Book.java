package com.softdesign.skillbranch.iceandfirepedia.data.storage.models;

import com.softdesign.skillbranch.iceandfirepedia.data.network.res.BookRes;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

/**
 * Created by Иван on 18.10.2016.
 */
@Entity(active = true, nameInDb = "BOOKS")
public class Book {
    @Id
    Long id;


    String name;


    /** Used for active entity operations. */
    @Generated(hash = 1097957864)
    private transient BookDao myDao;


    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    public Book(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public Book(BookRes data){
        name = data.getName();
    }

    @Generated(hash = 381496039)
    public Book(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Generated(hash = 1839243756)
    public Book() {
    }

    private Long idFromUrl(String url) {
        return Long.parseLong(url.substring(url.lastIndexOf('/') + 1));
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

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1115456930)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getBookDao() : null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
