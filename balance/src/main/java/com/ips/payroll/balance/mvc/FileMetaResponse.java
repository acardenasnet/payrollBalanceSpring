package com.ips.payroll.balance.mvc;

import java.util.LinkedList;

/**
 * Created by acardenas on 6/26/14.
 */
public class FileMetaResponse
{
    private LinkedList<FileMeta> fileMetas;
    private String downloadName;

    public LinkedList<FileMeta> getFileMetas()
    {
        if(fileMetas == null)
        {
            fileMetas = new LinkedList<FileMeta>();
        }
        return fileMetas;
    }

    public void setFileMetas(LinkedList<FileMeta> fileMetas)
    {
        this.fileMetas = fileMetas;
    }

    public String getDownloadName()
    {
        return downloadName;
    }

    public void setDownloadName(String downloadName)
    {
        this.downloadName = downloadName;
    }
}
