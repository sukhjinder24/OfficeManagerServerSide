/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helloWorld.models;

import java.util.List;

/**
 *
 * @author Sukhjinder
 */
public class PageResponse<Model> extends BaseResponse{
    
    public List<Model> items;

    public PageResponse() {
    }

    public void setItems(List<Model> items) {
        this.items = items;
    }

    public List<Model> getItems() {
        return items;
    }
}
