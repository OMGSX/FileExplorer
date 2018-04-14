package com.example.krist.fileexplorergame;

import java.util.ArrayList;

public class FilePOJO {

    private String fileName;
    private String detail;
    private String fileImage;
    private ArrayList<FilePOJO> filePojo = new ArrayList<>();

    public String getDetail()
    { return detail; }

    public void setDetail(String detail)
    {
        this.detail = detail;
    }


    public String getFileName()
    {
        return fileName;
    }


    public void setFileName(String fileName)
    { this.fileName =  fileName; }

    public void setFileImage(String fileImage)
    {
        this.fileImage = fileImage;
    }

    public String getFileImage()
    {
        return fileImage;
    }

}
