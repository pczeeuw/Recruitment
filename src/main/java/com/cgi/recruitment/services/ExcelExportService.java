package com.cgi.recruitment.services;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.cgi.recruitment.domain.Person;
import com.cgi.recruitment.domain.RecruitmentEvent;
import com.cgi.recruitment.fx.domain.FxRecruitmentEvent;
import com.cgi.recruitment.util.converters.orika.OrikaCustomConverter;

@Component
public class ExcelExportService {
	
	private RecruitmentEvent event;
	private File targetFile;
	
	@Value("${recruitment.fields.en}")
	private String[] fieldsEn;	
	
	@Value("${recruitment.fields.nl}")
	private String[] fieldsNl;
	
	public ExcelExportService () {
		
	}
	
	public void setRecruitmentEvent (FxRecruitmentEvent fxEvent) {
		this.event = OrikaCustomConverter.convertFromFx(fxEvent);
	}
	
	public void setTargetFile (File file) {
		this.targetFile = file;
	}
	
	public void exportToXLSX () {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(event.getEventName() + " " + event.getEventLocation());
		//Set event name, location and date:
		Row row = sheet.createRow(0);
		
		row.createCell(0).setCellValue(event.getEventName());
		row.createCell(1).setCellValue(event.getEventLocation());
		row.createCell(2).setCellValue(event.getEventDate().toString());
		
		
		//Create the Column names on the 2nd row
		row = sheet.createRow(1);
		int i = 0;
		for (String field : fieldsNl) {
			row.createCell(i++).setCellValue(field);
			
		}
		
		
		
		//Write all the persons in the Person List to the next X rows.
		i = 2;
		for (Person p : event.getPersonList()) {
			row = sheet.createRow(i++);
			for (int j = 0; j < fieldsEn.length; j++) {
				try {
					String val = "";
					Object ob = new PropertyDescriptor(fieldsEn[j], Person.class).getReadMethod().invoke(p);
					if (ob!=null){
						if (ob instanceof String)
							val = (String) ob;
						else if (ob instanceof LocalDate)
							val = ((LocalDate)ob).toString();
					}
					
					row.createCell(j).setCellValue(val);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| IntrospectionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
//			row = sheet.createRow(i++);
//			row.createCell(0).setCellValue(p.getFirstName());
//			row.createCell(1).setCellValue(p.getLastName());
//			row.createCell(2).setCellValue(p.getEmailAddress());
//			row.createCell(3).setCellValue(p.getPhoneNumber());
//			row.createCell(4).setCellValue(p.getStudy());
//			row.createCell(5).setCellValue(p.getGraduationDate().toString());
//			row.createCell(6).setCellValue(p.getInterestedIn());
//			row.createCell(7).setCellValue(p.getRegion());
//			row.createCell(8).setCellValue(p.getPrefStartDate().toString());
//			row.createCell(9).setCellValue(p.getComments());
		}
		persistWorkbook(workbook);		
	}
	
	private void persistWorkbook (HSSFWorkbook workbook) {
		try (FileOutputStream out = new FileOutputStream(targetFile)) {
			workbook.write(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	

}
