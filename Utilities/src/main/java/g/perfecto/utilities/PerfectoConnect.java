package g.perfecto.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class PerfectoConnect
{
  public String host;
  public String binFolder = "C:\\PerfectoConnect\\";
  public String fileName = "perfectoconnect64.exe";
  public String executablePath;
  public String tunnelId;
  public String downloadUrl = "https://downloads.connect.perfectomobile.com/clients/Perfecto-Connect-windows64.zip";
  private StringBuilder output;
  private StringBuilder errorOutput;
  private Log log;
  
  public PerfectoConnect(String host)
  {
    log = LogFactory.getLog(this.getClass());
    log.debug("Creating " + this.getClass() + " object");
    
    if (!host.endsWith(".perfectomobile.com"))
      host += ".perfectomobile.com";
    
    this.host = host;
    log.info("Initializing perfecto connect for host: " + host);
    
    if (executablePath == null)
    {
      executablePath = Paths.get(binFolder, fileName).toString();
    }
    
    log.info("Using binary: " + executablePath);
  }
  
  public String start()
  {
    String cmd = executablePath + " start -c " + host + " -s " + TokenStorage.getTokenForCloud(host);
    executeCommand(cmd);
    
    if (output.length() != 0)
    {
      Logger.LogInfo("Created tunnelId: " + output.toString());
      tunnelId = output.toString();
      return output.toString();
    }

    if (output.length() != 0)
      Logger.LogError(output.toString());
    
    return null;
  }
  
  public void stop()
  {
    String cmd = executablePath + " stop";
    executeCommand(cmd);

    if (output.length() != 0)
    {
      Logger.LogInfo(output.toString());
      Logger.LogInfo("Perfecto Connect stopped!");
    }

    if (output.length() != 0)
      Logger.LogError(errorOutput.toString());
  }
  
  private void executeCommand(String cmd)
  {
    String[] commands = {"cmd.exe", "/c", cmd};


    try {
      Process proc = Runtime.getRuntime().exec(commands);
      
      String line;
      errorOutput  = new StringBuilder();
      output = new StringBuilder();
      
      BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
      while ((line = stdError.readLine()) != null) {
        errorOutput.append(line); 
      }
            
      BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
      while ((line = stdInput.readLine()) != null) {
        output.append(line);
      }
      
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public String getActiveTunnelId()
  {
   return tunnelId; 
  }
  
  public void downloadClient(String saveToFolder)
  {   
    
  }
}