package action;

import actor.Actor;
import comparators.AwardComparator;
import comparators.NameComparator;
import comparators.RatingComparator;
import entertainment.Movie;
import entertainment.Show;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public  class Query {

    // id ul pentru actiunea
    private int id;
    private String objectType;
    private String sortCriteria;//asc sau desc
    private String criteria;
    // sunt doar 4 filtere, vad eu ce se intampla
    private List<List<String>> filter;// keywords pentru filtrare
    private int number;// daca e dat un numar sau ceva
    //TODO: add message to be written in out?? idk


    public Query(int id, int number, String objectType, String sortCriteria, String criteria, List<List<String>> filter) {
        this.id = id;
        this.number = number;
        this.objectType = objectType;
        this.sortCriteria = sortCriteria;
        this.criteria = criteria;
        this.filter = filter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getSortCriteria() {
        return sortCriteria;
    }

    public void setSortCriteria(String sortCriteria) {
        this.sortCriteria = sortCriteria;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public List<List<String>> getFilter() {
        return filter;
    }

    public void setFilter(List<List<String>> filter) {
        this.filter = filter;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    // add specific methods


}
