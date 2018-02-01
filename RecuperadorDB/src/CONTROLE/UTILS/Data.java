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
package CONTROLE.UTILS;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author mgarcia
 */
public class Data {
    
    public static String getDataAsStringBR(Date d) {
        SimpleDateFormat dmy = new SimpleDateFormat("dd/MM/yyyy");
        return dmy.format(d);
    }
    
    public static String getDataAsStringUS(Date d) {
        SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");
        return ymd.format(d);
    }
    
    public static java.sql.Date getDataAsSQL (Date d) {
        return new java.sql.Date(d.getTime());
    }
    
    public static Date getDataAsUtil(java.sql.Date d) {
        return new Date(d.getTime());
    }
    
}
