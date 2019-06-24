package com.hr.hrsystem.service.impl;

import com.hr.hrsystem.model.Employee;
import com.hr.hrsystem.service.EmployeeService;
import com.hr.hrsystem.service.ExcelService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Service
@Slf4j
public class ExcelServiceImpl implements ExcelService {
    @Autowired
    private EmployeeService employeeService;

    @Override
    public boolean createExcel() throws IOException {
        List<Employee> employees = employeeService.findAll();
        XSSFWorkbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Employees");
        sheet.setColumnWidth(0, 6000);
        sheet.setColumnWidth(1, 6000);
        sheet.setColumnWidth(2, 6000);
        sheet.setColumnWidth(3, 6000);
        sheet.setColumnWidth(4, 6000);
        sheet.setColumnWidth(5, 10000);

        Row header = sheet.createRow(0);

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFFont font = workbook.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 16);
        font.setBold(true);
        headerStyle.setFont(font);

        Cell headerCell = header.createCell(0);
        headerCell.setCellValue("First Name");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(1);
        headerCell.setCellValue("Middle Name");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(2);
        headerCell.setCellValue("Last Name");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(3);
        headerCell.setCellValue("Salary");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(4);
        headerCell.setCellValue("Основание за превод");
        headerCell.setCellStyle(headerStyle);

        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);

        Row row;
        int rowIndex = 2;

        Cell cell;

        for (Employee e : employees) {
            row = sheet.createRow(rowIndex);

            cell = row.createCell(0);
            cell.setCellValue(e.getPerson().getFirstName());
            cell.setCellStyle(style);

            cell = row.createCell(1);
            cell.setCellValue(e.getPerson().getMiddleName());
            cell.setCellStyle(style);

            cell = row.createCell(2);
            cell.setCellValue(e.getPerson().getLastName());
            cell.setCellStyle(style);

            cell = row.createCell(3);
            cell.setCellValue(e.getSecurityData().getSalary());
            cell.setCellStyle(style);

            cell = row.createCell(4);
            cell.setCellValue("Заплата за " + LocalDate.now().getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault()) + " " + LocalDate.now().getYear());
            cell.setCellStyle(style);

//            cell = row.createCell(4);
//            cell.setCellValue(e.getSecurityData().getIBAN());
//            cell.setCellStyle(style);

            rowIndex++;
        }

        File currDir = new File("upload-dir/excel");
        String path = currDir.getAbsolutePath();
        String fileLocation = path.substring(0, path.length() - 1) + LocalDate.now().toString() + "_salaries.xlsx";

        FileOutputStream outputStream = new FileOutputStream(fileLocation);
        workbook.write(outputStream);
        workbook.close();

        return true;
    }
}
