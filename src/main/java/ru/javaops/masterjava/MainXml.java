package ru.javaops.masterjava;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import ru.javaops.masterjava.xml.schema.ObjectFactory;
import ru.javaops.masterjava.xml.schema.Payload;
import ru.javaops.masterjava.xml.schema.User;
import ru.javaops.masterjava.xml.util.JaxbParser;
import ru.javaops.masterjava.xml.util.Schemas;

/**
 * User: gkislin
 * Date: 05.08.2015
 *
 * @link http://caloriesmng.herokuapp.com/
 * @link https://github.com/JavaOPs/topjava
 */
public class MainXml {
    private static final JaxbParser JAXB_PARSER = new JaxbParser(ObjectFactory.class);

    private static JAXBContext jaxbContext;

    public static void main(String[] args) {
        System.out.println("HW02 Start...");
        Map<String, List<String>> resultMap = new HashMap<>();
        JAXB_PARSER.setSchema(Schemas.ofClasspath("payload.xsd"));
        Arrays.stream(args).forEach(projectName -> {
            System.out.println("Find Users for Project with name " + projectName);
            try {
                //Get JAXBContext
                jaxbContext = JAXBContext.newInstance(Payload.class);
                //Create Unmarshaller
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

                //Setup schema validator
                SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
                Schema projectSchema = sf.newSchema(new File("src/main/resources/payload.xsd"));
                jaxbUnmarshaller.setSchema(projectSchema);

                //Unmarshal xml file
                Payload payload = (Payload) jaxbUnmarshaller.unmarshal(new File("src/main/resources/payload.xml"));
                
                List<String> usersInProjectList = new ArrayList<>();
                List<User> userList = payload.getUsers().getUser();
                
                for (User user : userList) {
                    user.getGroup().forEach(v -> {
                        if (projectName.equalsIgnoreCase(v.getGrp().toString())) {
                        	usersInProjectList.add(user.getFullName());
                        }
                    });
                }
                
                if (usersInProjectList.isEmpty() == false) {
                	Collections.sort(usersInProjectList);
                	resultMap.put(projectName, usersInProjectList);
                }

            } catch (JAXBException | SAXException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }


        });// end Arrays.stream(args).forEach
        
        if (resultMap.isEmpty() == false) {
        	for (Map.Entry<String, List<String>> entry : resultMap.entrySet()) {
				String project = entry.getKey();
				List<String> users = entry.getValue();
				System.out.println("For Project with name [" + project + "] was " + users.size() + " User(s) found: " + String.join(",", users));
			}
        } else {
        	System.out.println("Users not found!");
        }
        
        
    }
}
/**
 * 3: Реализовать класс MainXml, которые принимает параметром имя проекта в тестовом xml
 * и выводит отсортированный список его участников (использовать JAXB).
 **/
