/*
 * This is the class used to read through the file and load the details of the file into respective automobile
 * It also calls the exception class incase of any issues in the file data. 
 * It can also serialize and deserialize the automobile objects
 * Author: Vignan Uppugandla
 */

package util;

import java.io.*;
import java.util.*;
import java.util.logging.*;

import javax.rmi.CORBA.Util;

import exceptions.AutoException;
import model.Automobile;

public class FileIO {
	
	/*log variable for logging exceptions*/
	private final static Logger log = Logger.getLogger(Util.class.getName());
	
	/*Default Constructor*/
	public FileIO(){}
	
	/*Method to read properties file and the load the automobile object*/
	public Automobile parseProperties(String fileName) throws AutoException{
		
		Automobile auto = new Automobile();
		FileInputStream in;
		
		Properties props = new Properties();
		try{
			in = new FileInputStream(fileName);
			props.load(in);
		}catch(IOException e){	
			throw new AutoException(601351);
		}
		
		/*Reading carmake*/
		String carMake = props.getProperty("CarMake");
		
		if(!carMake.equals(null)){//Checking if car make value is not null
			/*Reading through all the remaining properties of the car*/
			String carModel = props.getProperty("CarModel");
			String carBasePrice = props.getProperty("CarBasePrice");
			String option1 = props.getProperty("Option1");
			String optionValue1a= props.getProperty("OptionValue1a");
			String optionValue1b = props.getProperty("OptionValue1b");
			String optionValue1c = props.getProperty("OptionValue1c");
			String optionValue1d = props.getProperty("OptionValue1d");
			String optionValue1e = props.getProperty("OptionValue1e");
			String optionValue1f = props.getProperty("OptionValue1f");
			String optionValue1g = props.getProperty("OptionValue1g");
			String optionValue1h = props.getProperty("OptionValue1h");
			String optionValue1i = props.getProperty("OptionValue1i");
			String optionValue1j = props.getProperty("OptionValue1j");
			String optionPrice1a = props.getProperty("OptionPrice1a");
			String optionPrice1b = props.getProperty("OptionPrice1b");
			String optionPrice1c = props.getProperty("OptionPrice1c");
			String optionPrice1d = props.getProperty("OptionPrice1d");
			String optionPrice1e = props.getProperty("OptionPrice1e");
			String optionPrice1f = props.getProperty("OptionPrice1f");
			String optionPrice1g = props.getProperty("OptionPrice1g");
			String optionPrice1h = props.getProperty("OptionPrice1h");
			String optionPrice1i = props.getProperty("OptionPrice1i");
			String optionPrice1j = props.getProperty("OptionPrice1j");
			String option2 = props.getProperty("Option2");
			String optionValue2a = props.getProperty("OptionValue2a");
			String optionValue2b = props.getProperty("OptionValue2b");
			String optionPrice2a = props.getProperty("OptionPrice2a");
			String optionPrice2b = props.getProperty("OptionPrice2b");
			String option3 = props.getProperty("Option3");
			String optionValue3a = props.getProperty("OptionValue3a");
			String optionValue3b = props.getProperty("OptionValue3b");
			String optionValue3c = props.getProperty("OptionValue3c");
			String optionPrice3a = props.getProperty("OptionPrice3a");
			String optionPrice3b = props.getProperty("OptionPrice3b");
			String optionPrice3c = props.getProperty("OptionPrice3c");
			String option4 = props.getProperty("Option4");
			String optionValue4a = props.getProperty("OptionValue4a");
			String optionValue4b = props.getProperty("OptionValue4b");
			String optionPrice4a = props.getProperty("OptionPrice4a");
			String optionPrice4b = props.getProperty("OptionPrice4b");
			String option5 = props.getProperty("Option5");
			String optionValue5a = props.getProperty("OptionValue5a");
			String optionValue5b = props.getProperty("OptionValue5b");
			String optionPrice5a = props.getProperty("OptionPrice5a");
			String optionPrice5b = props.getProperty("OptionPrice5b");
			
			/*Adding the properties to automobile object*/
			auto.setMake(carMake);
			auto.setModel(carModel);
			auto.setBasePrice(Integer.parseInt(carBasePrice));
			auto.addOptionSet(0, option1);
			auto.addOptionSet(1, option2);
			auto.addOptionSet(2, option3);
			auto.addOptionSet(3, option4);
			auto.addOptionSet(4, option5);
			
			/*setting Option1*/
			auto.setOption(0, 10);
			auto.setOptionName(0, 0, optionValue1a);
			auto.setOptionName(0, 1, optionValue1b);
			auto.setOptionName(0, 2, optionValue1c);
			auto.setOptionName(0, 3, optionValue1d);
			auto.setOptionName(0, 4, optionValue1e);
			auto.setOptionName(0, 5, optionValue1f);
			auto.setOptionName(0, 6, optionValue1g);
			auto.setOptionName(0, 7, optionValue1h);
			auto.setOptionName(0, 8, optionValue1i);
			auto.setOptionName(0, 9, optionValue1j);
			
			auto.setOptionPrice(0, 0, Integer.parseInt(optionPrice1a));
			auto.setOptionPrice(0, 1, Integer.parseInt(optionPrice1b));
			auto.setOptionPrice(0, 2, Integer.parseInt(optionPrice1c));
			auto.setOptionPrice(0, 3, Integer.parseInt(optionPrice1d));
			auto.setOptionPrice(0, 4, Integer.parseInt(optionPrice1e));
			auto.setOptionPrice(0, 5, Integer.parseInt(optionPrice1f));
			auto.setOptionPrice(0, 6, Integer.parseInt(optionPrice1g));
			auto.setOptionPrice(0, 7, Integer.parseInt(optionPrice1h));
			auto.setOptionPrice(0, 8, Integer.parseInt(optionPrice1i));
			auto.setOptionPrice(0, 9, Integer.parseInt(optionPrice1j));
			
			/*setting Option2*/
			auto.setOption(1, 2);
			auto.setOptionName(1, 0, optionValue2a);
			auto.setOptionName(1, 1, optionValue2b);
			
			auto.setOptionPrice(1, 0, Integer.parseInt(optionPrice2a));
			auto.setOptionPrice(1, 1, Integer.parseInt(optionPrice2b));
			
			/*setting Option3*/
			auto.setOption(2, 3);
			auto.setOptionName(2, 0, optionValue3a);
			auto.setOptionName(2, 1, optionValue3b);
			auto.setOptionName(2, 2, optionValue3c);
			
			auto.setOptionPrice(2, 0, Integer.parseInt(optionPrice3a));
			auto.setOptionPrice(2, 1, Integer.parseInt(optionPrice3b));
			auto.setOptionPrice(2, 2, Integer.parseInt(optionPrice3c));
			
			/*setting Option4*/
			auto.setOption(3, 2);
			auto.setOptionName(3, 0, optionValue4a);
			auto.setOptionName(3, 1, optionValue4b);
			
			auto.setOptionPrice(3, 0, Integer.parseInt(optionPrice4a));
			auto.setOptionPrice(3, 1, Integer.parseInt(optionPrice4b));
			
			/*setting Option5*/
			auto.setOption(4, 2);
			auto.setOptionName(4, 0, optionValue5a);
			auto.setOptionName(4, 1, optionValue5b);
			
			auto.setOptionPrice(4, 0, Integer.parseInt(optionPrice5a));
			auto.setOptionPrice(4, 1, Integer.parseInt(optionPrice5b));
		}		
		return auto;
	}
	
	/*method for finding the number of lines in a file*/
	private int findLines(String fileName) throws AutoException{
		
		LineNumberReader reader =null;
		int count = 0;
		try{
		reader  = new LineNumberReader(new FileReader(fileName));
		while (reader.readLine() != null) {
			count = reader.getLineNumber(); 	
		}
		reader.close();
	}catch(IOException e){
			throw new AutoException(601351);
	}
		return count;	
	}
	
	/*method for reading through file*/
	public Automobile readFile(String fileName) throws AutoException{
		Automobile automobile = new Automobile();
		try{
			FileHandler fh = new FileHandler("Logs/MyLog.log");//logging to MyLog File
			SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter); 
			log.addHandler(fh);
			
			int noOfOptions = findLines(fileName)-1;//calculating number of options from the number of lines
			if(noOfOptions==-1) throw new AutoException(601359);//if no data in file throw exception
			else{
				FileReader file = new FileReader(fileName);
				BufferedReader br = new BufferedReader(file);
				
				/*reading for model, make and base price*/
				String modelLine = br.readLine().trim();
						StringTokenizer ml = new StringTokenizer(modelLine,":");
						int tc = ml.countTokens();
						if(tc==1){//if one of make or model+base price exists
							String[] parts = modelLine.split(":");
							if(parts[0].isEmpty() || parts[0] == null){//if make not found
								try{
								StringTokenizer mp = new StringTokenizer(parts[1],"$");
								int mpc = mp.countTokens();
								if(mpc==1){//if one of model or base price exists
									String model = mp.nextToken();
									int value = convertToInt(model);
									if(value == -1){
										try{
										automobile.setModel(model); //Setting the model name
										throw new AutoException(601354);//Base Price missing
										}
										catch(AutoException e){//catch and fix missing base price exception
											int price = convertToInt(e.getFixMessage());
											automobile.setBasePrice(price);
											log.info(e.getFixMessage());
										}
									}
									else{
										try{
											automobile.setBasePrice(value);
											throw new AutoException(601353);//model Name missing
										}
										catch(AutoException mn){//catch and fix missing model name exception
											automobile.setModel(mn.getFixMessage());
											log.info(mn.getFixMessage());
										}
									}
									
								}else{
									automobile.setModel(mp.nextToken());
									automobile.setBasePrice(convertToInt(mp.nextToken()));
								}
							throw new AutoException(601350);//missing make
							}catch(AutoException m){//catch and fix make details
								automobile.setMake(m.getFixMessage());
								log.info(m.getFixMessage());
							}
							}else{//if model+baseprice details not exists
								try{
								automobile.setMake(parts[0]);
								throw new AutoException(601353);//missing model name
								}catch(AutoException a){//catch and fix model name
									try{
										automobile.setModel(a.getFixMessage());
										log.info(a.getFixMessage());
										throw new AutoException(601354);//missing base price
									}catch(AutoException b){//catch and fix base price
										automobile.setBasePrice(convertToInt(b.getFixMessage()));
										log.info(b.getFixMessage());
									}									
								}
							}
						}
						else{//if make, model and base price exists
							automobile.setMake(ml.nextToken());
							StringTokenizer mp = new StringTokenizer(ml.nextToken(),"$");
							int mpc = mp.countTokens();
							if(mpc==1){//if one of model or base price exists
								String model = mp.nextToken();
								int value = convertToInt(model);
								if(value == -1){
									try{
									automobile.setModel(model); //Setting the model name
									throw new AutoException(601354);//Base Price missing
									}
									catch(AutoException bp){//catch and fix base price
										int price = convertToInt(bp.getFixMessage());
										automobile.setBasePrice(price);
//										log.info(bp.getFixMessage());
									}
								}
								else{
									try{
										automobile.setBasePrice(value);
										throw new AutoException(601353);//model Name missing
									}
									catch(AutoException v){//catch and fix model name
										automobile.setModel(v.getFixMessage());
										log.info(v.getFixMessage());
									}
								}
							}else{//if both model name and base price exists
								automobile.setModel(mp.nextToken());
								automobile.setBasePrice(convertToInt(mp.nextToken()));
							}	
						}
			/* Instantiating the option set objects */	
			automobile.setOptionSet(noOfOptions);
			/* reading options */
			try{
			if(noOfOptions==0) throw new AutoException(601357);
			else{
			for(int i=0; i<noOfOptions; i++){//looping through options
				String optionsLine = br.readLine().trim();
						StringTokenizer st = new StringTokenizer(optionsLine,":");
						int oc = st.countTokens();
						if(oc==1){//if one of option set name and options exists
							String[] str = optionsLine.split(":");
							if(str[0].isEmpty() || str[0] == null){
								try{
								StringTokenizer ol = new StringTokenizer(str[1],",");
								int optionsCount =ol.countTokens();
								
								/* Instantiating the option set objects */
								automobile.setOption(i, optionsCount);

								for(int j=0; j<optionsCount; j++){
									StringTokenizer ot = new StringTokenizer(ol.nextToken(),"$");
									int optCount = ot.countTokens();
										/*if only one of option price or option*/
										if(optCount ==1){
											String option = ot.nextToken();
											int value = convertToInt(option);
											
											if(value == -1){
												try{
													automobile.setOptionName(i, j ,option);//Setting the option name
													throw new AutoException(601354);//Option Price missing	
												}
												catch(AutoException op){//catch and fix option price
													int price = convertToInt(op.getFixMessage());
													automobile.setOptionPrice(i, j, price);
													log.info(op.getFixMessage());
												}
											}
											else{
												try{
													automobile.setOptionPrice(i, j, value); 
													throw new AutoException(601355);	//Option missing
												}
												catch(AutoException o){//catch and fix option
													automobile.setOptionName(i, j ,o.getFixMessage());
													log.info(o.getFixMessage());
												}
											}
										}
										else{
											automobile.setOptionName(i, j ,ot.nextToken());//Setting the option name
											int value = convertToInt(ot.nextToken());
											automobile.setOptionPrice(i, j, value); //setting option price
										}
								}
							throw new AutoException(601352);//option set name missing
							}catch(AutoException ab){//catch and fix option set name
								automobile.setOptSetName(i,ab.getFixMessage());
								log.info(ab.getFixMessage());
						}
						}
						else{
								try{
									automobile.setOptSetName(i,str[0]);
									throw new AutoException(601358);//options missing for optioin set
								}catch(AutoException oss){
									log.info(oss.getFixMessage());
								}
							}
						}else{//if both option set and options exists
							automobile.setOptSetName(i,st.nextToken());
							StringTokenizer ol = new StringTokenizer(st.nextToken(),",");
							int optionsCount =ol.countTokens();
							
							/* Instantiating the option set objects */
							automobile.setOption(i, optionsCount);

							for(int j=0; j<optionsCount; j++){//looping though options
								StringTokenizer ot = new StringTokenizer(ol.nextToken(),"$");
								int optCount = ot.countTokens();
									/*if only one of option price or option*/
									if(optCount ==1){
										String option = ot.nextToken();
										int value = convertToInt(option);
										
										if(value == -1){
											try{
												automobile.setOptionName(i, j ,option);//Setting the option name
												throw new AutoException(601354);//Option Price missing	
											}
											catch(AutoException c){//catch and fix option price
												int price = convertToInt(c.getFixMessage());
												automobile.setOptionPrice(i, j, price);
												log.info(c.getFixMessage());
											}
										}
										else{
											try{
												automobile.setOptionPrice(i, j, value); 
												throw new AutoException(601355);	//Option missing
											}
											catch(AutoException d){//catch and fix option name
												automobile.setOptionName(i, j ,d.getFixMessage());
												log.info(d.getFixMessage());
											}
										}
									}
									else{
										automobile.setOptionName(i, j ,ot.nextToken());//Setting the option name
										int value = convertToInt(ot.nextToken());
										automobile.setOptionPrice(i, j, value); //setting option price
										
									}
							}
						}
				}
			}
			}catch(AutoException ae){//catch if no option sets exists for model
				log.info(ae.getFixMessage());
			}
				br.close();//Closes the buffer
			}
			}catch(AutoException e){
				if(e.getErrorCode() == 601351) {
					log.info(e.getFixMessage());
					throw new AutoException(601351);
				}else {
					System.out.println(e.getFixMessage());
					log.info(e.getFixMessage());
					System.exit(1);								
				}
		}catch(IOException e){//input output read exception
			System.out.println(e.getMessage());
			log.info(e.getMessage());
			System.exit(1);			
		}
		
		return automobile;
	}
	/*Serializing the object and Writing it to file*/
	public static void serializeAuto(LinkedHashMap<String, Automobile> auto){
		try{
			ObjectOutputStream objOutStream = new ObjectOutputStream(new FileOutputStream("auto.ser"));
			objOutStream.writeObject(auto); //writing the object to file
			objOutStream.close();//closing the output stream
		}catch (FileNotFoundException e) {
			e.printStackTrace();
			} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*Reading and DeSerializing objects from the file*/
		public static LinkedHashMap<String, Automobile> deserializeAuto(){
			LinkedHashMap<String, Automobile> auto = null;
			try{
			ObjectInputStream objInStream = new ObjectInputStream(new FileInputStream("auto.ser"));
			auto = (LinkedHashMap<String, Automobile>) objInStream.readObject();//reading the object and instantiating the new Automobile object
			objInStream.close();//closing the input stream
			}catch (IOException e) {
				e.printStackTrace();
				} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return auto;
			
		}
	
	/*method for converting string value to integer 
	 * Returns -1 when cannot be converted*/
	private int convertToInt(String string){
		int value=0;
		try{
		value = Integer.parseInt(string);//converting string to integer
		}catch(NumberFormatException e){//catching exception when the given string cannot be converted to integer
			return -1;
		}
		return value;
	}
}
