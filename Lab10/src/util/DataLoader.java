package util;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import model.*;

public class DataLoader {
    
    private final URL filepath;


    public DataLoader(URL filepath) {
        this.filepath = filepath;
    }


    public List<Datapoint> load() throws IOException {

        CSVFormat format = CSVFormat.Builder.create(CSVFormat.RFC4180)
        .setHeader()
        .setSkipHeaderRecord(true)
        .build();

        List<Datapoint> data = CSVParser
          .parse(this.filepath, StandardCharsets.UTF_8, format)
          .stream()
          .map(DataLoader::fromCSV)
          .toList();


          return data;

    }

    public static Datapoint fromCSV(CSVRecord record) {

        Coordinate northern = new Coordinate(
            Degree.parse(record.get("NORTHERN LATITUDE")),
            Degree.parse(record.get("NORTHERN LONGITUDE"))
        );

        Coordinate southern = new Coordinate(
            Degree.parse(record.get("SOUTHERN LATITUDE")),
            Degree.parse(record.get("SOUTHERN LONGITUDE"))
        );

        Coordinate central = new Coordinate(
            Degree.parse(record.get("CENTER LATITUDE")),
            Degree.parse(record.get("CENTER LONGITUDE"))
        );

        LocalDate date = LocalDate.of(2024, 4, 8);
        LocalTime time = LocalTime.parse(record.get(0).trim()); //<- first column must be gotten by id (because of secret encoding characters)
        LocalDateTime date_time = LocalDateTime.of(date, time);

        return new Datapoint(date_time, northern, southern, central);

    }
}
