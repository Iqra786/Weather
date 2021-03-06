
package com.ma.weather.weatherupdate.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Query {

    @SerializedName("query")
    @Expose
    private Query_ query;

    /**
     * 
     * @return
     *     The query
     */
    public Query_ getQuery() {
        return query;
    }

    /**
     * 
     * @param query
     *     The query
     */
    public void setQuery(Query_ query) {
        this.query = query;
    }

}
