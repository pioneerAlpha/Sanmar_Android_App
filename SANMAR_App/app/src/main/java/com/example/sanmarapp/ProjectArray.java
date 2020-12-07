package com.example.sanmarapp;

public class ProjectArray {
    int projectImage;
    String projectName;
    String projectAddress;
    String projectCategory;

    public ProjectArray(int projectImage, String projectName, String projectAddress, String projectCategory) {
        this.projectImage = projectImage;
        this.projectName = projectName;
        this.projectAddress = projectAddress;
        this.projectCategory = projectCategory;
    }

    public int getProjectImage() {
        return projectImage;
    }

    public void setProjectImage(int projectImage) {
        this.projectImage = projectImage;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectAddress() {
        return projectAddress;
    }

    public void setProjectAddress(String projectAddress) {
        this.projectAddress = projectAddress;
    }

    public String getProjectCategory() {
        return projectCategory;
    }

    public void setProjectCategory(String projectCategory) {
        this.projectCategory = projectCategory;
    }
}
