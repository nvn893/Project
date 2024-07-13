package gnrl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class genCSV  {
	public Map<String,String> csvreader (String input1, String input2) throws FileNotFoundException {
	
		HashMap <String, String> hash = new HashMap <String, String>();
		String path = System.getProperty("user.dir")+"//resources//userdata.csv";
		File fil = new File (path);
		Scanner scan = new Scanner (fil);
		scan.useDelimiter(",");
		List <String> a1= new ArrayList<String>();
		List <String> a2= new ArrayList<String>();
			
			while(scan.hasNextLine())
			{
				String str=scan.nextLine();
				String[] row=str.split(",");
				
				if(str.contains(input1))
				{
					for(int i=0;i<row.length;i++)
					{
						a1.add(row[i]);
					}	
				}
				
				if(str.contains(input2))
				{
					for(int i=0;i<row.length;i++)
					{
						a2.add(row[i]);
					}
				}
			}
			scan.close();
			
		for (int i=0; i<a1.size();i++)
		{
			hash.put(a1.get(i), a2.get(i));
		}
		
		return hash;
		
}
}