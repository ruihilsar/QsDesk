package com.tristatehvac.ciright.dataone.service.serviceImpl;

import java.io.DataOutputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.tristatehvac.ciright.dataone.service.DataOneBaseService;
import com.tristatehvac.ciright.dataone.service.DataOneParserService;
import com.tristatehvac.ciright.exception.CirightException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.tristatehvac.ciright.persistence.BaseEntity;
import org.apache.log4j.Logger;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class DataOneParserServiceImpl extends DataOneBaseService implements DataOneParserService {
	
	private static final Logger LOGGER = Logger.getLogger(DataOneParserServiceImpl.class);
	
	public void delete(BaseEntity obj) throws CirightException{
		getFacadeLookup().getPersistenceFacade().delete(obj);
	}

	public void save(BaseEntity obj) throws CirightException{
		getFacadeLookup().getPersistenceFacade().save(obj);
	}

	public void load(BaseEntity obj) throws CirightException{
		getFacadeLookup().getPersistenceFacade().load(obj);
	}
	
	public void update(BaseEntity obj) throws CirightException{
		getFacadeLookup().getPersistenceFacade().updateC(obj);
	}
	
	public List<String> getVehicleDetail(String year, String manufacturer, String product, String trim) throws CirightException, Exception {
		
		LOGGER.debug("***** ManufacturerTrimService : getVehicleDetail() *******");
		
		List<String> result = new ArrayList<String>();
		
		String postData = "client_id=7966&authorization_code=670890ccf4712d9a858ee63e092033d1762ba18a&decoder_query=";
		String decoderQuery = URLEncoder.encode(
						"<decoder_query>" +
							"<decoder_settings>" +
								"<display>full</display>" + 
								"<styles>on</styles>" + 
								"<style_data_packs>" + 
									"<basic_data>on</basic_data>" + 
									"<pricing>on</pricing>" + 
									"<engines>on</engines>" + 
									"<transmissions>on</transmissions>" + 
									"<specifications>on</specifications>" + 
									"<installed_equipment>on</installed_equipment>" +
									"<optional_equipment>off</optional_equipment>" + 
									"<generic_optional_equipment>on</generic_optional_equipment>" +
									"<colors>on</colors>" + 
									"<warranties>on</warranties>" +
									"<fuel_efficiency>on</fuel_efficiency>" +
									"<green_scores>on</green_scores>" +
									"<crash_test>on</crash_test>" +
								"</style_data_packs>" +
								"<common_data>on</common_data>" +
								"<common_data_packs>" + 
									"<basic_data>on</basic_data>" +
									"<pricing>on</pricing>" +
									"<engines>on</engines>" +
									"<transmissions>on</transmissions>" +
									"<specifications>on</specifications>" +
									"<installed_equipment>on</installed_equipment>" +
									"<generic_optional_equipment>on</generic_optional_equipment>" +
								"</common_data_packs>" +
							"</decoder_settings>" +
							"<query_requests>" +
								"<query_request identifier=\"Java Example\">" +
									"<vin></vin>" +
									"<year>" + year + "</year>" +
									"<make>" + manufacturer + "</make>" +
									"<model>" + product + "</model>" +
									"<trim>" + trim + "</trim>" +
									"<model_number/>" +
									"<package_code/>" +
									"<drive_type/>" +
									"<vehicle_type/>" +
									"<body_type/>" +
									"<doors/>" +
									"<bedlength/>" +
									"<wheelbase/>" +
									"<msrp/>" +
									"<invoice_price/>" +
									"<engine description=\"\">" +
										"<block_type/>" + 
											"<cylinders/>" +
										"<displacement/>" +
										"<fuel_type/>" +
									"</engine>" + 
									"<transmission description=\"\">" +
										"<trans_type/>" +
										"<trans_speeds/>" +
									"</transmission>" +
									"<optional_equipment_codes/>" +
									"<installed_equipment_descriptions/>" +
									"<interior_color description=\"\">" +
										"<color_code/>" +
									"</interior_color>" +
									"<exterior_color description=\"\">" +
										"<color_code/>" +
									"</exterior_color>" +
								"</query_request>" +
							"</query_requests>" +
						"</decoder_query>", "UTF-8");
		
		// Implement a hostname verify that does not verify the host name	
		class MyHostnameVerifier implements HostnameVerifier {
//			public boolean verify(String urlHostName, String certHostName){
//				return true;
//			}

			public boolean verify(String urlHost, SSLSession sslSession){
				return urlHost == sslSession.getPeerHost();
			}
		}

		MyHostnameVerifier verifier = new MyHostnameVerifier();
		HttpsURLConnection.setDefaultHostnameVerifier(verifier);

		// Create a trust manager that does not validate certificate chains
		TrustManager[] trustAllCerts = new TrustManager[] { 
			new X509TrustManager() {     
				public java.security.cert.X509Certificate[] getAcceptedIssuers() { 
            	return null;
				} 
				public void checkClientTrusted( 
					java.security.cert.X509Certificate[] certs, String authType) {
				} 
				public void checkServerTrusted( 
            	java.security.cert.X509Certificate[] certs, String authType) {	
				}
			} 
		}; 

		SSLContext sc = SSLContext.getInstance("SSL"); 
		sc.init(null, trustAllCerts, new java.security.SecureRandom()); 
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	
		URL decoder_url = new URL("https://www.dataonesoftware.com/webservices/vindecoder/decode");
		HttpsURLConnection connection = (HttpsURLConnection) decoder_url.openConnection();
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		connection.setDoInput(true);
		connection.setDoOutput(true);
			
		// Post the request data
		DataOutputStream output = new DataOutputStream(connection.getOutputStream());
		output.writeBytes(postData + decoderQuery);
		output.flush();
		output.close();
			
		// Retrieve the response data
//		DataInputStream input = new DataInputStream(connection.getInputStream()); 
//		// read in each character until end-of-stream is detected 
//		for (int c = input.read(); c != -1; c = input.read()) {

//		}
//		
//		input.close();
		
		//Create a "parser factory" for creating SAX parsers
        SAXParserFactory spfac = SAXParserFactory.newInstance();

        //Now use the parser factory to create a SAXParser object
        SAXParser sp = spfac.newSAXParser();

        //Create an instance of this class; it defines all the handler methods
        DataOneBaseService handler = new DataOneParserServiceImpl();

        //Finally, tell the parser to parse the input and notify the handler
        sp.parse(connection.getInputStream(), handler);
       
        String mechanicalGroup = handler.prepareMechanicalGroup();
        
        String safetyGroup = handler.prepareSafetyGroup();
        
        String interiorGroup = handler.prepareInteriorGroup();
        
        String exteriorGroup = handler.prepareExteriorGroup();
        
        String bodyTypeGroup = handler.prepareBodyTypeGroup();
        
        String driveTrainGroup = handler.prepareDriveTrainGroup();
        
        String EngineGroup = handler.prepareEngineGroup();
        
        String acceleration0_60Group = handler.prepare060Group();
        
        String powerGroup = handler.preparePowerGroup();
        
        String maxSeatingGroup = handler.prepareMaxSeatingGroup();
        
        String transmissionGroup = handler.prepareTransmissionGroup();
        
        String cargoVolumeGroup = handler.prepareCargoVolumeGroup();
        
        String warrantyGroup = handler.prepareWarrantyGroup();
        
        result.add(mechanicalGroup);
        result.add(exteriorGroup);
        result.add(interiorGroup);
        result.add(safetyGroup);
        result.add(bodyTypeGroup);
        result.add(driveTrainGroup);
        result.add(EngineGroup);
        result.add(acceleration0_60Group);
        result.add(powerGroup);
        result.add(maxSeatingGroup);
        result.add(transmissionGroup);
        result.add(cargoVolumeGroup);
        result.add(warrantyGroup);
        
		
		return result;
	}
	
	public void updateDataOneGroups(long yearId, long manufacturerId, long productId, long trimId, List<String> groupList) throws CirightException{
		
	}
}
