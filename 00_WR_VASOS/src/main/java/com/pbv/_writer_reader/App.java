/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.pbv._writer_reader;

import com.pbv._writer_reader.model.Vas;
import com.pbv._writer_reader.model.Dau;
import com.pbv._writer_reader.persistencia.GestorDades;
import com.pbv._writer_reader.utils.AskData;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author pbv
 */
public class App {
    
    private static ArrayList<Vas> llistaVasos;
    private static AskData ask;
    private static GestorDades gestorDades;
    private static GestorDades gestorDadesAlternatiu;

    public static void main(String[] args) throws IOException{
        ask = new AskData();
        gestorDades = new GestorDades("dades", "general");
        gestorDadesAlternatiu = new GestorDades("dadesAlternatiu", "generalAlternatiu");
        llistaVasos = dadesInicials();
        String option ;
        do {
            printOptions();
            option = ask.askString("Introdueix opció :  ");
            if (option.equalsIgnoreCase("V")){
                llistarStock(llistaVasos);
            } else if (option.equalsIgnoreCase("L")){
                llistaVasos = gestorDades.llegirTotsElsVasos();
            } else if (option.equalsIgnoreCase("D")){
                gestorDades.writeAll(llistaVasos);
            } else if (option.equalsIgnoreCase("S")){
                // No faig res, ja sortiré
            } else {
                System.out.println("Opció incorrecta");
            }
        } while (!option.equalsIgnoreCase("S"));
        
    }
    
    public static void printOptions(){
        System.out.println(" ");
        System.out.println("---- MENU PRINCIPAL ----");
        System.out.println("[V] Veure stock");
        System.out.println("[L] Llegir dades");
        System.out.println("[D] Desar dades");
        System.out.println("[S] Sortir");
    }
    
    public static ArrayList<Vas> dadesInicials(){
        Vas c1 = new Vas("VasVermell", 3);
        Dau pTemporal = new Dau("DauVermell");
        c1.ficarProducte(pTemporal);
        pTemporal = new Dau("DauRosa");
        c1.ficarProducte(pTemporal);
        Vas c2 = new Vas("VasGroc", 5);
        pTemporal = new Dau("DauTaronja");
        c2.ficarProducte(pTemporal);
        pTemporal = new Dau("DauBlanc");
        c2.ficarProducte(pTemporal);
        Vas c3 = new Vas("VasVerd", 10);
        pTemporal = new Dau("DauVerd");
        c3.ficarProducte(pTemporal);
        pTemporal = new Dau("DauBlau");
        c3.ficarProducte(pTemporal);
        pTemporal = new Dau("DauLila");
        c3.ficarProducte(pTemporal);
        ArrayList<Vas> alc = new ArrayList<>();
        alc.add(c1);
        alc.add(c2);
        alc.add(c3);
        return alc;
    }
    
    public static void llistarStock(ArrayList<Vas> caixes){
        if (caixes == null){
                System.out.println("..Ni hi ha caixes..");
            return;
        }
        System.out.println("..Stock Actual..");
        for (Vas c : caixes){
            System.out.println("Caixa " + c.getID());
            for (Dau p : c.getContingut()){
                if (p == null) {
                    System.out.println("Caixa buida");
                } else {
                    System.out.println("- " + p.getNom());
                }
            }
        }
    }
}
