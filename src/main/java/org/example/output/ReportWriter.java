package org.example.output;

import org.example.database.DB;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;


public class ReportWriter {

    FileWriter fileWriter;
    LocalDateTime localDateTime;

    public ReportWriter() throws IOException, SQLException {
        String path = "src/main/resources/missionreports/";
        localDateTime = LocalDateTime.now();
        String fileName = "missionReport-" + localDateTime;
        File file = new File(path + fileName);
        fileWriter = new FileWriter(file);

        createFile();
        reportVehicles();
        reportInstructions();
        reportResources();
        fileWriter.close();
    }

    private void createFile() throws IOException {
        fileWriter.write("Mission Successful -" + localDateTime + "\n");
        fileWriter.append("-------------------------------------------------\n");
    }

    private void reportVehicles() throws SQLException, IOException {
        ResultSet vehicleResults = DB.getVehicleDetails();
        fileWriter.append("VehicleId  \t\tVehicleType\n");
        while(vehicleResults.next()) {
            int id = vehicleResults.getInt(1);
            String type = vehicleResults.getString(2);
            fileWriter.append("VehicleID: ").append(String.valueOf(id)).append("\t").append("VehicleType: ").append(type).append("\n");
        }
        fileWriter.append("\n\n");
    }

    private void reportInstructions() throws SQLException, IOException {
        ResultSet instructionResults = DB.getInstructionDetails();
        fileWriter.append("InstructionInstance  \tVehicleId  \t\tInstruction\n");
        while(instructionResults.next()) {
            int id = instructionResults.getInt(1);
            int vehicleId = instructionResults.getInt(2);
            String instruction = instructionResults.getString(3);
            fileWriter.append("InstructionID: ").append(String.valueOf(id)).append("\t\tVehicleID: ").append(String.valueOf(vehicleId)).append("\tInstruction: ").append(instruction).append("\n");
        }
        fileWriter.append("\n\n");
    }

    private void reportResources() throws SQLException, IOException {
        ResultSet resourceResults = DB.getResourceDetails();
        fileWriter.append("ResourceInstance  \t\tVehicleId  \t\tResource\n");
        while(resourceResults.next()) {
            int id = resourceResults.getInt(1);
            int vehicleId = resourceResults.getInt(2);
            String resource = resourceResults.getString(3);
            fileWriter.append("ResourceInstanceID: " + id + "\tVehicleId: " + vehicleId + "\tResource: " + resource + "\n");
        }
        fileWriter.append("\n\n");
    }
}
