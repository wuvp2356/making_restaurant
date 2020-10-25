package com.example.making_restaurant.infrastructure;

import java.time.LocalDate;

import java.awt.Image;
import java.awt.image.BufferedImage;

public class RamenImage {
    public String filePath;
    public String fileName;
    public String lastModified;

    public RamenImage(String fileName ,String lastModified) {
        this.fileName = fileName;
        this.lastModified = lastModified;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath= filePath;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName= fileName;
    }

    public String getLastModified() {
        return this.lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public static BufferedImage reSize1(BufferedImage image, int width, int height) {
        BufferedImage thumb = new BufferedImage(width, height, image.getType());
        thumb.getGraphics().drawImage(image.getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING), 0, 0, width, height, null);
        return thumb;
    }
}