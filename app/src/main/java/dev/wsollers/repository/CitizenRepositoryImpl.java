package dev.wsollers.repository;

import dev.wsollers.domain.Citizen;

import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicBoolean;

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

  @Override
  public List<Citizen> getCitizens() {
    readCitizens();
    System.out.println(data);
    return new ArrayList<Citizen>();
  }

}
