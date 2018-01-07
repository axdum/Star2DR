package mob.star2dr.model;

/**
 * Created by User_1 on 05/12/2017.
 */

public class Trips {
    private String route_id;
    private String service_id;
    private String trip_headsign;
    private String direction_id;
    private String Bblock_id;
    private String Wwheelchair_accessible;

    public Trips(String route_id, String service_id, String trip_headsign, String direction_id, String bblock_id, String wwheelchair_accessible) {
        this.route_id = route_id;
        this.service_id = service_id;
        this.trip_headsign = trip_headsign;
        this.direction_id = direction_id;
        Bblock_id = bblock_id;
        Wwheelchair_accessible = wwheelchair_accessible;
    }

    public String getRoute_id() {
        return route_id;
    }

    public void setRoute_id(String route_id) {
        this.route_id = route_id;
    }

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public String getTrip_headsign() {
        return trip_headsign;
    }

    public void setTrip_headsign(String trip_headsign) {
        this.trip_headsign = trip_headsign;
    }

    public String getDirection_id() {
        return direction_id;
    }

    public void setDirection_id(String direction_id) {
        this.direction_id = direction_id;
    }

    public String getBblock_id() {
        return Bblock_id;
    }

    public void setBblock_id(String bblock_id) {
        Bblock_id = bblock_id;
    }

    public String getWwheelchair_accessible() {
        return Wwheelchair_accessible;
    }

    public void setWwheelchair_accessible(String wwheelchair_accessible) {
        Wwheelchair_accessible = wwheelchair_accessible;
    }
}
