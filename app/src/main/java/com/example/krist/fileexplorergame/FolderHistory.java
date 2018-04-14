package com.example.krist.fileexplorergame;

import android.os.Environment;

import java.io.File;
import java.util.Stack;

public class FolderHistory {

    Stack<String> folderHistory;

    public FolderHistory()
    {
        folderHistory = new Stack<>();
    }

    public Stack<String> getHistory()
    {
        File musicFilePath = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_MUSIC);
        folderHistory.add(musicFilePath.toString());
        return folderHistory;
    }
}