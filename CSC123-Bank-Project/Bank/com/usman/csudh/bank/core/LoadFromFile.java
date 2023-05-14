package com.usman.csudh.bank.core;
import java.io.*;

public class LoadFromFile extends Load {
    public InputStream getInputStream() throws IOException {
        return new FileInputStream("exchange-rate.csv");
    }
}