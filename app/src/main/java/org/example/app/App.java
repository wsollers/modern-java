/*
 * This source file was generated by the Gradle 'init' task
 */
package org.example.app;

import javax.persistence.EntityManager;

import org.example.list.LinkedList;

import static org.example.utilities.StringUtils.join;
import static org.example.utilities.StringUtils.split;
import static org.example.app.MessageUtils.getMessage;

import dev.wsollers.repository.CustomEntityManagerFactory;
import dev.wsollers.repository.CitizenRepository;
import dev.wsollers.repository.CitizenJsonRepository;
import dev.wsollers.logging.LogFactory;

import org.apache.commons.text.WordUtils;

public class App {
  public static void main(String[] args) {
    LinkedList tokens;
    tokens = split(getMessage());
    String result = join(tokens);
    System.out.println(WordUtils.capitalize(result));
    CitizenRepository citizenRepository = new CitizenJsonRepository();
    citizenRepository.getCitizens();
    LogFactory.testLogger();
    EntityManager em = CustomEntityManagerFactory.getEntityManager();
  }
}
