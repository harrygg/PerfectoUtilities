package g.perfecto.utilities;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.remote.RemoteWebDriver;

public class Network {
	
	private RemoteWebDriver driver = null;
	private Map<String, Object> params = new HashMap<>();
	
	private final String COMMAND_NVSTART = "mobile:vnetwork:start";
	private final String COMMAND_NVSTOP = "mobile:vnetwork:stop";
	private final String COMMAND_NVUPDATE = "mobile:vnetwork:update";
	private final String COMMAND_GETNETWORKSETTINGS = "mobile:network.settings:get";
	private final String COMMAND_SETNETWORKSETTINGS = "mobile:network.settings:set";
	
	public Boolean generateHarFile = false;
	public int latency = 0;
	public int bandwidthIn = 0;
	public int bandwidthOut = 0;
	public String blockedDestinations = null;
	public String blockedPorts = null;
	public int packetCorruption = 0;
	public int packetLoss = 0;
	public int correlation = 0;
	public int packetDuplication = 0;
	public int delayJitter = 0;
	public int packetReordering = 0;
	
	public String profile = null;
	public final String profile_2g_gprs_good = "2g_gprs_good";
	public final String profile_2g_gprs_average  = "2g_gprs_average";
	public final String profile_2g_gprs_poor  = "2g_gprs_poor";
	public final String profile_2g_edge_good = "2g_edge_good";
	public final String profile_2g_edge_average = "2g_edge_average";
	public final String profile_2g_edge_poor = "2g_edge_poor";
	public final String profile_3g_umts_good = "3g_umts_good";
	public final String profile_3g_umts_average = "3g_umts_average";
	public final String profile_3g_umts_poor = "3g_umts_poor";
	public final String profile_35g_hspa_good = "3.5g_hspa_good";
	public final String profile_35g_hspa_average = "3.5g_hspa_average";
	public final String profile_35g_hspa_poor = "3.5g_hspa_poor";
	public final String profile_35g_hspa_plus_good = "3.5g_hspa_plus_good";
	public final String profile_35g_hspa_plus_average = "3.5g_hspa_plus_average";
	public final String profile_35g_hspa_plus_poor = "3.5g_hspa_plus_poor";
	public final String profile_4g_lte_good = "4g_lte_good";
	public final String profile_4g_lte_average = "4g_lte_average";
	public final String profile_4g_lte_poor = "4g_lte_poor";
	public final String profile_4g_lte_advanced_good = "4g_lte_advanced_good";
	public final String profile_4g_lte_advanced_average = "4g_lte_advanced_average";
	public final String profile_4g_4g_lte_advanced_poor = "4g_lte_advanced_poor";
	public final String profile_bandwidth_good = "bandwidth_good";
	
	
	public Network(RemoteWebDriver driver) {
		this.driver = driver;
	}
	
	public Boolean EnableWiFi()
	{
		return ModifyNetworkSetting("wifi", true);
	}
	
	public Boolean DisableWiFi()
	{
		return ModifyNetworkSetting("wifi", false);
	}
	
	public Boolean EnableData() {
		return ModifyNetworkSetting("data", true);
	}
	
	public Boolean DisableData() {
		return ModifyNetworkSetting("data", false);
	}
	
	
	public Boolean EnableAirplaneMode() {
		return ModifyNetworkSetting("airplanemode", true);
	}
	
	public Boolean DisableAirplaneMode() {
		return ModifyNetworkSetting("airplanemode", false);
	}
	
	
	public Boolean EnableRadioMode() {
		return ModifyNetworkSetting("radioMode", true);
	}
	
	public Boolean DisableRadioMode() {
		return ModifyNetworkSetting("radioMode", false);
	}
	
	public Boolean IsDataEnabled()
	{
		return GetNetworkSetting("data");
	}
	
	public Boolean IsAirplaneModeEnabled()
	{
		return GetNetworkSetting("airplanemode");
	}
	
	public Boolean IsWiFiEnabled()
	{
		return GetNetworkSetting("wifi");
	}
	
	private Boolean ModifyNetworkSetting(String property, Boolean enable)
	{
		Map<String, Object> params = new HashMap<>();
		params.put(property, enable ? "enabled" : "disabled");
				
		try {
			driver.executeScript(COMMAND_SETNETWORKSETTINGS, params);
			return true;
		} catch (Exception ex)
		{
			System.out.println("Failed to set " + property);
			ex.printStackTrace(); 
			return false;
		}
	}
	
	private Boolean GetNetworkSetting(String property)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("property", property);
				
		try {
			String retVal = (String) driver.executeScript(COMMAND_GETNETWORKSETTINGS, params);
			return retVal == "true" || retVal == "True";
		} catch (Exception ex)
		{
			System.out.println("Failed to retrieve property" + params.get("property"));
			ex.printStackTrace(); 
			return false;
		}
	}
	
	public Boolean StartVirtualization()
	{
		
		if (generateHarFile)
			params.put("generateHarFile", "true");
		
	    SetNetworkVirtualizationSettings();
			
		try {
			driver.executeScript(COMMAND_NVSTART, params);
		} catch (Exception ex)
		{
			System.out.println("Failed to start Network Virtualization");
			ex.printStackTrace(); 
			return false;
		}
		
		return true;
	}
	

	public Boolean StopVirtualization()
	{
		Map<String, Object> params = new HashMap<>();
		
		if (generateHarFile)
			params.put("pcapFile", "true");
		
		try {
			driver.executeScript(COMMAND_NVSTOP, params);
		} catch (Exception ex)
		{
			System.out.println("Failed to stop Network Virtualization");
			ex.printStackTrace(); 
			return false;
		}
		return true;
	}

	public Boolean UpdateVirtualization()
	{
		SetNetworkVirtualizationSettings();
		
		try {
			driver.executeScript(COMMAND_NVUPDATE, params);
		} catch (Exception ex)
		{
			System.out.println("Failed to update Network Virtualization");
			ex.printStackTrace(); 
			return false;
		}
		
		return true;
	}
	
	private void SetNetworkVirtualizationSettings()
	{
		if (latency > 0)
			params.put("latency", latency);
		
		if (bandwidthIn > 0)
			params.put("bandwidth.in", bandwidthIn);
		
		if (bandwidthOut > 0)
			params.put("bandwidth.out", bandwidthOut);
		
		if (blockedDestinations != null && blockedDestinations.trim().length() > 0)
			params.put("blockedDestinations", blockedDestinations);
		
		if (blockedPorts != null && blockedPorts.trim().length() > 0)
			params.put("blockedPorts", blockedPorts);
		
		if (packetCorruption > 0)
			params.put("packetCorruption", packetCorruption);
		
		if (packetLoss > 0)
			params.put("packetLoss", packetLoss);
		
		if (packetDuplication > 0)
			params.put("packetDuplication", packetDuplication);
		
		if (packetReordering > 0)
			params.put("packetReordering", packetReordering);
		
		if (correlation > 0)
			params.put("correlation", correlation);
		
		if (delayJitter > 0)
			params.put("delayJitter", delayJitter);
		
		if (profile != null)
			params.put("profile", profile);

	}
	
}