package graphqe;

import cypher.CypherSolver;
import cypher.Pair;
import cypher.translate.CypherPair;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sqlsolver.logic.SqlSolver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Runner {
    int startRow = 1;
    public void run() {
        String path = "./dataset.xlsx";
        String sheet = "dataset";
        List<Pair<String, String>> queryPairs = readQueryPairFromXLSX(path, sheet);
        System.out.println("Start verifying...");
        for (Pair<String, String> queryPair : queryPairs) {
            try {
                System.out.println("q1:" + queryPair.getLeft());
                System.out.println("q2:" + queryPair.getRight());
                CypherPair pair = CypherPair.read(queryPair.getLeft(), queryPair.getRight());
                CypherSolver solver = new CypherSolver(pair);
                int result = solver.solveByAlgebra();
                writeVerification(path, sheet, result == SqlSolver.EQ);
            } catch (Exception | Error e) {
                e.printStackTrace();
                System.err.println("FAILED");
                writeVerification(path, sheet, false);
            }
            System.out.println("******************************************************");
        }
    }
    private List<Pair<String, String>> readQueryPairFromXLSX(String fileName, String sheetName) {
        List<Pair<String, String>> pairedData = new ArrayList<>();
        try (FileInputStream file = new FileInputStream(fileName);
             XSSFWorkbook workbook = new XSSFWorkbook(file)) {
            XSSFSheet sheet = workbook.getSheet(sheetName);
            if (sheet != null) {
                int lastRowNum = sheet.getLastRowNum();
                for (int i = 1; i <= lastRowNum; i++) {
                    Row row = sheet.getRow(i);
                    if (row != null) {
                        Cell cellA = row.getCell(0);
                        Cell cellB = row.getCell(1);

                        String valueA = (cellA != null) ? cellA.toString() : "";
                        String valueB = (cellB != null) ? cellB.toString() : "";
                        if (!valueB.equals("") && !valueA.equals("")) {
                            pairedData.add(new Pair<>(valueA, valueB));
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.printf("Read %d query pairs".formatted(pairedData.size()));
        return pairedData;
    }

    private void writeVerification(String fileName, String sheetName, boolean passed) {
        try (FileInputStream file = new FileInputStream(new File(fileName));
             XSSFWorkbook workbook = new XSSFWorkbook(file)) {
            XSSFSheet sheet = workbook.getSheet(sheetName);

            if (sheet != null) {
                Row row = sheet.getRow(startRow++);
                Cell cell = row.createCell(3);
                if (passed) {
                    cell.setCellValue("PASS");
                } else {
                    cell.setCellValue("FAILED");
                }
            }

            try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
                workbook.write(outputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
