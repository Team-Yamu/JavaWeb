package com.openwordbookPage.action.controller;

public class ActionForward
{
    private Boolean isRedirect = false;
    private String path = null;

    public ActionForward()
    {
        
    }

    public ActionForward(Boolean isRedirect, String path)
    {
        this.isRedirect = isRedirect;
        this.path = path;
    }

    public Boolean isRedirect()
    {
        return isRedirect;
    }

    public void setRedirect(Boolean redirect)
    {
        isRedirect = redirect;
    }

    public String getPath()
    {
        return path;
    }

    public void setPath(String path)
    {
        this.path = path;
    }
}
