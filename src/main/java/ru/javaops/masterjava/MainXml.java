package ru.javaops.masterjava;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.google.common.io.Resources;

import ru.javaops.masterjava.stax.UserInfo;
import ru.javaops.masterjava.xml.schema.EmailType;
import ru.javaops.masterjava.xml.schema.FlagType;
import ru.javaops.masterjava.xml.schema.ObjectFactory;
import ru.javaops.masterjava.xml.schema.Payload;
import ru.javaops.masterjava.xml.schema.User;
import ru.javaops.masterjava.xml.util.JaxbParser;
import ru.javaops.masterjava.xml.util.Schemas;
import ru.javaops.masterjava.xml.util.StaxStreamProcessor;
import ru.javaops.masterjava.xml.util.XPathProcessor;

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
    
    private static Map<String, List<User>> resultMap = new HashMap<>();
    
    private static final String XPATH_PAYLOAD_USERS_USER_CHILD = "/*[name()='Payload']/*[name()='Users']/*[name()='User'][%d]";
    private static final String XPATH_USERS = "/*[name()='Payload']/*[name()='Users']/*";
    private static final String XPATH_USER_FULLNAME = "/*[name()='Payload']/*[name()='Users']/*[name()='User']/*[name()='fullName']/text()";

	public static void main(String[] args) {
		System.out.println("HW02 Start...");

		Arrays.stream(args).forEach(projectName -> {

			try {
				
				// findUsersWithJAXB(projectName);

//				findUsersAndEmailsWithStAX(projectName);

				findUserWithXPath(projectName);
				
				resultMap.forEach((p,u)-> {
					System.out.println("--------------------------------------");
					System.out.println("ProjectName: " + p );
				
					u.stream().parallel().collect(Collectors.toList()).forEach(v -> {
						System.out.println(v.getFullName() + " - " + v.getCity() + " - " +  v.getFlag().value() + " - " + v.getEmail().value());
					});
					System.out.println("--------------------------------------");
				});
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		});
	}
    
	/**
     * Sucht Benutzer mit XPath, die in Project mit ProjectName teilnehmen.
     * Gibt die Namen und Email in Console für diese Project aus.
     * @param projectName Name des Projectes.
	 * @throws IOException Wenn ein Fehler auftritt
     */
    private static void findUserWithXPath(String projectName) throws IOException {
		try (InputStream is = Resources.getResource("payload.xml").openStream()) {

			resultMap.put(projectName, new ArrayList<>());
			
			XPathProcessor processor = new XPathProcessor(is);
			
			XPathExpression usersExpression = XPathProcessor.getExpression(XPATH_USERS);
			NodeList usersList = processor.evaluate(usersExpression, XPathConstants.NODESET);
			
			XPathExpression expressionFullname = XPathProcessor.getExpression(XPATH_USER_FULLNAME);
			NodeList fullNameList = processor.evaluate(expressionFullname, XPathConstants.NODESET);

			for (int i = 0; i < usersList.getLength(); i++) {
				
				String city = "";
				FlagType flagType = null;
				EmailType emailType = null;
				
				XPathExpression user1Expression = XPathProcessor.getExpression(String.format(XPATH_PAYLOAD_USERS_USER_CHILD, i+1));
				Node userNode = (Node)processor.evaluate(user1Expression, XPathConstants.NODE);
				
				for (int k = 0; k < userNode.getAttributes().getLength(); k++) {
					if (userNode.getAttributes().item(k).getNodeName().equalsIgnoreCase("flag")) {
						String flag = userNode.getAttributes().item(k).getTextContent();
						flagType = FlagType.fromValue(flag);
					}
					if (userNode.getAttributes().item(k).getNodeName().equalsIgnoreCase("city")) {
						city = userNode.getAttributes().item(k).getTextContent();
						
					}
					if (userNode.getAttributes().item(k).getNodeName().equalsIgnoreCase("email")) {
						String email = userNode.getAttributes().item(k).getTextContent();
						emailType = EmailType.fromValue(email);
					}
				}
				 
				User user = new User();
				user.setFullName(fullNameList.item(i).getTextContent());
				user.setCity(city);
				user.setFlag(flagType);
				user.setEmail(emailType);
				resultMap.get(projectName).add(user);
			}

		} catch (IOException e) {
			throw e;
		}
		
	}

	/**
     * Sucht Benutzer und E-Mail mit StAX, die in Project mit ProjectName teilnehmen.
     * Gibt die Namen und EMail in Console für diese Project aus.
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

