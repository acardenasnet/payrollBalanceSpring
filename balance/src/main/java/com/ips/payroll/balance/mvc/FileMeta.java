package com.ips.payroll.balance.mvc;

public class FileMeta
{

    private String fileName;
    private String fileSize;
    private String fileType;
    private boolean success;

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String aFileName)
    {
        fileName = aFileName;
    }

    public String getFileSize()
    {
        return fileSize;
    }

    public void setFileSize(String aFileSize)
    {
        fileSize = aFileSize;
    }

    public String getFileType()
    {
        return fileType;
    }

    public void setFileType(String aFfileType)
    {
        fileType = aFfileType;
    }

    public boolean isSuccess()
    {
        return success;
    }

    public void setSuccess(boolean isSuccess)
    {
        success = isSuccess;
    }

    @Override
    public String toString()
    {
        StringBuilder myStringBuilder = new StringBuilder();
        myStringBuilder.append("[File Name = ").append(getFileName())
                .append(", valid = ").append(isSuccess())
                .append("]");

        return myStringBuilder.toString();
    }
}