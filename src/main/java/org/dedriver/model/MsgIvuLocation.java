package org.dedriver.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MsgIvuLocation {

    private final static Logger LOG = LoggerFactory.getLogger(MsgIvuLocation.class);

    //entity
    private final String date;
    private final String time;
    private final String logLevel;
    private final String addressPartA;
    private final String addressPartB;
    private final String peer;
    private final String addressNext;
    private final String direction;
    //communication
    //header
    //sender
    private final String sender;
    //receiver
    private final String receiver;
    //telegram
    //telegram hdr
    private final String teleType;
    private final String teleVersion;
    private final String teleId;
    //location msg
    //position
    private final String netPoint;
    private final String relPosition;
    private final String longitude;
    private final String latitude;
    private final String offRoute;
    private final String velocity;
    private final String heading;
    //driver number
    private final String driverNumber;
    //trip
    private final String blockNo;
    private final String lineNo;
    private final String tripNo;
    private final String routeNo;
    private final String deviation;
    private final String loadDegree;
    private final String destinationNo;
    private final String tripType;

    public MsgIvuLocation(String date, String time, String logLevel, String addressPartA, String addressPartB, String peer, String addressNext, String direction, String sender, String receiver, String teleType, String teleVersion, String teleId, String netPoint, String relPosition, String longitude, String latitude, String offRoute, String velocity, String heading, String driverNumber, String blockNo, String lineNo, String tripNo, String routeNo, String deviation, String loadDegree, String destinationNo, String tripType) {
        if (!isValidString(date)) {
            throw new IllegalArgumentException("string date invalid");
        }
        this.date = date;
        if (!isValidString(time)) {
            throw new IllegalArgumentException("string time invalid");
        }
        this.time = time;
        if (!isValidString(logLevel)) {
            throw new IllegalArgumentException("string logLevel invalid");
        }
        this.logLevel = logLevel;
        if (!isValidString(addressPartA)) {
            throw new IllegalArgumentException("string addressPartA invalid");
        }
        this.addressPartA = addressPartA;
        if (!isValidString(addressPartB)) {
            throw new IllegalArgumentException("string addressPartB invalid");
        }
        this.addressPartB = addressPartB;
        if (!isValidString(peer)) {
            throw new IllegalArgumentException("string peer invalid");
        }
        this.peer = peer;
        if (!isValidString(addressNext)) {
            throw new IllegalArgumentException("string addressNext invalid");
        }
        this.addressNext = addressNext;
        if (!isValidString(direction)) {
            throw new IllegalArgumentException("string direction invalid");
        }
        this.direction = direction;
        if (!isValidString(sender)) {
            throw new IllegalArgumentException("string sender invalid");
        }
        this.sender = sender;
        if (!isValidString(receiver)) {
            throw new IllegalArgumentException("string receiver invalid");
        }
        this.receiver = receiver;
        if (!isValidString(teleType)) {
            throw new IllegalArgumentException("string teleType invalid");
        }
        this.teleType = teleType;
        if (!isValidString(teleVersion)) {
            throw new IllegalArgumentException("string teleVersion invalid");
        }
        this.teleVersion = teleVersion;
        if (!isValidString(teleId)) {
            throw new IllegalArgumentException("string teleId invalid");
        }
        this.teleId = teleId;
        if (!isValidString(netPoint)) {
            throw new IllegalArgumentException("string netPoint invalid");
        }
        this.netPoint = netPoint;
        if (!isValidString(relPosition)) {
            throw new IllegalArgumentException("string relPosition invalid");
        }
        this.relPosition = relPosition;
        if (!isValidString(longitude)) {
            throw new IllegalArgumentException("string longitude invalid");
        }
        this.longitude = longitude;
        if (!isValidString(latitude)) {
            throw new IllegalArgumentException("string latitude invalid");
        }
        this.latitude = latitude;
        if (!isValidString(offRoute)) {
            throw new IllegalArgumentException("string offRoute invalid");
        }
        this.offRoute = offRoute;
        if (!isValidString(velocity)) {
            throw new IllegalArgumentException("string velocity invalid");
        }
        this.velocity = velocity;
        if (!isValidString(heading)) {
            throw new IllegalArgumentException("string heading invalid");
        }
        this.heading = heading;
        if (!isValidString(driverNumber)) {
            throw new IllegalArgumentException("string driverNumber invalid");
        }
        this.driverNumber = driverNumber;
        if (!isValidString(blockNo)) {
            throw new IllegalArgumentException("string blockNo invalid");
        }
        this.blockNo = blockNo;
        if (!isValidString(lineNo)) {
            throw new IllegalArgumentException("string lineNo invalid");
        }
        this.lineNo = lineNo;
        if (!isValidString(tripNo)) {
            throw new IllegalArgumentException("string tripNo invalid");
        }
        this.tripNo = tripNo;
        if (!isValidString(routeNo)) {
            throw new IllegalArgumentException("string routeNo invalid");
        }
        this.routeNo = routeNo;
        if (!isValidString(deviation)) {
            throw new IllegalArgumentException("string deviation invalid");
        }
        this.deviation = deviation;
        if (!isValidString(loadDegree)) {
            throw new IllegalArgumentException("string loadDegree invalid");
        }
        this.loadDegree = loadDegree;
        if (!isValidString(destinationNo)) {
            throw new IllegalArgumentException("string destinationNo invalid");
        }
        this.destinationNo = destinationNo;
        if (!isValidString(tripType)) {
            throw new IllegalArgumentException("string tripType invalid");
        }
        this.tripType = tripType;
    }

    /**
     * validate input of type {@link String}
     *
     * @param input identifier to be validated
     * @return true if valid; false otherwise
     */
    public static boolean isValidString(String input) {
        if (input != null) {
            return true;
        }
        return false;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public String getAddressPartA() {
        return addressPartA;
    }

    public String getAddressPartB() {
        return addressPartB;
    }

    public String getPeer() {
        return peer;
    }

    public String getAddressNext() {
        return addressNext;
    }

    public String getDirection() {
        return direction;
    }

    public String getSender() {
        return sender;
    }

    public String getReceriver() {
        return receiver;
    }

    public String getTeleType() {
        return teleType;
    }

    public String getTeleVersion() {
        return teleVersion;
    }

    public String getTeleId() {
        return teleId;
    }

    public String getNetPoint() {
        return netPoint;
    }

    public String getRelPosition() {
        return relPosition;
    }

    public String getLongiture() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getOffRoute() {
        return offRoute;
    }

    public String getVelocity() {
        return velocity;
    }

    public String getHeading() {
        return heading;
    }

    public String getDriverNumber() {
        return driverNumber;
    }

    public String getBlockNo() {
        return blockNo;
    }

    public String getLineNo() {
        return lineNo;
    }

    public String getTripNo() {
        return tripNo;
    }

    public String getRouteNo() {
        return routeNo;
    }

    public String getDeviation() {
        return deviation;
    }

    public String getLoadDegree() {
        return loadDegree;
    }

    public String getDestinationNo() {
        return destinationNo;
    }

    public String getTripType() {
        return tripType;
    }

    @Override
    public String toString() {
        return "MsgIvuLocation{" +
                "date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", logLevel='" + logLevel + '\'' +
                ", addressPartA='" + addressPartA + '\'' +
                ", addressPartB='" + addressPartB + '\'' +
                ", peer='" + peer + '\'' +
                ", addressNext='" + addressNext + '\'' +
                ", direction='" + direction + '\'' +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", teleType='" + teleType + '\'' +
                ", teleVersion='" + teleVersion + '\'' +
                ", teleId='" + teleId + '\'' +
                ", netPoint='" + netPoint + '\'' +
                ", relPosition='" + relPosition + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", offRoute='" + offRoute + '\'' +
                ", velocity='" + velocity + '\'' +
                ", heading='" + heading + '\'' +
                ", driverNumber='" + driverNumber + '\'' +
                ", blockNo='" + blockNo + '\'' +
                ", lineNo='" + lineNo + '\'' +
                ", tripNo='" + tripNo + '\'' +
                ", routeNo='" + routeNo + '\'' +
                ", deviation='" + deviation + '\'' +
                ", loadDegree='" + loadDegree + '\'' +
                ", destinationNo='" + destinationNo + '\'' +
                ", tripType='" + tripType + '\'' +
                '}';
    }
}
