package main.model;

public class CorruptOfficer {
    private String name;
    private String position;
    private String corruptionCase;
    private String imagePath;
    
    public CorruptOfficer(String name, String position, String corruptionCase, String imagePath) {
        this.name = name;
        this.position = position;
        this.corruptionCase = corruptionCase;
        this.imagePath = imagePath;
    }

    public String getName() { return name; }
    public String getPosition() { return position; }
    public String getCorruptionCase() { return corruptionCase; }
    public String getImagePath() { return imagePath; }
}