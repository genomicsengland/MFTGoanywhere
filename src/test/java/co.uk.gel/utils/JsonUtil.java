package co.uk.gel.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.google.gson.Gson;
import io.restassured.path.json.JsonPath;
import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class JsonUtil {
	private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);
	private static final String INDEX_DELIMITER = "\\[|\\]";
	private static final String PATH_DELMITER = "\\.";

	/*
	 * Load JSON data from file and Update data from fields and value in the Map
	 * field field which needs to be updated
	 * updateValue new value to be set for the field
	 */
	public String updateJsonDataFromFile(String filepath, String field, String updateValue)  throws IOException, ParseException, JSONException {
		ClassLoader classLoader = getClass().getClassLoader();
		String fileName = URLDecoder.decode(classLoader.getResource(filepath).getFile(),  "UTF-8");
		String lines = FileUtils.readFileToString(new File(fileName));
		return updateJsonDataStr(lines, field, updateValue);
	}

	/*
	 * Load JSON data from file and Update data from fields and value in the Map
	 * filepath json file with path
	 * dataSet map to include fields and new values
	 */
	public String updateJsonDataFromFile(String filepath,  Map<String, Object> dataSet)  throws IOException, ParseException, JSONException {
		ClassLoader classLoader = getClass().getClassLoader();
		String fileName = URLDecoder.decode(classLoader.getResource(filepath).getFile(),  "UTF-8");
		String lines = FileUtils.readFileToString(new File(fileName));
		return updateJsonDataStr(lines, dataSet);
	}

	/*
	 * Update JSON data from fields and value in the Map
	 * jsonData JSON data to be updated
	 * dataSet map to include fields and new values
	 */
	public String updateJsonDataStr(String jsonData, Map<String, Object> dataSet) throws ParseException, IOException, JSONException
	{
		for(String field :dataSet.keySet()) {
			Object updateValue = dataSet.get(field);
			jsonData = updateJsonDataStr(jsonData, field, updateValue);
		}
		return  jsonData;
	}

	/*
	 * Update JSON data from fields and value in the Map
	 * filepath json file with path
	 * dataSet map to include fields and new values
	 */
	public String updateJsonDataStr(String jsonData, String field, Object updateValue)
			throws IOException, JSONException, ParseException {
		JSONParser parser = new JSONParser();
		JSONObject object = (JSONObject)parser.parse(jsonData);
		String[] fields = field.split(PATH_DELMITER);
		List<String> indexes = new ArrayList();
		for(String fieldname: fields) {
			indexes.add(fieldname);
		}
		JSONObject modifiedJsonObject = reuseJsonObject(object, indexes, updateValue);
		return modifiedJsonObject.toJSONString();
	}

	public JSONObject reuseJsonObject(Object object,List<String> fields, Object updateValue)  throws IOException, ParseException, JSONException {
		String key = fields.get(0);
		String[] arrayIndex = key.split(INDEX_DELIMITER);
		if(arrayIndex.length > 1) {
			JSONArray names= (JSONArray) ((JSONObject)object).get(arrayIndex[0]);
			Object namedIndex = names.get(Integer.parseInt(arrayIndex[1]));
			fields.remove(0);

			if(namedIndex instanceof JSONObject) {
				names.set(Integer.parseInt(arrayIndex[1]), reuseJsonObject(namedIndex, fields, updateValue));
			}else {
				names.set(Integer.parseInt(arrayIndex[1]), updateValue);
			}
			return (JSONObject) object;
		}
		else if(fields.size() > 1)
		{
			Object namedIndex = ((JSONObject)object).get(key);
			fields.remove(0);
			JSONObject lookupNestedKey = reuseJsonObject(namedIndex, fields, updateValue);

			if(object!= null && ((JSONObject)object).containsKey(key)) {
				((JSONObject)object).replace(key, lookupNestedKey);
			}else {
				log.error("PATH NOT FOUND: "+ key);
			}
			return (JSONObject) object;
		}
		else
		{
			if(object!=null && ((JSONObject)object).containsKey(key)) {
				((JSONObject)object).replace(key, updateValue);
			}else {
				log.error("PATH NOT FOUND: "+ key);
			}
		}
		return (JSONObject) object;
	}


	/*
	 * Validate fields in the JSON data with fields and value in the Map
	 * jsonData JSON data to be validated
	 * dataSet map to include fields and values to be validated
	 */
	public void validateJsonData(String jsonData, Map<String, Object> dataSet) throws ParseException, IOException, JSONException
	{
		JsonPath json = new JsonPath(jsonData);
		for(String field :dataSet.keySet()) {
			Object updateValue = dataSet.get(field);
			Object actualValue = json.get(field);
			Assert.assertEquals(actualValue, updateValue);
		}
	}
	/* public <T> List<? extends IPostAPI> getPayloadForPostApi(String endpoint, String payloadLocation) {

        System.out.println(env.getProperty("CONSENT_POST_PAYLOAD_Location"));

        switch (endpoint.toLowerCase()) {

            case "consent-questionnaire-response":
                JsonToObject<ConsentQuestionnaireDTO> listConsentQuestionnaireDTO = new JsonToObject<ConsentQuestionnaireDTO>();
                return listConsentQuestionnaireDTO.getObjects(new ConsentQuestionnaireDTO() , payloadLocation);

            case "consent":
                JsonToObject<Consent> listConsent = new JsonToObject<Consent>();
                return listConsent.getObjects(new Consent() , payloadLocation);


            default:
                throw new IllegalArgumentException("Invalid endpoint in cucumber feature file: " + endpoint.toLowerCase());
        }

    }*/

	/*public String lookupPathFromJson(String json, String path) {
    	ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    	JsonNode data;
		try {
			data = mapper.readValue(new File("src/test/resources/jsonfiles/consent-questionnaire-response.json"), JsonNode.class);

			ArrayNode baseNode = (ArrayNode) data.path("based_on");
			//JsonNode testNode = baseNode.findPath("identifier_id");
			return parseJsonData(data, "based_on/identifier_id", mapper);
		} catch (JsonParseException e) {
			log.error(e.getMessage(), e);
		} catch (JsonMappingException e) {
			log.error(e.getMessage(), e);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}

    	return null;
    }*/
	public Object lookupPathFromJson(String json, String path) {
		JsonPath jsonPath = new JsonPath(json);
		return jsonPath.getJsonObject(path);
	}

	public String parseJsonData(JsonNode dataNode, String path, ObjectMapper mapper) {

		String[] nodes = path.split("/");
		if(nodes != null) {
			System.out.println("Current Nodes" + nodes.length);

			if(dataNode.getClass() == ArrayNode.class ) {
				List<JsonNode> currentNode = dataNode.findValues(nodes[0]);
				for(JsonNode cnode:currentNode) {
					return parseJsonData(cnode, path.replace(nodes[0]+"/", ""), mapper);
				}

			}else {
				System.out.println("Non -Array :" + dataNode.getClass());
				return dataNode.asText();
			}
		}

		return null;
	}
	public String parseJsonData(JsonNode dataNode, String path) {

		String[] nodes = path.split("/");
		if(nodes != null) {
			System.out.println("Current Nodes" + nodes.length);

			if(dataNode.getClass() == ArrayNode.class ) {
				List<JsonNode> currentNode = dataNode.findValues(nodes[0]);
				for(JsonNode cnode:currentNode) {
					return parseJsonData(cnode, path.replace(nodes[0]+"/", ""));
				}

			}else {
				System.out.println("Non -Array :" + dataNode.getClass());
				return dataNode.asText();
			}
		}

		return null;
	}
	public Object loadPayloadFromFile(String payload, Class classType) throws UnsupportedEncodingException, FileNotFoundException {
		Gson gson = new Gson();
		ClassLoader classLoader = getClass().getClassLoader();
		String fileProp = "env.getProperty(payload)"; //get the payload file name wrt environment
		String fileName = URLDecoder.decode(classLoader.getResource(fileProp).getFile(),  "UTF-8");
		File postReferralPayload = new File(fileName);
		FileReader fileReader = new FileReader(postReferralPayload);
		Object jsonObject = gson.fromJson(fileReader, classType);
		return jsonObject;
	}

	// Load json file to parse
	public JSONObject loadJsonFile(String jsonFilePath) throws IOException, ParseException
	{
		log.debug("Before Lookup from JSON: " + jsonFilePath);
		JSONParser parser = new JSONParser();
		ClassLoader classLoader = getClass().getClassLoader();
		URL resource = classLoader.getResource((jsonFilePath));
		//String file = resource.getFile();
		String dr = URLDecoder.decode(classLoader.getResource(jsonFilePath).getFile(), "UTF-8");
		log.debug("Before Lookup from JSON: " + dr);
		FileReader fr = new FileReader(dr);
		return (JSONObject)parser.parse(fr);  //Return an object and downcast to JSON Object
	}

	public void verifyJsonFields(JsonPath response, String responseField, String propertyPath, String field)  throws IOException, ParseException {
		Map<String, Object> addressMap = response.getJsonObject(responseField);
		JSONObject object = loadJsonFile(propertyPath);

		JSONObject addressObjectReq= (JSONObject) object.get(field);

		Set<String> allnames = addressObjectReq.keySet();
		for(String value : allnames)
		{
			Assert.assertEquals(addressMap.get(value), addressObjectReq.get(value));
			//System.out.println("Expected " + value + " : "+ addressObjectReq.get(value)+ " Actual is : " + addressMap.get(value));
		}
	}

	public static String getValueOfAttribute(String attributeName, JSONObject object)
	{
		String name = (String) object.get(attributeName); //Return an object and downcast to String
		return name;
	}

	public static JSONArray getJsonArray(String arrayName, JSONObject object)
	{
		JSONArray array = (JSONArray) object.get(arrayName);  //Return an object and downcast to JSON Array
		return array;
	}



	public static JSONObject getJsonObject(Object objectName, JSONObject object)
	{
		JSONObject array = (JSONObject) object.get(objectName); //Return an object and downcast JSON Array
		return array;
	}


}
