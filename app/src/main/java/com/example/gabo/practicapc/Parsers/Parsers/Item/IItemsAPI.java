package com.example.gabo.practicapc.Parsers.Parsers.Item;

import com.example.gabo.practicapc.Entities.Item;
import com.example.gabo.practicapc.Entities.ItemCategory;

import java.util.ArrayList;

public interface IItemsAPI {
    public ArrayList<ItemCategory> GetItems();
    public Boolean PostItems(Item item);
}
