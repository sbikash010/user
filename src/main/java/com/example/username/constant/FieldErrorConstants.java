package com.example.username.constant;

public class FieldErrorConstants {
    public static final String FILE_SEPARATOR = System.getProperty("file.separator");
    public static final String PRESENT_DIR = System.getProperty("user.dir");
    public static final String NOT_NULL = "notNUll";
    public static final String MIN_VALUE = "minValue";
    public static final String UPLOAD_DIR = new StringBuilder().append(PRESENT_DIR).append( FILE_SEPARATOR).
            append("user").append( FieldErrorConstants.FILE_SEPARATOR).append("jpg").
            append( FieldErrorConstants.FILE_SEPARATOR).toString();
}
