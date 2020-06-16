package com.news.db.vo;

public class NewsBean
{
    private String hash;
    private String json_data;
    private int visit_count;

    public String getHash()
    {
        return hash;
    }

    public void setHash(String hash)
    {
        this.hash = hash;
    }

    public String getJson_data()
    {
        return json_data;
    }

    public void setJson_data(String json_data)
    {
        this.json_data = json_data;
    }

    public int getVisit_count()
    {
        return visit_count;
    }

    public void setVisit_count(int visit_count)
    {
        this.visit_count = visit_count;
    }
}
