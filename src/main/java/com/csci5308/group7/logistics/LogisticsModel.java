/**
 * @author Parth Shah
 */
package com.csci5308.group7.logistics;

import com.csci5308.group7.database.*;
import com.csci5308.group7.logistics.interfaces.ILogisticsModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LogisticsModel implements ILogisticsModel {

    private static LogisticsModel singleInstance = null;
    private final IDBConnection dbConnection = DBConnection.instance();
    private final String COLUMNS_INSERT = "payloadType, pnr, weight, baggageType, checkinCounterId";

    public static ILogisticsModel instance() {
        if (null == singleInstance) {
            singleInstance = new LogisticsModel();
        }
        return singleInstance;
    }

    @Override
    public List<HashMap<String, Object>> getCheckpoints(String type) {
        Connection connection = dbConnection.createConnection();
        List<HashMap<String, Object>> counterList = new ArrayList<>();
        PreparedStatement preparedStatement = null;

        try {
            String counterQuery = "SELECT * FROM checkpoints WHERE type = '" + type + "'";
            preparedStatement = connection.prepareStatement(counterQuery);

            ResultSet result = preparedStatement.executeQuery(counterQuery);

            while (result.next()) {
                HashMap<String, Object> counter = new HashMap<>();

                counter.put("id", result.getInt("id"));
                counter.put("xcoordinate", result.getInt("xcoordinate"));
                counter.put("ycoordinate", result.getInt("ycoordinate"));
                counter.put("operator", result.getString("operator"));
                counter.put("operatorContact", result.getString("operatorContact"));
                counter.put("publicIdentifier", result.getString("publicIdentifier"));

                counterList.add(counter);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(preparedStatement != null) {
                    preparedStatement.close();
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return counterList;
    }

    @Override
    public List<Integer> checkinPassengerBaggage(JSONObject request) {
        Connection connection = dbConnection.createConnection();
        List<Integer> baggageIds = new ArrayList<>();
        PreparedStatement preparedStatement = null;

        try{
            JSONArray baggageList = request.getJSONArray("bags");

            for(int i = 0; i < baggageList.length(); i++){
                JSONObject baggage = baggageList.getJSONObject(i);

                String checkinQuery = "INSERT INTO checkedBaggage (" + COLUMNS_INSERT + ") VALUES (?, ?, ?, ?, ?)";
                preparedStatement = connection.prepareStatement(checkinQuery, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, request.getString("type"));
                preparedStatement.setString(2, request.getString("pnr"));
                preparedStatement.setInt(3, baggage.getInt("weight"));
                preparedStatement.setString(4, baggage.getString("type"));
                preparedStatement.setInt(5, request.getInt("counterId"));

                preparedStatement.executeUpdate();

                ResultSet keys = preparedStatement.getGeneratedKeys();

                if(keys.next()){
                    baggageIds.add(keys.getInt(1));
                }
            }

        } catch(SQLException | JSONException e){
            e.printStackTrace();
        } finally {
            try {
                if(preparedStatement != null) {
                    preparedStatement.close();
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return baggageIds;
    }

    public void updateBaggageStatus (CheckpointType type, int baggageId) {
        Connection connection = dbConnection.createConnection();
        PreparedStatement preparedStatement = null;

        try{
            String updateQuery = "UPDATE checkedBaggage SET state = ? WHERE baggageId = ?";
            preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, type.toString());
            preparedStatement.setInt(2, baggageId);
            preparedStatement.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        } finally {
            try {
                if(preparedStatement != null) {
                    preparedStatement.close();
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public CheckpointType getBaggageState(int baggageId) {
        Connection connection = dbConnection.createConnection();
        PreparedStatement preparedStatement = null;

        try{
            String query = "SELECT state FROM checkedBaggage WHERE baggageId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, baggageId);
            ResultSet result = preparedStatement.executeQuery();

            if(result.next()){
                return CheckpointType.valueOf(result.getString("state"));
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
