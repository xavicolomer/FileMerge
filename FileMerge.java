/**
* This executable is a simple class to merge all the files specified as arguments.
*
* @author  @xaviercolomer
* @version 1.0
* @since   2014-12-07 
*/

import java.io.BufferedReader;
import java.util.List;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;

public class FileMerge {

    public static String content = "";

    public static void main(String[] args) {
        String fileOutput = "";
        boolean next = false; 
        List<String> files = new ArrayList<String>();

        for (String arg : args) {
            if (next)
            {
                fileOutput = arg;
            }
            else 
            {
                if (arg.equals("-o"))
                {
                    next = true;
                }
                else 
                {
                    files.add(arg);
                    next = false;
                }
            }
        }

        for (String file : files) {
            appendFileContents(file);
        }

        if (fileOutput.equals(""))
        {
            System.out.println("You have to specify an output file, for example -o filename.txt");
            System.exit(0);
        }

        writeFile(fileOutput, content);
    }


    public static void appendFileContents(String filePath)
    {
        try {

            BufferedReader br = new BufferedReader(new FileReader(filePath));
            try {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }
                content = content + sb.toString() + "\n";
            } finally {
                br.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeFile(String filePath, String content)
    {
        try {
            PrintWriter writer = new PrintWriter(filePath, "UTF-8");
            writer.println(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
