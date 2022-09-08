package co.uk.gel.utils;

import com.opencsv.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVFileReader {

    private static final Logger log = LoggerFactory.getLogger(CSVFileReader.class);

    static String test_data_file_location = System.getProperty("user.dir") + File.separator + "testdata";
    private List<Map> mi_portal_data_set = new ArrayList<Map>();

    public boolean readTestData(){
        try{
            File file = new File(test_data_file_location+File.separator+ "FILENAME");
            if(!file.exists()){
                log.info("TestData File: " + "FILENAME" + " not exists in " + test_data_file_location);
                return false;
            }
            CSVReader csvReader = new CSVReader(new FileReader(file));
            String[] testData = null;
            Map<String, Integer> map = new HashMap<String,Integer>(); //Create map
            int count = 0;
            while((testData = csvReader.readNext()) != null){
               if(count == 0){//First row expected to be title
                    for(int colIx=0; colIx<testData.length; colIx++) { //loop from first to last index
                        map.put(testData[colIx],colIx); //add the cell contents (name of column) and cell index to the map
                    }
                }else {
                    /*MIPortalTestData mipData = new MIPortalTestData();
                    mipData.setGlh_name(testData[map.get("GLHName")]);
                    mipData.setGlh_code(testData[map.get("GLHCode")]);
                    mipData.setReferral_id(testData[map.get("ReferralID_HumanReadble")]);
                    mipData.setConsignment_number(testData[map.get("ConsignmentNumber")]);
                    mipData.setOrdering_entity(testData[map.get("OrderingEntity")]);
                    mipData.setPatient_ngsid(testData[map.get("PatientNGISID")]);
                    mipData.setTest_type(testData[map.get("TestType")]);
                    mi_portal_data_set.add(mipData);*/
                }
                count++;
            }
            log.info("No Of Data Read:"+mi_portal_data_set.size());
            return true;
        }catch(Exception exp){
            log.info("Exception in reading TestData:"+exp);
            return false;
        }
    }

}//end
