import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.junit.jupiter.api.Test;

class WorkbookClassTest {
	
	@Test
	void testReadWorkbook() throws EncryptedDocumentException, IOException {
		WorkbookClass n1 = new WorkbookClass("test.xlsx");
		assertEquals(2.0,n1.myWorkbook.getSheet("Sheet1").getRow(1).getCell(3).getNumericCellValue());
	}

	@Test
	void testCountyCreator() throws EncryptedDocumentException, IOException {
		WorkbookClass n1 = new WorkbookClass("test.xlsx");
		Map<String, County> counties = n1.countyCreator();
		String statskeys = counties.get("101010").stats.keySet().toString();
		assertEquals("[Data2, LastData, Data1]", statskeys);
	}
	
	@Test
	void testCountyCreator2() throws EncryptedDocumentException, IOException {
		WorkbookClass n1 = new WorkbookClass("test.xlsx");
		Map<String, County> counties = n1.countyCreator();
		String statskeys = counties.get("123456").stats.keySet().toString();
		assertEquals("[Data2, LastData, Data1]", statskeys);
	}

	@Test
	void testStateCreator() throws EncryptedDocumentException, IOException {
		WorkbookClass n1 = new WorkbookClass("test.xlsx");
		Map<String, County> counties = n1.countyCreator();
		Map<String, State> states = n1.stateCreator(counties);
		assertEquals(1, states.get("NV").stats.get("Data2").getN());
	}

	@Test
	void testListStates() throws EncryptedDocumentException, IOException {
		WorkbookClass n1 = new WorkbookClass("test.xlsx");
		Map<String, County> counties = n1.countyCreator();
		ArrayList<String> listOfStates = n1.listStates(counties);
		//ArrayList<String> expectedStates = new ArrayList<String> {"NV", "CA"};
		assertEquals("[NV, CA]", listOfStates.toString());
	}

	@Test
	void testIsDataSheetFalse() throws EncryptedDocumentException, IOException {
		WorkbookClass n1 = new WorkbookClass("test.xlsx");
		Boolean expected = false;
		assertEquals(expected, n1.isDataSheet(n1.myWorkbook.getSheet("NotData")));
	}
	
	@Test
	void testIsDataSheetTrue() throws EncryptedDocumentException, IOException {
		WorkbookClass n1 = new WorkbookClass("test.xlsx");
		Boolean expected = true;
		assertEquals(expected, n1.isDataSheet(n1.myWorkbook.getSheet("Sheet1")));
	}

	@Test
	void testListDataSheets() throws EncryptedDocumentException, IOException {
		WorkbookClass n1 = new WorkbookClass("test.xlsx");
		String outList = n1.listDataSheets().get(1).getSheetName();
		assertEquals("Sheet2", outList);
	}
}