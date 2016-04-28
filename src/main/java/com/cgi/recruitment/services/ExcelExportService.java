package com.cgi.recruitment.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;

import com.cgi.recruitment.domain.Person;
import com.cgi.recruitment.domain.RecruitmentEvent;
import com.cgi.recruitment.fx.domain.FxRecruitmentEvent;
import com.cgi.recruitment.util.converters.EventConverter;

@Component
public class ExcelExportService {
	
	private RecruitmentEvent event;
	private File targetFile;
	
	private String[] fields = {"Voornaam","Achternaam","E-mailadres","Telefoonnummer","Studie","Afstudeerdatum","Op zoek naar","Kantoor","Per wanneer","Opmerkingen"};
	
	public ExcelExportService () {
		
	}
	
	public void setRecruitmentEvent (FxRecruitmentEvent fxEvent) {
		this.event = EventConverter.convertToRecruitmentEvent(fxEvent);
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
		
		row = sheet.createRow(1);
		int i = 0;
		for (String field : fields) {
			row.createCell(i++).setCellValue(field);
		}
		
		i = 2;
		for (Person p : event.getPersonSet()) {
			row = sheet.createRow(i++);
			row.createCell(0).setCellValue(p.getFirstName());
			row.createCell(1).setCellValue(p.getLastName());
			row.createCell(2).setCellValue(p.getEmailAddress());
			row.createCell(3).setCellValue(p.getPhoneNumber());
			row.createCell(4).setCellValue(p.getStudy());
			row.createCell(5).setCellValue(p.getGraduationDate().toString());
			row.createCell(6).setCellValue(p.getLookingFor());
			row.createCell(7).setCellValue(p.getWorkingLocation());
			row.createCell(8).setCellValue(p.getFreePer().toString());
			row.createCell(9).setCellValue(p.getComments());
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
