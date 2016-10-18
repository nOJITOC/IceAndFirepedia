package com.softdesign.skillbranch.iceandfirepedia.data.storage.models;


import com.softdesign.skillbranch.iceandfirepedia.data.network.res.CharacterRes;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinProperty;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Unique;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
@Entity(active = true, nameInDb = "CHARACTERS")
public class SwornMember {

    @Id
    private Long id;
    @Unique
    private long apiId;

    public long getApiId() {
        return apiId;
    }

    private String name;


    private String born;
    private String died;
    @ToMany(joinProperties = {
            @JoinProperty(name = "id", referencedName = "characterRemoteId")
    })
    private List<Title> titles;
    @ToMany (joinProperties = {
            @JoinProperty(name = "id", referencedName = "characterRemoteId")})
    private List<Alias> aliases;


    private Long father;

    private Long mother;

    private Long bookId;

    @ToOne(joinProperty = "bookId")
    private Book lastBook;
    private Long houseRemoteId;

    /** Used for active entity operations. */
    @Generated(hash = 118802721)
    private transient SwornMemberDao myDao;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    @Generated(hash = 1827934959)
    private transient Long lastBook__resolvedKey;

    public SwornMember(CharacterRes data, Long houseId) {
        apiId = idFromUrl(data.getUrl());
        name = data.getName();
        born = data.getBorn();
        died = data.getDied();
        father = idFromUrl(data.getFather());
        mother = idFromUrl(data.getMother());
        houseRemoteId = houseId;
        List<String> books = data.getBooks();
        if(books.isEmpty())bookId = null;
        else bookId = idFromUrl(data.getBooks().get(data.getBooks().size()-1));
    }

    private Long idFromUrl(String url) {
        return url.isEmpty()?null:Long.parseLong(url.substring(url.lastIndexOf('/') + 1));
    }



    public SwornMember(String name) {
        this.name = name;
    }

    @Generated(hash = 221091662)
    public SwornMember(Long id, long apiId, String name, String born, String died, Long father,
            Long mother, Long bookId, Long houseRemoteId) {
        this.id = id;
        this.apiId = apiId;
        this.name = name;
        this.born = born;
        this.died = died;
        this.father = father;
        this.mother = mother;
        this.bookId = bookId;
        this.houseRemoteId = houseRemoteId;
    }

    @Generated(hash = 211909483)
    public SwornMember() {
    }


    public String getName() {
        return name;
    }


    public Long getHouseRemoteId() {
        return this.houseRemoteId;
    }

    public void setHouseRemoteId(Long houseRemoteId) {
        this.houseRemoteId = houseRemoteId;
    }

    public Long getMother() {
        return this.mother;
    }

    public void setMother(Long mother) {
        this.mother = mother;
    }

    public Long getFather() {
        return this.father;
    }

    public void setFather(Long father) {
        this.father = father;
    }

    public String getDied() {
        return this.died;
    }

    public void setDied(String died) {
        this.died = died;
    }

    public String getBorn() {
        return this.born;
    }

    public void setBorn(String born) {
        this.born = born;
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

    public void setApiId(long apiId) {
        this.apiId = apiId;
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
    @Generated(hash = 731614754)
    public synchronized void resetAliases() {
        aliases = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 383901698)
    public List<Alias> getAliases() {
        if (aliases == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            AliasDao targetDao = daoSession.getAliasDao();
            List<Alias> aliasesNew = targetDao._querySwornMember_Aliases(id);
            synchronized (this) {
                if(aliases == null) {
                    aliases = aliasesNew;
                }
            }
        }
        return aliases;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1506933621)
    public synchronized void resetTitles() {
        titles = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1779607687)
    public List<Title> getTitles() {
        if (titles == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TitleDao targetDao = daoSession.getTitleDao();
            List<Title> titlesNew = targetDao._querySwornMember_Titles(id);
            synchronized (this) {
                if(titles == null) {
                    titles = titlesNew;
                }
            }
        }
        return titles;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 853564134)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getSwornMemberDao() : null;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1529181698)
    public void setLastBook(Book lastBook) {
        synchronized (this) {
            this.lastBook = lastBook;
            bookId = lastBook == null ? null : lastBook.getId();
            lastBook__resolvedKey = bookId;
        }
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1941709547)
    public Book getLastBook() {
        Long __key = this.bookId;
        if (lastBook__resolvedKey == null || !lastBook__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            BookDao targetDao = daoSession.getBookDao();
            Book lastBookNew = targetDao.load(__key);
            synchronized (this) {
                lastBook = lastBookNew;
                lastBook__resolvedKey = __key;
            }
        }
        return lastBook;
    }

    public Long getBookId() {
        return this.bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }


}
