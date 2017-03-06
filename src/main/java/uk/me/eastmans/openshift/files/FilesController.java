package uk.me.eastmans.openshift.files;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 * Created by markeastman on 06/03/2017.
 */
@RestController
public class FilesController {

    private String localFilePath = ".";

    @RequestMapping("/list")
    public File[] listFiles() {
        // We need to get a list of files and return to the display
        File current = new File(localFilePath);
        return current.listFiles();
    }
}
