package com.softdesign.skillbranch.iceandfirepedia.data.network;




import com.softdesign.skillbranch.iceandfirepedia.data.network.res.BookRes;
import com.softdesign.skillbranch.iceandfirepedia.data.network.res.CharacterRes;
import com.softdesign.skillbranch.iceandfirepedia.data.network.res.HouseRes;
import com.softdesign.skillbranch.iceandfirepedia.utils.AppConfig;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RestService {


    @GET("houses?pageSize=50&page={page}")
    Call<HouseRes> getHouses(@Path("page") String page);


    @GET("houses/{id}")
    Call<HouseRes> getHouseById(@Path(("id")) Long id);
    @GET("characters/{id}")
    Call<CharacterRes> getCharacterById(@Path("id") Long id);
    @GET("{url}")
    Call<CharacterRes> getCharacterByUrl(@Path("url") String url);
    @GET("books?pageSize=50")
    Call<List<BookRes>> getBooks();
}
