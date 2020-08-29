package Model;

public enum SHIP {
    BLUE("ViewManager/resource/ShipChooser/playerShip3_blue.png", "ViewManager/resource/Laser/laserBlue03.png"),
    GREEN("ViewManager/resource/ShipChooser/playerShip1_green.png", "ViewManager/resource/Laser/laserGreen.png"),
    ORANGE("ViewManager/resource/ShipChooser/playerShip2_orange.png", "ViewManager/resource/Laser/laserRed03.png"),
    RED("ViewManager/resource/ShipChooser/playerShip2_red.png", "ViewManager/resource/Laser/laserRed03.png");

    private final String urlShip;
    private final String urlLaser;
    SHIP(String urlShip, String urlLaser) {
        this.urlShip = urlShip;
        this.urlLaser = urlLaser;
    }

    public String getUrlShip() {
        return urlShip;
    }
    public String getUrlLaser(){
        return urlLaser;
    }
}
