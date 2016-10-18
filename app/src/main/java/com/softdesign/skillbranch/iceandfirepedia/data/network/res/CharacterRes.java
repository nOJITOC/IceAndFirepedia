package com.softdesign.skillbranch.iceandfirepedia.data.network.res;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Иван on 14.10.2016.
 */
public class CharacterRes {

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("born")
    @Expose
    private String born;
    @SerializedName("died")
    @Expose
    private String died;
    @SerializedName("titles")
    @Expose
    private List<String> titles = new ArrayList<String>();
    @SerializedName("aliases")
    @Expose
    private List<String> aliases = new ArrayList<String>();
    @SerializedName("father")
    @Expose
    private String father;
    @SerializedName("mother")
    @Expose
    private String mother;

    @SerializedName("books")
    @Expose
    private List<String> books;

    public List<String> getBooks() {
        return books;
    }

    /**
     *
     * @return
     * The url
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     * The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The born
     */
    public String getBorn() {
        return born;
    }

    /**
     *
     * @param born
     * The born
     */
    public void setBorn(String born) {
        this.born = born;
    }

    /**
     *
     * @return
     * The died
     */
    public String getDied() {
        return died;
    }

    /**
     *
     * @param died
     * The died
     */
    public void setDied(String died) {
        this.died = died;
    }

    /**
     *
     * @return
     * The titles
     */
    public List<String> getTitles() {
        return titles;
    }

    /**
     *
     * @param titles
     * The titles
     */
    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    /**
     *
     * @return
     * The aliases
     */
    public List<String> getAliases() {
        return aliases;
    }

    /**
     *
     * @param aliases
     * The aliases
     */
    public void setAliases(List<String> aliases) {
        this.aliases = aliases;
    }

    /**
     *
     * @return
     * The father
     */
    public String getFather() {
        return father;
    }

    /**
     *
     * @param father
     * The father
     */
    public void setFather(String father) {
        this.father = father;
    }

    /**
     *
     * @return
     * The mother
     */
    public String getMother() {
        return mother;
    }

    /**
     *
     * @param mother
     * The mother
     */
    public void setMother(String mother) {
        this.mother = mother;
    }
}
