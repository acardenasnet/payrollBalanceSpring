package com.ips.payroll.balance.mvc;

import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.ips.payroll.balance.model.ReportItem;

@RunWith(MockitoJUnitRunner.class)
public class FileMetaResponseTest
{
    private FileMetaResponse fileMetaResponse; 
    
    @Mock
    private LinkedList<FileMeta> mockFileMetas;
    
    @Mock
    private LinkedList<ReportItem> mockReportItems;
    
    @Before
    public void setup()
    {
        fileMetaResponse = new FileMetaResponse();
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void nullsValues()
    {
        Assert.assertNotNull(fileMetaResponse.getFileMetas());
        Assert.assertNotNull(fileMetaResponse.getReportItems());
    }
    
    @Test
    public void assignAndRetrieveValues()
    {
        Mockito.when(mockFileMetas.size()).thenReturn(1);
        fileMetaResponse.setFileMetas(mockFileMetas);
        Assert.assertNotNull(fileMetaResponse.getFileMetas());
        Assert.assertNotNull(fileMetaResponse.getReportItems());
        Assert.assertEquals(1, fileMetaResponse.getFileMetas().size());
    }
    
    @Test(expected = IllegalStateException.class)
    public void assignAndRetrieveValuesException()
    {
        Mockito.when(mockFileMetas.size()).thenThrow(new IllegalStateException());
        Mockito.when(mockReportItems.size()).thenReturn(1,100,42);
        fileMetaResponse.setFileMetas(mockFileMetas);
        fileMetaResponse.setReportItems(mockReportItems);
        
        Assert.assertNotNull(fileMetaResponse.getFileMetas());
        Assert.assertNotNull(fileMetaResponse.getReportItems());
        Assert.assertEquals(1, fileMetaResponse.getReportItems().size());
        Assert.assertEquals(100, fileMetaResponse.getReportItems().size());
        Assert.assertEquals(42, fileMetaResponse.getReportItems().size());
        Assert.assertEquals(1, fileMetaResponse.getFileMetas().size());
    }
}
