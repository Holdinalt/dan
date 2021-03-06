package clientPackage;

import businessLogic.commands.Command;
import businessLogic.commands.CommandType;
import businessLogic.factories.StudyGroupFactory;
import businessLogic.sourseDate.StudyGroup;
import clientPackage.excpetions.InvalidCommandException;
import com.google.gson.Gson;
import connectionPackage.connectionData.Request;
import javafx.util.Pair;
import sun.security.krb5.internal.Ticket;

import java.util.HashMap;

public class RequestBuilder {
    private String commandName = "";
    private String commandArgs = "";
    private HashMap<String, CommandType> commandMap;

    public RequestBuilder(HashMap<String,CommandType> commandMap) {
        this.commandMap = commandMap;
    }

    public Pair<String,String> completeQuery(String name, String args) throws InvalidCommandException {
        if(!commandMap.containsKey(name)){
            System.out.println("Invalid command name");
            throw new InvalidCommandException();
        }

        String commandType = String.valueOf(commandMap.get(name));

        switch (commandType){
            case "ARGS":
                if(args == null){
                    System.out.println("Данная команда  содержит аргументы");
                    throw new InvalidCommandException();
                }
                return new Pair<String, String>(name,args);
            case "CLEAR":
                if(!(args == null)  ){
                    System.out.println("Данная команда не содержит аргументов");
                    throw new InvalidCommandException();
                }
                return new Pair<String, String>(name,args);
            case "OBJECT":
                StudyGroup studyGroup = new StudyGroupFactory().createStudyGroup();
                return new Pair<String, String>(name, new Gson().toJson(studyGroup));
            default:
                throw new InvalidCommandException();
        }


    }

}
