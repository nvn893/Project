package gnrl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.*;

public class genXLS2 {
	
	public HashMap<String, String> xlsreader (String input) throws IOException {
		String path = System.getProperty("user.dir")+"//resources//tabledata.xlsx";
		FileInputStream file = new FileInputStream(new File(path));
		Workbook work = WorkbookFactory.create(file);
		Sheet sht = work.getSheetAt(0);
		HashMap <String, String> hash = new HashMap<>();
		Row hrow = sht.getRow(0);
			
		int val = -5;
		for (int i = 1; i<=sht.getLastRowNum();i++)
		{
			Row r1 = sht.getRow(i);
			if (r1 != null)
			{
				Cell c1 = r1.getCell(0);
				if (c1!= null && c1.toString().equalsIgnoreCase(input))
				{
					val = i;
					break;
				}
			}
		}
		
		if (val!= -5)
		{
			Row r2 = sht.getRow(val);
			for (int j = 0; j<hrow.getLastCellNum(); j++)
			{
				Cell hc = hrow.getCell(j);
				Cell c2 = r2.getCell(j);
				if (hc != null && c2 != null)
				{
					String cellValue;
                    switch (c2.getCellType()) {
                        case NUMERIC:
                            cellValue = String.valueOf((long) c2.getNumericCellValue());
                            break;
                        case STRING:
                            cellValue = c2.getStringCellValue();
                            break;
                        default:
                            cellValue = c2.toString();
                            break;
				} hash.put(hc.toString(), cellValue);
			}
			}
		} else {
			System.out.println("Entered test case not found");
		}
		
		work.close();
		file.close();
	
		return hash;
}
}
