/*
 * Copyright (C) 2018 mgarcia
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package CONTROLE.UTIL;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author mgarcia
 */
public class Data {

    /*
    *   Esta classe fornece utilidades para tratamento de Datas dos tipos
    *   java.sql.Date e java.util.Date, fornecendo métodos fáceis para
    *   transformação de datas em strings em formatos pré determinados
    *   ou o inverso, transformar strings formatadas em data.
    *   Autor: Mateus Garcia
    *   github.com/NaturesProphet
    */
    
    
    //devolve uma string formatada no padrão brasileiro
    public static String getDataAsStringBR(Date d) {
        SimpleDateFormat dmy = new SimpleDateFormat("dd/MM/yyyy");
        return dmy.format(d);
    }

    //devolve uma string formatada no padrão americano
    public static String getDataAsStringUS(Date d) {
        SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");
        return ymd.format(d);
    }

    //devolve um java.sql.Date (para uso em PreparedStatements)
    public static java.sql.Date getDataAsSQL(Date d) {
        return new java.sql.Date(d.getTime());
    }

    //devolve um java.util.Date
    public static Date getDataAsUtil(java.sql.Date d) {
        return new Date(d.getTime());
    }

    //devolve um java.util.Date pela string parão BR fornecida
    public static Date getDataFromStringDMY(String d) throws ParseException {
        SimpleDateFormat dmy = new SimpleDateFormat("dd/MM/yyyy");
        return dmy.parse(d);
    }
    
    //devolve um java.util.Date pela string parão US fornecida
    public static Date getDataFromStringYMD(String d) throws ParseException {
        SimpleDateFormat dmy = new SimpleDateFormat("yyyy-MM-dd");
        return dmy.parse(d);
    }

    //retorna o horario simples do momento em que foi invocado o método
    public static String getHora() {
        //GregorianCalendar now = new GregorianCalendar();
        Date d = new Date();
        SimpleDateFormat df = new SimpleDateFormat("H:mm:ss");
        return df.format(d);

    }

    //retorna data e hora no formato BR
    public static String getDataHoraBR() {
        Locale locale = new Locale("pt", "BR");
        Date agora = new Date();
        SimpleDateFormat formatador = new SimpleDateFormat("dd' de 'MMMMM' de 'yyyy' - 'HH':'mm'h'", locale);
        return formatador.format(agora.getTime());
    }

}
