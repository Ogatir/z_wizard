package z_wizard.project;

import com.google.gson.Gson;

import java.io.*;

public class JsonParser {

    public static void ProjectToJson(ProjectParams projectParams, File file){
        Gson gson = new Gson();
        String projectStr = gson.toJson(projectParams,ProjectParams.class);
        try {
            FileWriter fileStream = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileStream);
            writer.write(projectStr);
            writer.close();
            fileStream.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static ProjectParams JsonToProject(File file){
        Gson gson = new Gson();
        ProjectParams params = null;
        try {
            FileReader fileStream = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileStream);
            params = gson.fromJson(reader, ProjectParams.class);
            reader.close();
            fileStream.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        return params;
    }
}
