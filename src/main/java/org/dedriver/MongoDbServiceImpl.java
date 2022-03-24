package org.dedriver;

import com.mongodb.BasicDBObject;
import de.swingbe.ifleet.model.Entity;
import org.bson.Document;
import org.dedriver.utils.MongoDbAccess;
import org.dedriver.utils.MsgValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import static com.mongodb.client.model.Filters.eq;
import static org.dedriver.model.MsgDedeObu.*;

class MongoDbServiceImpl implements MongoDbService {

    private static final String COLLECTION_NAME_MSG_IVU_LCT = "ivulocationmsgs";

    private static final String COLLECTION_NAME_MSG_DEDE_OBU = "vehicles";

    private static final MongoDbAccess dbAccessIvuLct = new MongoDbAccess("mongodb://localhost:27017", "dede-server-test", COLLECTION_NAME_MSG_IVU_LCT);

    private static final MongoDbAccess dbAccessDedeObu = new MongoDbAccess("mongodb://localhost:27017", "dede-server-test", COLLECTION_NAME_MSG_DEDE_OBU);

    private final static Logger LOG = LoggerFactory.getLogger(MongoDbServiceImpl.class);

    public MongoDbServiceImpl() {
    }

    private static void insert(BasicDBObject dbObject, MongoDbAccess dbAccess, String key) {
        String value = dbObject.getString(key);

        //find document
        Document document = (Document) dbAccess.getCollection().find(eq(key, value)).first();

        if (document != null) {

            //update document
            BasicDBObject query = new BasicDBObject();
            query.put(key, value);

            BasicDBObject updateObject = new BasicDBObject();
            updateObject.put("$set", dbObject);

            dbAccess.getCollection().updateOne(query, updateObject);
        } else {

            //create and insert new document
            Document docNew = new Document(dbObject);
            dbAccess.getCollection().insertOne(docNew);
        }
    }

    private static String getTimeUnix(Entity entity) {
        //Instantiating the SimpleDateFormat class
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss,SSS");

        //Parsing the given String to Date object
        Date date = null;
        try {
            date = sdf.parse(entity.getDate() + entity.getTime());
        } catch (ParseException e) {
            LOG.error("ParseException detected, message: " + e.getMessage() + ", trace: " + Arrays.toString(e.getStackTrace()));
            e.printStackTrace();
        }

        //build Epoch time
        long timeUnix = date.getTime();
        String ts = Long.toString(timeUnix);
        return ts;
    }

    private static String formatCoordinate(String coordinate) {
        String coordinateFormed = coordinate;

        //remove tailing dot
        //match a dot when it is followed by a whitespace or the end of the string
        coordinateFormed = coordinateFormed.replaceAll("\\.(?=\\s|$)", "");

        //add decimal point
        if (coordinate.length() > 7) {
            coordinateFormed = new StringBuilder(coordinate).insert(coordinate.length() - 7, ".").toString();
        }
        return coordinateFormed;
    }

    @Override
    public void insertMsgIvuLct(Entity entity) {

        //TODO Is data validation required?

        //validate timeUnix
        String timeUnix = getTimeUnix(entity);
        if (!MsgValidation.isValidTs(timeUnix)) {
            LOG.info("timeUnix value must be greater than " + MsgValidation.TS_MIN);
            return;
        }


        //create document
        BasicDBObject dbObject = new BasicDBObject();
        dbObject.put("date", entity.getDate());
        dbObject.put("time", entity.getTime());
        dbObject.put("timeUnix", timeUnix);
        dbObject.put("logLevel", entity.getLogLevel());
        dbObject.put("addressPartA", entity.getAddressPartA());
        dbObject.put("addressPartB", entity.getAddressPartB());
        dbObject.put("peer", entity.getPeer());
        dbObject.put("addressNext", entity.getAddressNext());
        dbObject.put("direction", entity.getDirection());
        dbObject.put("senderType", entity.getCc().getHeader().getSender().getType());
        dbObject.put("senderId", entity.getCc().getHeader().getSender().getId());
        dbObject.put("receiverType", entity.getCc().getHeader().getReceiver().getType());
        dbObject.put("receiverId", entity.getCc().getHeader().getReceiver().getId());
        dbObject.put("teleType", entity.getCc().getTelegram().getTeleHeader().getTeleType());
        dbObject.put("teleVersion", entity.getCc().getTelegram().getTeleHeader().getTeleVersion());
        dbObject.put("teleId", entity.getCc().getTelegram().getTeleHeader().getTeleId());
        dbObject.put("netPoint", entity.getCc().getTelegram().getLocationMessage().getPosition().getNetPoint());
        dbObject.put("relPosition", entity.getCc().getTelegram().getLocationMessage().getPosition().getRelPosition());
        dbObject.put("longitude", entity.getCc().getTelegram().getLocationMessage().getPosition().getLongitude());
        dbObject.put("latitude", entity.getCc().getTelegram().getLocationMessage().getPosition().getLatitude());
        dbObject.put("offRoute", entity.getCc().getTelegram().getLocationMessage().getPosition().getOffRoute());
        dbObject.put("velocity", entity.getCc().getTelegram().getLocationMessage().getPosition().getVelocity());
        dbObject.put("heading", entity.getCc().getTelegram().getLocationMessage().getPosition().getHeading());
        dbObject.put("driverNumber", entity.getCc().getTelegram().getLocationMessage().getDriverNumber());
        dbObject.put("blockNo", entity.getCc().getTelegram().getLocationMessage().getTrip().getBlockNo());
        dbObject.put("lineNo", entity.getCc().getTelegram().getLocationMessage().getTrip().getLineNo());
        dbObject.put("tripNo", entity.getCc().getTelegram().getLocationMessage().getTrip().getTripNo());
        dbObject.put("routeNo", entity.getCc().getTelegram().getLocationMessage().getTrip().getRouteNo());
        dbObject.put("deviation", entity.getCc().getTelegram().getLocationMessage().getTrip().getDeviation());
        dbObject.put("loadDegree", entity.getCc().getTelegram().getLocationMessage().getTrip().getLoadDegree());
        dbObject.put("destinationNo", entity.getCc().getTelegram().getLocationMessage().getTrip().getDestinationNo());
        dbObject.put("tripType", entity.getCc().getTelegram().getLocationMessage().getTrip().getTripType());

        //insert document into collection
        if (dbAccessIvuLct.getCollection() != null) {
            insert(dbObject, dbAccessIvuLct, "senderId");
        } else {
            LOG.error("collection unavailable: " + COLLECTION_NAME_MSG_IVU_LCT);
        }
    }

    @Override
    public void insertMsgDedeObu(Entity entity) {

        //TODO create Validator class

        //validate id
        String id = entity.getCc().getHeader().getSender().getId();
        if (!isValidId(id)) {
            LOG.info("id length must be greater than " + ID_MIN);
            return;
        }

        //validate coordinates
        //validate lat
        String lat = formatCoordinate(entity.getCc().getTelegram().getLocationMessage().getPosition().getLatitude());
        if (!isValidLat(lat)) {
            LOG.info("lat value must be between " + LAT_MIN + " and " + LAT_MAX);
            return;
        }
        //validate lon
        String lon = formatCoordinate(entity.getCc().getTelegram().getLocationMessage().getPosition().getLongitude());
        if (!isValidLon(lon)) {
            LOG.info("lon value must be between " + LON_MIN + " and " + LON_MAX);
            return;
        }

        //validate ts
        String ts = getTimeUnix(entity);
        if (!MsgValidation.isValidTs(ts)) {
            LOG.info("ts value must be greater than " + MsgValidation.TS_MIN);
            return;
        }

        //validate alias
        String alias = entity.getCc().getHeader().getSender().getId();
        if (alias == null) {
            alias = "";
        }

        //validate tripId
        String tripId = entity.getCc().getTelegram().getLocationMessage().getTrip().getTripNo();
        if (tripId == null) {
            tripId = "";
        }

        //validate lineNo
        String lineNo = entity.getCc().getTelegram().getLocationMessage().getTrip().getLineNo();
        if (lineNo == null) {
            lineNo = "";
        }

        //validate label
        String label = entity.getCc().getTelegram().getLocationMessage().getDriverNumber();
        if (label == null) {
            label = "";
        }

        //create document
        BasicDBObject dbObject = new BasicDBObject();
        dbObject.put("uuid", id);
        dbObject.put("lat", lat);
        dbObject.put("lon", lon);
        dbObject.put("ts", ts);
        dbObject.put("alias", alias);
        dbObject.put("tripId", tripId);
        dbObject.put("lineNo", lineNo);
        dbObject.put("label", label);

        //insert document into collection
        if (dbAccessDedeObu.getCollection() != null) {
            insert(dbObject, dbAccessDedeObu, "uuid");
        } else {
            LOG.error("collection unavailable: " + COLLECTION_NAME_MSG_DEDE_OBU);
        }
    }
}
