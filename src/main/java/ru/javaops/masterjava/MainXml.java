package ru.javaops.masterjava;

import java.io.File;
import java.io.IOException;
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
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import com.google.common.io.Resources;

import ru.javaops.masterjava.stax.UserInfo;
import ru.javaops.masterjava.xml.schema.ObjectFactory;
import ru.javaops.masterjava.xml.schema.Payload;
import ru.javaops.masterjava.xml.schema.User;
import ru.javaops.masterjava.xml.util.JaxbParser;
import ru.javaops.masterjava.xml.util.Schemas;
import ru.javaops.masterjava.xml.util.StaxStreamProcessor;

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

		Arrays.stream(args).forEach(projectName -> {

			try {
				
				// findUsersWithJAXB(projectName);

				findUsersAndEmailsWithStAX(projectName);
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		});
	}
    
    /**
     * Sucht Benutzer und E-Mail mit StAX, die in Project mit ProjectName teilnehmen.
     * Gibt die Namen in Console für diese Project aus.
     * @param projectName Name des Projectes.
     */
    private static void findUsersAndEmailsWithStAX(String projectName) {
		try (StaxStreamProcessor processor = new StaxStreamProcessor(
				Resources.getResource("payload.xml").openStream())) {
			XMLStreamReader reader = processor.getReader();

			Map<String, List<UserInfo>> resultMap = new HashMap<>();
			List<UserInfo> userInfoList = new ArrayList<>();

			while (reader.hasNext()) {
				int event = reader.next();
				if (event == XMLEvent.START_ELEMENT) {
					if ("User".equals(reader.getLocalName())) {
						String email = reader.getAttributeValue(2);

						while (reader.hasNext()) {
							int nextEvent = reader.next();
							if (nextEvent == XMLEvent.START_ELEMENT) {
								if ("fullName".equals(reader.getLocalName())) {
									String name = reader.getElementText();
									while (reader.hasNext()) {
										int eventGroup = reader.next();
										if (eventGroup == XMLEvent.START_ELEMENT) {
											if ("group".equals(reader.getLocalName()) && projectName.equals(reader.getAttributeValue(0))) {
												UserInfo userInfo = new UserInfo(name, email);
												userInfoList.add(userInfo);
												break;
											}
										}
									}
									break;
								}

							}

						}
					}
				}
			}

			if (! userInfoList.isEmpty()) {
				resultMap.put(projectName, userInfoList);
			}
			
			if (! resultMap.isEmpty()) {
		    	for (Map.Entry<String, List<UserInfo>> entry : resultMap.entrySet()) {
					String project = entry.getKey();
					List<UserInfo> users = entry.getValue();
					System.out.println("------------------------------------------------------");
					System.out.println("For Project with name [" + project + "] was " + users.size() + " User(s) found: ");
					users.forEach(v -> {
						System.out.println(v); 
					});
					System.out.println("------------------------------------------------------");
				}
		    } else {
		    	System.out.println("Users not found!");
		    }

		} catch (XMLStreamException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
     * Sucht Benutzer mit JAXB, die in Project mit ProjectName teilnehmen.
     * Gibt die Namen in Console für diese Project aus.
     * @param projectName Name des Projectes.
     */
    public static void findUsersWithJAXB(String projectName) {
    	Map<String, List<String>> resultMap = new HashMap<>();
        JAXB_PARSER.setSchema(Schemas.ofClasspath("payload.xsd"));
        
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
            
            if (! usersInProjectList.isEmpty()) {
            	Collections.sort(usersInProjectList);
            	resultMap.put(projectName, usersInProjectList);
            }

        } catch (JAXBException | SAXException e) {
            throw new RuntimeException(e);
        }

    
    if (! resultMap.isEmpty()) {
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

