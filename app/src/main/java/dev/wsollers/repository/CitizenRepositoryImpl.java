package dev.wsollers.repository;

import dev.wsollers.domain.Citizen;
import dev.wsollers.domain.Address;
import dev.wsollers.domain.Ethnicity;
import dev.wsollers.domain.Gender;
import dev.wsollers.domain.Race;

import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicBoolean;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CitizenRepositoryImpl implements CitizenRepository {

  public static final String CITIZENS_JSON = "citizens.json";

  private static AtomicBoolean initialized = new AtomicBoolean(false);
  private static String data = null;

  private void readCitizens() {
    if (initialized.get()) {
      return;
    }
    try {
      var citizensJsonURI = ClassLoader.getSystemResource(CITIZENS_JSON).toURI();

      data = Files.readString(Paths.get(citizensJsonURI));
    }
    catch (Exception e) {
      //TODO: log exception
      e.printStackTrace();
    }
    initialized.set(true);
  }

  private void parseJsonToObjects() {
    ObjectMapper mapper = new ObjectMapper();

        try {

            List<Citizen> citizens = mapper.readValue(data, 
              mapper.getTypeFactory().constructCollectionType(
                List.class, Citizen.class));
                //Address.class,Ethnicity.class,Gender.class,Race.class));

            // compact print
            System.out.println(citizens);

            // pretty print
            String prettyStaff1 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(citizens);

            System.out.println(prettyStaff1);


        } catch (IOException e) {
            e.printStackTrace();
        }
  }

  @Override
  public List<Citizen> getCitizens() {
    readCitizens();
    System.out.println(data);
    parseJsonToObjects();
    return new ArrayList<Citizen>();
  }

}
