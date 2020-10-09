package sample.Controllers;

public class Position {


    private double course;
    private double longitude;
    private double latitude;
    private String finLong;
    private String finLat;
    private double distance;
    private int number;

    public Position() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getDistance() {
        return distance;
    }

    public void setCourse(double course) {
        this.course = course;
    }

    public double getCourse() {
        return course;
    }

    public void setFinLong(String finLong) {
        this.finLong = finLong;
    }

    public void setFinLat(String finLat) {
        this.finLat = finLat;
    }

    public String getFinLong() {
        return finLong;
    }

    public String getFinLat() {
        return finLat;
    }


    public double getLongitude() {

        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


    public void setLatitude(double latitude) {

        this.latitude = latitude;
    }


}
