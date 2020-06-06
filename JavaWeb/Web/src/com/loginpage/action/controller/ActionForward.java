package com.loginpage.action.controller;

public class ActionForward
{
    private Boolean isRedirect = false;
    private String path = null;

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
