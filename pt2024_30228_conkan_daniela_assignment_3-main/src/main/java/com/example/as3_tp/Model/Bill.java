package com.example.as3_tp.Model;

/**
 * record used to make a bill from all orders
 * @param idComanda id-ul comenzii
 * @param cantita cantitatea comenzii
 * @param pret pretul comenzii
 * @param adresa adresa comenzii
 */
public record Bill(int idComanda, int cantita, float pret, String adresa) {


}
