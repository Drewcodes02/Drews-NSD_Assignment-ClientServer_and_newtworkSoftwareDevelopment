package Assigment.commands;

import org.json.JSONObject;

import java.util.ArrayList;

public class ReturnMessages {

    public static JSONObject error(String message) {
        JSONObject obj = new JSONObject();
        obj.put("_class", "ErrorResponse");
        obj.put("error", message);
        return obj;
    }

    public static JSONObject success() {
        JSONObject obj = new JSONObject();
        obj.put("_class", "SuccessResponse");
        return obj;
    }

    public static JSONObject getRequest(ArrayList<JSONObject> messages) {
        JSONObject obj = new JSONObject();
        obj.put("_class", "MessageListResponse");
        obj.put("messages", messages);
        return obj;
    }

    public static JSONObject logoutMessage() {
        JSONObject obj = new JSONObject();
        obj.put("_class", "LogoutSuccessResponse");
        return obj;
    }

}
