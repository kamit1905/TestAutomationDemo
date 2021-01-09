package com.app;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GetBigBasketData_1 {
	
	public static void main(String[] args) {
		
		XSSFWorkbook workbook = new XSSFWorkbook(); 
		  
	    // Create a blank sheet 
	    XSSFSheet sheet = workbook.createSheet("Order_details");
	    
	    Map<Integer, Object[]> data = new TreeMap<Integer, Object[]>(); 
        data.put(0, new Object[]{"ProductName","Pqty","Pmrp","Pselling"});
        
        data.put(1,new Object[]{"Brinjal","1 Gm","15.75","10"});
        data.put(2,new Object[]{"Lady","1 Rh","0RC","10"});
        data.put(3,new Object[]{"Onion","1 Ty","15.75","14"});
        data.put(4,new Object[]{"Grapes","1 Kg","80","75"});
        data.put(5,new Object[]{"Beetroot","1 kg","40","35"});
        
        
        System.out.println("data "+data.values());
		 Set<Integer> keyset = data.keySet(); 
		 System.out.println("keyset "+keyset);
	        int rownum = 0; 
	        for (Integer key : keyset) { 
	            // this creates a new row in the sheet 
	            Row row = sheet.createRow(rownum++); 
	            Object[] objArr = data.get(key);
	            //System.out.println("objarray "+objArr.toString());
	            //System.out.println("objarr "+objArr[key]);
	            int cellnum = 0; 
	            for (Object obj : objArr) { 
	                // this line creates a cell in the next column of that row 
	                Cell cell = row.createCell(cellnum++); 
	                if (obj instanceof String) 
	                    cell.setCellValue((String)obj); 
	                else if (obj instanceof Integer) 
	                    cell.setCellValue((Integer)obj); 
	            } 
	        } 
	        try { 
	            // this Writes the workbook gfgcontribute 
	            FileOutputStream out = new FileOutputStream(new File(System.getProperty("user.dir")+"/src/main/java/com/app/testdata/checkwebscrapper.xlsx")); 
	            workbook.write(out); 
	            out.close(); 
	            System.out.println("Order written successfully in csv"); 
	        } 
	        catch (Exception e) { 
	            e.printStackTrace(); 
	        } 
	}

}
