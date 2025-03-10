package com.saltyFish.appointment.utils;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class DBImportUtil {

    private final JdbcTemplate jdbcTemplate;
    private static final int BATCH_SIZE = 1000; // Process 1000 records at a time

    public DBImportUtil(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void loadCSVToDatabase(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;
            List<Object[]> batchArgs = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                if (firstLine) { // Skip header
                    firstLine = false;
                    continue;
                }

                String[] data = line.split(",");
                batchArgs.add(new Object[]{data[0].trim(), Integer.parseInt(data[1].trim()), data[2].trim(), data[3].trim(), data[4].trim(), data[5].trim(), data[6].trim(), data[7].trim(), data[8].trim(), data[9].trim(), data[10].trim(), data[11].trim(), data[12].trim(), data[13].trim()});

                // Execute batch insert every 1000 records
                if (batchArgs.size() >= BATCH_SIZE) {
                    insertBatch(batchArgs);
                    batchArgs.clear(); // Clear memory
                }
            }

            // Insert any remaining records
            if (!batchArgs.isEmpty()) {
                insertBatch(batchArgs);
            }

            System.out.println("CSV Data Successfully Loaded!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertBatch(List<Object[]> batchArgs) {
        String sql = "INSERT INTO appointment (serviceOwnerId, startTime, endTime, confirmationNumber, communicationSw, customerId, ownerConfirmed, requesterConfirmed, serviceType, cancelReason, cancelDate, cancelTime, cancelBy, serviceId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.batchUpdate(sql, batchArgs);
        System.out.println("Inserted " + batchArgs.size() + " records.");
    }
}
