/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pratikum11;

import java.io.File;
import java.io.FileWriter;
import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 *
 * @author Surinanda
 */
public class ExportToExcel {
    public ExportToExcel(JTable table, File file) {
    try{
    TableModel tableModel = table.getModel();
    FileWriter fOut = new FileWriter(file);
    for(int i = 0; i < tableModel.getColumnCount(); i++){
    fOut.write(tableModel.getColumnName(i)+"\t");
    }
    fOut.write("\n");
    for(int i = 0; i < tableModel.getRowCount(); i++){
    for(int j = 0; j < tableModel.getColumnCount(); j++){
    fOut.write(tableModel.getValueAt(i,j).toString()+"\t");
    }
    fOut.write("\n");
    }
    fOut.close();
    } catch (Exception e){
    e.printStackTrace();
    }
}
}