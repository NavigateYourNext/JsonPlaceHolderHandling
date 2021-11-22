package org.demo.baseclass;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import java.util.ArrayList;
import java.util.List;

/**
 * Parent Class
 */
public class BaseClass
{
    public static RequestSpecification httpRequest;
    public static Response httpResponse;
    public static String userName;
    public static String baseUrl = "https://jsonplaceholder.typicode.com/";
    public static String userDetails;
    public static JSONParser parser = new JSONParser();
    public static JSONArray jsonArray = new JSONArray();
    public static int userId;
    public static List<Integer> postId = new ArrayList<Integer>();
    public static List<String> emails = new ArrayList<String>();
    public static String emailRegex = "^(.+)@(.+)$";

}
