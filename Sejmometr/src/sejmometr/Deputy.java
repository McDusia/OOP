

public class Deputy {

    private String id;
    private String name;
    private String wydatkiNaNaprawy;
    int kadencja;

    /*private String dataset;
    private String url;
    private String mp_url;
    private String schema_url;
    private String global_id;
    private String slug;
    private Object score;
    */
   // private DeputyInfo data;
    /*private Layers layers;
    private Aggs Aggs;
     */

    public Deputy()
    {
        id= "";
        name="";
    }

    public void addId(String readedId)
    {
        this.id = readedId;
    }
    public void addName(String readedName)
    {
        this.name = readedName;
    }
    public String returnName()
    {
        return this.name;
    }
    public String returnID()
    {
        return this.id;
    }
    //DeputyInfo getData(
    // {)
//        return data;
//
}
