package com.softdesign.skillbranch.iceandfirepedia.data.storage.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinProperty;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.Unique;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

/**
 * Created by Иван on 14.10.2016.
 */
@Entity(active = true,nameInDb = "HOUSES")
public class House {
    @Id
    private Long id;
    @NotNull
    @Unique
    private String url;
    @NotNull
    private String name;
    private String words;

    @ToMany (joinProperties = {
            @JoinProperty(name = "id", referencedName = "houseRemoteId")
    })
    List<SwornMember> swornMembers;
    /** Used for active entity operations. */
    @Generated(hash = 1167916919)
    private transient HouseDao myDao;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;


    public House(String url, String name, String words) {
        this.id = Long.parseLong(url.substring(url.lastIndexOf('/')+1));
        this.url = url;
        this.name = name;
        this.words = words;
    }


    @Generated(hash = 743782175)
    public House(Long id, @NotNull String url, @NotNull String name, String words) {
        this.id = id;
        this.url = url;
        this.name = name;
        this.words = words;
    }


    @Generated(hash = 389023854)
    public House() {
    }


    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setId(Long id) {
        this.id = id;
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


    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 264415194)
    public synchronized void resetSwornMembers() {
        swornMembers = null;
    }


    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1371768310)
    public List<SwornMember> getSwornMembers() {
        if (swornMembers == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            SwornMemberDao targetDao = daoSession.getSwornMemberDao();
            List<SwornMember> swornMembersNew = targetDao._queryHouse_SwornMembers(id);
            synchronized (this) {
                if(swornMembers == null) {
                    swornMembers = swornMembersNew;
                }
            }
        }
        return swornMembers;
    }


    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 451323429)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getHouseDao() : null;
    }

}
