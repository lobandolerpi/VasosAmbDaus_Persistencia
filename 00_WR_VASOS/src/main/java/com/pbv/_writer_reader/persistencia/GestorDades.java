/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pbv._writer_reader.persistencia;

import com.pbv._writer_reader.model.Vas;
import com.pbv._writer_reader.model.Dau;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author pbv
 */
public class GestorDades {
    private File directoriDades;
    private File fitxerGeneral;
    private ArrayList<File> fitxersVasos;
    
    public GestorDades(String folder, String file) throws IOException {
        directoriDades = new File(folder);
        fitxerGeneral = new File(folder + File.separator + file);
        if (!directoriDades.exists()){
            directoriDades.mkdir();
        }
        
        if(!fitxerGeneral.exists()){
            fitxerGeneral.createNewFile();
        }
    }
    
    public void escriureNomesLlistaVasos( ArrayList<Vas> llistaVasINPUT) throws IOException{
        BufferedWriter bw = new BufferedWriter(new FileWriter(fitxerGeneral));
        String liniaTmp;
        for (Vas vas : llistaVasINPUT){
            liniaTmp = vas.getID() + ";" + vas.getMaxItems();
            bw.write(liniaTmp);
            bw.newLine();
        }
        bw.close();
    }
    
    public void escriureNomes1Vas (Vas vas) throws IOException{
        String path = directoriDades.getPath() +File.separator + vas.getID();   
        File fileVas = new File(path);
        if (!fileVas.exists()){
            fileVas.createNewFile();
        }
        BufferedWriter bwc = new BufferedWriter(new FileWriter(fileVas));
        String liniaTmp;
        for (Dau dau : vas.getContingut()){
            liniaTmp = dau.getNom();
            bwc.write(liniaTmp);
            bwc.newLine();
        }
        bwc.close();
    }
    
    public void writeAll(ArrayList<Vas> llistaVasINPUT) throws IOException{
        escriureNomesLlistaVasos(llistaVasINPUT);
        for (Vas vas : llistaVasINPUT){
            escriureNomes1Vas(vas);
        }
    }
    
    public ArrayList<Dau> llegir1FileVas(String path) throws FileNotFoundException, IOException{
        ArrayList<Dau> llistaDausOut = new ArrayList<>();
        File f = new File(path);
        Dau dauTmp;
        BufferedReader br = new BufferedReader(new FileReader(f));
        String lineRead = "kk";
        do { 
            lineRead = br.readLine();
            if (lineRead != null){
                dauTmp = new Dau(lineRead);
                llistaDausOut.add(dauTmp);
            }
        } while (lineRead != null);
        br.close();
        return llistaDausOut;
    }
    
    public ArrayList<Vas> llegirTotsElsVasos() throws FileNotFoundException, IOException {
        ArrayList<Vas> llistaVasosOut = new ArrayList<>();
        Vas vasTmp;
        String rutaVas;
        ArrayList<Dau> llistaDausTmp;
        BufferedReader br = new BufferedReader(new FileReader(fitxerGeneral));
        String lineRead = "kk";
        do { 
            lineRead = br.readLine();
            if (lineRead != null){
                String[] partsDeLaLinia = lineRead.split(";");
                vasTmp = new Vas(partsDeLaLinia[0], Integer.parseInt(partsDeLaLinia[1]));
                rutaVas = directoriDades + File.separator + vasTmp.getID();
                llistaDausTmp = llegir1FileVas(rutaVas);
                for (Dau dau : llistaDausTmp){
                    vasTmp.ficarProducte(dau);
                }
                llistaVasosOut.add(vasTmp);
            }
        } while (lineRead != null);
        br.close();
        return llistaVasosOut;
    }
}
