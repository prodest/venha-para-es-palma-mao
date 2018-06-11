/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testprodest.cgd;

import java.util.List;

/**
 *
 * @author Helen França
 * @param <GenericType>
 */
public interface GenericDAO<GenericType> {

    // public List<GenericType> getAll();

    /**
     *
     * @return
     */
    public int getNextId();

    public void insert(GenericType obj);

}
