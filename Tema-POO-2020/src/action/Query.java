package action;


import java.util.List;

public class Query {


    // class used in parsing the query's parameters

    private int id;
    private String objectType;
    private String sortCriteria;
    private String criteria;
    private List<List<String>> filter;
    private int number;



    public Query(final int id, final int number,
                 final String objectType, final String sortCriteria,
                 final String criteria, final List<List<String>> filter) {
        this.id = id;
        this.number = number;
        this.objectType = objectType;
        this.sortCriteria = sortCriteria;
        this.criteria = criteria;
        this.filter = filter;

    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getObjectType() {
        return objectType;
    }

    /**
     *
     * @param objectType
     */
    public void setObjectType(final String objectType) {
        this.objectType = objectType;
    }

    /**
     *
     * @return
     */
    public String getSortCriteria() {
        return sortCriteria;
    }

    /**
     *
     * @param sortCriteria
     */
    public void setSortCriteria(final String sortCriteria) {
        this.sortCriteria = sortCriteria;
    }

    /**
     *
     * @return
     */
    public String getCriteria() {
        return criteria;
    }

    /**
     *
     * @param criteria
     */
    public void setCriteria(final String criteria) {
        this.criteria = criteria;
    }

    /**
     *
     * @return
     */
    public List<List<String>> getFilter() {
        return filter;
    }

    /**
     *
     * @param filter
     */
    public void setFilter(final List<List<String>> filter) {
        this.filter = filter;
    }

    /**
     *
     * @return
     */
    public int getNumber() {
        return number;
    }

    /**
     * @param number
     */
    public void setNumber(final int number) {
        this.number = number;
    }

}
