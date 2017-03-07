package uk.me.eastmans.openshift.files;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * Created by markeastman on 06/03/2017.
 */
@RestController
public class FilesController {

    private String localFilePath = ".";
    private static String NEW_FILE_NAME = "modified_via_openshift.txt";

    @RequestMapping("/list")
    public File[] listFiles() {
        // We need to get a list of files and return to the display
        File current = new File(localFilePath);
        return current.listFiles();
    }

    @RequestMapping("/update")
    public String updateFile()
    {
        File current = new File(localFilePath);
        File file = new File(current,NEW_FILE_NAME);
        boolean exists = file.exists();
        String msg = "Unable to process file operations";
        try
        {
            FileWriter wr = new FileWriter(file);
            wr.write( "Modified at " + new Date() );
            msg = "File " + file.getName() + " has been " + (exists ? "updated" : "created");
            wr.close();
        }
        catch (IOException exception)
        {
            msg = "Unable to write file " + exception.toString();
        }
        return msg;
    }

    @RequestMapping("/callon")
    public String callOn()
    {
        try
        {
            RestTemplate client = new RestTemplate();
            String message = client.getForObject("http://localhost:8090/dummy",String.class);
            return "Retrieved: " + message;
        }
        catch (Exception e)
        {
            return "Unable to execute REST " + e.getMessage();
        }
    }
}
