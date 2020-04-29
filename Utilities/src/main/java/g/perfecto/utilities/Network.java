package g.perfecto.utilities;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.remote.RemoteWebDriver;

public class Network {

  private RemoteWebDriver driver = null;
  private Map<String, Object> params = new HashMap<>();
  
  private final static String COMMAND_VNETWORK_START = "mobile:vnetwork:start";
  private final static String COMMAND_VNETWORK_STOP = "mobile:vnetwork:stop";
  private final static String COMMAND_VNETWORK_UPDATE = "mobile:vnetwork:update";
  private final static String COMMAND_GET_NETWORK_SETTINGS = "mobile:network.settings:get";
  private final static String COMMAND_SET_NETWORK_SETTINGS = "mobile:network.settings:set";

  public Boolean generateHarFile;
  public Integer latency;
  public Integer bandwidthIn;
  public Integer bandwidthOut;
  public String blockedDestinations;
  public String blockedPorts;
  public Integer packetCorruption;
  public Integer packetLoss;
  public Integer correlation;
  public Integer packetDuplication;
  public Integer delayJitter;
  public Integer packetReordering;
  public String profile = null;
  public final static String PROFILE_2G_GPRS_GOOD = "2g_gprs_good";
  public final static String PROFILE_2G_GPRS_AVERAGE  = "2g_gprs_average";
  public final static String PROFILE_2G_GPRS_POOR  = "2g_gprs_poor";
  public final static String PROFILE_2G_EDGE_GOOD = "2g_edge_good";
  public final static String PROFILE_2G_EDGE_AVERAGE = "2g_edge_average";
  public final static String PROFILE_2G_EDGE_POOR = "2g_edge_poor";
  public final static String PROFILE_3G_UMTS_GOOD = "3g_umts_good";
  public final static String PROFILE_3G_UMTS_AVERAGE = "3g_umts_average";
  public final static String PROFILE_3G_UMTS_POOR = "3g_umts_poor";
  public final static String PROFILE_35G_HSPA_GOOD = "3.5g_hspa_good";
  public final static String PROFILE_35G_HSPA_AVERAGE = "3.5g_hspa_average";
  public final static String PROFILE_35G_HSPA_POOR = "3.5g_hspa_poor";
  public final static String PROFILE_35G_HSPA_PLUS_GOOD = "3.5g_hspa_plus_good";
  public final static String PROFILE_35G_HSPA_PLUS_AVERAGE = "3.5g_hspa_plus_average";
  public final static String PROFILE_35G_HSPA_PLUS_POOR = "3.5g_hspa_plus_poor";
  public final static String PROFILE_4G_LTE_GOOD = "4g_lte_good";
  public final static String PROFILE_4G_LTE_AVERAGE = "4g_lte_average";
  public final static String PROFILE_4G_LTE_POOR = "4g_lte_poor";
  public final static String PROFILE_4G_LTE_ADVANCED_GOOD = "4g_lte_advanced_good";
  public final static String PROFILE_4G_LTE_ADVANCED_AVERAGE = "4g_lte_advanced_average";
  public final static String PROFILE_4G_4G_LTE_ADVANCED_POOR = "4g_lte_advanced_poor";
  public final static String PROFILE_BANDWIDTH_GOOD = "bandwidth_good";

  public Network(RemoteWebDriver driver) 
  {
    Logger.LogDebug("Creating Network object");
    this.driver = driver;
  }

  public Boolean enableWiFi()
  {
    return modifyNetworkSetting("wifi", true);
  }

  public Boolean disableWiFi()
  {
    return modifyNetworkSetting("wifi", false);
  }

  public Boolean enableData() {
    return modifyNetworkSetting("data", true);
  }

  public Boolean disableData() {
    return modifyNetworkSetting("data", false);
  }

  public Boolean enableAirplaneMode() {
    return modifyNetworkSetting("airplanemode", true);
  }

  public Boolean disableAirplaneMode() {
    return modifyNetworkSetting("airplanemode", false);
  }

  public Boolean enableRadioMode() {
    return modifyNetworkSetting("radioMode", true);
  }

  public Boolean disableRadioMode() {
    return modifyNetworkSetting("radioMode", false);
  }

  public Boolean isDataEnabled()
  {
    return getNetworkSetting("data");
  }

  public Boolean isAirplaneModeEnabled()
  {
    return getNetworkSetting("airplanemode");
  }

  public Boolean isWiFiEnabled()
  {
    return getNetworkSetting("wifi");
  }

  private Boolean modifyNetworkSetting(String property, Boolean enable)
  {
    Map<String, Object> params = new HashMap<>();
    params.put(property, enable ? "enabled" : "disabled");
    return Helper.executeMethod(driver, COMMAND_SET_NETWORK_SETTINGS, params);
  }

  private Boolean getNetworkSetting(String property)
  {
    Map<String, Object> params = new HashMap<>();
    params.put("property", property);
    return Helper.executeMethod(driver, COMMAND_GET_NETWORK_SETTINGS, params);
  }

  /**
   * Starts network virtualization
   * @return
   */
  public Boolean startVirtualization()
  {
    return Helper.executeMethod(driver, COMMAND_VNETWORK_START, createParametersMap());
  }

  public Boolean startVirtualization(Boolean generateHarFile)
  {
    this.generateHarFile = generateHarFile;
    return startVirtualization();
  }

  public Boolean stopVirtualization()
  {
    Map<String, Object> params = new HashMap<>();
    if (generateHarFile)
      params.put("generateHarFile", "true");
    return Helper.executeMethod(driver, COMMAND_VNETWORK_STOP, params);
  }

  /**
   * Ends the network virtualization simulation.
   * @return
   */
  public Boolean stopVirtualization(Boolean generateHarFile)
  {
    this.generateHarFile = generateHarFile;
    return stopVirtualization();
  }
  
  public Boolean updateVirtualization()
  {
    return Helper.executeMethod(driver, COMMAND_VNETWORK_UPDATE, createParametersMap());
  }

  private Map<String, Object> createParametersMap()
  {
    Map<String, Object> params = new HashMap<>();
    
    if (generateHarFile != null)
      params.put("pcapFile", String.valueOf(generateHarFile));
    
    if (latency != null)
      params.put("latency", latency);

    if (bandwidthIn != null)
      params.put("bandwidth.in", bandwidthIn);

    if (bandwidthOut != null)
      params.put("bandwidth.out", bandwidthOut);

    if (blockedDestinations != null && !blockedDestinations.isEmpty())
      params.put("blockedDestinations", blockedDestinations);

    if (blockedPorts != null && !blockedPorts.isEmpty())
      params.put("blockedPorts", blockedPorts);

    if (packetCorruption != null)
      params.put("packetCorruption", packetCorruption);

    if (packetLoss != null)
      params.put("packetLoss", packetLoss);

    if (packetDuplication != null)
      params.put("packetDuplication", packetDuplication);

    if (packetReordering != null)
      params.put("packetReordering", packetReordering);

    if (correlation != null)
      params.put("correlation", correlation);

    if (delayJitter != null)
      params.put("delayJitter", delayJitter);

    if (profile != null)
      params.put("profile", profile);
    
    return params;
  }
}