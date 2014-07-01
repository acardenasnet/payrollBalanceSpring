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

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getFileSize()
    {
        return fileSize;
    }

    public void setFileSize(String fileSize)
    {
        this.fileSize = fileSize;
    }

    public String getFileType()
    {
        return fileType;
    }

    public void setFileType(String fileType)
    {
        this.fileType = fileType;
    }

    public boolean isSuccess()
    {
        return success;
    }

    public void setSuccess(boolean success)
    {
        this.success = success;
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