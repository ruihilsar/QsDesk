package com.tristatehvac.ciright.dataone.service;

import org.apache.log4j.Logger;

import com.tristatehvac.ciright.dataone.command.ExteriorModel;
import com.tristatehvac.ciright.dataone.command.InteriorModel;
import com.tristatehvac.ciright.dataone.command.MechanicalModel;
import com.tristatehvac.ciright.dataone.command.SafetyModel;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.tristatehvac.ciright.service.FacadeLookup;


public abstract class DataOneBaseService extends DefaultHandler{
	
	private FacadeLookup facadeLookup;
	
	private static final Logger LOGGER = Logger.getLogger(DataOneBaseService.class);
	
	public FacadeLookup getFacadeLookup() {
		return facadeLookup;
	}

	public void setFacadeLookup(FacadeLookup facadeLookUp) {
		this.facadeLookup = facadeLookUp;
	}

	private String value;
    private boolean inBasicData = false;
    private boolean inBodyType = false;
    private boolean inDriveType = false;
    private boolean inBrakeSystem = false;
    private boolean inRestraintType = false;
    private boolean inEngine = false;
    private boolean inEngineAvailability = false;
    private boolean inEngineBlockType = false;
    private boolean inEngineCylinders = false;
    private boolean inEngineDisplacement = false;
    private boolean inEngineFuelInduction = false;
    private boolean inEngineFuelQuality = false;
    private boolean inEngineFuelType = false;
    private boolean inEngineMSRP = false;
    private boolean inEngineInvoicePrice = false;
    private boolean inEngineMaxHp = false;
    private boolean inEngineMaxHpAt = false;
    private boolean inEngineMaxPayload = false;
    private boolean inEngineMaxTorque = false;
    private boolean inEngineMaxTorqueAt = false;
    private boolean inEngineOilCapacity = false;
    private boolean inEngineValves = false;
    private boolean inTransmission = false;
    private boolean inTransmissionType = false;
    private boolean inTransmissionDetailType = false;
    private boolean inTransmissionGears = false;
    private boolean inSpecification = false;
    private boolean inSpecificationCategory = false;
    private boolean inSpecificationDriveType = false;
    private boolean inSpecificationDriveTypeDriveType = false;
    private boolean inSpecificationFuelTanks = false;
    private boolean inSpecificationFuelTanksCapacity = false;
    private boolean inSpecificationPerformanceSpecifications = false;
    private boolean inSpecificationPerformanceSpecificationsAcceleration = false;
    private boolean inEquipment = false;
    private boolean inEqu4WDType = false;
    private boolean inEquAxleRatio = false;
    private boolean inEquCenterDifferential = false;
    private boolean inEquLockingDifferential = false;
    private boolean inEquAlternator = false;
    private boolean inEquStabilityControl = false;
    private boolean inEquTractionControl = false;
    private boolean inEquFrontShockType = false;
    private boolean inEquFrontSusClassification = false;
    private boolean inEquFrontSusType = false;
    private boolean inEquRearShockType = false;
    private boolean inEquRearSusClassification = false;
    private boolean inEquRearSusType = false;
    private boolean inEquStabilizerBar = false;
    private boolean inEquFrontAirbags = false;
    private boolean inEquPassengerAirbagDeactivation = false;
    private boolean inEquSideAirbags = false;
    private boolean inEquSideCurtainAirbags = false;
    private boolean inEquBrakesABS = false;
    private boolean inEquBrakesBrakingAssist = false;
    private boolean inEquBrakesElectronicBrakeforceDistribution = false;
    private boolean inEquBrakesEmergencyBrakingAssist = false;
    private boolean inEquChildSafetyLocks = false;
    private boolean inEquChildSafetySeatAnchors = false;
    private boolean inEquSafetyActiveHeadRestraints = false;
    private boolean inEquSafetyBodySideReinforcements = false;
    private boolean inEquSafetyCrumpleZones = false;
    private boolean inEquSafetyImpactAbsorbingBumpers = false;
    private boolean inEquSafetyImpactSensor = false;
    private boolean inEquSafetyParkingSensors = false;
    private boolean inEquSeatbeltsFront = false;
    private boolean inEquSeatbeltsSecondRow = false;
    private boolean inEquSecurityAtas = false;
    private boolean inSpecificationMSS = false;
    private boolean inSpecificationWT = false;
    private boolean inSpecificationMSSGroundClearance = false;
    private boolean inSpecificationMSSWheelbase = false;
    private boolean inSpecificationWTFrontTireType = false;
    private boolean inSpecificationWTFrontWheelDiameter = false;
    private boolean inEquExteriorFeaturesExhaust = false;
    private boolean inEquExteriorFeaturesExhaustTipColor = false;
    private boolean inEquExteriorFeaturesGrilleColor = false;
    private boolean inEquExteriorFeaturesRearSpoiler = false;
    private boolean inEquExteriorFeaturesRearSpoilerColor = false;
    private boolean inEquExteriorFeaturesWindowTrim = false;
    private boolean inEquLightsDaytimeRunningLights = false;
    private boolean inEquLightsHeadlights = false;
    private boolean inEquMirrorsExteriorMirrors = false;
    private boolean inEquSpareTireSize = false;
    private boolean inEquWheelRimType = false;
    private boolean inEquTiresType = false;
    private boolean inEquWheelSpokes = false;
    private boolean inEquWindowsFrontWipers = false;
    private boolean inEquWindowsTinted = false;
    private boolean inSpecificationInteriorDimensions = false;
    private boolean inSpecificationSeating = false;
    private boolean inSpecificationInteriorDimensionsCargoVolume = false;
    private boolean inSpecificationInteriorDimensionsInteriorVolume = false;
    private boolean inSpecificationSeatingMaxSeating = false;
    private boolean inSpecificationSeatingSeatingRows = false;
    private boolean inSpecificationSeatingStandardSeating = false;
    
    private boolean inEquAuxiliaryAudioInput = false;
    private boolean inEquCDChanger = false;
    private boolean inEquDigitalSoundProcessing = false;
    private boolean inEquHardDrive = false;
    private boolean inEquInDashCD = false;
    private boolean inEquMP3Player = false;
    private boolean inEquPremiumBrand = false;
    private boolean inEquRadio = false;
    private boolean inEquSatelliteRadio = false;
    private boolean inEquSurroundAudio = false;
    private boolean inEquTotalNumberOfSpeakers = false;
    private boolean inEquArmrests = false;
    private boolean inEquDashTrim = false;
    private boolean inEquSteeringWheelTrim = false;
    private boolean inEquCenterConsole = false;
    private boolean inEquMultiFunctionRemote = false;
    private boolean inEquPowerOutlets = false;
    private boolean inEquRearViewCamera = false;
    private boolean inEquRearViewMonitor = false;
    private boolean inEquSteeringWheel = false;
    private boolean inEquSteeringWheelMountControls = false;
    private boolean inEquStorage = false;
    private boolean inEquAdjustableLumbarSupport = false;
    private boolean inEquHeated = false;
    private boolean inEquCompass = false;
    private boolean inEquExternalTemperatureDisplay = false;
    private boolean inEquFuelEconomyDisplay = false;
    private boolean inEquTachometer = false;
    private boolean inEquTripComputer = false;
    private boolean inEquTripOdometer = false;
    private boolean inEquWoodgrainAccents = false;
    private boolean inEquPowerDoorLocks = false;
    private boolean inEquCenterArmrest = false;
    private boolean inEquFolding = false;
    private boolean inEquType = false;
    private boolean inEquSunroof = false;
    private boolean inEquFrontSeatType = false;
    private boolean inEquUpholstery = false;
    private boolean inEquNavigationSystem = false;
    private boolean inEquPhone = false;
    private boolean inEquRealTimeTraffic = false;
    private boolean inEquVideoMonitorSize = false;
    private boolean inEquVideoMonitorLocation = false;
    private boolean inEquOneTouchWindows = false;
    private boolean inEquPowerWindows = false;
    private boolean inWarranty = false;
    
    private SafetyModel safety = new SafetyModel();
    private MechanicalModel mechanical = new MechanicalModel();
    private InteriorModel interior = new InteriorModel();
    private ExteriorModel exterior = new ExteriorModel();
    private String bodyType = null;
    private String driveTrain = null;
    private String engineBlockType = null;
    private String engineCylinder = null;
    private String acceleration0_60 = null;
    private String max_hp = null;
    private String max_hp_at = null;
    private String maxSeating = null;
    private String transmissionName = null;
    private String cargoVolume = null;
    private String warranty = "";

    /*
     * When the parser encounters plain text (not XML elements),
     * it calls(this method, which accumulates them in a string buffer
     */
    public void characters(char[] buffer, int start, int length) {
    	value = new String(buffer, start, length);
    }
    
    /*
     * Every time the parser encounters the beginning of a new element,
     * it calls this method, which resets the string buffer
     */ 
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    	value = "";
    	
    	/** common data part **/
    	if (qName.equals("basic_data")) {
    		// one of the high levers
    		inBasicData = true;
    		inEngine = false;
    		inTransmission = false;
    		inSpecification = false;
    		inEquipment = false;
        }
    	if(qName.equals("drive_type") && inBasicData){
    		// the beginning of Mechanical Object
    		inDriveType = true;
    	}
    	if(qName.equals("brake_system") && inBasicData){
    		inBrakeSystem = true;
    	}
    	if(qName.equals("restraint_type") && inBasicData){
    		// the beginning of Safety Object
    		inRestraintType = true;
    	}
    	if(qName.equals("body_type") && inBasicData){
    		// the beginning of Mechanical Object
    		inBodyType = true;
    	}
    	/** end common data **/
    	
    	
    	/** engines part **/
    	if(qName.equals("engine")){
    		// one of the high levers
    		inEngine = true;
    		inBasicData = false;
    		inTransmission = false;
    		inSpecification = false;
    		inEquipment = false;
    		
    		for(int i = 0; i < attributes.getLength(); i++){

                String attName=attributes.getQName(i);
                if(attName.equals("name")){
                	this.mechanical.setEngine_name(attributes.getValue(i));
                }
                if(attName.equals("brand")){
                	this.mechanical.setEngine_brand(attributes.getValue(i));
                }
            }
    	}
    	if(qName.equals("availability") && inEngine){
    		inEngineAvailability = true;
    	}
    	if(qName.equals("block_type") && inEngine){
    		inEngineBlockType = true;
    	}
    	if(qName.equals("cylinders") && inEngine){
    		inEngineCylinders = true;
    	}
    	if(qName.equals("displacement") && inEngine){
    		inEngineDisplacement = true;
    	}
    	if(qName.equals("fuel_induction") && inEngine){
    		inEngineFuelInduction = true;
    	}
    	if(qName.equals("fuel_quality") && inEngine){
    		inEngineFuelQuality = true;
    	}
    	if(qName.equals("fuel_type") && inEngine){
    		inEngineFuelType = true;
    	}
    	if(qName.equals("msrp") && inEngine){
    		inEngineMSRP = true;
    	}
    	if(qName.equals("invoice_price") && inEngine){
    		inEngineInvoicePrice = true;
    	}
    	if(qName.equals("max_hp") && inEngine){
    		inEngineMaxHp = true;
    	}
    	if(qName.equals("max_hp_at") && inEngine){
    		inEngineMaxHpAt = true;
    	}
    	if(qName.equals("max_payload") && inEngine){
    		inEngineMaxPayload = true;
    	}
    	if(qName.equals("max_torque") && inEngine){
    		inEngineMaxTorque = true;
    	}
    	if(qName.equals("max_torque_at") && inEngine){
    		inEngineMaxTorqueAt = true;
    	}
    	if(qName.equals("oil_capacity") && inEngine){
    		inEngineOilCapacity = true;
    	}
    	if(qName.equals("valves") && inEngine){
    		inEngineValves = true;
    	}
    	/** end engines **/
    	
    	
    	/** transmissions part **/
    	if(qName.equals("transmission")){
    		// one of the high levers
    		inTransmission = true;
    		inEngine = false;
    		inBasicData = false;
    		inSpecification = false;
    		inEquipment = false;
    		
    		for(int i = 0; i < attributes.getLength(); i++){

                String attName=attributes.getQName(i);
                if(attName.equals("name")){
                	this.mechanical.setTransmission_name(attributes.getValue(i));
                	this.transmissionName = attributes.getValue(i);
                }
                if(attName.equals("brand")){
                	this.mechanical.setTransmission_brand(attributes.getValue(i));
                }
            }
    	}
    	if(qName.equals("type") && inTransmission){
    		inTransmissionType = true;
    	}
    	if(qName.equals("detail_type") && inTransmission){
    		inTransmissionDetailType = true;
    	}
    	if(qName.equals("gears") && inTransmission){
    		inTransmissionGears = true;
    	}
    	/** end transmissions **/
    	
    	
    	/** specification part **/
    	if(qName.equals("specifications")){
    		inSpecification = true;
    		inEngine = false;
    		inBasicData = false;
    		inTransmission = false;
    		inEquipment = false;
    	}
    	if(qName.equals("category") && inSpecification){
    		inSpecificationCategory = true;
    		
    		for(int i = 0; i < attributes.getLength(); i++){
    			String attrName = attributes.getQName(i);
    			if(attrName.equals("name")){
    				String categoryName = attributes.getValue(i);
    				
    				/** mechanical group **/
    				if(categoryName.equals("Drive Type")){
    					inSpecificationDriveType = true;
    				}
    				if(categoryName.equals("Fuel Tanks")){
    					inSpecificationFuelTanks = true;
    				}
    				if(categoryName.equals("Performance Specifications")){
    					inSpecificationPerformanceSpecifications = true;
    				}
    				/** end mechanical group **/
    				
    				/** interior group **/
    				if(categoryName.equals("Interior Dimensions")){
    					inSpecificationInteriorDimensions= true;
    				}
    				if(categoryName.equals("Seating")){
    					inSpecificationSeating= true;
    				}
    				/** end interior group **/
    				
    				/** exterior group **/
    				if(categoryName.equals("Measurements of Size and Shape")){
    					inSpecificationMSS = true;
    				}
    				if(categoryName.equals("Wheels and Tires")){
    					inSpecificationWT = true;
    				}
    				/** end exterior group **/
    			}
    		}
    	}
    	if(qName.equals("specification") && inSpecificationDriveType){
    		
    		for(int i = 0; i < attributes.getLength(); i++){
    			String attrName = attributes.getQName(i);
    			if(attrName.equals("name")){
    				String speName = attributes.getValue(i);
    				if(speName.equals("Drive Type")){
    					inSpecificationDriveTypeDriveType = true;
    				}
    			}
    		}
    	}
    	if(qName.equals("specification") && inSpecificationFuelTanks){
    		
    		for(int i = 0; i < attributes.getLength(); i++){
    			String attrName = attributes.getQName(i);
    			if(attrName.equals("name")){
    				String speName = attributes.getValue(i);
    				if(speName.equals("Fuel Tank 1 Capacity (Gallons)")){
    					inSpecificationFuelTanksCapacity = true;
    				}
    			}
    		}
    	}
    	if(qName.equals("specification") && inSpecificationPerformanceSpecifications){
    		
    		for(int i = 0; i < attributes.getLength(); i++){
    			String attrName = attributes.getQName(i);
    			if(attrName.equals("name")){
    				String speName = attributes.getValue(i);
    				if(speName.equals("Acceleration (0-60MPH)")){
    					inSpecificationPerformanceSpecificationsAcceleration = true;
    				}
    			}
    		}
    	}
    	if(qName.equals("specification") && inSpecificationInteriorDimensions){
    		
    		for(int i = 0; i < attributes.getLength(); i++){
    			String attrName = attributes.getQName(i);
    			if(attrName.equals("name")){
    				String speName = attributes.getValue(i);
    				if(speName.equals("Cargo Volume")){
    					inSpecificationInteriorDimensionsCargoVolume = true;
    				}
    				if(speName.equals("Interior Volume")){
    					inSpecificationInteriorDimensionsInteriorVolume = true;
    				}
    			}
    		}
    	}
    	if(qName.equals("specification") && inSpecificationSeating){
    		
    		for(int i = 0; i < attributes.getLength(); i++){
    			String attrName = attributes.getQName(i);
    			if(attrName.equals("name")){
    				String speName = attributes.getValue(i);
    				if(speName.equals("Max Seating")){
    					inSpecificationSeatingMaxSeating = true;
    				}
    				if(speName.equals("Seating Rows")){
    					inSpecificationSeatingSeatingRows = true;
    				}
    				if(speName.equals("Standard Seating")){
    					inSpecificationSeatingStandardSeating = true;
    				}
    			}
    		}
    	}
    	if(qName.equals("specification") && inSpecificationMSS){
    		
    		for(int i = 0; i < attributes.getLength(); i++){
    			String attrName = attributes.getQName(i);
    			if(attrName.equals("name")){
    				String speName = attributes.getValue(i);
    				if(speName.equals("Ground Clearance")){
    					inSpecificationMSSGroundClearance = true;
    				}
    				if(speName.equals("Wheelbase")){
    					inSpecificationMSSWheelbase = true;
    				}
    			}
    		}
    	}
		if(qName.equals("specification") && inSpecificationWT){
			
			for(int i = 0; i < attributes.getLength(); i++){
				String attrName = attributes.getQName(i);
				if(attrName.equals("name")){
					String speName = attributes.getValue(i);
					if(speName.equals("Front Tire Type")){
						inSpecificationWTFrontTireType = true;
					}
					if(speName.equals("Front Wheel Diameter")){
						inSpecificationWTFrontWheelDiameter = true;
					}
				}
			}
		}
    	/** end specification **/
    	
    	
    	/** equipment part **/
    	if(qName.equals("equipment")){
    		inEquipment = true;
    		inEngine = false;
    		inBasicData = false;
    		inTransmission = false;
    		inSpecification = false;
    		
    		for(int i = 0; i < attributes.getLength(); i++){
    			String attrName = attributes.getQName(i);
    			if(attrName.equals("name")){
    				String equipName = attributes.getValue(i);
    				
    				/** mechanical group **/
    				if(equipName.equals("4WD Type")){
    					inEqu4WDType = true;
    				}
    				if(equipName.equals("Axle ratio")){
    					inEquAxleRatio = true;
    				}
    				if(equipName.equals("Center differential")){
    					inEquCenterDifferential = true;
    				}
    				if(equipName.equals("Locking differential")){
    					inEquLockingDifferential = true;
    				}
    				if(equipName.equals("Alternator")){
    					inEquAlternator = true;
    				}
    				if(equipName.equals("Stability control")){
    					inEquStabilityControl = true;
    				}
    				if(equipName.equals("Traction control")){
    					inEquTractionControl = true;
    				}
    				if(equipName.equals("Front Shock Type")){
    					inEquFrontShockType = true;
    				}
    				if(equipName.equals("Front Suspension Classification")){
    					inEquFrontSusClassification = true;
    				}
    				if(equipName.equals("Front Suspension Type")){
    					inEquFrontSusType = true;
    				}
    				if(equipName.equals("Rear Shock Type")){
    					inEquRearShockType = true;
    				}
    				if(equipName.equals("Rear Suspension Classification")){
    					inEquRearSusClassification = true;
    				}
    				if(equipName.equals("Rear Suspension Type")){
    					inEquRearSusType = true;
    				}
    				if(equipName.equals("Stabilizer bar(s)")){
    					inEquStabilizerBar = true;
    				}
    				/** end mechanical group **/
    				
    				/** safety group **/
    				if(equipName.equals("Front airbags")){
    					inEquFrontAirbags = true;
    				}
    				if(equipName.equals("Passenger airbag deactivation")){
    					inEquPassengerAirbagDeactivation = true;
    				}
    				if(equipName.equals("Side airbags")){
    					inEquSideAirbags = true;
    				}
    				if(equipName.equals("Side curtain airbags")){
    					inEquSideCurtainAirbags = true;
    				}
    				if(equipName.equals("ABS")){
    					inEquBrakesABS = true;
    				}
    				if(equipName.equals("Braking assist")){
    					inEquBrakesBrakingAssist = true;
    				}
    				if(equipName.equals("Electronic brakeforce distribution")){
    					inEquBrakesElectronicBrakeforceDistribution = true;
    				}
    				if(equipName.equals("Emergency braking assist")){
    					inEquBrakesEmergencyBrakingAssist = true;
    				}
    				if(equipName.equals("Child safety locks")){
    					inEquChildSafetyLocks = true;
    				}
    				if(equipName.equals("Child seat anchors")){
    					inEquChildSafetySeatAnchors = true;
    				}
    				if(equipName.equals("Active head restraints")){
    					inEquSafetyActiveHeadRestraints = true;
    				}
    				if(equipName.equals("Body side reinforcements")){
    					inEquSafetyBodySideReinforcements = true;
    				}
    				if(equipName.equals("Crumple zones")){
    					inEquSafetyCrumpleZones = true;
    				}
    				if(equipName.equals("Impact absorbing bumpers")){
    					inEquSafetyImpactAbsorbingBumpers = true;
    				}
    				if(equipName.equals("Impact sensor")){
    					inEquSafetyImpactSensor = true;
    				}
    				if(equipName.equals("Parking sensors")){
    					inEquSafetyParkingSensors = true;
    				}
    				if(equipName.equals("Front seatbelts")){
    					inEquSeatbeltsFront = true;
    				}
    				if(equipName.equals("Second row seatbelts")){
    					inEquSeatbeltsSecondRow = true;
    				}
    				if(equipName.equals("Anti-theft alarm system")){
    					inEquSecurityAtas = true;
    				}
    				/** end safety group **/
    				
    				/** interior group **/
    				if(equipName.equals("Auxiliary audio input")){
    					inEquAuxiliaryAudioInput = true;
    				}
    				if(equipName.equals("CD changer")){
    					inEquCDChanger = true;
    				}
    				if(equipName.equals("Digital Sound Processing")){
    					inEquDigitalSoundProcessing = true;
    				}
    				if(equipName.equals("Hard Drive")){
    					inEquHardDrive = true;
    				}
    				if(equipName.equals("In-Dash CD")){
    					inEquInDashCD = true;
    				}
    				if(equipName.equals("MP3 player")){
    					inEquMP3Player = true;
    				}
    				if(equipName.equals("Premium Brand")){
    					inEquPremiumBrand = true;
    				}
    				if(equipName.equals("Radio")){
    					inEquRadio = true;
    				}
    				if(equipName.equals("Satellite Radio")){
    					inEquSatelliteRadio = true;
    				}
    				if(equipName.equals("Surround audio")){
    					inEquSurroundAudio = true;
    				}
    				if(equipName.equals("Total Number of speakers")){
    					inEquTotalNumberOfSpeakers = true;
    				}
    				if(equipName.equals("Armrests")){
    					inEquArmrests = true;
    				}
    				if(equipName.equals("Dash trim")){
    					inEquDashTrim = true;
    				}
    				if(equipName.equals("Steering wheel trim")){
    					inEquSteeringWheelTrim = true;
    				}
    				if(equipName.equals("Center console")){
    					inEquCenterConsole = true;
    				}
    				if(equipName.equals("Multi-function remote")){
    					inEquMultiFunctionRemote = true;
    				}
    				if(equipName.equals("Power outlet(s)")){
    					inEquPowerOutlets = true;
    				}
    				if(equipName.equals("Rear view camera")){
    					inEquRearViewCamera = true;
    				}
    				if(equipName.equals("Rear view monitor")){
    					inEquRearViewMonitor = true;
    				}
    				if(equipName.equals("Steering wheel")){
    					inEquSteeringWheel = true;
    				}
    				if(equipName.equals("Steering wheel mounted controls")){
    					inEquSteeringWheelMountControls = true;
    				}
    				if(equipName.equals("Storage")){
    					inEquStorage = true;
    				}
    				if(equipName.equals("Adjustable lumbar support")){
    					inEquAdjustableLumbarSupport = true;
    				}
    				if(equipName.equals("Heated")){
    					inEquHeated = true;
    				}
    				if(equipName.equals("Compass")){
    					inEquCompass = true;
    				}
    				if(equipName.equals("External temperature display")){
    					inEquExternalTemperatureDisplay = true;
    				}
    				if(equipName.equals("Fuel economy display")){
    					inEquFuelEconomyDisplay = true;
    				}
    				if(equipName.equals("Tachometer")){
    					inEquTachometer = true;
    				}
    				if(equipName.equals("Trip computer")){
    					inEquTripComputer = true;
    				}
    				if(equipName.equals("Trip odometer")){
    					inEquTripOdometer = true;
    				}
    				if(equipName.equals("Woodgrain accents")){
    					inEquWoodgrainAccents = true;
    				}
    				if(equipName.equals("Power door locks")){
    					inEquPowerDoorLocks = true;
    				}
    				if(equipName.equals("Center armrest")){
    					inEquCenterArmrest = true;
    				}
    				if(equipName.equals("Folding")){
    					inEquFolding = true;
    				}
    				if(equipName.equals("Type")){
    					inEquType = true;
    				}
    				if(equipName.equals("Sunroof")){
    					inEquSunroof = true;
    				}
    				if(equipName.equals("Front seat type")){
    					inEquFrontSeatType = true;
    				}
    				if(equipName.equals("Upholstery")){
    					inEquUpholstery = true;
    				}
    				if(equipName.equals("Navigation system")){
    					inEquNavigationSystem = true;
    				}
    				if(equipName.equals("Phone")){
    					inEquPhone = true;
    				}
    				if(equipName.equals("Real Time Traffic")){
    					inEquRealTimeTraffic = true;
    				}
    				if(equipName.equals("Video Monitor Size")){
    					inEquVideoMonitorSize = true;
    				}
    				if(equipName.equals("Video monitor location")){
    					inEquVideoMonitorLocation = true;
    				}
    				if(equipName.equals("One-touch windows")){
    					inEquOneTouchWindows = true;
    				}
    				if(equipName.equals("Power windows")){
    					inEquPowerWindows = true;
    				}
    				/** end interior group **/
    				
    				/** exterior group **/
    				if(equipName.equals("Exhaust")){
    					inEquExteriorFeaturesExhaust = true;
    				}
    				if(equipName.equals("Exhaust tip color")){
    					inEquExteriorFeaturesExhaustTipColor = true;
    				}
    				if(equipName.equals("Grille color")){
    					inEquExteriorFeaturesGrilleColor = true;
    				}
    				if(equipName.equals("Rear spoiler")){
    					inEquExteriorFeaturesRearSpoiler = true;
    				}
    				if(equipName.equals("Rear spoiler color")){
    					inEquExteriorFeaturesRearSpoilerColor = true;
    				}
    				if(equipName.equals("Window trim")){
    					inEquExteriorFeaturesWindowTrim = true;
    				}
    				if(equipName.equals("Daytime running lights")){
    					inEquLightsDaytimeRunningLights = true;
    				}
    				if(equipName.equals("Headlights")){
    					inEquLightsHeadlights = true;
    				}
    				if(equipName.equals("Exterior mirrors")){
    					inEquMirrorsExteriorMirrors = true;
    				}
    				if(equipName.equals("Size")){
    					inEquSpareTireSize = true;
    				}
    				if(equipName.equals("Rim type")){
    					inEquWheelRimType = true;
    				}
    				if(equipName.equals("Type")){
    					inEquTiresType = true;
    				}
    				if(equipName.equals("Wheel spokes")){
    					inEquWheelSpokes = true;
    				}
    				if(equipName.equals("Front wipers")){
    					inEquWindowsFrontWipers = true;
    				}
    				if(equipName.equals("Tinted")){
    					inEquWindowsTinted = true;
    				}
    				/** end exterior group **/
    			}
    		}
    	}
    	/** end equipment **/
    	
    	/** warranty part **/
    	if(qName.equals("warranty")){
    		
    		inWarranty = true;
    		for(int i = 0; i < attributes.getLength(); i++){
    			String attrName = attributes.getQName(i);
    			if(attrName.equals("name")){
    				String warrantyName = attributes.getValue(i);
    				this.warranty = this.warranty.isEmpty() ? "name#" + warrantyName : this.warranty + "|name#" + warrantyName;
    			}
    		}
    	}
    	/** end warranty **/
    }

    /*
     * When the parser encounters the end of an element, it calls this method
     */
    public void endElement(String uri, String localName, String qName) throws SAXException {
    	
    	/** common data part **/
    	if(qName.equals("body_type") && inBasicData){
    		inBodyType = false;
        	this.bodyType = value;
        }
    	if(qName.equals("drive_type") && inBasicData){
    		inDriveType = false;
        	this.mechanical.setBasic_driveType(value);
        	this.driveTrain = value;
        }
        if(qName.equals("brake_system") && inBasicData){
        	inBrakeSystem = false;
        	this.mechanical.setBasic_brakeSystem(value);
        }
        if(qName.equals("restraint_type") && inBasicData){
        	inRestraintType = false;
        	this.safety.setBasic_restraintType(value);
        }
        if(qName.equals("basic_data")){
        	inBasicData = false;
        }
        /** end common data **/
        
        
        /** engines part **/
    	if(qName.equals("availability") && inEngine){
    		inEngineAvailability = false;
        	this.mechanical.setEngine_availability(value);
        }
        if(qName.equals("block_type") && inEngine){
        	inEngineBlockType = false;
        	this.mechanical.setEngine_blockType(value);
        	this.engineBlockType = value;
        }
        if(qName.equals("cylinders") && inEngine){
        	inEngineCylinders = false;
        	this.mechanical.setEngine_cylinders(value);
        	this.engineCylinder = value;
        }
        if(qName.equals("displacement") && inEngine){
        	inEngineDisplacement = false;
        	this.mechanical.setEngine_displacement(value);
        }
        if(qName.equals("fuel_induction") && inEngine){
        	inEngineFuelInduction = false;
        	this.mechanical.setEngine_fuelInduction(value);
        }
        if(qName.equals("fuel_quality") && inEngine){
        	inEngineFuelQuality = false;
        	this.mechanical.setEngine_fuelQuality(value);
        }
        if(qName.equals("fuel_type") && inEngine){
        	inEngineFuelType = false;
        	this.mechanical.setEngine_fuelType(value);
        }
        if(qName.equals("msrp") && inEngine){
        	inEngineMSRP = false;
        	this.mechanical.setEngine_msrp(value);
        }
        if(qName.equals("invoice_price") && inEngine){
        	inEngineInvoicePrice = false;
        	this.mechanical.setEngine_invoicePrice(value);
        }
        if(qName.equals("max_hp") && inEngine){
        	inEngineMaxHp = false;
        	this.mechanical.setEngine_maxHp(value);
        	this.max_hp = value;
        }
        if(qName.equals("max_hp_at") && inEngine){
        	inEngineMaxHpAt = false;
        	this.mechanical.setEngine_maxHpAt(value);
        	this.max_hp_at = value;
        }
        if(qName.equals("max_payload") && inEngine){
    		inEngineMaxPayload = false;
        	this.mechanical.setEngine_maxPayload(value);
        }
        if(qName.equals("max_torque") && inEngine){
        	inEngineMaxTorque = false;
        	this.mechanical.setEngine_maxTorque(value);
        }
        if(qName.equals("max_torque_at") && inEngine){
        	inEngineMaxTorqueAt = false;
        	this.mechanical.setEngine_maxTorqueAt(value);
        }
        if(qName.equals("oil_capacity") && inEngine){
        	inEngineOilCapacity = false;
        	this.mechanical.setEngine_oilCapacity(value);
        }
        if(qName.equals("valves") && inEngine){
        	inEngineValves = false;
        	this.mechanical.setEngine_valves(value);
        }
        if(qName.equals("engine")){
        	inEngine = false;
        }
        /** end engines **/
        
        
        /** transmissions part **/
        if(qName.equals("type") && inTransmission){
        	inTransmissionType = false;
        	this.mechanical.setTransmission_type(value);
        }
        if(qName.equals("detail_type") && inTransmission){
        	inTransmissionDetailType = false;
        	this.mechanical.setTransmission_detailType(value);
        }
        if(qName.equals("gears") && inTransmission){
        	inTransmissionGears = false;
        	this.mechanical.setTransmission_gears(value);
        }
        if(qName.equals("transmission")){
        	inTransmission = false;
        }
        /** end transmissions **/
        
        
        /** specification part **/
    	if(qName.equals("specification") && inSpecificationDriveTypeDriveType){
    		inSpecificationDriveTypeDriveType = false;
    		this.mechanical.setSpe_driveType_driveType(value);
    	}
    	if(qName.equals("category") && inSpecificationDriveType){
    		inSpecificationDriveType = false;
    		inSpecificationCategory = false;
    	}
    	if(qName.equals("specification") && inSpecificationFuelTanksCapacity){
    		inSpecificationFuelTanksCapacity = false;
    		this.mechanical.setSpe_fuelTanks_fuelTanks1Capacity(value);
    	}
    	if(qName.equals("category") && inSpecificationFuelTanks){
    		inSpecificationFuelTanks = false;
    		inSpecificationCategory = false;
    	}
    	if(qName.equals("specification") && inSpecificationPerformanceSpecificationsAcceleration){
    		inSpecificationPerformanceSpecificationsAcceleration = false;
    		this.mechanical.setSpe_performance_acceleration(value);
    		this.acceleration0_60 = value;
    	}
    	if(qName.equals("category") && inSpecificationPerformanceSpecifications){
    		inSpecificationPerformanceSpecifications = false;
    		inSpecificationCategory = false;
    	}
    	
    	if(qName.equals("specification") && inSpecificationInteriorDimensionsCargoVolume){
    		inSpecificationInteriorDimensionsCargoVolume = false;
    		this.interior.setSpe_id_cargoVolume(value);
    		this.cargoVolume = value;
    	}
    	if(qName.equals("specification") && inSpecificationInteriorDimensionsInteriorVolume){
    		inSpecificationInteriorDimensionsInteriorVolume = false;
    		this.interior.setSpe_id_interiorVolumn(value);
    	}
    	if(qName.equals("category") && inSpecificationInteriorDimensions){
    		inSpecificationInteriorDimensions = false;
    		inSpecificationCategory = false;
    	}
    	
    	if(qName.equals("specification") && inSpecificationSeatingMaxSeating){
    		inSpecificationSeatingMaxSeating = false;
    		this.interior.setSpe_seating_maxSeating(value);
    		this.maxSeating = value;
    	}
    	if(qName.equals("specification") && inSpecificationSeatingSeatingRows){
    		inSpecificationSeatingSeatingRows = false;
    		this.interior.setSpe_seating_seatingRows(value);
    	}
    	if(qName.equals("specification") && inSpecificationSeatingStandardSeating){
    		inSpecificationSeatingStandardSeating = false;
    		this.interior.setSpe_seating_standardSeating(value);
    	}
    	if(qName.equals("category") && inSpecificationSeating){
    		inSpecificationSeating = false;
    		inSpecificationCategory = false;
    	}
    	
    	if(qName.equals("specification") && inSpecificationMSSGroundClearance){
    		inSpecificationMSSGroundClearance = false;
    		this.exterior.setSpe_mss_groundClearance(value);
    	}
    	if(qName.equals("specification") && inSpecificationMSSWheelbase){
    		inSpecificationMSSWheelbase = false;
    		this.exterior.setSpe_mss_wheelbase(value);
    	}
    	if(qName.equals("category") && inSpecificationMSS){
    		inSpecificationMSS = false;
    		inSpecificationCategory = false;
    	}
    	
    	if(qName.equals("specification") && inSpecificationWTFrontTireType){
    		inSpecificationWTFrontTireType = false;
    		this.exterior.setSpe_wt_frontTireType(value);
    	}
    	if(qName.equals("specification") && inSpecificationWTFrontWheelDiameter){
    		inSpecificationWTFrontWheelDiameter = false;
    		this.exterior.setSpe_wt_frontWheelDiameter(value);
    	}
    	if(qName.equals("category") && inSpecificationWT){
    		inSpecificationWT = false;
    		inSpecificationCategory = false;
    	}
    	
    	if(qName.equals("specifications")){
    		inSpecification = false;
    	}
    	/** end specification **/
    	
    	
    	/** equipment part **/
    	
    	/** mechanical group **/
    	if(qName.equals("value") && inEqu4WDType){
    		String prefixValue = this.mechanical.getEqu_drivetrain_4WDType();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.mechanical.setEqu_drivetrain_4WDType(prefixValue + "," + value);
	    		}
	    		else {
	    			this.mechanical.setEqu_drivetrain_4WDType(value);
	    		}
    		}
    	}
    	
    	if(qName.equals("value") && inEquAxleRatio){
    		String prefixValue = this.mechanical.getEqu_drivetrain_axleRatio();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.mechanical.setEqu_drivetrain_axleRatio(prefixValue + "," + value);
	    		}
	    		else {
	    			this.mechanical.setEqu_drivetrain_axleRatio(value);
	    		}
    		}
    	}
    	
    	if(qName.equals("value") && inEquCenterDifferential){
    		String prefixValue = this.mechanical.getEqu_drivetrain_centerDifferential();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.mechanical.setEqu_drivetrain_centerDifferential(prefixValue + "," + value);
	    		}
	    		else {
	    			this.mechanical.setEqu_drivetrain_centerDifferential(value);
	    		}
    		}
    	}
    	if(qName.equals("value") && inEquLockingDifferential){
    		String prefixValue = this.mechanical.getEqu_drivetrain_lockingDifferential();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.mechanical.setEqu_drivetrain_lockingDifferential(prefixValue + "," + value);
	    		}
	    		else {
	    			this.mechanical.setEqu_drivetrain_lockingDifferential(value);
	    		}
    		}
    	}
    	
    	if(qName.equals("value") && inEquAlternator){
    		String prefixValue = this.mechanical.getEqu_engine_alternator();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.mechanical.setEqu_engine_alternator(prefixValue + "," + value);
	    		}
	    		else {
	    			this.mechanical.setEqu_engine_alternator(value);
	    		}
    		}
    	}
    	
    	if(qName.equals("value") && inEquStabilityControl){
    		String prefixValue = this.mechanical.getEqu_st_stabilityControl();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.mechanical.setEqu_st_stabilityControl(prefixValue + "," + value);
	    		}
	    		else {
	    			this.mechanical.setEqu_st_stabilityControl(value);
	    		}
    		}
    	}
    	
    	if(qName.equals("value") && inEquTractionControl){
    		String prefixValue = this.mechanical.getEqu_st_tractionControl();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.mechanical.setEqu_st_tractionControl(prefixValue + "," + value);
	    		}
	    		else {
	    			this.mechanical.setEqu_st_tractionControl(value);
	    		}
    		}
    	}
    	
    	if(qName.equals("value") && inEquFrontShockType){
    		String prefixValue = this.mechanical.getEqu_suspension_frontShockType();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.mechanical.setEqu_suspension_frontShockType(prefixValue + "," + value);
	    		}
	    		else {
	    			this.mechanical.setEqu_suspension_frontShockType(value);
	    		}
    		}
    	}
    	
    	if(qName.equals("value") && inEquFrontSusClassification){
    		String prefixValue = this.mechanical.getEqu_suspension_frontSuspensionClassification();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.mechanical.setEqu_suspension_frontSuspensionClassification(prefixValue + "," + value);
	    		}
	    		else {
	    			this.mechanical.setEqu_suspension_frontSuspensionClassification(value);
	    		}
    		}
    	}
    	
    	if(qName.equals("value") && inEquFrontSusType){
    		String prefixValue = this.mechanical.getEqu_suspension_frontSuspensionType();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.mechanical.setEqu_suspension_frontSuspensionType(prefixValue + "," + value);
	    		}
	    		else {
	    			this.mechanical.setEqu_suspension_frontSuspensionType(value);
	    		}
    		}
    	}
    	
    	if(qName.equals("value") && inEquRearShockType){
    		String prefixValue = this.mechanical.getEqu_suspension_rearShockType();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.mechanical.setEqu_suspension_rearShockType(prefixValue + "," + value);
	    		}
	    		else {
	    			this.mechanical.setEqu_suspension_rearShockType(value);
	    		}
    		}
    	}
    	
    	if(qName.equals("value") && inEquRearSusClassification){
    		String prefixValue = this.mechanical.getEqu_suspension_rearSuspensionClassification();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.mechanical.setEqu_suspension_rearSuspensionClassification(prefixValue + "," + value);
	    		}
	    		else {
	    			this.mechanical.setEqu_suspension_rearSuspensionClassification(value);
	    		}
    		}
    	}
    	
    	if(qName.equals("value") && inEquRearSusType){
    		String prefixValue = this.mechanical.getEqu_suspension_rearSuspensionType();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.mechanical.setEqu_suspension_rearSuspensionType(prefixValue + "," + value);
	    		}
	    		else {
	    			this.mechanical.setEqu_suspension_rearSuspensionType(value);
	    		}
    		}
    	}
    	
    	if(qName.equals("value") && inEquStabilizerBar){
    		String prefixValue = this.mechanical.getEqu_suspension_stabilizerBar();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.mechanical.setEqu_suspension_stabilizerBar(prefixValue + "," + value);
	    		}
	    		else {
	    			this.mechanical.setEqu_suspension_stabilizerBar(value);
	    		}
    		}
    	}
    	/** end mechanical group **/
    	
    	/** safety group **/
    	if(qName.equals("value") && inEquFrontAirbags){
    		String prefixValue = this.safety.getEqu_airbags_frontAirbags();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.safety.setEqu_airbags_frontAirbags(prefixValue + "," + value);
	    		}
	    		else {
	    			this.safety.setEqu_airbags_frontAirbags(value);
	    		}
    		}
    	}
    	
    	if(qName.equals("value") && inEquPassengerAirbagDeactivation){
    		String prefixValue = this.safety.getEqu_airbags_PassengerAirbagDeactivation();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.safety.setEqu_airbags_PassengerAirbagDeactivation(prefixValue + "," + value);
	    		}
	    		else {
	    			this.safety.setEqu_airbags_PassengerAirbagDeactivation(value);
	    		}
    		}
    	}
    	
    	if(qName.equals("value") && inEquSideAirbags){
    		String prefixValue = this.safety.getEqu_airbags_sideAirbags();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.safety.setEqu_airbags_sideAirbags(prefixValue + "," + value);
	    		}
	    		else {
	    			this.safety.setEqu_airbags_sideAirbags(value);
	    		}
    		}
    	}
    	
    	if(qName.equals("value") && inEquSideCurtainAirbags){
    		String prefixValue = this.safety.getEqu_airbags_sideCurtainAirbags();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.safety.setEqu_airbags_sideCurtainAirbags(prefixValue + "," + value);
	    		}
	    		else {
	    			this.safety.setEqu_airbags_sideCurtainAirbags(value);
	    		}
    		}
    	}
    	
    	if(qName.equals("value") && inEquBrakesABS){
    		String prefixValue = this.safety.getEqu_brakes_ABS();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.safety.setEqu_brakes_ABS(prefixValue + "," + value);
	    		}
	    		else {
	    			this.safety.setEqu_brakes_ABS(value);
	    		}
    		}
    	}
    	
    	if(qName.equals("value") && inEquBrakesBrakingAssist){
    		String prefixValue = this.safety.getEqu_brakes_brakingAssist();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.safety.setEqu_brakes_brakingAssist(prefixValue + "," + value);
	    		}
	    		else {
	    			this.safety.setEqu_brakes_brakingAssist(value);
	    		}
    		}
    	}
    	
    	if(qName.equals("value") && inEquBrakesElectronicBrakeforceDistribution){
    		String prefixValue = this.safety.getEqu_brakes_electronicBrakeforce();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.safety.setEqu_brakes_electronicBrakeforce(prefixValue + "," + value);
	    		}
	    		else {
	    			this.safety.setEqu_brakes_electronicBrakeforce(value);
	    		}
    		}
    	}
    	
    	if(qName.equals("value") && inEquBrakesEmergencyBrakingAssist){
    		String prefixValue = this.safety.getEqu_brakes_emergencyBrakeAssist();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.safety.setEqu_brakes_emergencyBrakeAssist(prefixValue + "," + value);
	    		}
	    		else {
	    			this.safety.setEqu_brakes_emergencyBrakeAssist(value);
	    		}
    		}
    	}
    	
    	if(qName.equals("value") && inEquChildSafetyLocks){
    		String prefixValue = this.safety.getEqu_child_childSafetyLocks();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.safety.setEqu_child_childSafetyLocks(prefixValue + "," + value);
	    		}
	    		else {
	    			this.safety.setEqu_child_childSafetyLocks(value);
	    		}
    		}
    	}
    	
    	if(qName.equals("value") && inEquChildSafetySeatAnchors){
    		String prefixValue = this.safety.getEqu_child_childSeatAnchors();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.safety.setEqu_child_childSeatAnchors(prefixValue + "," + value);
	    		}
	    		else {
	    			this.safety.setEqu_child_childSeatAnchors(value);
	    		}
    		}
    	}
    	
    	if(qName.equals("value") && inEquSafetyActiveHeadRestraints){
    		String prefixValue = this.safety.getEqu_Safety_activeHeadRestraints();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.safety.setEqu_Safety_activeHeadRestraints(prefixValue + "," + value);
	    		}
	    		else {
	    			this.safety.setEqu_Safety_activeHeadRestraints(value);
	    		}
    		}
    	}
    	
    	if(qName.equals("value") && inEquSafetyBodySideReinforcements){
    		String prefixValue = this.safety.getEqu_Safety_bodySideReinforcements();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.safety.setEqu_Safety_bodySideReinforcements(prefixValue + "," + value);
	    		}
	    		else {
	    			this.safety.setEqu_Safety_bodySideReinforcements(value);
	    		}
    		}
    	}
    	
    	if(qName.equals("value") && inEquSafetyCrumpleZones){
    		String prefixValue = this.safety.getEqu_Safety_crumpleZones();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.safety.setEqu_Safety_crumpleZones(prefixValue + "," + value);
	    		}
	    		else {
	    			this.safety.setEqu_Safety_crumpleZones(value);
	    		}
    		}
    	}
    	
    	if(qName.equals("value") && inEquSafetyImpactAbsorbingBumpers){
    		String prefixValue = this.safety.getEqu_Safety_impactAbsorbingBumpers();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.safety.setEqu_Safety_impactAbsorbingBumpers(prefixValue + "," + value);
	    		}
	    		else {
	    			this.safety.setEqu_Safety_impactAbsorbingBumpers(value);
	    		}
    		}
    	}
    	
    	if(qName.equals("value") && inEquSafetyImpactSensor){
    		String prefixValue = this.safety.getEqu_Safety_impactSensor();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.safety.setEqu_Safety_impactSensor(prefixValue + "," + value);
	    		}
	    		else {
	    			this.safety.setEqu_Safety_impactSensor(value);
	    		}
    		}
    	}
    	
    	if(qName.equals("value") && inEquSafetyParkingSensors){
    		String prefixValue = this.safety.getEqu_Safety_parkingSensor();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.safety.setEqu_Safety_parkingSensor(prefixValue + "," + value);
	    		}
	    		else {
	    			this.safety.setEqu_Safety_parkingSensor(value);
	    		}
    		}
    	}
    	
    	if(qName.equals("value") && inEquSeatbeltsFront){
    		String prefixValue = this.safety.getEqu_seatBelts_front();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.safety.setEqu_seatBelts_front(prefixValue + "," + value);
	    		}
	    		else {
	    			this.safety.setEqu_seatBelts_front(value);
	    		}
    		}
    	}
    	
    	if(qName.equals("value") && inEquSeatbeltsSecondRow){
    		String prefixValue = this.safety.getEqu_seatBelts_secondRow();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.safety.setEqu_seatBelts_secondRow(prefixValue + "," + value);
	    		}
	    		else {
	    			this.safety.setEqu_seatBelts_secondRow(value);
	    		}
    		}
    	}
    	
    	if(qName.equals("value") && inEquSecurityAtas){
    		String prefixValue = this.safety.getEqu_security_antiTheftAlarmSystem();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.safety.setEqu_security_antiTheftAlarmSystem(prefixValue + "," + value);
	    		}
	    		else {
	    			this.safety.setEqu_security_antiTheftAlarmSystem(value);
	    		}
    		}
    	}
    	/** end safety group **/
    	
    	/** interior group **/
    	if(qName.equals("value") && inEquAuxiliaryAudioInput){
    		String prefixValue = this.interior.getEqu_as_auxiliaryAudioInput();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.interior.setEqu_as_auxiliaryAudioInput(prefixValue + "," + value);
	    		}
	    		else {
	    			this.interior.setEqu_as_auxiliaryAudioInput(value);
	    		}
    		}
    	}
    	if(qName.equals("value") && inEquCDChanger){
    		String prefixValue = this.interior.getEqu_as_cdChanger();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.interior.setEqu_as_cdChanger(prefixValue + "," + value);
	    		}
	    		else {
	    			this.interior.setEqu_as_cdChanger(value);
	    		}
    		}
    	}
    	if(qName.equals("value") && inEquDigitalSoundProcessing){
    		String prefixValue = this.interior.getEqu_as_digitalSoundProcessing();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.interior.setEqu_as_digitalSoundProcessing(prefixValue + "," + value);
	    		}
	    		else {
	    			this.interior.setEqu_as_digitalSoundProcessing(value);
	    		}
    		}
    	}
    	if(qName.equals("value") && inEquHardDrive){
    		String prefixValue = this.interior.getEqu_as_hardDrive();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.interior.setEqu_as_hardDrive(prefixValue + "," + value);
	    		}
	    		else {
	    			this.interior.setEqu_as_hardDrive(value);
	    		}
    		}
    	}
    	if(qName.equals("value") && inEquInDashCD){
    		String prefixValue = this.interior.getEqu_as_inDashCD();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.interior.setEqu_as_inDashCD(prefixValue + "," + value);
	    		}
	    		else {
	    			this.interior.setEqu_as_inDashCD(value);
	    		}
    		}
    	}
    	if(qName.equals("value") && inEquMP3Player){
    		String prefixValue = this.interior.getEqu_as_mp3Player();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.interior.setEqu_as_mp3Player(prefixValue + "," + value);
	    		}
	    		else {
	    			this.interior.setEqu_as_mp3Player(value);
	    		}
    		}
    	}
    	if(qName.equals("value") && inEquPremiumBrand){
    		String prefixValue = this.interior.getEqu_as_premiumBrand();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.interior.setEqu_as_premiumBrand(prefixValue + "," + value);
	    		}
	    		else {
	    			this.interior.setEqu_as_premiumBrand(value);
	    		}
    		}
    	}
    	if(qName.equals("value") && inEquRadio){
    		String prefixValue = this.interior.getEqu_as_radio();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.interior.setEqu_as_radio(prefixValue + "," + value);
	    		}
	    		else {
	    			this.interior.setEqu_as_radio(value);
	    		}
    		}
    	}
    	if(qName.equals("value") && inEquSatelliteRadio){
    		String prefixValue = this.interior.getEqu_as_satelliteRadio();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.interior.setEqu_as_satelliteRadio(prefixValue + "," + value);
	    		}
	    		else {
	    			this.interior.setEqu_as_satelliteRadio(value);
	    		}
    		}
    	}
    	if(qName.equals("value") && inEquSurroundAudio){
    		String prefixValue = this.interior.getEqu_as_surroundAudio();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.interior.setEqu_as_surroundAudio(prefixValue + "," + value);
	    		}
	    		else {
	    			this.interior.setEqu_as_surroundAudio(value);
	    		}
    		}
    	}
    	if(qName.equals("value") && inEquTotalNumberOfSpeakers){
    		String prefixValue = this.interior.getEqu_as_totalNumberOfSpeakers();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.interior.setEqu_as_totalNumberOfSpeakers(prefixValue + "," + value);
	    		}
	    		else {
	    			this.interior.setEqu_as_totalNumberOfSpeakers(value);
	    		}
    		}
    	}
    	if(qName.equals("value") && inEquArmrests){
    		String prefixValue = this.interior.getEqu_cf_armrests();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.interior.setEqu_cf_armrests(prefixValue + "," + value);
	    		}
	    		else {
	    			this.interior.setEqu_cf_armrests(value);
	    		}
    		}
    	}
    	if(qName.equals("value") && inEquDashTrim){
    		String prefixValue = this.interior.getEqu_cf_dashTrim();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.interior.setEqu_cf_dashTrim(prefixValue + "," + value);
	    		}
	    		else {
	    			this.interior.setEqu_cf_dashTrim(value);
	    		}
    		}
    	}
    	if(qName.equals("value") && inEquSteeringWheelTrim){
    		String prefixValue = this.interior.getEqu_cf_steeringWheelTrim();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.interior.setEqu_cf_steeringWheelTrim(prefixValue + "," + value);
	    		}
	    		else {
	    			this.interior.setEqu_cf_steeringWheelTrim(value);
	    		}
    		}
    	}
    	if(qName.equals("value") && inEquCenterConsole){
    		String prefixValue = this.interior.getEqu_cf_centerConsole();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.interior.setEqu_cf_centerConsole(prefixValue + "," + value);
	    		}
	    		else {
	    			this.interior.setEqu_cf_centerConsole(value);
	    		}
    		}
    	}
    	if(qName.equals("value") && inEquMultiFunctionRemote){
    		String prefixValue = this.interior.getEqu_cf_multiFunctionRemote();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.interior.setEqu_cf_multiFunctionRemote(prefixValue + "," + value);
	    		}
	    		else {
	    			this.interior.setEqu_cf_multiFunctionRemote(value);
	    		}
    		}
    	}
    	if(qName.equals("value") && inEquPowerOutlets){
    		String prefixValue = this.interior.getEqu_cf_powerOutlets();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.interior.setEqu_cf_powerOutlets(prefixValue + "," + value);
	    		}
	    		else {
	    			this.interior.setEqu_cf_powerOutlets(value);
	    		}
    		}
    	}
    	if(qName.equals("value") && inEquRearViewCamera){
    		String prefixValue = this.interior.getEqu_cf_rearViewCamera();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.interior.setEqu_cf_rearViewCamera(prefixValue + "," + value);
	    		}
	    		else {
	    			this.interior.setEqu_cf_rearViewCamera(value);
	    		}
    		}
    	}
    	if(qName.equals("value") && inEquRearViewMonitor){
    		String prefixValue = this.interior.getEqu_cf_rearViewMonitor();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.interior.setEqu_cf_rearViewMonitor(prefixValue + "," + value);
	    		}
	    		else {
	    			this.interior.setEqu_cf_rearViewMonitor(value);
	    		}
    		}
    	}
    	if(qName.equals("value") && inEquSteeringWheel){
    		String prefixValue = this.interior.getEqu_cf_steeringWheel();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.interior.setEqu_cf_steeringWheel(prefixValue + "," + value);
	    		}
	    		else {
	    			this.interior.setEqu_cf_steeringWheel(value);
	    		}
    		}
    	}
    	if(qName.equals("value") && inEquSteeringWheelMountControls){
    		String prefixValue = this.interior.getEqu_cf_steeringWheelMountedControls();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.interior.setEqu_cf_steeringWheelMountedControls(prefixValue + "," + value);
	    		}
	    		else {
	    			this.interior.setEqu_cf_steeringWheelMountedControls(value);
	    		}
    		}
    	}
    	if(qName.equals("value") && inEquStorage){
    		String prefixValue = this.interior.getEqu_cf_storage();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.interior.setEqu_cf_storage(prefixValue + "," + value);
	    		}
	    		else {
	    			this.interior.setEqu_cf_storage(value);
	    		}
    		}
    	}
    	if(qName.equals("value") && inEquAdjustableLumbarSupport){
    		String prefixValue = this.interior.getEqu_ds_adjustableLumbarSupport();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.interior.setEqu_ds_adjustableLumbarSupport(prefixValue + "," + value);
	    		}
	    		else {
	    			this.interior.setEqu_ds_adjustableLumbarSupport(value);
	    		}
    		}
    	}
    	if(qName.equals("value") && inEquHeated){
    		String prefixValue = this.interior.getEqu_ds_heated();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.interior.setEqu_ds_heated(prefixValue + "," + value);
	    		}
	    		else {
	    			this.interior.setEqu_ds_heated(value);
	    		}
    		}
    	}
    	if(qName.equals("value") && inEquCompass){
    		String prefixValue = this.interior.getEqu_instrumentation_compass();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.interior.setEqu_instrumentation_compass(prefixValue + "," + value);
	    		}
	    		else {
	    			this.interior.setEqu_instrumentation_compass(value);
	    		}
    		}
    	}
    	if(qName.equals("value") && inEquExternalTemperatureDisplay){
    		String prefixValue = this.interior.getEqu_instrumentation_externalTemperatureDisplay();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.interior.setEqu_instrumentation_externalTemperatureDisplay(prefixValue + "," + value);
	    		}
	    		else {
	    			this.interior.setEqu_instrumentation_externalTemperatureDisplay(value);
	    		}
    		}
    	}
    	if(qName.equals("value") && inEquFuelEconomyDisplay){
    		String prefixValue = this.interior.getEqu_instrumentation_fuelEconomyDisplay();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.interior.setEqu_instrumentation_fuelEconomyDisplay(prefixValue + "," + value);
	    		}
	    		else {
	    			this.interior.setEqu_instrumentation_fuelEconomyDisplay(value);
	    		}
    		}
    	}
    	if(qName.equals("value") && inEquTachometer){
    		String prefixValue = this.interior.getEqu_instrumentation_tachometer();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.interior.setEqu_instrumentation_tachometer(prefixValue + "," + value);
	    		}
	    		else {
	    			this.interior.setEqu_instrumentation_tachometer(value);
	    		}
    		}
    	}
    	if(qName.equals("value") && inEquTripComputer){
    		String prefixValue = this.interior.getEqu_instrumentation_tripComputer();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.interior.setEqu_instrumentation_tripComputer(prefixValue + "," + value);
	    		}
	    		else {
	    			this.interior.setEqu_instrumentation_tripComputer(value);
	    		}
    		}
    	}
    	if(qName.equals("value") && inEquTripOdometer){
    		String prefixValue = this.interior.getEqu_instrumentation_tripOdometer();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.interior.setEqu_instrumentation_tripOdometer(prefixValue + "," + value);
	    		}
	    		else {
	    			this.interior.setEqu_instrumentation_tripOdometer(value);
	    		}
    		}
    	}
    	if(qName.equals("value") && inEquWoodgrainAccents){
    		String prefixValue = this.interior.getEqu_interior_woodgrainAccents();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.interior.setEqu_interior_woodgrainAccents(prefixValue + "," + value);
	    		}
	    		else {
	    			this.interior.setEqu_interior_woodgrainAccents(value);
	    		}
    		}
    	}
    	if(qName.equals("value") && inEquPowerDoorLocks){
    		String prefixValue = this.interior.getEqu_locks_powerDoorLocks();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.interior.setEqu_locks_powerDoorLocks(prefixValue + "," + value);
	    		}
	    		else {
	    			this.interior.setEqu_locks_powerDoorLocks(value);
	    		}
    		}
    	}
    	if(qName.equals("value") && inEquCenterArmrest){
    		String prefixValue = this.interior.getEqu_rs_centerArmrest();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.interior.setEqu_rs_centerArmrest(prefixValue + "," + value);
	    		}
	    		else {
	    			this.interior.setEqu_rs_centerArmrest(value);
	    		}
    		}
    	}
    	if(qName.equals("value") && inEquFolding){
    		String prefixValue = this.interior.getEqu_rs_folding();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.interior.setEqu_rs_folding(prefixValue + "," + value);
	    		}
	    		else {
	    			this.interior.setEqu_rs_folding(value);
	    		}
    		}
    	}
    	if(qName.equals("value") && inEquType){
    		String prefixValue = this.interior.getEqu_rs_type();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.interior.setEqu_rs_type(prefixValue + "," + value);
	    		}
	    		else {
	    			this.interior.setEqu_rs_type(value);
	    		}
    		}
    	}
    	if(qName.equals("value") && inEquSunroof){
    		String prefixValue = this.interior.getEqu_roof_sunroof();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.interior.setEqu_roof_sunroof(prefixValue + "," + value);
	    		}
	    		else {
	    			this.interior.setEqu_roof_sunroof(value);
	    		}
    		}
    	}
    	if(qName.equals("value") && inEquFrontSeatType){
    		String prefixValue = this.interior.getEqu_seats_frontSeatType();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.interior.setEqu_seats_frontSeatType(prefixValue + "," + value);
	    		}
	    		else {
	    			this.interior.setEqu_seats_frontSeatType(value);
	    		}
    		}
    	}
    	if(qName.equals("value") && inEquUpholstery){
    		String prefixValue = this.interior.getEqu_seats_upholstery();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.interior.setEqu_seats_upholstery(prefixValue + "," + value);
	    		}
	    		else {
	    			this.interior.setEqu_seats_upholstery(value);
	    		}
    		}
    	}
    	if(qName.equals("value") && inEquNavigationSystem){
    		String prefixValue = this.interior.getEqu_telematics_navigationSystem();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.interior.setEqu_telematics_navigationSystem(prefixValue + "," + value);
	    		}
	    		else {
	    			this.interior.setEqu_telematics_navigationSystem(value);
	    		}
    		}
    	}
    	if(qName.equals("value") && inEquPhone){
    		String prefixValue = this.interior.getEqu_telematics_phone();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.interior.setEqu_telematics_phone(prefixValue + "," + value);
	    		}
	    		else {
	    			this.interior.setEqu_telematics_phone(value);
	    		}
    		}
    	}
    	if(qName.equals("value") && inEquRealTimeTraffic){
    		String prefixValue = this.interior.getEqu_telematics_realTimeTraffic();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.interior.setEqu_telematics_realTimeTraffic(prefixValue + "," + value);
	    		}
	    		else {
	    			this.interior.setEqu_telematics_realTimeTraffic(value);
	    		}
    		}
    	}
    	if(qName.equals("value") && inEquVideoMonitorSize){
    		String prefixValue = this.interior.getEqu_vs_videoMonitorSize();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.interior.setEqu_vs_videoMonitorSize(prefixValue + "," + value);
	    		}
	    		else {
	    			this.interior.setEqu_vs_videoMonitorSize(value);
	    		}
    		}
    	}
    	if(qName.equals("value") && inEquVideoMonitorLocation){
    		String prefixValue = this.interior.getEqu_vs_videoMonitorLocation();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.interior.setEqu_vs_videoMonitorLocation(prefixValue + "," + value);
	    		}
	    		else {
	    			this.interior.setEqu_vs_videoMonitorLocation(value);
	    		}
    		}
    	}
    	if(qName.equals("value") && inEquOneTouchWindows){
    		String prefixValue = this.interior.getEqu_windows_oneTouchWindows();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.interior.setEqu_windows_oneTouchWindows(prefixValue + "," + value);
	    		}
	    		else {
	    			this.interior.setEqu_windows_oneTouchWindows(value);
	    		}
    		}
    	}
    	if(qName.equals("value") && inEquPowerWindows){
    		String prefixValue = this.interior.getEqu_windows_powerWindows();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.interior.setEqu_windows_powerWindows(prefixValue + "," + value);
	    		}
	    		else {
	    			this.interior.setEqu_windows_powerWindows(value);
	    		}
    		}
    	}
    	/** end interior group **/
    	
    	/** exterior group **/
    	if(qName.equals("value") && inEquExteriorFeaturesExhaust){
    		String prefixValue = this.exterior.getEqu_ef_exhaust();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.exterior.setEqu_ef_exhaust(prefixValue + "," + value);
	    		}
	    		else {
	    			this.exterior.setEqu_ef_exhaust(value);
	    		}
    		}
    	}
    	
    	if(qName.equals("value") && inEquExteriorFeaturesExhaustTipColor){
    		String prefixValue = this.exterior.getEqu_ef_exhaustTipColor();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.exterior.setEqu_ef_exhaustTipColor(prefixValue + "," + value);
	    		}
	    		else {
	    			this.exterior.setEqu_ef_exhaustTipColor(value);
	    		}
    		}
    	}
    	
    	if(qName.equals("value") && inEquExteriorFeaturesGrilleColor){
    		String prefixValue = this.exterior.getEqu_ef_grilleColor();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.exterior.setEqu_ef_grilleColor(prefixValue + "," + value);
	    		}
	    		else {
	    			this.exterior.setEqu_ef_grilleColor(value);
	    		}
    		}
    	}
    	
    	if(qName.equals("value") && inEquExteriorFeaturesRearSpoiler){
    		String prefixValue = this.exterior.getEqu_ef_rearSpoiler();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.exterior.setEqu_ef_rearSpoiler(prefixValue + "," + value);
	    		}
	    		else {
	    			this.exterior.setEqu_ef_rearSpoiler(value);
	    		}
    		}
    	}
    	
    	if(qName.equals("value") && inEquExteriorFeaturesRearSpoilerColor){
    		String prefixValue = this.exterior.getEqu_ef_rearSpoilerColor();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.exterior.setEqu_ef_rearSpoilerColor(prefixValue + "," + value);
	    		}
	    		else {
	    			this.exterior.setEqu_ef_rearSpoilerColor(value);
	    		}
    		}
    	}
    	
    	if(qName.equals("value") && inEquExteriorFeaturesWindowTrim){
    		String prefixValue = this.exterior.getEqu_ef_windowTrim();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.exterior.setEqu_ef_windowTrim(prefixValue + "," + value);
	    		}
	    		else {
	    			this.exterior.setEqu_ef_windowTrim(value);
	    		}
    		}
    	}
    	
    	if(qName.equals("value") && inEquLightsDaytimeRunningLights){
    		String prefixValue = this.exterior.getEqu_lights_daytimeRunningLights();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.exterior.setEqu_lights_daytimeRunningLights(prefixValue + "," + value);
	    		}
	    		else {
	    			this.exterior.setEqu_lights_daytimeRunningLights(value);
	    		}
    		}
    	}
    	
    	if(qName.equals("value") && inEquLightsHeadlights){
    		String prefixValue = this.exterior.getEqu_lights_headlights();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.exterior.setEqu_lights_headlights(prefixValue + "," + value);
	    		}
	    		else {
	    			this.exterior.setEqu_lights_headlights(value);
	    		}
    		}
    	}
    	
    	if(qName.equals("value") && inEquMirrorsExteriorMirrors){
    		String prefixValue = this.exterior.getEqu_mirrors_exteriorMirrors();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.exterior.setEqu_mirrors_exteriorMirrors(prefixValue + "," + value);
	    		}
	    		else {
	    			this.exterior.setEqu_mirrors_exteriorMirrors(value);
	    		}
    		}
    	}
    	
    	if(qName.equals("value") && inEquSpareTireSize){
    		String prefixValue = this.exterior.getEqu_st_size();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.exterior.setEqu_st_size(prefixValue + "," + value);
	    		}
	    		else {
	    			this.exterior.setEqu_st_size(value);
	    		}
    		}
    	}
    	
    	if(qName.equals("value") && inEquWheelRimType){
    		String prefixValue = this.exterior.getEqu_wheels_rimType();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.exterior.setEqu_wheels_rimType(prefixValue + "," + value);
	    		}
	    		else {
	    			this.exterior.setEqu_wheels_rimType(value);
	    		}
    		}
    	}
    	
    	if(qName.equals("value") && inEquTiresType){
    		String prefixValue = this.exterior.getEqu_tires_type();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.exterior.setEqu_tires_type(prefixValue + "," + value);
	    		}
	    		else {
	    			this.exterior.setEqu_tires_type(value);
	    		}
    		}
    	}
    	
    	if(qName.equals("value") && inEquWheelSpokes){
    		String prefixValue = this.exterior.getEqu_wheels_wheelSpokes();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.exterior.setEqu_wheels_wheelSpokes(prefixValue + "," + value);
	    		}
	    		else {
	    			this.exterior.setEqu_wheels_wheelSpokes(value);
	    		}
    		}
    	}
    	
    	if(qName.equals("value") && inEquWindowsFrontWipers){
    		String prefixValue = this.exterior.getEqu_windows_frontWipers();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.exterior.setEqu_windows_frontWipers(prefixValue + "," + value);
	    		}
	    		else {
	    			this.exterior.setEqu_windows_frontWipers(value);
	    		}
    		}
    	}
    	
    	if(qName.equals("value") && inEquWindowsTinted){
    		String prefixValue = this.exterior.getEqu_windows_tinted();
    		if(!isDuplicateValue(prefixValue, value)){
	    		if(prefixValue != null && !prefixValue.isEmpty()){
	    			this.exterior.setEqu_windows_tinted(prefixValue + "," + value);
	    		}
	    		else {
	    			this.exterior.setEqu_windows_tinted(value);
	    		}
    		}
    	}
    	/** end exterior group **/
    	
    	if(qName.equals("equipment")){
    		
    		/** mechanical group **/
    		inEqu4WDType = false;
    		inEquAxleRatio = false;
    		inEquCenterDifferential = false;
    		inEquLockingDifferential = false;
    		inEquAlternator = false;
    		inEquStabilityControl = false;
    		inEquTractionControl = false;
    		inEquFrontShockType = false;
    		inEquFrontSusClassification = false;
    		inEquFrontSusType = false;
    		inEquRearShockType = false;
    		inEquRearSusClassification = false;
    		inEquRearSusType = false;
    		inEquStabilizerBar = false;
    		/** end mechanical group **/
    		
    		/** safety group **/
    		inEquFrontAirbags = false;
    	    inEquPassengerAirbagDeactivation = false;
    	    inEquSideAirbags = false;
    	    inEquSideCurtainAirbags = false;
    	    inEquBrakesABS = false;
    	    inEquBrakesBrakingAssist = false;
    	    inEquBrakesElectronicBrakeforceDistribution = false;
    	    inEquBrakesEmergencyBrakingAssist = false;
    	    inEquChildSafetyLocks = false;
    	    inEquChildSafetySeatAnchors = false;
    	    inEquSafetyActiveHeadRestraints = false;
    	    inEquSafetyBodySideReinforcements = false;
    	    inEquSafetyCrumpleZones = false;
    	    inEquSafetyImpactAbsorbingBumpers = false;
    	    inEquSafetyImpactSensor = false;
    	    inEquSafetyParkingSensors = false;
    	    inEquSeatbeltsFront = false;
    	    inEquSeatbeltsSecondRow = false;
    	    inEquSecurityAtas = false;
    		/** end safety group **/
    	    
    	    /** interior group **/
    	    inEquAuxiliaryAudioInput = false;
    	    inEquCDChanger = false;
    	    inEquDigitalSoundProcessing = false;
    	    inEquHardDrive = false;
    	    inEquInDashCD = false;
    	    inEquMP3Player = false;
    	    inEquPremiumBrand = false;
    	    inEquRadio = false;
    	    inEquSatelliteRadio = false;
    	    inEquSurroundAudio = false;
    	    inEquTotalNumberOfSpeakers = false;
    	    inEquArmrests = false;
    	    inEquDashTrim = false;
    	    inEquSteeringWheelTrim = false;
    	    inEquCenterConsole = false;
    	    inEquMultiFunctionRemote = false;
    	    inEquPowerOutlets = false;
    	    inEquRearViewCamera = false;
    	    inEquRearViewMonitor = false;
    	    inEquSteeringWheel = false;
    	    inEquSteeringWheelMountControls = false;
    	    inEquStorage = false;
    	    inEquAdjustableLumbarSupport = false;
    	    inEquHeated = false;
    	    inEquCompass = false;
    	    inEquExternalTemperatureDisplay = false;
    	    inEquFuelEconomyDisplay = false;
    	    inEquTachometer = false;
    	    inEquTripComputer = false;
    	    inEquTripOdometer = false;
    	    inEquWoodgrainAccents = false;
    	    inEquPowerDoorLocks = false;
    	    inEquCenterArmrest = false;
    	    inEquFolding = false;
    	    inEquType = false;
    	    inEquSunroof = false;
    	    inEquFrontSeatType = false;
    	    inEquUpholstery = false;
    	    inEquNavigationSystem = false;
    	    inEquPhone = false;
    	    inEquRealTimeTraffic = false;
    	    inEquVideoMonitorSize = false;
    	    inEquVideoMonitorLocation = false;
    	    inEquOneTouchWindows = false;
    	    inEquPowerWindows = false;
    	    /** end interior group **/
    	    
    	    /** exterior group **/
    	    inEquExteriorFeaturesExhaust = false;
    	    inEquExteriorFeaturesExhaustTipColor = false;
    	    inEquExteriorFeaturesGrilleColor = false;
    	    inEquExteriorFeaturesRearSpoiler = false;
    	    inEquExteriorFeaturesRearSpoilerColor = false;
    	    inEquExteriorFeaturesWindowTrim = false;
    	    inEquLightsDaytimeRunningLights = false;
    	    inEquLightsHeadlights = false;
    	    inEquMirrorsExteriorMirrors = false;
    	    inEquSpareTireSize = false;
    	    inEquWheelRimType = false;
    	    inEquTiresType = false;
    	    inEquWheelSpokes = false;
    	    inEquWindowsFrontWipers = false;
    	    inEquWindowsTinted = false;
    	    /** end exterior group **/
    		
    		inEquipment = false;
    	}
    	/** end equipment **/
    	
    	/** warranty group **/
    	if(qName.equals("type") && inWarranty){
    		if(!value.isEmpty()){
    			this.warranty = this.warranty + "~type#" + value;
    		}
    	}
    	if(qName.equals("months") && inWarranty){
    		if(!value.isEmpty()){
    			this.warranty = this.warranty + "~months#" + value;
    		}
    	}
    	if(qName.equals("miles") && inWarranty){
    		if(!value.isEmpty()){
    			this.warranty = this.warranty + "~miles#" + value;
    		}
    	}
    	if(qName.equals("warranty")){
    		inWarranty = false;
    	}
    	/** end warranty group **/
    }

    public String prepareMechanicalGroup() {
    	
    	return this.mechanical.toString();
    }
    
    public String prepareSafetyGroup() {
    	
    	return this.safety.toString();
    }
    
    public String prepareExteriorGroup() {
    	
    	return this.exterior.toString();
    }
    
    public String prepareInteriorGroup() {
    	
    	return this.interior.toString();
    }
    
    public String prepareBodyTypeGroup() {
    	
    	return this.bodyType;
    }
    
    public String prepareDriveTrainGroup() {
    	
    	return this.driveTrain;
    }
    
    public String prepareEngineGroup() {
    	
    	return this.engineBlockType + "|" + this.engineCylinder;
    }

    public String prepare060Group() {
    	
    	return this.acceleration0_60;
    }
    
    public String preparePowerGroup() {
    	
    	return this.max_hp + "|" + this.max_hp_at;
    }
    
    public String prepareMaxSeatingGroup() {
    	
    	return this.maxSeating;
    }
    
    public String prepareTransmissionGroup() {
    	
    	return this.transmissionName;
    }
    
    public String prepareCargoVolumeGroup() {
    	
    	return this.cargoVolume;
    }
    
    public String prepareWarrantyGroup() {
    	
    	return this.warranty;
    }
    
    private boolean isDuplicateValue(String prefix, String value){
		
		if(prefix != null && !prefix.isEmpty()){
			String[] strArray = prefix.split(",");
			
			for(int i = 0; i < strArray.length; i++){
				if(value.equals(strArray[i])){
					return true;
				}
			}
		}
		return false;
	}
}
