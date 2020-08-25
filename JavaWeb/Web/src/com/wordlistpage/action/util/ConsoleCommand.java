package com.wordlistpage.action.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleCommand
{
    private StringBuffer buffer;
    private Process process;
    private BufferedReader bufferedReader;
    private StringBuffer readBuffer;

    public String inputCommand(String cmd)
    {
        buffer = new StringBuffer();

//        buffer.append("cmd.exe ");
//        buffer.append("/c ");
        buffer.append("cmd.exe ");
        buffer.append("/c ");

        buffer.append(cmd);

        return buffer.toString();
    }

    public String execCommand(String cmd)
    {
        try
        {
            process= Runtime.getRuntime().exec(cmd);
            bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line = null;
            readBuffer = new StringBuffer();

            while((line = bufferedReader.readLine())!=null)
            {
                readBuffer.append(line);
                readBuffer.append("\n");
            }
            return readBuffer.toString();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}
