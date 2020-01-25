package com.ewoxej.okdoc;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

public class DataStorage {
    static class DocField
    {
        public String key;
        public String value;
    }
    public static class DocEntry
    {
        public List<DocField> fields;
        public List<Bitmap> images;
        public String name;
        public DocEntry()
        {
            fields = new ArrayList<DocField>();
            images = new ArrayList<Bitmap>();
        }
    }
    private static DataStorage instance;
    private DataStorage()
    {
        docs = new ArrayList<DocEntry>();
    }
    public static DataStorage get()
    {
        if(instance == null)
            instance = new DataStorage();
        return instance;
    }
    private List<DocEntry> docs;
    public List<DocEntry> getDocs()
    {
        return docs;
    }
}
