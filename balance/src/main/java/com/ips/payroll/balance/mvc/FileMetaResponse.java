package com.ips.payroll.balance.mvc;

import com.ips.payroll.balance.model.ReportItem;

import java.util.LinkedList;

/**
 * Created by acardenas on 6/26/14.
 */
public class FileMetaResponse
{
    private LinkedList<FileMeta> fileMetas;
    private String downloadName;
    private LinkedList<ReportItem> reportItems;

    public LinkedList<FileMeta> getFileMetas()
    {
        if (fileMetas == null)
        {
            fileMetas = new LinkedList<FileMeta>();
        }
        return fileMetas;
    }

    public void setFileMetas(LinkedList<FileMeta> aFileMetas)
    {
        fileMetas = aFileMetas;
    }

    public String getDownloadName()
    {
        return downloadName;
    }

    public void setDownloadName(String aDownloadName)
    {
        this.downloadName = aDownloadName;
    }

    public LinkedList<ReportItem> getReportItems()
    {
        if (reportItems == null)
        {
            reportItems = new LinkedList<ReportItem>();
        }
        return reportItems;
    }

    public void setReportItems(LinkedList<ReportItem> aReportItems)
    {
        this.reportItems = aReportItems;
    }
}
