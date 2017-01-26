package sejmometr;

public class Deputy {
	
	private String id;
	private String name;
	private String wydatkiNaNaprawy;
	
	/*private String dataset;
	private String url;
	private String mp_url;
	private String schema_url;
	private String global_id;
	private String slug;
	private Object score;
	*/
	private DeputyInfo data;
	/*private Layers layers;
	private Aggs Aggs;
	 */
	public void addId(String readedId)
	{
		this.id = readedId;
	}
	public void addName(String readedName)
	{
		this.name = readedName;
	}
	public Deputy()
	{
		data = new DeputyInfo();
	}
	
	DeputyInfo getData()
	{
		return data;
	}
	
}
