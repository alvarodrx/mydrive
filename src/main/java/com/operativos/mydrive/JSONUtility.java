package com.operativos.mydrive;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONUtility
{

    public JSONObject createUser(String userName, String password){
        //User Details
        JSONObject userDetail = new JSONObject();
        userDetail.put("userName", userName);
        userDetail.put("password", password);

        //New User
        JSONObject userObject = new JSONObject();
        userObject.put("user", userDetail);

        return userObject;
    }

    public void createNewUser(String userName, String password){

        //User Details
        JSONObject userObject = createUser(userName,password);

        //Add user to list
        JSONArray userList = new JSONArray();//readJSONFile();
        userList.add(userObject);

        //Write JSON file
        try (FileWriter file = new FileWriter("users.json")) {

            file.write(userList.toJSONString());
            file.flush();
            //file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        /*/User Details
        JSONObject userObject = createUser(userName,password);

        //Add user to list
        JSONArray userList = new JSONArray();//readJSONFile();
        userList.add(userObject);

        //Write JSON file
        try (FileWriter file = new FileWriter("users.json")) {

            file.write(userList.toJSONString());
            file.flush();
            //file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
         */
    }

    public JSONArray readJSONFile()
    {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        JSONArray userList = new JSONArray();

        try (FileReader reader = new FileReader("users.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            userList = (JSONArray) obj;
            //System.out.println(userList);

            //Iterate over employee array
            //userList.forEach( emp -> parseEmployeeObject( (JSONObject) emp ) );



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return userList;
    }

    private void parseEmployeeObject(JSONObject employee)
    {
        //Get employee object within list
        JSONObject employeeObject = (JSONObject) employee.get("employee");

        //Get employee first name
        String firstName = (String) employeeObject.get("firstName");
        System.out.println(firstName);

        //Get employee last name
        String lastName = (String) employeeObject.get("lastName");
        System.out.println(lastName);

        //Get employee website name
        String website = (String) employeeObject.get("website");
        System.out.println(website);
    }

}