package org.demo.commonmethods;

import io.restassured.http.Method;
import io.restassured.response.Response;
import org.demo.baseclass.BaseClass;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.Iterator;
import java.util.List;

/**
 *
 * Common Reusable Methods
 */
public class CommonReusableMethods extends BaseClass
{
    public static Response getUrlResponse(String urlName)
    {
        return httpRequest.request(Method.GET,"/"+urlName+"/");
    }

    public static String getResponseBody(String urlName)
    {
        httpResponse = httpRequest.request(Method.GET,"/"+urlName+"/");
        return httpResponse.getBody().asString();
    }

    public static boolean findSpecificUser(JSONArray jsonArray, String userName)
    {
        boolean result = false;

        Iterator<JSONObject> iterator = jsonArray.iterator();
        while (iterator.hasNext())
        {
            JSONObject factObj = (JSONObject) iterator.next();
            String value = (String) factObj.get("username");

            if(value.equals(userName))
                result = true;
        }

        return result;
    }

    public static int findUserId(JSONArray jsonArray,String userName)
    {
        int id=0;

        Iterator<JSONObject> iterator = jsonArray.iterator();
        while (iterator.hasNext())
        {
            JSONObject factObj = (JSONObject) iterator.next();
            String value = (String) factObj.get("username");

            if(value.equals(userName))
                id = Integer.parseInt(factObj.get("id").toString());
        }

        return id;
    }

    public static List<Integer> getAllPostIdByUserId(int userId)throws Exception
    {
        String postResponse = getResponseBody("posts");
        JSONArray jsonArray = (JSONArray) parser.parse(postResponse);

        System.out.println("\nPost Details Posted By User Are - \n");


        Iterator<JSONObject> iterator = jsonArray.iterator();
        while (iterator.hasNext())
        {
            JSONObject factObj = (JSONObject) iterator.next();
            String value = String.valueOf(factObj.get("userId"));

            if(value.equals(String.valueOf(userId)))
            {
                System.out.println(factObj);
                postId.add(Integer.parseInt(factObj.get("id").toString()));
            }


        }

        return postId;
    }

    public static void getAllCommentsIdByPostId(List<Integer> postIds)throws Exception
    {
        System.out.println("\nComments Details By Post ID's Are - \n");

        int count = 0;
        for(Integer i:postIds) {
            String commentsResponse = getResponseBody("posts/" + i + "/comments");
            JSONArray jsonArray = (JSONArray) parser.parse(commentsResponse);

            System.out.println(jsonArray);

            Iterator<JSONObject> iterator = jsonArray.iterator();
            while (iterator.hasNext())
            {
                JSONObject factObj = (JSONObject) iterator.next();
                emails.add(factObj.get("email").toString());
            }
        }
    }
}
