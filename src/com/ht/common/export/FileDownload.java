package com.ht.common.export;

import org.springframework.stereotype.Controller;  
import org.springframework.web.context.ServletContextAware;  
  

import com.opensymphony.xwork2.ActionSupport;

import javax.servlet.ServletContext;  

import java.io.*;  
  
@Controller  
 
public class FileDownload extends ActionSupport implements ServletContextAware
{
    private static final long    serialVersionUID    = 620526993016670680L;

    private ServletContext        context;

    @Override
    public void setServletContext(ServletContext context)
    {
        this.context = context;
    }

    public InputStream getFileDownload()
    {
        return context.getResourceAsStream("/download/10.JPG");
    }

    @Override
    public String execute()
    {
        return SUCCESS;
    }
}
