package com.softdesign.skillbranch.iceandfirepedia.data.managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;


import com.softdesign.skillbranch.iceandfirepedia.data.network.RestService;
import com.softdesign.skillbranch.iceandfirepedia.data.network.ServiceGenerator;
import com.softdesign.skillbranch.iceandfirepedia.data.network.res.BookRes;
import com.softdesign.skillbranch.iceandfirepedia.data.network.res.CharacterRes;
import com.softdesign.skillbranch.iceandfirepedia.data.network.res.HouseRes;
import com.softdesign.skillbranch.iceandfirepedia.data.storage.models.Alias;
import com.softdesign.skillbranch.iceandfirepedia.data.storage.models.Book;
import com.softdesign.skillbranch.iceandfirepedia.data.storage.models.DaoSession;
import com.softdesign.skillbranch.iceandfirepedia.data.storage.models.House;
import com.softdesign.skillbranch.iceandfirepedia.data.storage.models.HouseDao;
import com.softdesign.skillbranch.iceandfirepedia.data.storage.models.SwornMember;
import com.softdesign.skillbranch.iceandfirepedia.data.storage.models.SwornMemberDao;
import com.softdesign.skillbranch.iceandfirepedia.data.storage.models.Title;
import com.softdesign.skillbranch.iceandfirepedia.utils.AppConfig;
import com.softdesign.skillbranch.iceandfirepedia.utils.IceAndFireApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataManager {

    private static final String TAG = "DataManager";
    private static DataManager INSTANCE = null;
    private final DaoSession mDaoSession;
    private HouseDao mHouseDao;
    private PreferencesManager mPreferencesManager;
    private SwornMemberDao mMemberDao;

    private Context mContext;
    private RestService mRestService;

    public DataManager() {
        this.mContext = IceAndFireApplication.getContext();
        this.mRestService = ServiceGenerator.createService(RestService.class);
        mPreferencesManager = new PreferencesManager();
        this.mDaoSession = IceAndFireApplication.getDaoSession();
        mHouseDao = mDaoSession.getHouseDao();
        mMemberDao = mDaoSession.getSwornMemberDao();
    }


    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public PreferencesManager getPreferencesManager() {
        return mPreferencesManager;
    }


    public static DataManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DataManager();
        }
        return INSTANCE;

    }

    public Context getContex() {
        return mContext;
    }

    //region =========Network===========
    public Call<HouseRes> getHousesFromNetwork(int page) {
        return mRestService.getHouses("" + page);
    }

    public Call<CharacterRes> getCharacterByUrl(String url) {
        return mRestService.getCharacterByUrl(url);
    }

    private Call<CharacterRes> getCharacterById(Long id) {
        return mRestService.getCharacterById(id);
    }

    public Call<HouseRes> getHouseFromApiById(Long houseId) {
        return mRestService.getHouseById(houseId);
    }


    //endregion
    //============================Database===============

    public void saveBooksToDb() {
        Call<List<BookRes>> call = mRestService.getBooks();
        call.enqueue(new Callback<List<BookRes>>() {
            @Override
            public void onResponse(Call<List<BookRes>> call, Response<List<BookRes>> response) {
                List<Book> books = new ArrayList<Book>();
                for (BookRes bookRes : response.body()) {
                    books.add(new Book(bookRes));
                }
                mDaoSession.getBookDao().insertOrReplaceInTx(books);
            }

            @Override
            public void onFailure(Call<List<BookRes>> call, Throwable t) {
                Log.e(TAG, " books not added");
                getPreferencesManager().setDbComplete(false);
            }
        });
    }

    public void saveHouseToDb(final Long houseId) {
        Call<HouseRes> call = getHouseFromApiById(houseId);
        final List<String> members = new ArrayList<>();
        call.enqueue(new Callback<HouseRes>() {
            @Override
            public void onResponse(Call<HouseRes> call, Response<HouseRes> response) {
                members.addAll(response.body().getSwornMembers());
                mHouseDao.insertOrReplace(
                        new House(response.body().getUrl(), response.body().getName(), response.body().getWords()));

                for (String member : members) {
                    Long id = Long.parseLong(member.substring(member.lastIndexOf('/') + 1));
                    saveMemberOfHouseToDb(id, houseId);
                }
            }

            @Override
            public void onFailure(Call<HouseRes> call, Throwable t) {
                Log.e(TAG + " house", " " + houseId + " not added");
                getPreferencesManager().setDbComplete(false);
            }
        });
    }


    private class DbTask extends AsyncTask<Long, Void, Void> {
        @Override
        protected Void doInBackground(Long... longs) {
            for (final Long aLong : longs) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Call<HouseRes> call = getHouseFromApiById(aLong);
                        try {
                            HouseRes house = call.execute().body();
                            mHouseDao.insertOrReplace(new House(house.getUrl(), house.getName(), house.getWords()));

                            for (String member : house.getSwornMembers()) {
                                Long id = Long.parseLong(member.substring(member.lastIndexOf('/') + 1));
                                saveMemberOfHouseToDb(id, aLong);
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();
            }
            return null;
        }
    }

    private void saveMemberOfHouseToDb(final Long characterId, final Long houseId) {


        Call<CharacterRes> call = getCharacterById(characterId);

        call.enqueue(new Callback<CharacterRes>() {
            @Override
            public void onResponse(Call<CharacterRes> call, Response<CharacterRes> response) {
                SwornMember member = new SwornMember(response.body(), houseId);
                mMemberDao.insertOrReplace(member);
                List<String> aliasesString = response.body().getAliases();
                List<Alias> aliases = new ArrayList<Alias>();
                List<Title> titles = new ArrayList<Title>();
                for (String s : response.body().getTitles()) {
                    titles.add(new Title(s, member.getId()));
                }
                mDaoSession.getTitleDao().insertOrReplaceInTx(titles);
                for (String alias : aliasesString) {
                    aliases.add(new Alias(alias, member.getId()));
                }
                mDaoSession.getAliasDao().insertOrReplaceInTx(aliases);
                if (member.getFather() != null) {
                    if (loadCharacterByApiId(member.getFather()) == null) {
                        saveMemberOfHouseToDb(member.getFather(), houseId);
                    }
                }
                if (member.getMother() != null) {
                    if (loadCharacterByApiId(member.getMother()) == null) {
                        saveMemberOfHouseToDb(member.getMother(), houseId);
                    }
                }
            }

            @Override
            public void onFailure(Call<CharacterRes> call, Throwable t) {
                Log.e(TAG, "onFailure: Character" + characterId + "is not added.");
                getPreferencesManager().setDbComplete(false);
            }
        });

    }

    public List<SwornMember> loadMembersOfHouseFromDbByHouseId(Long houseId) {
        List<SwornMember> swornMembers = new ArrayList<>();
        try {
            swornMembers = mDaoSession.queryBuilder(SwornMember.class)
                    .where(SwornMemberDao.Properties.HouseRemoteId.eq(houseId))
                    .orderAsc(SwornMemberDao.Properties.Name)
                    .build()
                    .list();

        } catch (Exception e) {
            e.printStackTrace();
        }
//        return mMemberDao._queryHouse_SwornMembers(houseId);
        return swornMembers;
    }

    public SwornMember loadCharacterById(Long id) {
        return mMemberDao.load(id);
    }

    public SwornMember loadCharacterByApiId(Long id) {
        SwornMember swornMember = null;
        try {
            swornMember = mDaoSession.queryBuilder(SwornMember.class)
                    .where(SwornMemberDao.Properties.ApiId.eq(id))
                    .build()
                    .unique();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return swornMember;

    }


    //endregoion
}

